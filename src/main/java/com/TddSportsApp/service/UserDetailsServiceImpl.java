package com.TddSportsApp.service;

import com.TddSportsApp.models.UserEntity;
import com.TddSportsApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    private final String ADMIN_ROLE = "ADMIN";
    private final String USER_ROLE = "USER";

    @Override
    public UserDetails loadUserByUsername(String usernameAndRole) throws UsernameNotFoundException {

        String usernameAndRoleArray[] = parseUsernameAndRole(usernameAndRole);
        String requestedUsername = usernameAndRoleArray[0];
        String requestedRole = usernameAndRoleArray[1];

        UserEntity userEntity = tryLoadUserFromDB(requestedUsername, requestedRole);

        System.out.println("User loaded: " + userEntity.getUsername());

        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(userEntity.getRole()));

        return new User(userEntity.getUsername(), userEntity.getPassword(), true, true, true, true, authorities);
    }

    private String[] parseUsernameAndRole(String usernameAndRole) {
        String[] usernameAndRoleArray = usernameAndRole.split(";");

        if (usernameAndRoleArray == null || usernameAndRoleArray.length != 2) {
            throw new UsernameNotFoundException("Both username and role must be provided in login request.");
        }
        return usernameAndRoleArray;
    }

    private UserEntity tryLoadUserFromDB(String requestedUsername, String requestedRole) {
        UserEntity userEntity = userRepository.findByUsername(requestedUsername)
                .orElseThrow(
                        () -> new UsernameNotFoundException(
                                "User " + requestedUsername + " does not exists."));

        String roleFromDB = userEntity.getRole();
        System.out.println("Role from DB: " + roleFromDB);
        System.out.println("Role sent: " + requestedRole);
        validateRole(requestedRole, roleFromDB, requestedUsername);
        return userEntity;
    }

    private void validateRole(String requestedRole, String roleFromDB, String requestedUsername) {
        if (!roleFromDB.equals(requestedRole)) {
            throw new UsernameNotFoundException(
                    "User " + requestedUsername + " sent a role it does not have. Role sent: "
                            + requestedRole
                            + ". Correct Role: " + roleFromDB + ".");
        } else if (!(requestedRole.equals(ADMIN_ROLE) || requestedRole.equals(USER_ROLE))) {
            throw new UsernameNotFoundException("User " + requestedUsername + " sent a role that is not valid.");
        }
    }

}
