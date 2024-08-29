document.getElementById('create-customer-form').addEventListener('submit', function(event) {
    event.preventDefault();
    const customer = {
        uuid: document.getElementById('uuid').value,
        first_name: document.getElementById('first_name').value,
        last_name: document.getElementById('last_name').value,
        street: document.getElementById('street').value,
        address: document.getElementById('address').value,
        city: document.getElementById('city').value,
        state: document.getElementById('state').value,
        email: document.getElementById('email').value,
        phone: document.getElementById('phone').value
    };
    fetch('http://localhost:8080/api/create', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(customer)
    })
    .then(response => response.json())
    .then(data => alert('Customer created!'))
    .catch(error => console.error('Error:', error));
});

document.getElementById('update-customer-form').addEventListener('submit', function(event) {
    event.preventDefault();
    const id = document.getElementById('update-id').value;
    const customer = {
        first_name: document.getElementById('update-first_name').value,
        last_name: document.getElementById('update-last_name').value,
        street: document.getElementById('update-street').value,
        address: document.getElementById('update-address').value,
        city: document.getElementById('update-city').value,
        state: document.getElementById('update-state').value,
        email: document.getElementById('update-email').value,
        phone: document.getElementById('update-phone').value
    };
    fetch(`http://localhost:8080/api/${id}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(customer)
    })
    .then(response => response.json())
    .then(data => alert('Customer updated!'))
    .catch(error => console.error('Error:', error));
});

function getCustomer() {
    const id = document.getElementById('get-id').value;
    fetch(`http://localhost:8080/api/${id}`)
        .then(response => response.json())
        .then(data => document.getElementById('customer-details').textContent = JSON.stringify(data, null, 2))
        .catch(error => console.error('Error:', error));
}

function deleteCustomer() {
    const id = document.getElementById('delete-id').value;
    fetch(`http://localhost:8080/api/${id}`, { method: 'DELETE' })
        .then(response => response.text())
        .then(data => alert(data))
        .catch(error => console.error('Error:', error));
}

document.getElementById('search-customers-form').addEventListener('submit', function(event) {
    event.preventDefault();
    const searchBy = document.getElementById('searchBy').value;
    const searchTerm = document.getElementById('searchTerm').value;
    const page = document.getElementById('page').value;
    const size = document.getElementById('size').value;
    const sortBy = document.getElementById('sortBy').value;
    const sortDir = document.getElementById('sortDir').value;
    fetch(`http://localhost:8080/api/customers?searchBy=${searchBy}&searchTerm=${searchTerm}&page=${page}&size=${size}&sortBy=${sortBy}&sortDir=${sortDir}`)
        .then(response => response.json())
        .then(data => document.getElementById('customers-list').textContent = JSON.stringify(data, null, 2))
        .catch(error => console.error('Error:', error));
});

function syncCustomers() {
    fetch('http://localhost:8080/api/sync', { method: 'POST' })
        .then(response => response.text())
        .then(data => document.getElementById('sync-status').textContent = data)
        .catch(error => console.error('Error:', error));
}
