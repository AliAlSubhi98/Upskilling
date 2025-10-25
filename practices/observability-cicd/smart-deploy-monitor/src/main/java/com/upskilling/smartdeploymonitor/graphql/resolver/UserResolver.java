package com.upskilling.smartdeploymonitor.graphql.resolver;

import com.upskilling.smartdeploymonitor.entity.User;
import com.upskilling.smartdeploymonitor.entity.UserRole;
import com.upskilling.smartdeploymonitor.graphql.dto.UserConnection;
import com.upskilling.smartdeploymonitor.graphql.dto.UserEdge;
import com.upskilling.smartdeploymonitor.graphql.dto.PageInfo;
import com.upskilling.smartdeploymonitor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
public class UserResolver {

    @Autowired
    private UserService userService;

    @QueryMapping
    public User user(@Argument String id) {
        return userService.findById(UUID.fromString(id)).orElse(null);
    }

    @QueryMapping
    public UserConnection users(@Argument Integer first, @Argument String after, 
                               @Argument String search, @Argument String role, 
                               @Argument String sortBy, @Argument String sortDir) {
        // Implementation for paginated user retrieval
        List<User> users = userService.getAllUsers();
        
        // Convert to UserEdge list
        List<UserEdge> edges = users.stream()
            .map(user -> new UserEdge(user, user.getId().toString()))
            .collect(Collectors.toList());
        
        // Create PageInfo
        PageInfo pageInfo = new PageInfo(false, false, null, null);
        
        return new UserConnection(edges, pageInfo, users.size());
    }

    @SchemaMapping(typeName = "User")
    public String id(User user) {
        return user.getId().toString();
    }

    @SchemaMapping(typeName = "User")
    public String email(User user) {
        return user.getEmail();
    }

    @SchemaMapping(typeName = "User")
    public String firstName(User user) {
        return user.getFirstName();
    }

    @SchemaMapping(typeName = "User")
    public String lastName(User user) {
        return user.getLastName();
    }

    @SchemaMapping(typeName = "User")
    public UserRole role(User user) {
        return user.getRole();
    }

    @SchemaMapping(typeName = "User")
    public Boolean isActive(User user) {
        return user.getIsActive();
    }

    @SchemaMapping(typeName = "User")
    public String createdAt(User user) {
        return user.getCreatedAt().toString();
    }

    @SchemaMapping(typeName = "User")
    public String updatedAt(User user) {
        return user.getUpdatedAt().toString();
    }
}
