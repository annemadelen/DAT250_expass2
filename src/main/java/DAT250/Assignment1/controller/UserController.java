package DAT250.Assignment1.controller;

import java.util.Collection;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import DAT250.Assignment1.manager.PollManager;
import DAT250.Assignment1.model.User;
// import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("/users")
public class UserController {
    
    private final PollManager pollManager;

    public UserController(PollManager pollManager) {
        this.pollManager = pollManager;
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return pollManager.addUser(user);
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return pollManager.getUser(id).orElse(null);
    }

    @GetMapping
    public Collection<User> getAllUsers() {
        return pollManager.getAllUsers().values();
    }
}
