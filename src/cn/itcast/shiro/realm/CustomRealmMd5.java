package cn.itcast.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

public class CustomRealmMd5 extends AuthorizingRealm {

	
	
	
	
	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		super.setName("customRealmMd5");
	}

	//用于授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		return null;
	}

	//用于认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// TODO Auto-generated method stub
		//第一步从token中取出身份信息
		String userCode = (String)token.getPrincipal();
		
		//根据用户输入的账号从数据库中查询密码
		String password = "f3694f162729b7d0254c6e40260bf15c";
		String salt="qwerty";
		
		//如果查询到，返回认证信息
		if(!userCode.equals("zhangsan")){
			return null;
		}
		//查询不到返回null
		SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(userCode, password, ByteSource.Util.bytes(salt), this.getName());
		
		return simpleAuthenticationInfo;
	}

}
