const cookieArr = document.cookie.split("=")
const userId = cookieArr[1];

const baseUrl = "http://localhost:8080/api/v1"

const headers = {
    'Content-Type': 'application/json'
}

async function getFavorites(userId) {
    await fetch(`${baseUrl}/users/${userId}`, {
        method: "GET",
        headers: headers
    })
        .then(response => response.json())
        .then(data => createFavoritesCards(data))
        .catch(err => console.error(err))
}

const createFavoritesCards = (array) => {
    favoritesContainer.innerHTML = ''
    array.forEach(obj => {
        let favoritesCard = document.createElement("div")
        favoritesCard.classList.add("m-2")
        favoritesCard.innerHTML = ``
    })
}