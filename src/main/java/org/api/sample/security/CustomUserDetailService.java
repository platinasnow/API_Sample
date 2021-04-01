package org.api.sample.security;

import lombok.RequiredArgsConstructor;
import org.api.sample.model.CustomUserDetails;
import org.api.sample.repository.MemberRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {

    private final MemberRepository membersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CustomUserDetails item = new CustomUserDetails(membersRepository.findById(username).orElseThrow(()-> new UsernameNotFoundException("Cannot find user")));
        List<String> roles = new ArrayList<>();
        roles.add("ROLE_USER");
        item.setRoles(roles);
        return item;
    }
}
