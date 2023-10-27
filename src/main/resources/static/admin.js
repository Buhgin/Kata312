
    function fetchAdminInfo() {
    fetch("api/admin")
        .then((response) => response.json())
        .then((data) => {
            let user = data.userActive;
            let userEmailElement = document.getElementById("adminInfo");
            userEmailElement.textContent =
                user.email +
                " with roles: " +
                user.roles.map((role) => role.role).join(", ");

            let usersRows = "";
            let users = data.users;
            users.forEach((user) => {
                let roles = user.roles.map((role) => role.role).join(", ");
                usersRows += `
                        <tr>
                            <td>${user.id}</td>
                            <td>${user.firstName}</td>
                            <td>${user.lastName}</td>
                            <td>${user.email}</td>
                            <td>${roles}</td>
                            <td><button onclick="editUser(${user.id})" class="btn btn-info">Edit</button></td>
                            <td><button onclick="deleteUser(${user.id})" class="btn btn-danger">Delete</button></td>
                        </tr>
                    `;
            });
            $("#usersTableBody").html(usersRows);
        })
        .catch((error) => console.error("Error fetching data: ", error));
}

    function editUser(userId) {
    fetch("api/admin/" + userId)
        .then((response) => {
            if (!response.ok) {
                throw new Error("Network response was not ok");
            }
            return response.json();
        })
        .then((user) => {
            fillModalWithData(user);
            $("#editUserModal").modal("show");
        })
        .catch((error) => {
            console.error("Error fetching user details: ", error);
            alert("Error fetching user details: " + error.message);
        });
}

    function fillModalWithData(user) {
    document.getElementById("editUserId").value = user.id;
    document.getElementById("editFirstName").value = user.firstName;
    document.getElementById("editLastName").value = user.lastName;
    document.getElementById("editEmail").value = user.email;
    EditLoadRoles();
}

    function submitEditUser() {
    const userId = document.getElementById("editUserId").value;
    const firstName = document.getElementById("editFirstName").value;
    const lastName = document.getElementById("editLastName").value;
    const email = document.getElementById("editEmail").value;
    const password = document.getElementById("editPassword").value;
    const roleIds = Array.from(
    document.getElementById("editRolesUser").selectedOptions
    ).map((option) => parseInt(option.value));

    fetch("api/admin/" + userId, {
    method: "PUT",
    headers: {
    "Content-Type": "application/json",
},
    body: JSON.stringify({
    firstName: firstName,
    lastName: lastName,
    email: email,
    password: password,
    roleIds: roleIds,
}),
})
    .then((response) => {
    if (response.ok) {
    fetchAdminInfo();
    $("#editUserModal").modal("hide");
} else {
    alert("Error updating user");
}
})
    .catch((error) => console.error("Error updating user: ", error));
}

    function submitAddUser() {
    let firstName = document.getElementById("firstName").value;
    let lastName = document.getElementById("lastName").value;
    let email = document.getElementById("email").value;
    let password = document.getElementById("password").value;
    let roleIds = Array.from(
    document.getElementById("rolesUser").selectedOptions
    ).map((option) => parseInt(option.value));
    formData = new FormData();
    formData.append("firstName", firstName);
    formData.append("lastName", lastName);
    formData.append("email", email);
    formData.append("password", password);
    roleIds.forEach((roleId) => {
    formData.append("roleIds", roleId);
});
    fetch("api/admin", {
    method: "POST",
    body: formData,
})
    .then((response) => {
    if (response.ok) {
    fetchAdminInfo();
} else {
    alert("Error adding user");
}
})
    .catch((error) => console.error("Error adding user: ", error));
}

    function loadRoles() {
    fetch("api/admin/roles")
        .then((response) => {
            if (!response.ok) {
                throw new Error("Network response was not ok");
            }
            return response.json();
        })
        .then((roles) => {
            const rolesSelect = document.getElementById("rolesUser");

            rolesSelect.innerHTML = "";

            roles.forEach((role) => {
                const option = document.createElement("option");
                option.value = role.id;
                option.textContent = role.role.toString();
                rolesSelect.appendChild(option);
            });
        })
        .catch((error) => {
            console.error(
                "There has been a problem with your fetch operation:",
                error
            );
        });
}

    function deleteUser(userId) {
    fetch("api/admin/delete/" + userId, {
        method: "DELETE",
    })
        .then((response) => {
            if (response.ok) {
                fetchAdminInfo();
            } else {
                alert("Error deleting user");
            }
        })
        .catch((error) => console.error("Error deleting user: ", error));

}

    function EditLoadRoles() {
    fetch("api/admin/roles")
        .then((response) => {
            if (!response.ok) {
                throw new Error("Network response was not ok");
            }
            return response.json();
        })
        .then((roles) => {
            const rolesSelect = document.getElementById("editRolesUser"); // Обновленный ID

            rolesSelect.innerHTML = "";

            roles.forEach((role) => {
                const option = document.createElement("option");
                option.value = role.id;
                option.textContent = role.role.toString();
                rolesSelect.appendChild(option);
            });
        })
        .catch((error) => {
            console.error(
                "There has been a problem with your fetch operation:",
                error
            );
        });
}

    document.addEventListener("DOMContentLoaded", function () {
    fetchAdminInfo();
    loadRoles();
});

