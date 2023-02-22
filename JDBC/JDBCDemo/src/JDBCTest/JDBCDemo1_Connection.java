package JDBCTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCDemo1_Connection {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://127.0.0.1:3306/db1";
        String username = "root";
        String password = "123456";
        Connection conn = DriverManager.getConnection(url,username,password);
        String sql1 = "update account set money = 2000 where id = 1";
        String sql2 = "update account set money = 2000 where id = 2";
        Statement statement = conn.createStatement();

        try {
            //开启事务
            conn.setAutoCommit(false);
            int count1 = statement.executeUpdate(sql1);
            System.out.println(count1);
            int i = 3/0;
            int count2 = statement.executeUpdate(sql2);
            System.out.println(count2);
        } catch (Exception e) {
            conn.rollback();//回滚事务
            e.printStackTrace();
        }
        conn.commit();//提交事务
        statement.close();
        conn.close();
    }
}
