package com.cuiwei.test;

import com.cuiwei.domain.Orders;
import com.cuiwei.domain.User;
import com.cuiwei.mapper.OrderMapper;
import com.cuiwei.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class MybatisTest {

     private  SqlSession sqlSession;

     private  SqlSessionFactory sessionFactory;

    //在@Test方法标注的方法之前执行
    @Before
    public void before() throws  IOException{
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");

         sessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

         sqlSession = sessionFactory.openSession();
    }

    //在@Test注解的方法之后执行
    @After
    public void after(){
        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 测试查询方法
     * @throws IOException
     */
    @Test
    public  void testSelect() throws IOException {

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        List<User> users = mapper.findAll();

        for (User user : users) {
            System.out.println(user);

        }
    }

    /**
     * 测试添加方法
     *
     */
    @Test
    public void testInsert(){
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        User user = new User();

        user.setUsername("赵文超");
        user.setSex("男");
        user.setBirthday(new Date());
        user.setAddress("大同");

        mapper.save(user);

    }


    /**
     * 测试更新方法
     */

    @Test
    public void testUpdate(){
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        User user  = new User();
        user.setUsername("崔巍最帅");
        user.setBirthday(new Date());
        user.setId(2);

        mapper.update(user);
    }

    /**
     * 删除用户
     */
    @Test
    public void testDelete(){
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        mapper.delete(13);
    }


    /**
     * 一对一查询所有订单，查询所有订单，同时查询订单所属的用户信息（注解方式）
     */

    @Test
    public void testOneToOne(){

        OrderMapper mapper = sqlSession.getMapper(OrderMapper.class);

        List<Orders> orders = mapper.findAllWithUser();

        for (Orders order : orders) {
            System.out.println(order);
        }


    }


    /**
     * 一对多查询（注解方式）
     */
    @Test
    public void testOneToMany(){
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        List<User> users = mapper.findAllWithOrder();

        for (User user : users) {
            System.out.println(user);
            System.out.println(user.getOrdersList());
        }
    }

    /**
     * 多对多查询，查询所有用户及用户的角色
     */
    @Test
    public void testManyToMany(){
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        List<User> roles= mapper.findAllWithRole();

        for (User role : roles) {
            System.out.println(role);
            System.out.println(role.getRoleList());
        }
    }

}
