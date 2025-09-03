package DAT250.Assignment1.controller;

import DAT250.Assignment1.manager.PollManager;
import DAT250.Assignment1.model.Poll;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;


@RestController
@RequestMapping("/polls")
public class PollController {
    
    private final PollManager pollManager;

    public PollController(PollManager pollManager) {
        this.pollManager = pollManager;
    }
    @PostMapping
    public Poll createPoll(@RequestBody Poll poll) {
        return pollManager.addPoll(poll);
    }

    @GetMapping
    public Collection<Poll> getAllPolls() {
        return pollManager.getAllPolls().values();
    }

    @GetMapping("/{id}") 
    public Poll getPoll(@PathVariable Long id) {
        return pollManager.getPoll(id);
    }
}
