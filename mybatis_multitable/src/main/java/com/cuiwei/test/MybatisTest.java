package com.cuiwei.test;

import com.cuiwei.domain.Orders;
import com.cuiwei.domain.User;
import com.cuiwei.mapper.OrderMapper;
import com.cuiwei.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class MybatisTest {
    /**
     * 一对一关联查询  查询所有订单，并且查询出每个订单所属的用户信息
     */
    @Test
    public void test01() throws  Exception{
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");

        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        SqlSession sqlSession = sessionFactory.openSession();

        OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);

        List<Orders> orders = mapper.findAllWithUsers();

        for (Orders order : orders) {
            System.out.println(order);
        }

        sqlSession.close();

    }

    /**
     * 查询用户，，以及每个用户对应的订单信息
     * @throws Exception
     */
    @Test
    public void test02() throws  Exception{
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");

        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        SqlSession sqlSession = sessionFactory.openSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        List<User> orders = mapper.findAllWithOrder();
        for (User order : orders) {
            System.out.println(order);
        }



        sqlSession.close();

    }

    /**
     * 多对多关联查询  查询所有用的所有角色
     * @throws Exception
     */
    @Test
    public void test03() throws  Exception{
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");

        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        SqlSession sqlSession = sessionFactory.openSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        List<User> roles = mapper.findAllWithRole();
        for (User role : roles) {
            System.out.println(role);
        }



        sqlSession.close();

    }

    /**
     * 一对一嵌套查询：查询所有订单及关联的用户信息
     * @throws Exception
     */
    @Test
    public void test04() throws  Exception{
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");

        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        SqlSession sqlSession = sessionFactory.openSession();

        OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);

        List<Orders> orders = mapper.findAllWithUsers2();

        for (Orders order : orders) {
            System.out.println(order);
        }


        sqlSession.close();

    }

    /**
     * 一对多嵌套查询  查询所有用户，以及每个用户的订单信息
     * @throws Exception
     */
    @Test
    public void test05() throws  Exception{
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");

        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        SqlSession sqlSession = sessionFactory.openSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        List<User> users = mapper.findAllWithOrder2();

        for (User user : users) {
            System.out.println(user);
            //用到该用户的订单信息了
           // System.out.println(user.getOrdersList());

        }
        //用到该用户的订单信息了



        sqlSession.close();

    }

    /**
     * 多对多嵌套查询   查询所有用户和用户的角色信息
     * @throws Exception
     */
    @Test
    public void test06() throws  Exception{
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");

        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        SqlSession sqlSession = sessionFactory.openSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        List<User>users = mapper.findAllWithRole2();

        for (User user : users) {
            System.out.println(user);

        }

        sqlSession.close();

    }


    //测试和验证Mybatis的一级缓存
    public void testOneCache() throws Exception{
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");

        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        SqlSession sqlSession = sessionFactory.openSession();

        UserMapper userMapperr = sqlSession.getMapper(UserMapper.class);

        //根据ID查询用户信息
        User user1= userMapperr.findById(1);
        System.out.println(user1);

        User user2= userMapperr.findById(1);
        System.out.println(user2);

        sqlSession.close();
    }

    /**
     * 测试Mybatis的二级缓存
     * @throws Exception
     */
    @Test

    public void testTwoCache() throws Exception{
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");

        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        SqlSession sqlSession1 = sessionFactory.openSession();


        UserMapper userMapper1 = sqlSession1.getMapper(UserMapper.class);

        //第一次查询
        User user1 = userMapper1.findById(1);

        System.out.println(user1);

        //执行sqldession.close或者sqlsession.commint才会将一级缓存刷新到二级缓存
        sqlSession1.close();

        SqlSession sqlSession2 = sessionFactory.openSession();

        UserMapper userMapper2 = sqlSession2.getMapper(UserMapper.class);

        User user2 = userMapper2.findById(1);

        System.out.println(user2);

        sqlSession2.close();

    }
}
