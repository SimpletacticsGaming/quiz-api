package de.simpletactics.game;

import de.simpletactics.database.DataBaseCon;
import de.simpletactics.questions.Question;
import de.simpletactics.questions.QuestionLoader;
import de.simpletactics.questions.QuestionStack;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Game {

    private int gameId;
    private DataBaseCon con;
    private QuestionStack qs;
    private QuestionLoader ql;
    private JsonGame jsonGame;

    //Variablen für DDIR
    private Question currQuestion;
    private int points;
    private int bank;
    private int turn;

    public Game(DataBaseCon con, JsonGame jsonGame) throws SQLException {
        this.con = con;
        this.ql = new QuestionLoader(this.con);
        this.qs = new QuestionStack();
        this.jsonGame = jsonGame;
        openGame();
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getBank() {
        return bank;
    }

    public void setBank(int bank) {
        this.bank = bank;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    private void openGame() throws SQLException {
        Statement state = this.con.getCon().createStatement();
        ResultSet rs = state.executeQuery("SELECT id + 1 AS id FROM quiz_game ORDER BY id desc limit 1");
        rs.next();
        this.gameId = rs.getInt("id");
        state.executeUpdate("INSERT INTO quiz_game (id,type,date) VALUES ('"+this.gameId+"', '1', (SELECT NOW()))");
    }

    private int getGameId() {
        return gameId;
    }

    public void start() throws SQLException {
        ql.load(qs);
        this.points = 0;
        this.bank = 0;
        this.turn = 0;
    }

    public Question nextQuestion() throws SQLException {
        if (qs.isEmpty()){
            ql.load(qs);
        }
        this.currQuestion = qs.pop();
        Statement state = this.con.getCon().createStatement();
        state.executeUpdate("DELETE FROM quiz_connection WHERE frage_id = "+this.currQuestion.getId()+"");
        state.executeUpdate("INSERT INTO quiz_connection (game_id, frage_id) VALUES ('"+this.gameId+"', '"+this.currQuestion.getId()+"')");
        return this.currQuestion;
    }

    public void bank(){
        this.bank += this.points;
        this.points = 0;
        this.turn = 0;
        parseCurrGameToJsonGame();
    }

    public void correct() throws SQLException {
        int[] arrPoints = {1, 2, 4, 6, 9, 12, 15, 18, 21, 25};
        this.points = arrPoints[turn];
        if (arrPoints.length > this.turn){
            this.turn++;
        }
        nextQuestion();
        parseCurrGameToJsonGame();
    }

    public void reset() throws SQLException {
        this.points = 0;
        this.turn = 0;
        nextQuestion();
        parseCurrGameToJsonGame();
    }

    public void parseCurrGameToJsonGame(){
        this.jsonGame.setQuestion(this.currQuestion.getQuestion());
        this.jsonGame.setAnswer(this.currQuestion.getAnswer());
        this.jsonGame.setBank(this.bank);
        this.jsonGame.setPoints(this.points);
        this.jsonGame.setTurn(this.turn);
    }

}
