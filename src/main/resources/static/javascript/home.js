const cookieArr = document.cookie.split("=")
const userId = cookieArr[1];

const favoritesContainer = document.getElementById("favorites_container")

const baseUrl = `http://localhost:8080/api/v1/favorites`

const headers = {
    'Content-Type': 'application/json'
}

async function getFavorites(userId) {
    await fetch(`${baseUrl}/games/${userId}`, {
        method:"GET",
        headers:headers
    })
        .then(response => response.json())
        .then(data => createFavoritesCard(data))
        .catch(err => console.error(err))
}

const createFavoritesCard = (array) => {
    console.log(array)
    favoritesContainer.innerHTML = ''
    array.forEach(obj => {
        let favoritesCard = document.createElement("div")
        favoritesCard.classList.add("m-2")
        favoritesCard.innerHTML = `
        <div id="favorites-card" class="card d-flex" style="width: 18rem; height: 18rem;" xmlns="http://www.w3.org/1999/html" xmlns="http://www.w3.org/1999/html">
            <div class="card-body d-flex flex-column justify-content-between" style="height: fit-content">
                <p class="card-text">${obj.gameTitle}</p>
                <p class="card-text">${obj.publisher}</p>
                <p class="card-text">${obj.genre}</p>
                <p class="card-text">${obj.platform}</p>
                <p class="card-text">${obj.rating}</p>
            </div>
        </div>
    `
        favoritesContainer.append(favoritesCard);
    })
}

async function addFavoritesToUser() {
    const response = await fetch(`${baseUrl}/add/${userId}`,{
        method: "POST",
        headers: headers
    })
        .catch(err => console.error(err.message))
    if (response.status === 200) {
        return getFavorites(userId)
    }
}

async function deleteFavorites(favoritesId) {
    const response = await fetch(`${baseUrl}/${favoritesId}`, {
        method: "DELETE",
        body: getFavoritesById(favoritesId),
        headers: headers
    })
        .catch(err => console.error(err));
    if (response.status === 200) {
        return getFavorites(userId);
    }
}

async function getFavoritesById(favoritesId) {
    await fetch(`${baseUrl}/${favoritesId}`, {
        method:"GET",
        headers:headers
    })
        .then(res => res.json())
}
async function handleDeleteAndAddFavorites() {
    try {
        await deleteFavorites();
        await addFavoritesToUser();
    }
    catch (error) {
        console.error(error);
    }
}


fetch(`${baseUrl}/games/${userId}`, {
    method:"GET",
    headers:headers
})
    .then(response => response.json())
    .then(data => createFavoritesCard(data))
    .catch(err => console.error(err))
