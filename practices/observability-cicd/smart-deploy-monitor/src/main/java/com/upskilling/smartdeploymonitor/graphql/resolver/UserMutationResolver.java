package com.upskilling.smartdeploymonitor.graphql.resolver;

import com.upskilling.smartdeploymonitor.entity.User;
import com.upskilling.smartdeploymonitor.entity.UserRole;
import com.upskilling.smartdeploymonitor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
public class UserMutationResolver {

    @Autowired
    private UserService userService;

    @MutationMapping
    public User createUser(@Argument String email, @Argument String password, 
                          @Argument String firstName, @Argument String lastName, 
                          @Argument UserRole role) {
        if (role == UserRole.ADMIN) {
            return userService.createAdminUser(email, password, firstName, lastName);
        } else {
            return userService.createUser(email, password, firstName, lastName);
        }
    }

    @MutationMapping
    public User updateUser(@Argument String id, @Argument String email, 
                           @Argument String firstName, @Argument String lastName, 
                           @Argument UserRole role, @Argument Boolean isActive) {
        UUID userId = UUID.fromString(id);
        
        // Update basic user info
        if (firstName != null || lastName != null || email != null) {
            userService.updateUser(userId, firstName, lastName, email);
        }
        
        // Handle activation/deactivation
        if (isActive != null) {
            if (isActive) {
                userService.activateUser(userId);
            } else {
                userService.deactivateUser(userId);
            }
        }
        
        return userService.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @MutationMapping
    public Boolean deleteUser(@Argument String id) {
        try {
            userService.deleteUser(UUID.fromString(id));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
