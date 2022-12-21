package com.example.usercrud.service.serviceImp;

import com.example.usercrud.entity.AppUser;
import com.example.usercrud.mapper.AppUserMapper;
import com.example.usercrud.util.CustomUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private AppUserMapper appUserMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AppUser> appUser = appUserMapper.findUserByUserName(username);
        if(appUser.isEmpty()){
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetail(appUser.get());
    }
    @Transactional
    public UserDetails loadUserById(Long id) {
        AppUser user = appUserMapper.findById(id).orElseThrow(
                () -> new UsernameNotFoundException("User not found with id : " + id)
        );
        return new CustomUserDetail(user);
    }
}
