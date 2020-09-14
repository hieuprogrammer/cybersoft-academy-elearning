package edu.cybersoft.elearning.service.impl;

import edu.cybersoft.elearning.domain.model.User;
import edu.cybersoft.elearning.dto.model.UserDetailsDto;
import edu.cybersoft.elearning.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service("userDetailsService")
@Transactional(rollbackOn = Exception.class)
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = this.userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Email dang nhap khong tim thay trong he thong!");
        }
        // Lấy quyền của User:
        String roleName = user.getRole().getName();
        //
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(roleName));
        UserDetailsDto userDetailsDto = new UserDetailsDto(user.getEmail(), user.getPassword(), authorities);
        return userDetailsDto;
    }
}