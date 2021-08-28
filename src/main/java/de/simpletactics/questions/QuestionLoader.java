package de.simpletactics.questions;

import de.simpletactics.database.DataBaseCon;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QuestionLoader {

    private final int DEFAULT_AMOUNT_OF_QUESTIONS = 3;
    private DataBaseCon con;

    public QuestionLoader(DataBaseCon con) {
        this.con = con;
    }

    public int size(ResultSet rs) throws SQLException {
        rs.last();
        int size = rs.getRow();
        rs.first();
        return size;
    }

    public void load(QuestionStack qs, int amountOfQuestions) throws SQLException {
        Statement state = con.getCon().createStatement();
        ResultSet rs = state.executeQuery("SELECT f.* FROM quiz_fragen AS f LEFT JOIN quiz_connection AS c ON f.id = c.frage_id " +
                "LEFT JOIN quiz_game AS g ON c.game_id = g.id WHERE g.date is NULL OR DATEDIFF(NOW(),g.date) > 365 " +
                "ORDER BY RAND() LIMIT "+ amountOfQuestions);

        if ( size(rs) < amountOfQuestions ){
            rs = state.executeQuery("SELECT f.* FROM quiz_fragen AS f LEFT JOIN quiz_connection AS c ON f.id = c.frage_id " +
                    "LEFT JOIN quiz_game AS g ON c.game_id = g.id" +
                    "ORDER BY RAND() LIMIT " + 50);
        }


        try{
            //Create question object and add it to the stack
            do {
                int id = rs.getInt("id");
                String question = rs.getString("frage");
                String answer = rs.getString("antwort");
                //System.out.println("id: "+ id + ", Question: " + question + " Answer: " + answer);
                qs.add(new Question(id, question, answer));
            }while ( rs.next() );

            //Close Streams
            rs.close();
            state.close();
        }
        catch (QuestionException e){
            System.out.println("Fehler mit QuestionException");
        }
    }

    public void load(QuestionStack qs) throws SQLException {
        Statement state = con.getCon().createStatement();
        ResultSet rs = state.executeQuery("SELECT f.* FROM quiz_fragen AS f LEFT JOIN quiz_connection AS c ON f.id = c.frage_id " +
                "LEFT JOIN quiz_game AS g ON c.game_id = g.id WHERE g.date is NULL OR DATEDIFF(NOW(),g.date) > 365 " +
                "ORDER BY RAND() LIMIT "+ DEFAULT_AMOUNT_OF_QUESTIONS);

        if ( size(rs) < DEFAULT_AMOUNT_OF_QUESTIONS ){
            System.err.println("Nicht genügend Fragen in der Datenbank vorhanden!");
        }

        try{
            //Create question object and add it to the stack
            do {
                int id = rs.getInt("id");
                String question = rs.getString("frage");
                String answer = rs.getString("antwort");
                //System.out.println("id: "+ id + ", Question: " + question + " Answer: " + answer);
                qs.add(new Question(id, question, answer));
            }while ( rs.next() );

            //Close Streams
            rs.close();
            state.close();
        }
        catch (QuestionException e){
            System.out.println("Fehler mit QuestionException");
        }
    }
}
