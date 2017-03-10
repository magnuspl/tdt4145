import database.Connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by henri on 08.03.2017.
 */
public class QueryTest extends Connect {

    protected Connection conn() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://mysql.stud.ntnu.no:3306/henrisor_69", "henrisor_tdt4145", "123");
    }

    private ResultSet NyOvelse() throws SQLException {
        Connection conn = conn();
        ResultSet rs1 = conn.createStatement().executeQuery("SELECT * FROM  Ovelse");
        while (rs1.next()) {
            System.out.println(rs1.getString(1)); //gets the first column's rows.
        }
        return rs1;
    }

    public static void main(String[] args) throws SQLException {
        QueryTest ov = new QueryTest();
        System.out.println(ov.NyOvelse());
    }

}

