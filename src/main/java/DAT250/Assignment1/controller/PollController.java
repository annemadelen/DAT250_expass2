package DAT250.Assignment1.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import DAT250.Assignment1.model.Poll;
import DAT250.Assignment1.manager.PollManager;


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
        // Optional<Poll> pollOpt = pollManager.getPoll(id);
        // if (pollOpt.isEmpty()) return ResponseEntity.notFound().build();

        // Poll poll = pollOpt.get();


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

        pollManager.addPoll(poll); // save changes
        return ResponseEntity.ok(poll);
    }

    @GetMapping
    public List<Poll> getAllPolls() {
        return new ArrayList<>(pollManager.getAllPolls().values());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Poll> getPoll(@PathVariable Long id) {
        Poll poll = pollManager.getPoll(id);
        return poll != null ? ResponseEntity.ok(poll) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removePoll(@PathVariable Long id) {
        pollManager.removePoll(id);
        return ResponseEntity.noContent().build();
    }
}
