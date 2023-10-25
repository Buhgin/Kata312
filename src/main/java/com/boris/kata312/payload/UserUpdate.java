package com.boris.kata312.payload;


import java.util.List;



public record UserUpdate(String firstName, String lastName, String email, String password, List<Long> roleIds) {
}
