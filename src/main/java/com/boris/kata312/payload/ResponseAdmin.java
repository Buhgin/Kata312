package com.boris.kata312.payload;

import com.boris.kata312.model.Role;
import com.boris.kata312.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

public record ResponseAdmin(List<User> users, List<Role> roles, User userActive) {


}
