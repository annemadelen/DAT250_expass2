import React, { useState } from 'react'

export default function PollCreation({ onCreate }) {
    const [question, setQuestion] = useState("")
    const [options, setOptions] = useState([""])

    const handleAddOption = () => {
        setOptions([...options, ""])
    }

    const handleOptionChange = (index, value) => {
        const updated = [...options]
        updated[index] = value
        setOptions(updated)
    }

    const handleSubmit = (e) => {
        e.preventDefault();
        if (!question.trim()) return; // prevent empty questions
        onCreate({ question, options })
        setQuestion("")
        setOptions([""])
    }

    return (
        <form onSubmit={handleSubmit} className="poll-form">
            <h2>Create a new poll</h2>
            <input
                type="text"
                placeholder="Poll question"
                value={question}
                onChange={(e) => setQuestion(e.target.value)}
                required
            />

        {options.map((opt, idx) => (
            <input
                key={idx}
                type="text"
                placeholder={`Option ${idx + 1}`}
                value={opt}
                onChange={(e) => handleOptionChange(idx, e.target.value)}
                required
            />
        ))}

        <button type="button" onClick={handleAddOption}>
            + Add Option
        </button>
        <button type="submit">Create Poll</button>
    </form>
    )
}