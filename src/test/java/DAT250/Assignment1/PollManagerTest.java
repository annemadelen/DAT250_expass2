package DAT250.Assignment1;

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import DAT250.Assignment1.model.Poll;
import DAT250.Assignment1.model.User;
import DAT250.Assignment1.model.Vote;
import DAT250.Assignment1.model.VoteOption;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PollManagerTest {
    
    @Autowired
    private TestRestTemplate restTemplate;

    private User user1;
    private User user2;
    private Poll poll;

    @Test 
    void testScenario() { 
        // create a new user
        User userReq1 = new User();
        userReq1.setUsername("user1");
        userReq1.setEmail("u1@example.com");
        
        user1 = restTemplate.postForObject("/users", userReq1, User.class);

        // list all users (should show 1)
        User[] users = restTemplate.getForObject("/users", User[].class);
        assertEquals(1, users.length);

        // create another user
        User userReq2 = new User();
        userReq2.setUsername("user2");
        userReq2.setEmail("u2@example.com");
        
        user2 = restTemplate.postForObject("/users", userReq2, User.class);


        // list all users again (should show 2)
        users = restTemplate.getForObject("/users", User[].class);
        assertEquals(2, users.length);

        // user 1 creates a new poll
        Poll pollReq = new Poll();
        pollReq.setQuestion("Favorite color?");
        pollReq.setCreator(user1);
        pollReq.setPublishedAt(Instant.now());
        
        VoteOption option1 = new VoteOption();
        option1.setCaption("Red");
        option1.setPresentationOrder(1); 
        VoteOption option2 = new VoteOption();
        option2.setCaption("Blue");
        option2.setPresentationOrder(2);
        
        pollReq.setOptions(List.of(option1, option2));
        poll = restTemplate.postForObject("/polls", pollReq, Poll.class);
        
        Poll[] polls = restTemplate.getForObject("/polls", Poll[].class);
        assertEquals(1, polls.length);

        //user 2 votes on the poll
        Vote voteReq = new Vote();
        voteReq.setVoter(user2);
        voteReq.setVoteOption(poll.getOptions().get(0)); 

        Vote vote = restTemplate.postForObject("/votes", voteReq, Vote.class);
    
        // user 2 changes vote 
        vote.setVoteOption(poll.getOptions().get(1));
        restTemplate.put("/votes/" + vote.getId(), vote);

        //checks updated vote
        Vote updatedVote = restTemplate.getForObject("/votes/" + vote.getId(), Vote.class);
        assertEquals("Blue", updatedVote.getVoteOption().getCaption());

        //delete poll 
        restTemplate.delete("/polls/" + poll.getId());

        Vote[] votes = restTemplate.getForObject("/votes", Vote[].class);
        assertEquals(0, votes.length);

    }   
}
