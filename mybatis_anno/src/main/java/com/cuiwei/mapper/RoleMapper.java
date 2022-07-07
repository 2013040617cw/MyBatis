package com.cuiwei.mapper;

import com.cuiwei.domain.Role;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RoleMapper {
    /**
     * 根据传递过来的用户ID，查询该用户所具有的角色信息
     */
@Select("select * from sys_role r INNER JOIN sys_user_role ur ON ur.roleid = r.id where ur.userid = #{uid}")
    public List<Role> findAllById(Integer uid);
}
