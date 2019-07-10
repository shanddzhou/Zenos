package com.zhoushan.blog.business.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhoushan.blog.business.entity.User;
import com.zhoushan.blog.business.entity.UserPwd;
import com.zhoushan.blog.business.enums.UserNotificationEnum;
import com.zhoushan.blog.business.enums.UserPrivacyEnum;
import com.zhoushan.blog.business.enums.UserStatusEnum;
import com.zhoushan.blog.business.service.SysUserService;
import com.zhoushan.blog.business.vo.UserConditionVO;
import com.zhoushan.blog.framework.exception.ZenosCommentException;
import com.zhoushan.blog.framework.exception.ZenosException;
import com.zhoushan.blog.framework.holder.RequestHolder;
import com.zhoushan.blog.persistence.beans.SysUser;
import com.zhoushan.blog.persistence.mapper.SysUserMapper;
import com.zhoushan.blog.utils.IpUtil;
import com.zhoushan.blog.utils.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author zhooo
 * @version V1.0
 * @Title: SysUserServiceImpl
 * @Package com.zhoushan.blog.business.service.impl
 * @Description: TODO
 * @date 2019/7/9 16:25
 */
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public PageInfo<User> findPageBreakByCondition(UserConditionVO vo) {
        PageHelper.startPage(vo.getPageNumber(), vo.getPageSize());
        List<SysUser> sysUserList = sysUserMapper.findPageBreakByCondition(vo);
        if (CollectionUtils.isEmpty(sysUserList)) {
            return null;
        }
        List<User> arrayList = new ArrayList<>();
        for (SysUser sysUser : sysUserList) {
            arrayList.add(new User(sysUser));
        }
        PageInfo pageInfo = new PageInfo<>(sysUserList);
        pageInfo.setList(arrayList);
        return pageInfo;
    }

    @Override
    public User updateUserLastLoginInfo(User user) {
        if (user != null) {
            user.setLastLoginTime(new Date());
            user.setLoginCount(user.getLoginCount() + 1);
            user.setLastLoginIp(IpUtil.getRealIp(RequestHolder.getRequest()));
            this.updateSelective(user);
        }
        return user;
    }

    @Override
    public User getByUserName(String userName) {
        SysUser sysUser = new SysUser();
        sysUser.setUsername(userName);
        sysUser = sysUserMapper.selectOne(sysUser);
        return null == sysUser ? null : new User(sysUser);
    }

    @Override
    public List<User> listByRoleId(Long roleId) {
        List<SysUser> sysUserList = sysUserMapper.listByRoleId(roleId);
        if (CollectionUtils.isEmpty(sysUserList)) {
            return null;
        }
        ArrayList<User> arrayList = new ArrayList<>();
        for (SysUser sysUser : sysUserList) {
            arrayList.add(new User(sysUser));
        }
        return arrayList;
    }

    @Override
    public boolean updatePwd(UserPwd userPwd) throws Exception {
        if (!userPwd.getNewPassword().equals(userPwd.getNewPasswordRepeat())) {
            throw new ZenosException("新密码不一致~");
        }
        User user = this.getByPrimaryKey(userPwd.getId());
        if (null == user) {
            throw new ZenosException("用户不存在~");
        }
        user.setPassword(userPwd.getNewPassword());
        user.setUpdateTime(new Date());
        return this.updateSelective(user);
    }

    @Override
    public User getByUuidAndSource(String uuid, String source) {
        if (StringUtils.isEmpty(uuid) || StringUtils.isEmpty(source)) {
            return null;
        }
        SysUser sysUser = new SysUser();
        sysUser.setUuid(uuid);
        sysUser.setSource(source);
        sysUser = sysUserMapper.selectOne(sysUser);
        return null == sysUser ? null : new User(sysUser);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User insert(User entity) {
        Assert.notNull(entity, "User不能为空~");
        entity.setCreateTime(new Date());
        entity.setUpdateTime(new Date());
        entity.setRegIp(IpUtil.getRealIp(RequestHolder.getRequest()));
        entity.setPrivacy(UserPrivacyEnum.PUBLIC.getCode());
        entity.setNotification(UserNotificationEnum.DETAIL);
        entity.setStatus(UserStatusEnum.NORMAL.getCode());
        sysUserMapper.insertSelective(entity.getSysUser());
        return entity;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByPrimaryKey(Long primaryKey) {
        return sysUserMapper.deleteByPrimaryKey(primaryKey) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateSelective(User entity) {
        Assert.notNull(entity, "User不能为空~");
        entity.setUpdateTime(new Date());
        if (!StringUtils.isEmpty(entity.getPassword())) {
            try {
                entity.setPassword(PasswordUtil.encrypt(entity.getPassword(), entity.getUsername()));
            } catch (Exception e) {
                e.printStackTrace();
                throw new ZenosCommentException("密码加密失败");
            }
        } else {
            entity.setPassword(null);
        }
        return sysUserMapper.updateByPrimaryKeySelective(entity.getSysUser()) > 0;
    }

    @Override
    public User getByPrimaryKey(Long primaryKey) {
        Assert.notNull(primaryKey, "primaryKey不能为空~");
        SysUser sysUser = sysUserMapper.selectByPrimaryKey(primaryKey);
        return null == sysUser ? null : new User(sysUser);
    }
}
