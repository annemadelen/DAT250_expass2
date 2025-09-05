package DAT250.Assignment1.model;

import java.time.Instant;

public class Vote {
    private Long id;
    private User voter;
    private VoteOption voteOption;
    private Instant publishedAt;

    public Vote() {
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
