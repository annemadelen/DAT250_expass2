package DAT250.Assignment1.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import DAT250.Assignment1.broker.RedisBroker;
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
        Poll createdPoll = pollManager.addPoll(poll);

        String topicName = "poll:" + createdPoll.getId();
        RedisBroker.subscribe(topicName);

        System.out.println("Created poll topic: " + topicName);
        return createdPoll;
    }

    @PostMapping("/{id}/vote/{optionIdx}")
    public ResponseEntity<Poll> vote(
            @PathVariable Long id,
            @PathVariable int optionIdx,
            @RequestBody Map<String, Integer> body
    ) {
        Poll poll = pollManager.getPoll(id);
        if (poll == null) return ResponseEntity.notFound().build();

        int delta = body.getOrDefault("delta", 1);

        if (optionIdx < 0 || optionIdx >= poll.getOptions().size()) {
            return ResponseEntity.badRequest().build();
        }

        poll.getOptions().get(optionIdx).setVotes(
            poll.getOptions().get(optionIdx).getVotes() + delta
        );
        pollManager.addPoll(poll); // Save changes

        // Publish the event
        String topicName = "poll:" + id;
        String message = "Vote on option " + optionIdx + " (+" + delta + ")";
        RedisBroker.publish(topicName, message);

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
