package DAT250.Assignment1.controller;

import DAT250.Assignment1.manager.PollManager;
import DAT250.Assignment1.model.Vote;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;


@RestController
@RequestMapping("/votes")
public class VoteController {
    
    private final PollManager pollManager;

    public VoteController(PollManager pollManager) {
        this.pollManager = pollManager;
    }

    @PostMapping
    public Vote createVote(@RequestBody Vote vote) {
        return pollManager.addVote(vote);
    }

    @GetMapping
    public Collection<Vote> getAllVotes() {
        return pollManager.getAllVotes().values();
    }

    @GetMapping("/{id}")
    public Vote getVote(@PathVariable Long id) {
        return pollManager.getVote(id).orElse(null);
    }
}
