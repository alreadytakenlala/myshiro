package top.lijunliang.permission.config;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import top.lijunliang.permission.ApplicationServer;
import top.lijunliang.permission.filter.MyFormAuthenticationFilter;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 项目每次启动时，根据shiroConfig的配置，将相应权限url加载到shiro框架中
 */
@Configuration //@Configuration中所有带@Bean注解的方法都会被动态代理，调用该方法返回的都是同一个实例
public class ShiroConfig
{
	protected static final Logger logger = LoggerFactory.getLogger(ApplicationServer.class);

	@Bean
	public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
		logger.info("shiro配置过滤拦截");
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		//拦截器.
		Map<String,String> filterChainDefinitionMap = new HashMap<String,String>();
		// 配置不会被拦截的链接 顺序判断
		filterChainDefinitionMap.put("/static/**", "anon"); //anon:所有url都都可以匿名访问
		//配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
		filterChainDefinitionMap.put("/logout", "logout");
		//<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
		filterChainDefinitionMap.put("/**", "authc"); //需要认证才能进行访问
		// 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
		shiroFilterFactoryBean.setLoginUrl("/login");
		// 登录成功后要跳转的链接
		shiroFilterFactoryBean.setSuccessUrl("/index");
		//未授权界面;
		shiroFilterFactoryBean.setUnauthorizedUrl("/403");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilterFactoryBean;
	}

	/**
	 * 凭证匹配器
	 * （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了
	 * ）
	 * @return
	 */
	@Bean
	public HashedCredentialsMatcher hashedCredentialsMatcher(){
		HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
		hashedCredentialsMatcher.setHashAlgorithmName("md5");//散列算法:这里使用MD5算法;
		hashedCredentialsMatcher.setHashIterations(2);//散列的次数，比如散列两次，相当于 md5(md5(""));
		return hashedCredentialsMatcher;
	}

	@Bean
	public MyShiroRealm myShiroRealm(){
		logger.info("装载MyShiroRealm");
		MyShiroRealm myShiroRealm = new MyShiroRealm();
		myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
		return myShiroRealm;
	}

	@Bean
	public SecurityManager securityManager(){
		DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
		securityManager.setRealm(myShiroRealm());
		return securityManager;
	}

	/**
	 *  开启shiro aop注解支持.
	 *  使用代理方式;所以需要开启代码支持;
	 * @param securityManager
	 * @return
	 */
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
		logger.info("注解配置");
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
		return authorizationAttributeSourceAdvisor;
	}

	@Bean
	public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator autoProxyCreator = new DefaultAdvisorAutoProxyCreator();
		autoProxyCreator.setProxyTargetClass(true);
		return autoProxyCreator;
	}

	@Bean(name="simpleMappingExceptionResolver")
	public SimpleMappingExceptionResolver
	createSimpleMappingExceptionResolver() {
		SimpleMappingExceptionResolver r = new SimpleMappingExceptionResolver();
		Properties mappings = new Properties();
		mappings.setProperty("DatabaseException", "databaseError");//数据库异常处理
		mappings.setProperty("UnauthorizedException","403");
		r.setExceptionMappings(mappings);  // None by default
		r.setDefaultErrorView("error");    // No default
		r.setExceptionAttribute("ex");     // Default is "exception"
		r.setWarnLogCategory("example.MvcLogger");     // No default
		return r;
	}

	/**
	 * 配置被拦截处理
	 */
	@Bean
	public FormAuthenticationFilter formAuthenticationFilter()
	{
		logger.info("装载MyFormAuthenticationFilter");
		return new MyFormAuthenticationFilter();
	}
}