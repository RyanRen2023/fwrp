const inventory = [];
const itemModal = new bootstrap.Modal(document.getElementById('itemModal'));

function showAddModal() {
    document.getElementById('modalTitle').innerText = 'Add Item';
    document.getElementById('itemForm').reset();
    document.getElementById('itemId').value = '';
    itemModal.show();
}

function showEditModal(id) {
    document.getElementById('modalTitle').innerText = 'Edit Item';
    document.getElementById('itemName').value = inventory[id].name;
    document.getElementById('quantity').value = inventory[id].quantity;
    document.getElementById('expirationDate').value = inventory[id].expirationDate;
    document.getElementById('price').value = inventory[id].price;
    document.getElementById('discount').value = inventory[id].discount;
    document.getElementById('itemId').value = id;
    itemModal.show();
}

function saveItem() {
    const name = document.getElementById('itemName').value;
    const quantity = document.getElementById('quantity').value;
    const expirationDate = document.getElementById('expirationDate').value;
    const price = document.getElementById('price').value;
    const discount = document.getElementById('discount').value;
    const itemId = document.getElementById('itemId').value;

    const item = { name, quantity, expirationDate, price, discount };

    if (itemId) {
        inventory[itemId] = item;
    } else {
        inventory.push(item);
    }

    updateInventoryTable();
    itemModal.hide();
}

function deleteItem(id) {
    inventory.splice(id, 1);
    updateInventoryTable();
}

function updateInventoryTable() {
    const tableBody = document.getElementById('inventory-table');
    tableBody.innerHTML = '';

    inventory.forEach((item, id) => {
        const row = `
            <tr>
                <td>${item.name}</td>
                <td>${item.quantity}</td>
                <td>${item.expirationDate}</td>
                <td>${item.price}</td>
                <td>${item.discount}</td>
                <td>
                    <button class="btn btn-warning btn-sm" onclick="showEditModal(${id})">Edit</button>
                    <button class="btn btn-danger btn-sm" onclick="deleteItem(${id})">Delete</button>
                </td>
            </tr>
        `;
        tableBody.innerHTML += row;
    });
}

function logout() {
    // Implement logout functionality
    alert('Logging out...');
}