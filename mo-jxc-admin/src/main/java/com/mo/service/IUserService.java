package com.mo.service;

import com.mo.model.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mo.request.UserUpdateRequest;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author mo
 * @since 2022-11-02
 */
public interface IUserService extends IService<User> {

    User login(String userName, String password);

    User findByUserName(String userName);

    User updateUserInfo(UserUpdateRequest request);

    User updateUserPassword(String userName, String oldPassword, String newPassword, String confirmPassword);
}
