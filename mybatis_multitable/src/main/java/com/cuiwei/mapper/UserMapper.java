package com.cuiwei.mapper;

import com.cuiwei.domain.User;

import java.util.List;


public interface UserMapper {
    /**
     * 一对多的关联查询  查询所有用户，还要查询每个用的的订单信息
     */
    public List<User> findAllWithOrder();

    /**
     *  多对多关联查询  查询所有用户，同时还要查询出每个用户所关联的角色信息
     */

    public List<User> findAllWithRole();

    //根据id查询用户
    public User findById(Integer id);


    /**
     * 一对多嵌套查询  查询所有用户，以及每个用户的订单信息
     *
     */
    public List<User>  findAllWithOrder2();


    /**
     * 多对多嵌套查询  查询所有用户  以及用户的角色信息
     */
    public List<User> findAllWithRole2();


}
