package cn.itcast.shiro.authentication;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

public class AuthenticationTest {
	
	//用户的登录和退出
	@Test
	public void testLoginAndLogout(){
		
		//创建SecurityManager工厂,通过ini的配置文件常见工厂
		Factory<SecurityManager> factory  = new IniSecurityManagerFactory("classpath:shiro-first.ini");
		//创建SecurityManager
		SecurityManager securityManager = factory.getInstance();
		
		//将SecurityManager设置到当前的运行环境中
		SecurityUtils.setSecurityManager(securityManager);
		//从SecurityUtils里面创建一个subject
		Subject subject = SecurityUtils.getSubject();
		//在认证提交前准备token(令牌)
		UsernamePasswordToken token = new UsernamePasswordToken("zhangsan","111111");
		//执行认证的提交
		try {
			subject.login(token);
		} catch (AuthenticationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//是否认证通过
		boolean isAuthenticated = subject.isAuthenticated();
		
		System.out.println("是否认证通过"+isAuthenticated);
		
		subject.logout();
		
		System.out.println("是否认证通过"+subject.isAuthenticated());
		
	}
	//用户的登录和退出
	@Test
	public void testCustomRealm(){
		
		//创建SecurityManager工厂,通过ini的配置文件常见工厂
		Factory<SecurityManager> factory  = new IniSecurityManagerFactory("classpath:shiro-realm-md5.ini");
		//创建SecurityManager
		SecurityManager securityManager = factory.getInstance();
		
		//将SecurityManager设置到当前的运行环境中
		SecurityUtils.setSecurityManager(securityManager);
		//从SecurityUtils里面创建一个subject
		Subject subject = SecurityUtils.getSubject();
		//在认证提交前准备token(令牌)
		UsernamePasswordToken token = new UsernamePasswordToken("zhangsan","111111");
		//执行认证的提交
		try {
			subject.login(token);
		} catch (AuthenticationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//是否认证通过
		boolean isAuthenticated = subject.isAuthenticated();
		
		System.out.println("是否认证通过"+isAuthenticated);
		
		subject.logout();
		
		System.out.println("是否认证通过"+subject.isAuthenticated());
	}
	//用户的登录和退出
	//自定义的realm，进行散列的匹配
	@Test
	public void testCustomRealmMd5(){
		
		//创建SecurityManager工厂,通过ini的配置文件常见工厂
		Factory<SecurityManager> factory  = new IniSecurityManagerFactory("classpath:shiro-realm-md5.ini");
		//创建SecurityManager
		SecurityManager securityManager = factory.getInstance();
		
		//将SecurityManager设置到当前的运行环境中
		SecurityUtils.setSecurityManager(securityManager);
		//从SecurityUtils里面创建一个subject
		Subject subject = SecurityUtils.getSubject();
		//在认证提交前准备token(令牌)
		UsernamePasswordToken token = new UsernamePasswordToken("zhangsan","111111");
		//执行认证的提交
		try {
			subject.login(token);
		} catch (AuthenticationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//是否认证通过
		boolean isAuthenticated = subject.isAuthenticated();
		
		System.out.println("是否认证通过"+isAuthenticated);
		
		subject.logout();
		
		System.out.println("是否认证通过"+subject.isAuthenticated());
	}
}
