package com.mo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mo.enums.BizCodeEnum;
import com.mo.exception.BizException;
import com.mo.model.User;
import com.mo.mapper.UserMapper;
import com.mo.request.UserQueryRequest;
import com.mo.request.UserUpdateRequest;
import com.mo.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mo.utils.AssertUtil;
import com.mo.utils.PageResultUtil;
import com.mo.utils.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

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
    @Autowired
    private PasswordEncoder encoder;


    /**
     * 用户列表查询
     *
     * @param request
     * @return
     */
    @Override
    public Map<String, Object> userList(UserQueryRequest request) {

        IPage<User> pageInfo = new Page<>(request.getPage(), request.getLimit());

        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("is_del", 0);

        if (StringUtils.isNoneBlank(request.getUserName())){
            userQueryWrapper.like("user_name",request.getUserName());
        }

        IPage<User> userPage = userMapper.selectPage(pageInfo, userQueryWrapper);

        Map<String, Object> resultMap = PageResultUtil.getPageResult(userPage.getTotal(), userPage.getRecords());

        return resultMap;
    }

    /**
     * 用户密码更新
     *
     * @param userName
     * @param oldPassword
     * @param newPassword
     * @param confirmPassword
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public User updateUserPassword(String userName, String oldPassword, String newPassword, String confirmPassword) {

        User user = null;
        user = findByUserName(userName);

        //用户名非空 必须存在
        AssertUtil.isTrue(null == user, BizCodeEnum.USER_UNREGISTER);
        AssertUtil.isTrue(StringUtil.isEmpty(oldPassword), BizCodeEnum.USER_OLDPWDEMPTY);
        AssertUtil.isTrue(StringUtil.isEmpty(newPassword), BizCodeEnum.USER_NEWPWDEMPTY);
        AssertUtil.isTrue(StringUtil.isEmpty(confirmPassword), BizCodeEnum.USER_CONFIRMPWDEMPTY);

        //比对加密的密码
        AssertUtil.isTrue(!(encoder.matches(oldPassword, user.getPassword())), BizCodeEnum.USER_OLDPWDERROR);
        AssertUtil.isTrue(!(newPassword.equals(confirmPassword)), BizCodeEnum.USER_NEWPWDERROR);
        AssertUtil.isTrue(newPassword.equals(oldPassword), BizCodeEnum.USER_OLDNEWPWDERROR);

        //更新用户密码
        user.setPassword(encoder.encode(newPassword));
        int result = userMapper.updateById(user);

        return result > 0 ? user : null;
    }


    /**
     * 用户信息更新
     *
     * @param request
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public User updateUserInfo(UserUpdateRequest request) {

        //用户名默认是唯一的，非空
        AssertUtil.isTrue(StringUtil.isEmpty(request.getUserName()), BizCodeEnum.USER_NAMEEMPTY);

        User targetUser = findByUserName(request.getUserName());
        //正在修改的用户id 与 数据库中同名的 用户id  取反 为真 && 同名的用户id 不为空
        AssertUtil.isTrue(null != targetUser && !(targetUser.getId().equals(request.getId())), BizCodeEnum.USER_NAMEISEXIST);

        User user = new User();
        BeanUtils.copyProperties(request, user);

        //修改用户信息
        int result = userMapper.updateById(user);

        return result > 0 ? user : null;
    }

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

        User user = findByUserName(userName);

        return user;
    }

    /**
     * 根据用户名查找
     *
     * @param userName
     * @return
     */
    public User findByUserName(String userName) {
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("is_del", 0)
                .eq("user_name", userName));

        return user;
    }
}
