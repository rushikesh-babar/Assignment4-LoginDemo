document.addEventListener('DOMContentLoaded', async function () {
    try {
        const response = await fetch("http://localhost:8080/getSessionUser", {
            method: "GET",
            credentials: "include"  // ✅ Include session cookies
        });

        if (!response.ok) {
            throw new Error("User session not found");
        }

        const user = await response.json();
        console.log("Session User:", user);  // ✅ Debug user data

        if (!user || user.type !== "ADMIN") {
            alert("Access denied! Admins only.");
            window.location.href = 'images.html'; // Redirect non-admin users
        }
    } catch (error) {
        console.error("Error fetching session user:", error);
        alert("Session expired! Please login again.");
        window.location.href = "login.html"; // Redirect to login
    }
});


async function fetchMessages() {
    const apiUrl = 'http://localhost:8080/getMessages';
    try {
        const response = await fetch(apiUrl, { 
            method: "GET",
            credentials: "include" // ✅ Send session cookie
        });

        console.log("Response Status:", response.status); // ✅ Debug API status

        if (!response.ok) {
            throw new Error(`HTTP Error status: ${response.status}`);
        }

        const messages = await response.json();
        populateTable(messages);
    } catch (error) {
        console.error(`Error fetching messages`, error);
        alert(`Failed to fetch messages`);
    }
}

document.getElementById('fetch-messages-btn').addEventListener('click', fetchMessages);


function populateTable(messages) {
    const tableBody = document.querySelector('#messages-table tbody');
    tableBody.innerHTML = '';
    
    messages.forEach(message => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${message.name}</td>
            <td>${message.mobile}</td>
            <td>${message.message}</td>
        `;
        tableBody.appendChild(row);
    });
}

document.getElementById('fetch-messages-btn').addEventListener('click', fetchMessages);
