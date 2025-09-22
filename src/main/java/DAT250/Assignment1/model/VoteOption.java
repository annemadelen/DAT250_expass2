package DAT250.Assignment1.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "vote_options")
public class VoteOption {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String caption;
    private int presentationOrder;
    private int votes = 0;

    @ManyToOne
    @JoinColumn(name = "poll_id")
    private Poll poll;

    @OneToMany(mappedBy = "voteOption", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vote> votesCast = new ArrayList<>();

    public VoteOption() {}

    public VoteOption(String caption, Poll poll, int order) {
        this.caption = caption;
        this.poll = poll;
        this.presentationOrder = order;
    }

    // Getters and Setters
    public Long getId() { 
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public int getPresentationOrder() {
        return presentationOrder;
    }

    public void setPresentationOrder(int presentationOrder) {
        this.presentationOrder = presentationOrder;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public List<Vote> getVotesCast() {
        return votesCast;
    }

    public void setVotesCast(List <Vote> votesCast) {
        this.votesCast = votesCast;
    }
}
