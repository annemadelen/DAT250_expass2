package DAT250.Assignment1.controller;

import java.util.Collection;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;


import DAT250.Assignment1.manager.PollManager;
import DAT250.Assignment1.model.Poll;


@RestController
@CrossOrigin(origins = "http://localhost:5173/")
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

    @PostMapping("/{id}/vote/{optionIdx}")
    public ResponseEntity<Poll> vote(
        @PathVariable Long id,
        @PathVariable int optionIdx,
        @RequestBody Map<String, Integer> body
    ) {
        Poll poll = pollManager.getPoll(id);
        if (poll == null) {
            return ResponseEntity.notFound().build();
        }

        int delta = body.getOrDefault("delta", 0);
        if (optionIdx < 0 || optionIdx >= poll.getOptions().size()) {
            return ResponseEntity.badRequest().build();
        }

        poll.getOptions().get(optionIdx).setVotes(
            poll.getOptions().get(optionIdx).getVotes() + delta
        );
        return ResponseEntity.ok(poll);
    }



    @GetMapping
    public Collection<Poll> getAllPolls() {
        return pollManager.getAllPolls().values();
    }

    @GetMapping("/{id}") 
    public Poll getPoll(@PathVariable Long id) {
        return pollManager.getPoll(id);
    }

    @DeleteMapping("/{id}")
    public void removePoll(@PathVariable Long id) {
        pollManager.removePoll(id);
    }
}
