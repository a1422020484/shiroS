package cn.itcast.shiro.realm;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class CustomRealm extends AuthorizingRealm {
	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		super.setName("customRealm");
	}

	//用于授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		//principals 获得主身份信息
		String userCode = (String)principals.getPrimaryPrincipal();
		//连接数据库查询权限信息
		//模拟从数据库存在的信息
		List<String> permissions = new ArrayList<String>();
		permissions.add("user:create");
		permissions.add("user:update");
		
		//查询 到权限数据，就返回
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		
		simpleAuthorizationInfo.addStringPermissions(permissions);
		
		return simpleAuthorizationInfo;
	}

	//用于认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// TODO Auto-generated method stub
		//第一步从token中取出身份信息
		String userCode = (String)token.getPrincipal();
		 
		//根据用户输入的账号从数据库中查询密码
		String password = "111111";
		
		//如果查询到，返回认证信息
		if(!userCode.equals("zhangsan")){
			return null;
		}
		//查询不到返回null
		SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(userCode, password, this.getName());
		
		return simpleAuthenticationInfo;
	}

}
