package de.simpletactics;

import de.simpletactics.database.DataBaseCon;
import de.simpletactics.formatParser.JsonParser;
import de.simpletactics.game.Game;
import de.simpletactics.game.JsonGame;
import de.simpletactics.questions.QuestionLoader;
import de.simpletactics.questions.QuestionStack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Runner {

    public static void main(String[] args) throws SQLException {
        //Testing
        JsonGame jsonGame = new JsonGame();
        JsonParser jsonParser = new JsonParser();
        Game game = new Game(new DataBaseCon(DriverManager.getConnection("")), jsonGame);
        game.start();
        game.correct();
        jsonParser.parse(jsonGame);
        game.correct();
        jsonParser.parse(jsonGame);
        game.bank();
        jsonParser.parse(jsonGame);
        game.reset();
        jsonParser.parse(jsonGame);


        /*
        Scanner scanner = new Scanner(System.in);
        while (true){
            String a = scanner.nextLine();
            System.out.println(game.nextQuestion().toString());
            System.out.println("Größe: " + game.getQs().stackSize());
        }
         */
    }

}
