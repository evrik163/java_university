const button = document.getElementById('save')
function getUsersInfo () {
    const userRow = document.getElementById('user_row').children
    const userRoles = {}

    for (let item of userRow) {
        const tds = item.children
        let username
        let role
        for (let td of tds) {
            if (td.children.length === 0) {
                username = td.textContent
            }
            else {
                const select = td.children[0]
                for (let option of select) {
                    if (option.selected) {
                        role = option.textContent
                    }
                }
            }
        }
        userRoles[username] = role
    }

    const dataForSend = JSON.stringify(userRoles)

    fetch("/save_roles", {
        method: "POST",
        body: dataForSend,
        headers: {
            "Content-type": "application/json; charset=UTF-8",
        },
    })
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! Status: ${response.status}`);
            }
            window.location.href = '/posts';
        })
        .catch(error => {
            console.error('Error:', error);
        });
}

button.addEventListener('click', getUsersInfo)