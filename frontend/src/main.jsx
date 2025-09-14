import React, { useState } from 'react'
import { useEffect } from 'react'
import ReactDOM from 'react-dom/client'
import Poll from './components/Poll'
import PollCreation from './components/PollCreation'
import Login from './components/Login'
import './index.css'

function App() {
  const [polls, setPolls] = useState([]);
  const [user, setUser] = useState(null);

  // Load polls on mount
  useEffect(() => {
    fetch('/polls')
    .then(res => res.json())
    .then(data => setPolls(data))
    .catch(() => setPolls([]));
  }, []);

  // Create a new poll
  const handleCreatePoll = async (newPoll) => {
    if (!user) {
      alert("Please log in to create a poll");
      return;
    }

    const formattedPoll = {
      question: newPoll.question,
      options: newPoll.options
        .filter(opt => opt.trim() !== "") // remove empty options
        .map(opt => ({ caption: opt.trim(), votes: 0 })), // make objects
      creatorId: user.id,
    };

    const response = await fetch('/polls', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(formattedPoll)
    })

    const savedPoll = await response.json()
    setPolls([...polls, savedPoll])
  }

  // Handle upvote/downvote
  const handleVote = async (pollId, optionIdx, delta) => {
    if(!user) {
      alert("Please log in to vote");
      return;
    }

    const response = await fetch(
      `/polls/${pollId}/vote/${optionIdx}`,
     {
      method:'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ delta })
     }
    );

    const updatedPoll = await response.json()
    setPolls(polls.map(p => (p.id === updatedPoll.id ? updatedPoll : p)))
  };

  return (
    <div>
      <h1>Poll App</h1>

      {!user ? (
        <Login onLogin={setUser} />
      ) : (
        <div>
          <p>Welcome, <strong>{user.username}</strong> 🎉 </p>
          <PollCreation onCreate={handleCreatePoll} />
          {polls.map((poll) => (
            <Poll key={poll.id} poll={poll} onVote={handleVote} />
          ))}
          </div>
      )}
      </div>
  );
}


ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <App />
  </React.StrictMode>,
)
