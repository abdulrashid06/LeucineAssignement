document.getElementById('loginForm').addEventListener('submit', async function(event) {
    event.preventDefault(); // Prevent the default form submission

    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;

    console.log(email, password);

    try {
        const response = await fetch('http://localhost:8080/api/auth/logIn', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ username: email, password: password })
        });

        const data = await response.json();

        if (response.ok) {
            console.log(data); // Console log the response for debugging

            const { token, user } = data;
            const { role } = user;

            // Store the token in localStorage or sessionStorage
            localStorage.setItem('token', token);

            // Redirect based on the role
            if (role === 'STUDENT') {
                window.location.href = 'StudentDashboard.html';
            } else if (role === 'FACULTY_MEMBER') {
                window.location.href = 'FacultyDashboard.html';
            } else if (role === 'ADMINISTRATOR') {
                window.location.href = 'AdminDashboard.html';
            }
        } else {
            // Handle errors
            alert(data.message || 'Invalid email or password');
        }
    } catch (error) {
        console.error('Error:', error);
        alert('An error occurred. Please try again.');
    }
});
