const button = document.getElementById('save')
function getPostInfo () {
    const text = document.getElementById('topic').value
    const topicText = document.getElementById('post_text').value
    const checkboxs = document.getElementById('checkboxes').children
    const choosenUsers = []


    for (let item of checkboxs) {
        const isChecked = item.children[0].checked
        if (isChecked){
            choosenUsers.push(item.children[1].textContent)
        }
    }

    const dataForSend = JSON.stringify({
        topic: text,
        postText: topicText,
        users: choosenUsers,
    })

    fetch("/save_post", {
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

button.addEventListener('click', getPostInfo)