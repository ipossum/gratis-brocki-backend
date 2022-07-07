package ch.zhaw.gratisbrockibackend.service;

import ch.zhaw.gratisbrockibackend.domain.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component("appUserDetailsServiceImpl")
public interface AppUserDetailsService extends UserDetailsService{

	User findByEmail(String email);

}
