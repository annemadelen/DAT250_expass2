package DAT250.Assignment1.manager;

import DAT250.Assignment1.model.*;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.HashMap;
import java.util.Optional;

@Component
public class PollManager {
    
    private final Map<Long, User> users = new HashMap<>();
    private final Map<Long, Poll> polls = new HashMap<>();
    private final Map<Long, Vote> votes = new HashMap<>();

    private Long userIdCounter = 1L;
    private Long pollIdCounter = 1L;
    private Long voteIdCounter = 1L;

    // User
    public User addUser(User user) {
        user.setId(userIdCounter++);
        users.put(user.getId(), user);
        return user;
    }

    public Optional getUser(Long id) {
        return Optional.ofNullable(users.get(id));
    }

    public Map<Long, User> getAllUsers() {
        return users;
    }

    // Poll
    public Poll addPoll(Poll poll) {
        poll.setId(pollIdCounter++);
        polls.put(poll.getId(), poll);
        return poll;
    }

    public Poll getPoll(Long id) {
        return (polls.get(id));
    }

    public Map<Long, Poll> getAllPolls() {
        return polls;
    }

    // Vote
    public Vote addVote(Vote vote) {
        vote.setId(voteIdCounter++);
        votes.put(vote.getId(), vote);
        return vote;
    }

    public Optional<Vote> getVote(Long id) {
        return Optional.ofNullable(votes.get(id));
    }

    public Map<Long, Vote> getAllVotes() {
        return votes;
    }
}
