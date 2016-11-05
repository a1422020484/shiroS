package cn.itcast.shiro.authentication;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
public class MD5Test {
	public static void main(String[] args) {
		String source = "111111";
		String salt = "qwerty";
		
		int  hashIterations = 1;
		
		Md5Hash md5Hash = new Md5Hash(source, salt, hashIterations);
		
		String password_md5 = md5Hash.toString();
		System.out.println(password_md5);
		
		//algorithmName 散列算法
		SimpleHash simpleHash = new SimpleHash("md5", source, salt, hashIterations);
		String password_simplemd5 = simpleHash.toString();
		System.out.println(password_simplemd5);
	}
}
