package com.boris.kata312.payload;

import com.boris.kata312.model.Role;
import com.boris.kata312.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseAdmin {
    private List<User> users;
    private List<Role> roles;
    private User userActive;
    private User userNew;
    private List<Role> roleList;

}
