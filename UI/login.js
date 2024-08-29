document.getElementById('loginForm').addEventListener('submit', async function(event) {
    event.preventDefault();
    
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;
    
    try {
        const response = await fetch('http://localhost:8080/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ email, password })
        });

        console.log("THis is response " +response)
        
        if (response.ok) {
            const token = await response.text();
            console.log("THis is data:= "+token)
            localStorage.setItem('jwtToken', token); // Store JWT token in localStorage
            window.location.href = 'customers.html'; // Redirect to customers page
        } else {
            const errorData = await response.json();
            document.getElementById('error-message').textContent = errorData.message;
        }
    } catch (error) {
        console.error('Error:', error);
    }
});
