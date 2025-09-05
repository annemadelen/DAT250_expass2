package DAT250.Assignment1;

import DAT250.Assignment1.manager.PollManager;
import DAT250.Assignment1.model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class PollManagerTest {
    
    private PollManager pollManager;

    private User user1;
    private User user2;
    private Poll poll;

    @BeforeEach
    void setup() {
        pollManager = new PollManager();
    }

    @Test 
    void testScenario() { 
        // create a new user
        user1 = pollManager.addUser(new User() {{
            setUsername("user1");
            setEmail("u1@example.com");
        }});

        // list all users (should show 1)
        assertEquals(1, pollManager.getAllUsers().size());

        // create another user
        user2 = pollManager.addUser(new User() {{
            setUsername("user2");
            setEmail("u2@example.com");
        }});

        // list all users again (should show 2)
        assertEquals(2, pollManager.getAllUser().size());

        // user 1 creates a new poll
        poll = pollManager.addPoll(new Poll() {{
            setQuestion("Favorite color?");
            setCreator(user1);
            setPublishedAt(Instant.now());
        }});

        poll.setOptions(List.of(
            new VoteOption() {{ setCaption("Red"); setPresentationOrder(1); }},
            new VoteOption() {{ setCaption("Blue"); setPresentationOrder(2); }}
        ));

        // list polls (should show 1)
        assertEquals(1, pollManager.getAllPolls().size());

        // user 2 votes on the poll
        Vote vote = pollManager.addVote(new Vote() {{
            setVoter(user2);
            setVoteOption(poll.getOptions().get(0)); // votes red
        }});

        // list votes (should show 1)
        assertEquals(1, pollManager.getAllVotes().size());

        // user 2 changes their vote
        vote.setVoteOption(poll.getOptions().get(1)); // changes to blue
        pollManager.addVote(vote);

        // checks if vote is blue
        assertEquals("Blue", pollManager.getVote(vote.getId()).getVoteOption().getCaption());

        // Deletes one poll
        pollManager.getAllPoll().clear();
        pollManager.getAllVotes().clear(); // manual clean up

        // list votes (should be empty)
        assertEquals(0, pollManager.getAllVotes().size());
    }   
}
