package ch.zhaw.gratisbrockibackend.service;

import ch.zhaw.gratisbrockibackend.service.UserDetailsImpl;
import ch.zhaw.gratisbrockibackend.domain.User;
import ch.zhaw.gratisbrockibackend.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AppUserDetailsServiceImpl implements AppUserDetailsService {

	private final UserRepository userRepository;

	@Override
	public User findByEmail(String email) {
		return userRepository.findUserByEmail(email);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = findByEmail(username);
		if( user == null ){
			throw new UsernameNotFoundException(username);
		}
		return new UserDetailsImpl(user);
	}
}
