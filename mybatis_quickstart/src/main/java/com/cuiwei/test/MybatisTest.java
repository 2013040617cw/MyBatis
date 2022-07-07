package com.cuiwei.test;

import com.cuiwei.domain.User;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class MybatisTest {
    /*快速入门代码*/
    @Test
    public void mybatisQuickStart() throws Exception {
        //1.加载核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");

        //2.获取SqlSessionFactory 工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        //3.获取SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();


        //4.执行Sql 参数：statementid:namespace.id;
        List<User> users = sqlSession.selectList("userMapper.findAll");


        //5.遍历打印结果
        for (User user : users) {
            System.out.println(user);
        }

        //6.关闭资源
        sqlSession.close();
    }


    //测试新增用户
    @Test
    public void testSave() throws Exception {
        //加载核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");

        //获取工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        //获取一个会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        User user = new User();
        user.setUsername("cuiwei");
        user.setBirthday(new Date());
        user.setSex("男");
        user.setAddress("盂县");
        sqlSession.insert("userMapper.saveUser", user);
        //sqlSession.commit()手动提交事务
        sqlSession.commit();
        sqlSession.close();


    }

    //进行数据的更新
    @Test
    public void testUpdate() throws Exception {
        //加载核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");

        //获取工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        //获取一个会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();

        User user = new User();
        user.setId(6);
        user.setUsername("lihuikang");
        user.setBirthday(new Date());
        user.setSex("女");
        user.setAddress("清徐县");
        sqlSession.update("userMapper.updateUser", user);

        //sqlSession.commit()手动提交事务
        sqlSession.commit();
        sqlSession.close();


    }

    //测试删除用户
    @Test
    public void testDelete() throws Exception {
        //加载核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");

        //获取工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        //获取一个会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();


        sqlSession.delete("userMapper.deleteUser", 6);

        //sqlSession.commit()手动提交事务
        sqlSession.commit();
        sqlSession.close();


    }
}
