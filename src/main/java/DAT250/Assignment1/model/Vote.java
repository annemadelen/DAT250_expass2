package DAT250.Assignment1.model;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "votes")
public class Vote {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Instant publishedAt;
    
    @ManyToOne
    @JoinColumn(name = "voter_id")
    private User voter;

    @ManyToOne
    @JoinColumn(name = "vote_option_id")
    private VoteOption voteOption;


    public Vote() {
        this.publishedAt = Instant.now();
    }

    public Vote(User voter, VoteOption voteOption) {
        this.voter = voter;
        this.voteOption = voteOption;
        this.publishedAt = Instant.now();
    }

    // Getters and Setters
    public Long getId() { 
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Instant publishedAt) {
        this.publishedAt = publishedAt;
    }

    public User getVoter() {
        return voter;
    }

    public void setVoter(User voter) {
        this.voter = voter;
    }

    public VoteOption getVoteOption() {
        return voteOption;
    }

    public void setVoteOption(VoteOption voteOption) {
        this.voteOption = voteOption;
    }
}
