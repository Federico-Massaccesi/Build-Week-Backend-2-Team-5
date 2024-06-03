package it.epicode.ENello.Management.security;

import it.epicode.ENello.Management.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserDetailsService implements UserDetailsService {

   @Autowired
   UserRepository users;

   @Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       var user = users.findOneByUsername(username).orElseThrow();
       var applicationUser = SecurityUserDetails.build(user);
       return applicationUser;
   }

}
