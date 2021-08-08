package de.simpletactics.database;

import java.sql.Connection;

public class DatabaseInterface {

    private static Connection con;

    public static Connection getCon(){
        return con;
    }

    public static void setCon(Connection connection){
        con = connection;
    }

}
