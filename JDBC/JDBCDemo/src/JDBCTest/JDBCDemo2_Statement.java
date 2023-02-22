package JDBCTest;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.ArrayList;

class Account{
    private int id;
    private String name;
    private double money;
    public Account(int id, String name, double money) {
        this.id = id;
        this.name = name;
        this.money = money;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", money=" + money +
                '}';
    }
}
public class JDBCDemo2_Statement {
    private Connection conn;
    private Statement st;
    @BeforeEach
    public void connectToMySQL() throws SQLException {
        String url = "jdbc:mysql://127.0.0.1:3306/db1";
        String username = "root";
        String password = "123456";
        conn = DriverManager.getConnection(url,username,password);
        st = conn.createStatement();
        System.out.println("MySQL连接成功");
    }
    @AfterEach
    public void cleanUp() throws SQLException {
        st.close();
        conn.close();
        System.out.println("清理完成");
    }
    /**
     * Statement.executeUpdate()执行DML语句
     */
    @Test
    public void testDML() throws Exception{
        String sql1 = "update account set money = 2000 where id = 1";
        String sql2 = "Insert into account(id,name,money) values(3,'王五',4000)";
        System.out.println(st.executeUpdate(sql1));
        System.out.println(st.executeUpdate(sql2));
    }

    /**
     * Statement.executeUpdate()执行DML语句
     */
    @Test
    public void testDDL() throws Exception{
        String sql1 = "CREATE table test(id int,name char(10))";
        System.out.println(st.executeUpdate(sql1));
    }

    /**
     * Statement.executeQuery()执行DQL语句
     */
    @Test
    public void testDQL() throws Exception{
        String sql = "Select * from account";
        ResultSet result = st.executeQuery(sql);
        ArrayList<Account> accounts = new ArrayList<>();
        while(result.next()){
            accounts.add(new Account(result.getInt(1),result.getString(2),result.getDouble(3)));
        }
        for(Account a:accounts){
            System.out.println(a);
        }
    }

}
