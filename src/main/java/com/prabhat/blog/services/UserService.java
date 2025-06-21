package com.prabhat.blog.services;


import com.prabhat.blog.domain.entities.User;

import java.util.UUID;

public interface UserService {
    User getUserById(UUID id);
}
