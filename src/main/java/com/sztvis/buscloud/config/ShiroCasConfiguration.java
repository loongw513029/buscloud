package com.sztvis.buscloud.config;

import com.sztvis.buscloud.config.shiro.MyShiroCasRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.cas.CasFilter;
import org.apache.shiro.cas.CasSubjectFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.jasig.cas.client.session.SingleSignOutFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by hdwang on 2017/6/20.
 * shiro+cas 配置
 */
//@Configuration
public class ShiroCasConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(ShiroCasConfiguration.class);

    // cas server地址
    public static final String casServerUrlPrefix = "https://localhost:8080";
//    // Cas登录页面地址
    public static final String casLoginUrl = casServerUrlPrefix + "/login";
//    // Cas登出页面地址
    public static final String casLogoutUrl = casServerUrlPrefix + "/home/logout";
//    // 当前工程对外提供的服务地址
    public static final String shiroServerUrlPrefix = "http://localhost:8080";
//    // casFilter UrlPattern
//    public static final String casFilterUrlPattern = "/cas";
//    // 登录地址
//    public static final String loginUrl = casLoginUrl + "?service=" + shiroServerUrlPrefix + casFilterUrlPattern;
    public static final String loginUrl = casServerUrlPrefix + "/home/login";
//    // 登出地址（casserver启用service跳转功能，需在webapps\cas\WEB-INF\cas.properties文件中启用cas.logout.followServiceRedirects=true）
//    public static final String logoutUrl = casLogoutUrl+"?service="+shiroServerUrlPrefix;
    public static final String logoutUrl = casServerUrlPrefix + "/home/logout";
//    // 登录成功地址
//    public static final String loginSuccessUrl = "/home";
//    // 权限认证失败跳转地址
//    public static final String unauthorizedUrl = "/error/403.html";

    private static final String casFilterUrlPattern = "/shiro-cas";

    @Bean(name = "securityManager")
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManagers = new DefaultWebSecurityManager();
        // 设置realm.
        securityManagers.setRealm(getShiroRealm());

        // 设置rememberMe管理器
        securityManagers.setRememberMeManager(rememberMeManager());
        System.out.println("securityManager");
        return securityManagers;
    }

    @Bean(name = "authRealm")
    public MyShiroCasRealm getShiroRealm() {
        //将MyRealm改成你自己的类,其他不动
        MyShiroCasRealm casRealm = new MyShiroCasRealm();
     //   casRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        //casRealm.setCasService(shiroServerUrlPrefix + casFilterUrlPattern);
        return casRealm;
    }

    //cookie对象
    @Bean
    public SimpleCookie rememberMeCookie() {
        // 这个参数是cookie的名称，对应前端的checkbox 的name = rememberMe
        SimpleCookie simpleCookie = new SimpleCookie("cookie_username");
        // <!-- 记住我cookie生效时间30天（259200） ,单位秒;-->
        simpleCookie.setMaxAge(259200);
        return simpleCookie;
    }

   // cookie管理对象
    @Bean(name = "cookieRememberMeManager")
    public CookieRememberMeManager rememberMeManager() {
        System.out.println("rememberMeManager()");
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        return cookieRememberMeManager;
    }

    //密码匹配凭证管理器
    @Bean(name = "hashedCredentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        System.out.println("hashedCredentialsMatcher");
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");// 散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashIterations(1024);// 散列的次数，比如散列两次，相当于
        // md5(md5(""));
        return hashedCredentialsMatcher;
    }

//过滤器
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
        filterRegistration.setFilter(new DelegatingFilterProxy("shiroFilter"));
        filterRegistration.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico");
        filterRegistration.setEnabled(true);
        filterRegistration.addUrlPatterns("/*");
        return filterRegistration;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilter() {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager());
        // 拦截器.
        Map<String, String> map = new LinkedHashMap<String, String>();
        map.put("/logout", "logout");
     //   map.put("/login", "authc");
        map.put("/web/**/**","anon");
        map.put("/images/**/**","anon");
        map.put("/css/**/**","anon");
        map.put("/error","anon");
        map.put("/**", "anon");
        //map.put("/")
        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/login");
        // 登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/index");
        // 未授权界面;
        shiroFilterFactoryBean.setUnauthorizedUrl("/404");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }

    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    public FilterRegistrationBean singleSignOutFilter(){
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setName("singleSignOutFilter");
        bean.setFilter(new SingleSignOutFilter());
        bean.addUrlPatterns("/*");
        bean.setEnabled(true);
        return bean;
    }

    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean() {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager());
        String loginUrl = casServerUrlPrefix + "/login?service=" + shiroServerUrlPrefix + casFilterUrlPattern;
        shiroFilterFactoryBean.setLoginUrl(loginUrl);
        shiroFilterFactoryBean.setSuccessUrl("/");
        Map<String, Filter> filters = new HashMap<>();
  //      filters.put("casFilter", casFilter);
        shiroFilterFactoryBean.setFilters(filters);
        shiroFilter();
        return shiroFilterFactoryBean;
    }
}