/*package com.freenow.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.freenow.dataaccessobject.IUserRepository;

@Service
public class JpaUserDetailsService implements UserDetailsService
{
    
    private IUserRepository userRepository;
    
    public JpaUserDetailsService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException
    {
        // TODO Auto-generated method stub
        return userRepository.findByUsername(userName).orElseThrow(() -> new UsernameNotFoundException("Username not found " + userName));
    }

}
*/
