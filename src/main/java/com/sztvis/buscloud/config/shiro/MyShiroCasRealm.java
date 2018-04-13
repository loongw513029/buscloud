package com.sztvis.buscloud.config.shiro;

import javax.annotation.PostConstruct;

import com.sztvis.buscloud.config.ShiroCasConfiguration;
import com.sztvis.buscloud.model.domain.TramMemberInfo;
import com.sztvis.buscloud.model.dto.CurrentUserInfo;
import com.sztvis.buscloud.model.dto.LoginParams;
import com.sztvis.buscloud.service.IMemberService;
import org.apache.commons.codec.binary.Hex;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cas.CasRealm;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by hdwang on 2017/6/20.
 * 安全数据源
 */
public class MyShiroCasRealm extends AuthenticatingRealm {
    @Autowired
    private IMemberService iMemberService;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken uptoken = (UsernamePasswordToken) authenticationToken;
        String username = uptoken.getUsername();
        String pwd = this.iMemberService.getpwd(username);
        System.out.println(pwd);
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username,"123456",null);
        return info;

    }

//    private static final Logger logger = LoggerFactory.getLogger(MyShiroCasRealm.class);
//fc1709d0a95a6be30bc5926fdb7f22f4
//fc1709d0a95a6be30bc5926fdb7f22f4
//    @Autowired
//    private IMemberService iMemberService;
//
//    @Override
//    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) {
//        AuthenticationInfo token = super.doGetAuthenticationInfo(authcToken);
//        String userName =(String) token.getPrincipals().getPrimaryPrincipal();
//        logger.info("当前Subject时获取到用户名为" + userName);
//        //根据用户名，查找用户信息
//        TramMemberInfo user = loginUser(userName);
//        if (user != null) {
//            //user字符应该是固定写法
//            SecurityUtils.getSubject().getSession().setAttribute("user", user);
//        }
//        //这个token返回后便会进入配置中的成功路径
//        return token;
//    }

//    @Override
//    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//        TramMemberInfo user = CommonUtil.getCurrentUser();
//        if (user != null) {
//            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//            List<AuthorityTreeBean> list;
//            if (user.getId() == 1) {
//                list = infoAuthorityMapper.selectAll(1,6);
//            } else {
//                list = infoAuthorityMapper.selectAuthorityByUserId(user.getId(), 1, 6);
//            }
//            List<String> permissions = new ArrayList<>();
//            for (AuthorityTreeBean authority : list) {
//                permissions.add(authority.getAuthCode());
//            }
//            info.addStringPermissions(permissions);
//            return info;
//        } else {
//            return null;
//        }
//    }

//    public TramMemberInfo loginUser(String userName) {
//        //查询用户信息
//        TramMemberInfo userBean = this.iMemberService.Logins(userName);
//        String pass = userBean.getPassword();
//        //这里是对数据库提取的密码做加密操作，业务逻辑不必深究
//        return userBean;
//    }
}
