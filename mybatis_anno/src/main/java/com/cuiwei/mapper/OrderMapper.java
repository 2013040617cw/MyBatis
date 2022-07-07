package com.cuiwei.mapper;

import com.cuiwei.domain.Orders;
import com.cuiwei.domain.User;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface OrderMapper {

    /**
     * 查询所有订单，同时查询订单所属的用户信息
     * @return
     */


    @Select("select * from orders")
    @Results({//代替了resultMap标签
            @Result(property = "id", column = "id",id = true),
            @Result(property = "ordertime", column = "ordertime"),
            @Result(property = "totla", column = "totla"),
            @Result(property = "uid", column = "uid"),

                @Result(property = "user",javaType = User.class, column = "uid",one = @One(select = "com.cuiwei.mapper.UserMapper.findById",fetchType = FetchType.EAGER))

    })
    public List<Orders> findAllWithUser();


    /**
     * 根据传过来的用户ID，查询用户所具有的订单信息
     * @param uid
     * @return
     */
    @Select("select  * from orders where uid = #{uid}")
    public List<Orders> findOrderById(Integer uid);
}

