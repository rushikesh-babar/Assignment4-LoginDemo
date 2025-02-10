function initMap() {
    var location = { lat: 18.518574, lng: 73.934799 };
    var map = new google.maps.Map(document.getElementById('googleMap'), {
        zoom: 15,
        center: location
    });
    var marker = new google.maps.Marker({
        position: location,
        map: map
    });
}

function loadScript() {
    var script = document.createElement('script');
    script.src = "https://maps.googleapis.com/maps/api/js?key=AIzaSyBEckMcSVFPEr-8Id9gBqSxM4slNdxNPts&callback=initMap";
    script.async = true;
    document.body.appendChild(script);
}
window.onload = loadScript;

document.getElementById("submitBtn").addEventListener("click", async () => {
    const name = document.getElementById("name").value.trim();
    const mobile = document.getElementById("mobile").value.trim();
    const message = document.getElementById("message").value.trim();
    
    
    const nameRegex = /^[A-Za-z]+(\s[A-Za-z]+)*$/; 
    const mobileRegex = /^\d{10}$/;    
    const messageRegex =/^[A-Za-z0-9\s.,!@#$%^&*()_+\-={}[\]:;"'<>,.?\/\\|`~]+$/;

    if (!name || !mobile || !message) {
        alert("Please fill in all fields.");
        return;
    }
    if(!nameRegex.test(name)){
		alert(`Name should contain only letters.`);
		return;
	}
	if(!mobileRegex.test(mobile)){
		alert(`Mobile number should contain only numbers and exactly 10 digits .`);
		return;
		
	}if(!messageRegex.test(message)){
		alert(`Message should contain only letters.`);
		return;
	}
	
    try {
        const response = await fetch("http://localhost:8080/save", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({ name, mobile, message }),
        });

        if (response.ok) {
            alert("Contact added successfully!");
            document.getElementById("contactForm").reset();
        } else {
            alert("Failed to add contact.");
        }
    } catch (error) {
        console.error("Error:", error);
        alert("An error occurred. Please try again.");
    }
});
