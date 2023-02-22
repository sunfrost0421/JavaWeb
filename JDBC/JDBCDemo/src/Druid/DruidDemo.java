package Druid;





import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;

public class DruidDemo {
    public static void main(String[] args) throws Exception {
        //加载配置文件
        //System.out.println(System.getProperty("user.dir"));//D:\workplace\java_web\JDBC
        Properties prop = new Properties();
        prop.load(new FileInputStream("JDBCDemo/src/druid.properties"));
        //prop.load(new FileInputStream("JDBCDemo\\src\\druid.properties"));
        //获取连接池对象
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);
        //获取连接
        Connection c = dataSource.getConnection();
        System.out.println(c);
    }
}
