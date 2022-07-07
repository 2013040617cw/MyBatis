package com.cuiwei.mapper;

import com.cuiwei.domain.User;
import org.apache.ibatis.annotations.*;

import javax.management.ListenerNotFoundException;
import java.util.List;

public interface UserMapper {
    /**
     * 查询用户
     */
    @Select(value = "select * from user")
    public List<User> findAll();


    /**
     * 添加用户
     */
    @Insert("insert into user(username,birthday,sex,address) values(#{username},#{birthday},#{sex},#{address})")
    public void save(User user);


    /**
     * 更新用户
     */
    @Update("update user set username = #{username},birthday = #{birthday} where id = #{id} ")
    public void update(User user);

    /**
     * 删除用户
     */
    @Delete("delete  from user where id = #{id}")
    public void delete(Integer id);


    /**
     * 根据ID查询用户
     *
     */

    @Select("select * from user where id = #{uid}")
    public User findById(Integer uid);


    /**
     * 查询所有用户，及用户的订单信息
     */

    @Select("select * from user")
    @Results({
            @Result(property = "id",column = "id",id = true),
            @Result(property = "username",column ="username" ),
            @Result(property = "birthday",column = "birthday"),
            @Result(property = "sex",column = "sex"),
            @Result(property = "address",column = "address"),

                @Result(property = "ordersList",javaType = List.class,column = "id",many = @Many(select = "com.cuiwei.mapper.OrderMapper.findOrderById"))


    })
    public List<User> findAllWithOrder();


    /**
     * 多对多查询   查询所有用户及所有订单信息
     *
     */

    @Select("select * from user")
    @Results({
            @Result(property = "id",column = "id",id = true),
            @Result(property = "username",column = "username"),
            @Result(property = "birthday",column = "birthday"),
            @Result(property = "sex",column = "sex"),
            @Result(property = "address",column = "address"),
            @Result(property = "roleList",javaType = List.class,column = "id",many = @Many(select = "com.cuiwei.mapper.RoleMapper.findAllById"))
    })
    public List<User> findAllWithRole();
}



