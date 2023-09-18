package com.like4u.aim.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.like4u.aim.pojo.User;

/**
 * @author Zhang Min
 * @version 1.0
 * @date 2023/7/16 9:05
 */
public interface UserService  {
    boolean checkLogin(String username, String password);
}
