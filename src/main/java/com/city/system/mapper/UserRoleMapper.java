package com.city.system.mapper;

import com.city.system.pojo.entity.UserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author mirror6
 * @since 2020-05-04
 */
public interface UserRoleMapper extends BaseMapper<UserRole> {

    /**
     * 批量插入用户-角色关联表
     *
     * @param userRoles 关联表
     * @return int
     */
    int insertBatch(@Param("list") List<UserRole> userRoles);

}
