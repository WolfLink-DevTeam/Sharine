package org.wolflink.sharine.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wolflink.sharine.entity.User;
import org.wolflink.sharine.repository.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController extends RestfulController<User,Long> {
    public UserController(UserRepository repository) {
        super(repository);
    }
}
