package com.cuiwei.mapper;

import com.cuiwei.domain.Role;

import java.util.List;

public interface RoleMapper {
    /**
     * 根据用户ID查询对应角色
     */
    public List<Role> findByUid(Integer uid);
}
