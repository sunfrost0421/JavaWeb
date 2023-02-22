package com.qrc.pojo;

import com.qrc.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
//Mapper代理开发
public class MyBatisDemo2 {
    public static void main(String[] args) throws IOException {
        //1.加载核心配置文件，获取对象SqlSessionFactory
        String resource = "mybatis_config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //2.获取对象SqlSession，用来执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.执行sql语句
        //List<User> users = sqlSession.selectList("test.selectAll");
        //改为获取Mapper代理对象
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = mapper.selectAll();
        System.out.println(users);
        //4.释放资源
        sqlSession.close();
    }
}
