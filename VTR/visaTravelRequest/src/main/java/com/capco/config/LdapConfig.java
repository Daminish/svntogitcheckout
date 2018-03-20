package com.capco.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;

@Configuration 
public class LdapConfig {
	
	@Bean
	public LdapTemplate ldapTemplate() {
		return new LdapTemplate(getLdapContextSource());
	}
	
	@Bean
	public LdapContextSource getLdapContextSource(){
		LdapContextSource ldapContextSource = new LdapContextSource();
		ldapContextSource.setUrl("ldap://INBE2K8S03.CAPCO.COM:389");
		ldapContextSource.setBase("dc=ad,dc=capco,dc=com");
		return ldapContextSource;
	}

}
