const itemModal = new bootstrap.Modal(document.getElementById('itemModal'));

function showAddModal() {
    document.getElementById('modalTitle').innerText = 'Add Item';
    document.getElementById('itemForm').reset();
    document.getElementById('itemId').value = '';
    itemModal.show();
}

function showEditModal(id) {
    document.getElementById('modalTitle').innerText = 'Edit Item';
    document.getElementById('itemName').value = inventory[id].fname;
    document.getElementById('quantity').value = inventory[id].inventory;
    document.getElementById('expirationDate').value = inventory[id].expiration;
    document.getElementById('price').value = inventory[id].price;
    document.getElementById('discount').value = inventory[id].discount;
    document.getElementById('itemId').value = id;
    itemModal.show();
}

function saveItem() {
    const fname = document.getElementById('itemName').value;
    const inventory = document.getElementById('quantity').value;
    const expiration = document.getElementById('expirationDate').value;
    const price = document.getElementById('price').value;
    const discount = document.getElementById('discount').value;
    const itemId = document.getElementById('itemId').value;

    const item = { fname, inventory, expiration, price, discount };

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
                <td>${item.fname}</td>
                <td>${item.inventory}</td>
                <td>${item.expiration}</td>
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

