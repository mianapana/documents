package ro.documentsmanager.documents.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ro.documentsmanager.documents.Entity.User;
import ro.documentsmanager.documents.Service.UserService;

@RestController

public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/getAll")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/user/add")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping("user/{id}")
    public User getUserById(@PathVariable int id) {
        return userService.getById(id);
    }

    @GetMapping("user/by-email/{email}")
    public User getUserByEmail(@PathVariable String email) {
        return userService.getByEmail(email);
    }

}
