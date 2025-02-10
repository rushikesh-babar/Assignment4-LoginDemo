document.addEventListener("DOMContentLoaded", function () {
    console.log("login.js loaded");  // ✅ Debug if JS file is loaded

    document.getElementById("loginForm").addEventListener("submit", function (event) {
        event.preventDefault();  // Prevent default form submission
        console.log("Login form submitted");  // ✅ Debug form submission

        let email = document.getElementById("email").value.trim();
        let password = document.getElementById("password").value.trim();
        let errorMessage = document.getElementById("errorMessage");

        console.log("Email:", email);
        console.log("Password:", password);  // ✅ Debug values

        if (!email || !password) {
            errorMessage.innerText = "Email and password are required!";
            errorMessage.style.display = "block";
            return;
        }

        fetch("http://localhost:8080/login", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            credentials: "include", // Send session cookies
            body: JSON.stringify({ email, password }) // Convert to JSON
        })
        .then(response => {
            console.log("Response status:", response.status);  // ✅ Debug HTTP status
            return response.json();
        })
.then(data => {
    console.log("Login Response:", data); 

    if (data.redirect) {
        localStorage.setItem('user', JSON.stringify(data.user)); // ✅ Store user details
        localStorage.setItem('userType', data.user.type.toLowerCase()); // ✅ Convert to lowercase for consistency
        console.log("Stored User:", localStorage.getItem("user")); 
        console.log("Stored User Type:", localStorage.getItem("userType"));
        window.location.href = data.redirect;  
    } else {
        document.getElementById("errorMessage").innerText = data.error || "Invalid credentials";
        document.getElementById("errorMessage").style.display = "block";
    }
})
})        .catch(error => {
            console.error("Error:", error);
            errorMessage.innerText = "An error occurred. Please try again.";
            errorMessage.style.display = "block";
        });
    });
