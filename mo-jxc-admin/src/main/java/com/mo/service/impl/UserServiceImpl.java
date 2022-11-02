package com.mo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mo.enums.BizCodeEnum;
import com.mo.model.User;
import com.mo.mapper.UserMapper;
import com.mo.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mo.utils.AssertUtil;
import com.mo.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author mo
 * @since 2022-11-02
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;


    /**
     * 用户登录
     *
     * @param userName
     * @param password
     * @return
     */
    @Override
    public User login(String userName, String password) {

        AssertUtil.isTrue(StringUtil.isEmpty(userName), BizCodeEnum.USER_NAMEEMPTY);

        AssertUtil.isTrue(StringUtil.isEmpty(password), BizCodeEnum.USER_PWDEMPTY);

        User user = userMapper.selectOne(new QueryWrapper<User>().eq("is_del", 0)
                .eq("user_name", userName));

        return user;
    }
}
