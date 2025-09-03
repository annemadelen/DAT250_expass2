package DAT250.Assignment1.model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Poll {
    
    private Long id;
    private String question;
    private Instant publishedAt;
    private Instant validUntil;

    private User creator; // Creator of the Poll
    private List<VoteOption> options = new ArrayList<>(); // 

    public Poll() {} 

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }
    
    public void setQuestion(String question) {
        this.question = question;
    }

    public Instant getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Instant publishedAt) {
        this.publishedAt = publishedAt;
    }

    public Instant getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(Instant validUntil) {
        this.validUntil =validUntil;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public List<VoteOption> getOptions() {
        return options;
    } 

    public void setOptions(List<VoteOption> options) {
        this.options = options;
    }
}
