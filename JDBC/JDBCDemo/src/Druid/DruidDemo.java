package Druid;





import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;

public class DruidDemo {
    public static void main(String[] args) throws Exception {
        //���������ļ�
        //System.out.println(System.getProperty("user.dir"));//D:\workplace\java_web\JDBC
        Properties prop = new Properties();
        prop.load(new FileInputStream("JDBCDemo/src/druid.properties"));
        //prop.load(new FileInputStream("JDBCDemo\\src\\druid.properties"));
        //��ȡ���ӳض���
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);
        //��ȡ����
        Connection c = dataSource.getConnection();
        System.out.println(c);
    }
}
