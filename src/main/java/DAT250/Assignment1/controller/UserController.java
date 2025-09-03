package DAT250.Assignment1.controller;

import DAT250.Assignment1.manager.PollManager;
import DAT250.Assignment1.model.User;
import org.springframework.web.bind.annotation.*;
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
}
