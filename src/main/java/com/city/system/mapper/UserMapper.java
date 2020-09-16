package com.city.system.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.city.system.pojo.entity.User;
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
public interface UserMapper extends BaseMapper<User> {

    /**
     * 用户列表
     *
     * @param page 分页对象
     * @return list
     */
    IPage<User> getUserList(@Param("page") Page<User> page, @Param("user") User user);

}
