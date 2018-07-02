package org.gege.shiro.charpter5.realm;

import java.util.concurrent.atomic.AtomicInteger;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;

//密码超出重试次数
public class MyCredentialsMatcher extends HashedCredentialsMatcher {
	private Ehcache passwordRetryCache;

    public MyCredentialsMatcher() {
        CacheManager cacheManager = CacheManager.newInstance(MyCredentialsMatcher.class.getClassLoader().getResource("shiro/charpter5/ehcache.xml"));
        passwordRetryCache = cacheManager.getCache("passwordRetryCache");
    }
	@Override
	public boolean doCredentialsMatch(AuthenticationToken token,
			AuthenticationInfo info) {
		  String username = (String)token.getPrincipal();
	        //retry count + 1
	        Element element = passwordRetryCache.get(username);
	        if(element == null) {
	            element = new Element(username , new AtomicInteger(0));
	            passwordRetryCache.put(element);
	        }
	        AtomicInteger retryCount = (AtomicInteger)element.getObjectValue();
	        if(retryCount.incrementAndGet() > 5) {
	            //if retry count > 5 throw
	            throw new ExcessiveAttemptsException();
	        }

	        boolean matches = super.doCredentialsMatch(token, info);
	        if(matches) {
	            //clear retry count
	            passwordRetryCache.remove(username);
	        }
	        return matches;
	}

	
}
