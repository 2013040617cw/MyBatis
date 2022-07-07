package com.cuiwei.mapper;

import com.cuiwei.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {


    /**
     * 根据ID进行用户查询
     *
     */
    public User findUserById(int id);

    /**
     * 查询所有用户
     * @return
     */
    public List<User> findAllResultMap();


    /**
     * 根据多条件查询   方式一
     * 根据ID和username的查询
     */
    public List fingByIdAndUsername1(int id,String username);


    /**
     * 多条件查询    方式二
     *
     */
    public List findByIdUsername2(@Param("id") int id,@Param("username") String username);


    /**
     * 多条件查询    方式三
     *
     */
    public List<User> findByIdAndUsername3(User user);


    /**
     * 模糊查询   方式一
     */

    public List<User> findByUsername(String username);


    /**
     * 模糊查询    方式二
     * @param username
     * @return
     */
    public List<User> findByUsername2(String username);


    /**
     * 添加用户 获取返回主键  方式一
     */
    public void saveUser(User user);


    /**
     * 添加用户   获取返回值  方式二

     */
    public void saveUser2(User user);

    /**‘
     * 动态Sql的if标签：多条件动态查询
     * @param user
     */
    public List<User> findByIdAndUsernameIf(User user);

    /**
     * 动态Sql的set标签     动态更新
     */
    public void UpdateIf(User user);

    /**
     * 动态Sql的foreach标签：多值查询
     *
     */
    public List<User> findByList(List<Integer> ids);
}
