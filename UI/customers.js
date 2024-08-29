document.addEventListener('DOMContentLoaded', () => {
    const customerTableBody = document.querySelector('#customerTable tbody');
    const addCustomerBtn = document.getElementById('addCustomerBtn');
    const syncBtn = document.getElementById('syncBtn');
    const searchBtn = document.getElementById('searchBtn');
    const searchDropdown = document.getElementById('searchDropdown');
    const searchInput = document.getElementById('searchInput');

    // Fetch customers and populate the table
    function fetchCustomers() {
        fetch('http://localhost:8080/api/customers', {
            headers: {
                'Authorization': 'Bearer ' + localStorage.getItem('jwtToken')
            }
        })
        .then(response => response.json())
        .then(data => {
            customerTableBody.innerHTML = '';
            data.forEach(customer => {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td>${customer.first_name}</td>
                    <td>${customer.last_name}</td>
                    <td>${customer.address}</td>
                    <td>${customer.city}</td>
                    <td>${customer.state}</td>
                    <td>${customer.email}</td>
                    <td>${customer.phone}</td>
                    <td>
                        <button class="edit-btn" onclick="editCustomer('${customer.uuid}')">Edit</button>
                        <button class="delete-btn" onclick="deleteCustomer('${customer.uuid}')">Delete</button>
                    </td>
                `;
                customerTableBody.appendChild(row);
            });
        });
    }

    // Edit customer functionality
    window.editCustomer = (uuid) => {
        // Redirect to the add customer page with UUID as query parameter for editing
        window.location.href = `add-customer.html?uuid=${uuid}`;
    };

    // Delete customer functionality
    window.deleteCustomer = (uuid) => {
        fetch(`http://localhost:8080/api/${uuid}`, {
            method: 'DELETE',
            headers: {
                'Authorization': 'Bearer ' + localStorage.getItem('jwtToken')
            }
        })
        .then(() => {
            fetchCustomers();
        });
    };

    // Search customers functionality
    searchBtn.addEventListener('click', () => {
        const searchBy = searchDropdown.value;
        const searchTerm = searchInput.value;

        fetch(`http://localhost:8080/api/customers?searchBy=${searchBy}&searchTerm=${searchTerm}`, {
            headers: {
                'Authorization': 'Bearer ' + localStorage.getItem('jwtToken')
            }
        })
        .then(response => response.json())
        .then(data => {
            customerTableBody.innerHTML = '';
            data.forEach(customer => {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td>${customer.first_name}</td>
                    <td>${customer.last_name}</td>
                    <td>${customer.address}</td>
                    <td>${customer.city}</td>
                    <td>${customer.state}</td>
                    <td>${customer.email}</td>
                    <td>${customer.phone}</td>
                    <td>
                        <button class="edit-btn" onclick="editCustomer('${customer.uuid}')">Edit</button>
                        <button class="delete-btn" onclick="deleteCustomer('${customer.uuid}')">Delete</button>
                    </td>
                `;
                customerTableBody.appendChild(row);
            });
        });
    });

    // Sync customers functionality
    syncBtn.addEventListener('click', () => {
        fetch('http://localhost:8080/api/sync', {
            method: 'POST',
            headers: {
                'Authorization': 'Bearer ' + localStorage.getItem('jwtToken')
            }
        })
        .then(() => {
            fetchCustomers();
        });
    });

    // Initial fetch of customers
    fetchCustomers();
});
