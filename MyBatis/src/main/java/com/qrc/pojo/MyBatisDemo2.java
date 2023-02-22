package com.qrc.pojo;

import com.qrc.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
//Mapper������
public class MyBatisDemo2 {
    public static void main(String[] args) throws IOException {
        //1.���غ��������ļ�����ȡ����SqlSessionFactory
        String resource = "mybatis_config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //2.��ȡ����SqlSession������ִ��sql
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3.ִ��sql���
        //List<User> users = sqlSession.selectList("test.selectAll");
        //��Ϊ��ȡMapper�������
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = mapper.selectAll();
        System.out.println(users);
        //4.�ͷ���Դ
        sqlSession.close();
    }
}
