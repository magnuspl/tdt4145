package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by torresrl on 07/03/2017.
 *
 * Bruksanvisning:
 *
 * 1) vi prøver kun å ha de mest basike funksjonene i denne klassen
 * 2) når du skal lage metoder for å legge til eks en trenings økt
 *    lag en ny klasse og bruk denne som super class.
 *
 * med disse to reglene sleper vi 50 metoder som vi leite igjennom
 * og kan heller korte det ned med å se på klasse navnet.
 */
public class DBConnect {


    //login info
    //static final String URL = "jdbc:mysql://mysql.stud.ntnu.no:3306/henrisor_69";
    //static final String user = "henrisor_tdt4145";
    //static final  String pass = "123";
    String URL = "jdbc:mysql://localhost:3306/henrisor_69";
    String user = "root";
    String pass = "123";


    Connection conn = null;
    Statement stmt = null;



    //når du oppreter klassen kobler den seg automatisk opp.
    public Connection Connect() {
        try {
            System.out.println("Connecting to database...");
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(URL, user, pass);
            stmt = conn.createStatement();
            return conn;

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (conn != null) {
            System.out.println("Connected to database");
        }
        return null;
    }


    // stenger forbinelsen etter bruk
    public void close(){
        try {
            if (stmt != null) {
                stmt.close();
                System.out.println("statment closed");
            }

            if (conn != null){
                conn.close();
                System.out.println("connection closed");
            }
        } catch (SQLException se){
            se.printStackTrace();
        }
    }




}
