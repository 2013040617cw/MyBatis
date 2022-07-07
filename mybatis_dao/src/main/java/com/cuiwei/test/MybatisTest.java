package com.cuiwei.test;

import com.cuiwei.domain.User;
import com.cuiwei.mapper.UserMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * mybatis的Dao层mapper代理方式测试
 */

public class MybatisTest {
    @Test
    public void test01() throws Exception {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //当前返回的，其实是基于UserMapper所产生的代理对象；底层：JDK动态代理
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.findUserById(1);
        System.out.println(user);


    }

    @Test
    public void test02() throws Exception {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //当前返回的，其实是基于UserMapper所产生的代理对象；底层：JDK动态代理

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> allResultMap = mapper.findAllResultMap();
        for (User user : allResultMap) {
            System.out.println(user);
        }

        sqlSession.close();
    }

    /**
     * 多条件查询方式一
     */
    @Test
    public void test03() throws Exception {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //当前返回的，其实是基于UserMapper所产生的代理对象；底层：JDK动态代理
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List user = mapper.fingByIdAndUsername1(12, "崔巍");
        System.out.println(user);
        sqlSession.close();


    }

    /**
     * 多条件查询方式二
     *
     * @throws Exception
     */
    @Test
    public void test04() throws Exception {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //当前返回的，其实是基于UserMapper所产生的代理对象；底层：JDK动态代理
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List user = mapper.findByIdUsername2(1, "子慕");

        System.out.println(user);
        sqlSession.close();
    }


    /**
     * 多条件查询  方式三
     *
     * @throws Exception
     */
    @Test
    public void test05() throws Exception {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //当前返回的，其实是基于UserMapper所产生的代理对象；底层：JDK动态代理
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();
        user.setId(1);
        user.setUsername("子慕");
        List<User> users = mapper.findByIdAndUsername3(user);
        for (User user1 : users) {
            System.out.println(user1);
        }
    }

    /**
     * 模糊查询    方式一
     *
     * @throws Exception
     */
    @Test
    public void test06() throws Exception {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //当前返回的，其实是基于UserMapper所产生的代理对象；底层：JDK动态代理
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = mapper.findByUsername("%崔巍%");

        for (User user : users) {
            System.out.println(user);
        }
        sqlSession.close();
    }



    /**
     * 模糊查询    方式二
     *
     * @throws Exception
     */
    @Test
    public void test07() throws Exception {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //当前返回的，其实是基于UserMapper所产生的代理对象；底层：JDK动态代理
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = mapper.findByUsername2("%崔巍%");

        for (User user : users) {
            System.out.println(user);
        }
        sqlSession.close();
    }


    /**
     * 返回主键 获取主键  方式一
     * @throws Exception
     */
@Test
    public void test08() throws Exception {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //当前返回的，其实是基于UserMapper所产生的代理对象；底层：JDK动态代理
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();
        user.setUsername("李慧康");
        user.setBirthday(new Date());
        user.setSex("男");
        user.setAddress("清徐");
        System.out.println(user);
        mapper.saveUser(user);
        System.out.println(user);
        sqlSession.commit();
        sqlSession.close();
    }


    /**
     * 添加用户返回主键   方式二
     * @throws Exception
     */
    @Test
    public void test09() throws Exception {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //当前返回的，其实是基于UserMapper所产生的代理对象；底层：JDK动态代理

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        User user = new User();
        user.setUsername("汤唯");
        user.setBirthday(new Date());
        user.setSex("女");
        user.setAddress("北京昌平");

        System.out.println(user);
        mapper.saveUser2(user);
        System.out.println(user);


        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 动态标签之if ：多条件查询
     * @throws Exception
     */
    @Test
    public void test10() throws Exception {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //当前返回的，其实是基于UserMapper所产生的代理对象；底层：JDK动态代理

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        User user = new User();
        user.setUsername("崔巍");
        user.setId(12);


        List<User> users = mapper.findByIdAndUsernameIf(user);
        for (User user1 : users) {
            System.out.println(user1);
        }


        sqlSession.commit();
        sqlSession.close();
    }

    /**
     * 动态Sqlset： 动态更新
     * @throws Exception
     */
    @Test
    public void test11() throws Exception {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //当前返回的，其实是基于UserMapper所产生的代理对象；底层：JDK动态代理

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        User user = new User();
        user.setUsername("崔巍");
        user.setAddress("北京富人区");
        user.setId(12);

        mapper.UpdateIf(user);

        sqlSession.commit();
        sqlSession.close();
    }


    /**
     * 动态Sql  foreach:多值查询
     * @throws Exception
     */
    @Test
    public void test12() throws Exception {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //当前返回的，其实是基于UserMapper所产生的代理对象；底层：JDK动态代理

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        ArrayList<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(2);
        ids.add(12);
        List<User> users = mapper.findByList(ids);
        for (User user : users) {
            System.out.println(user);
        }

        sqlSession.close();
    }

    /**
     * 核心配置文件的深入：plugin标签：pageHelper
     * @throws Exception
     */
    @Test
    public void test13() throws Exception {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //当前返回的，其实是基于UserMapper所产生的代理对象；底层：JDK动态代理

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        //设置分页参数
        //参数1：当前页
        //参数2：每页显示的条数
        PageHelper.startPage(1,2);
        List<User> users = mapper.findAllResultMap();
        for (User user : users) {
            System.out.println(user);
        }

        //获取分页相关的其他参数
        PageInfo<User>pageInfo  = new PageInfo<User>(users);

        System.out.println("总条数:" + pageInfo.getTotal());
        System.out.println("总页数:" + pageInfo.getPages());
        System.out.println("是否是第一页:" + pageInfo.isIsFirstPage());

        sqlSession.close();
    }
}
