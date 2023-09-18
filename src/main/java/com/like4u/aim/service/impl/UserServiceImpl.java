package com.like4u.aim.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.like4u.aim.mapper.UserMapper;
import com.like4u.aim.pojo.User;
import com.like4u.aim.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;

/**
 * @author Zhang Min
 * @version 1.0
 * @date 2023/7/16 9:06
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;


    @Override
    public boolean checkLogin(String username, String password) {

        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();

        wrapper.eq(StringUtils.hasLength(username),User::getUsername,username)
                .eq(StringUtils.hasLength(password),User::getPassword,password);

        User user1 = userMapper.selectOne(wrapper);
        return !Objects.isNull(user1);

    }
}
