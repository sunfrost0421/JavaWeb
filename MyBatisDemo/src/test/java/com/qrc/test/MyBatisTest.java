package com.qrc.test;

import com.qrc.mapper.Mapper;
import com.qrc.pojo.Brand;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;


import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyBatisTest {

    @Test
    public void testSelectAll() throws IOException {
        String resource = "mybatis_config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        Mapper mapper = sqlSession.getMapper(Mapper.class);
        List<Brand> brands = mapper.selectAll();
        System.out.println(brands);
        sqlSession.close();
    }

    @Test
    public void testSelectByID() throws IOException {
        int id = 1;

        String resource = "mybatis_config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        Mapper mapper = sqlSession.getMapper(Mapper.class);
        Brand b = mapper.selectByID(id);
        System.out.println(b);
        sqlSession.close();
    }

    @Test
    public void testSelectByCondition() throws IOException {
        int status = 1;
        String companyName = "华为";
        String brandName = "华为";
        companyName = "%"+companyName+"%";
        brandName = "%"+brandName+"%";
        Brand b = new Brand();
        b.setBrandName(brandName);
        b.setCompanyName(companyName);
        b.setStatus(status);
        Map map = new HashMap();
        map.put("companyName11", null);
        map.put("brandName", brandName);
        map.put("status", null);


        String resource = "mybatis_config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        Mapper mapper = sqlSession.getMapper(Mapper.class);
        List<Brand> brands = mapper.selectByCondition(map);
        System.out.println(brands);
        sqlSession.close();
    }

    @Test
    public void testSelectByConditionSingle() throws IOException {
        int status = 1;
        String companyName = "华为";
        String brandName = "华为";
        companyName = "%"+companyName+"%";
        brandName = "%"+brandName+"%";
        Brand b = new Brand();
//        b.setBrandName(brandName);
//        b.setCompanyName(companyName);
//        b.setStatus(status);

        String resource = "mybatis_config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        Mapper mapper = sqlSession.getMapper(Mapper.class);
        List<Brand> brands = mapper.selectByConditionSingle(b);
        System.out.println(brands);
        sqlSession.close();
    }

    @Test
    public void testAdd() throws IOException {
        Brand b = new Brand();
        b.setBrandName("波导手机");
        b.setCompanyName("波导");
        b.setStatus(1);
        b.setDescription("手机中的战斗机");
        b.setOrdered(100);

        String resource = "mybatis_config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //SqlSession sqlSession = sqlSessionFactory.openSession();
        SqlSession sqlSession = sqlSessionFactory.openSession(true);//true表示自动提交事务，false表示需要手动提交事务（默认）
        Mapper mapper = sqlSession.getMapper(Mapper.class);
        mapper.add(b);
        System.out.println(b.getId());
        sqlSession.close();
    }

    @Test
    public void testUpdateAll() throws IOException {
        Brand b = new Brand();
        b.setBrandName("波导手机");
        b.setCompanyName("波导");
        b.setStatus(1);
        b.setDescription("手机中的战斗机");
        b.setOrdered(200);
        b.setId(6);

        String resource = "mybatis_config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //SqlSession sqlSession = sqlSessionFactory.openSession();
        SqlSession sqlSession = sqlSessionFactory.openSession(true);//true表示自动提交事务，false表示需要手动提交事务（默认）
        Mapper mapper = sqlSession.getMapper(Mapper.class);
        mapper.updateAll(b);
        System.out.println(b.getId());
        sqlSession.close();
    }

    @Test
    public void testUpdateSome() throws IOException {
        Brand b = new Brand();
        b.setBrandName("波导手机666");
//        b.setCompanyName("波导");
//        b.setStatus(1);
//        b.setDescription("手机中的战斗机");
//        b.setOrdered(200);
        b.setId(6);

        String resource = "mybatis_config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //SqlSession sqlSession = sqlSessionFactory.openSession();
        SqlSession sqlSession = sqlSessionFactory.openSession(true);//true表示自动提交事务，false表示需要手动提交事务（默认）
        Mapper mapper = sqlSession.getMapper(Mapper.class);
        int count = mapper.updateSome(b);
        System.out.println(count);
        sqlSession.close();
    }

    @Test
    public void testDeleteByID() throws IOException {
        String resource = "mybatis_config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //SqlSession sqlSession = sqlSessionFactory.openSession();
        SqlSession sqlSession = sqlSessionFactory.openSession(true);//true表示自动提交事务，false表示需要手动提交事务（默认）
        Mapper mapper = sqlSession.getMapper(Mapper.class);
        mapper.deleteByID(7);
        sqlSession.close();
    }
    @Test
    public void testDeleteByIDs() throws IOException {
        String resource = "mybatis_config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //SqlSession sqlSession = sqlSessionFactory.openSession();
        SqlSession sqlSession = sqlSessionFactory.openSession(true);//true表示自动提交事务，false表示需要手动提交事务（默认）
        Mapper mapper = sqlSession.getMapper(Mapper.class);
        int[] my_ids = {8};
        mapper.deleteByIDs(my_ids);
        sqlSession.close();
    }

    @Test
    public void testSelectByID2() throws IOException {
        int id = 1;

        String resource = "mybatis_config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        Mapper mapper = sqlSession.getMapper(Mapper.class);
        Brand b = mapper.selectByID2(id);
        System.out.println(b);
        sqlSession.close();
    }
}
