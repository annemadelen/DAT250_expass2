package DAT250.Assignment1.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import DAT250.Assignment1.model.Poll;


@RestController
@CrossOrigin(origins = "http://localhost:5173/")
@RequestMapping("/polls")
public class PollController {

    private final PollService pollService;

    public PollController(PollService pollService) {
        this.pollService = pollService;
    }

    @PostMapping
    public Poll createPoll(@RequestBody Poll poll) {
        return pollService.addPoll(poll);
    }

    @PostMapping("/{id}/vote/{optionIdx}")
    public ResponseEntity<Poll> vote(
            @PathVariable Long id,
            @PathVariable int optionIdx,
            @RequestBody Map<String, Integer> body
    ) {
        Optional<Poll> pollOpt = pollService.getPoll(id);
        if (pollOpt.isEmpty()) return ResponseEntity.notFound().build();

        Poll poll = pollOpt.get();
        int delta = body.getOrDefault("delta", 0);

        if (optionIdx < 0 || optionIdx >= poll.getOptions().size()) {
            return ResponseEntity.badRequest().build();
        }

        poll.getOptions().get(optionIdx).setVotes(
                poll.getOptions().get(optionIdx).getVotes() + delta
        );

        pollService.addPoll(poll); // save changes
        return ResponseEntity.ok(poll);
    }

    @GetMapping
    public List<Poll> getAllPolls() {
        return pollService.getAllPolls();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Poll> getPoll(@PathVariable Long id) {
        return pollService.getPoll(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removePoll(@PathVariable Long id) {
        pollService.removePoll(id);
        return ResponseEntity.noContent().build();
    }
}
