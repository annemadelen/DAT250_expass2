import React from 'react'


export default function Poll({ poll, onVote }) {
    return (
        <div className="poll">
            <h2>Poll #{poll.id}</h2>
            <blockquote>{poll.question}</blockquote>

            <div className="options">
                {poll.options.map((opt, idx) => (
                    <div key ={idx} className="option">
                        <span className="caption">{opt.caption}</span>
                        <div className="buttons">
                            <button 
                                onClick={() => onVote(poll.id, idx, 1)} 
                                className="upvote"
                                >
                                👍
                                </button>
                            <button 
                                onClick={() => onVote(poll.id, idx, -1)} 
                                className="downvote"
                                >
                                👎
                                </button>
                            <span className="votes">{opt.votes} Votes</span>
                        </div>
                    </div>
                ))}
            </div>
        </div>
    );
}