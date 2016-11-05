package cn.itcast.shiro.authorization;

import java.util.Arrays;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

public class AuthorizationTest {

	@Test
	public void testAuthorization(){
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-permission.ini");
		
		SecurityManager securityManager = factory.getInstance();
		
		SecurityUtils.setSecurityManager(securityManager);
		
		Subject subject = SecurityUtils.getSubject();
		
		UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "123");
		
		try {
			subject.login(token);
		} catch (AuthenticationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//check检测
		subject.checkRole("role1");
		
		System.out.println("认证状态"+subject.isAuthenticated());
		
		//基于角色的授权
		//hasRole穿入的角色的唯一标示(单个角色)
		System.out.println("基于角色的权限状态"+subject.hasRole("role1"));
		//hasAllRoles
		System.out.println("基于多个角色的权限状态"+subject.hasAllRoles(Arrays.asList("role1","role2")));
		subject.checkPermission("user:create:1");
		//基于资源的授权
		//isPermitted
		System.out.println("基于资源的单个权限状态"+subject.isPermitted("user:create:1"));
		
		//基于资源的多个授权
		//isPermittedAll
		System.out.println("基于资源的多个授权"+subject.isPermittedAll("user:create:1","user:update:2"));
	}
	
	
	//自定义realm来进行资源授权测试
	@Test
	public void testAuthorizationCustomRealm(){
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-realm.ini");
		
		SecurityManager securityManager = factory.getInstance();
		
		SecurityUtils.setSecurityManager(securityManager);
		
		Subject subject = SecurityUtils.getSubject();
		
		UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "111111");
		
		try {
			subject.login(token);
		} catch (AuthenticationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		subject.checkPermission("user:create:1");
		//基于资源的授权
		//isPermitted
		System.out.println("基于资源的单个权限状态"+subject.isPermitted("user:create:1"));
		
		//基于资源的多个授权
		//isPermittedAll
		System.out.println("基于资源的多个授权"+subject.isPermittedAll("user:create:1","user:update:2"));
	}
}
