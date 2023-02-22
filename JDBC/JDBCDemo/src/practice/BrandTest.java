package practice;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class BrandTest implements AutoCloseable{
    private Connection conn;
    private Statement st;
    private PreparedStatement pst;

    public void connectToMySQL() throws Exception {
//        String url = "jdbc:mysql://127.0.0.1:3306/db1";
//        String username = "root";
//        String password = "123456";
//        conn = DriverManager.getConnection(url,username,password);
        Properties prop = new Properties();
        prop.load(new FileInputStream("JDBCDemo/src/druid.properties"));
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);
        conn = dataSource.getConnection();

        st = conn.createStatement();
        System.out.println("MySQL连接成功");
    }

    public void cleanUp() {

        try {
            st.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("清理失败");
        }
        System.out.println("清理完成");
    }

    @Override
    public void close() throws Exception {
        st.close();
        conn.close();
        if(pst!=null){
            pst.close();
        }
        System.out.println("清理完成");
    }

    public ArrayList<Brand> testSelectAll() throws SQLException {
        String sql = "select * from tb_brand";
        pst = conn.prepareStatement(sql);
        ResultSet result = pst.executeQuery(sql);
        ArrayList<Brand> brandList = new ArrayList<>();
        while(result.next()){
            brandList.add(new Brand(result.getInt(1),result.getString(2),
                    result.getString(3),result.getInt(4),
                    result.getString(5), result.getInt(6)));
        }
        return brandList;
    }

    public boolean testAdd(Brand b) throws Exception{
        String sql = "insert into tb_brand(brand_name,company_name,ordered,description,status) values(?,?,?,?,?)";
        pst = conn.prepareStatement(sql);
        pst.setString(1,b.getBrandName());
        pst.setString(2,b.getCompanyName());
        pst.setInt(3,b.getOrdered());
        pst.setString(4,b.getDescription());
        pst.setInt(5,b.getStatus());
        int flag = pst.executeUpdate();
        if(flag == 1){
            System.out.println("添加成功");
            return true;
        }else{
            System.out.println("添加失败");
            return false;
        }
    }

    public boolean testUpdate(Brand b) throws Exception{
        String sql = "update tb_brand set " +
                "brand_name = ?," +
                "company_name = ?," +
                "ordered = ?," +
                "description = ?," +
                "status = ? " +
                "where id = ?";
        pst = conn.prepareStatement(sql);
        pst.setString(1,b.getBrandName());
        pst.setString(2,b.getCompanyName());
        pst.setInt(3,b.getOrdered());
        pst.setString(4,b.getDescription());
        pst.setInt(5,b.getStatus());
        pst.setInt(6,b.getId());
        int flag = pst.executeUpdate();
        if(flag == 1){
            System.out.println("修改成功");
            return true;
        }else{
            System.out.println("修改失败");
            return false;
        }
    }

    public boolean testDelete(int id) throws Exception{
        String sql = "delete from tb_brand where id = ?";
        pst = conn.prepareStatement(sql);
        pst.setInt(1,id);
        int flag = pst.executeUpdate();
        if(flag == 1){
            System.out.println("修改成功");
            return true;
        }else{
            System.out.println("修改失败");
            return false;
        }
    }

}
class Test1{
    public static void main(String[] args) {
        try(
            BrandTest bt = new BrandTest();
        ){
            bt.connectToMySQL();
            ArrayList<Brand> brands = bt.testSelectAll();
            System.out.println(brands);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
class Test2{
    public static void main(String[] args) {
        try(
                BrandTest bt = new BrandTest();
        ){
            bt.connectToMySQL();
            Brand brand = new Brand(1,"苹果","小张水果店",1,"很便宜",0);
            bt.testAdd(brand);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
class Test3{
    public static void main(String[] args) {
        try(
                BrandTest bt = new BrandTest();
        ){
            bt.connectToMySQL();
            Brand brand = new Brand(1,"苹果","小张水果店",1,"很便宜",0);
            bt.testUpdate(brand);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
class Test4{
    public static void main(String[] args) {
        try(
                BrandTest bt = new BrandTest();
        ){
            bt.connectToMySQL();
            bt.testDelete(4);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
