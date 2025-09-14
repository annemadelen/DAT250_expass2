import React, { useState } from "react";

export default function Login({ onLogin }) {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [isSignup, setIsSignup] = useState(false);

    const handleSubmit = async (e) => {
        e.preventDefault();
        const endpoint = isSignup ? "signup" : "login";
        
        const response = await fetch(`/users/${endpoint}`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ username, password }),
        });

        const user = await response.json();
        if (!user || !user.id) {
            alert("Invalid username or password");
            return;
        }
        onLogin(user);
    };

    return (
        <form onSubmit={handleSubmit} className="login-form">
            <h2>{isSignup ? "Sign Up" : "Login"}</h2>
            <input
                type="text"
                placeholder="Username"
                value={username}
                onChange={(e) => setUsername(e.target.value)}
                required
            />
            <input
                type="password"
                placeholder="Password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                required
            />
            <button type="submit">{isSignup ? "Sign Up" : "Login"}</button>
            <p>
                {isSignup ? "Already have an account?": "No account yet?"}{" "}
                <button type="button" onClick={() => setIsSignup(!isSignup)}>
                    {isSignup ? "Login" : "Sign Up"}
                </button>
            </p>
        </form>
    )
}