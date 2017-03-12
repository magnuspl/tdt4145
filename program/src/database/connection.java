package database; /**
 * Created by henri on 06.03.2017.
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class connection {


    public static void main(String a[]) {

        try {
        Connection conn = DriverManager.getConnection("jdbc:mysql://mysql.stud.ntnu.no:3306/henrisor_69", "henrisor_tdt4145", "123");

        String query = "SELECT * FROM okt";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        List<String> myList = new ArrayList<String>();
        int col = rs.getMetaData().getColumnCount();
        while (rs.next()) {
            for (int i = 2; i <= col; i++) {

                myList.add(rs.getString(i));
            }
        }
        System.out.println(myList);
        conn.close();
    } catch (Exception e) {
        System.err.println(e);
    }
}
}