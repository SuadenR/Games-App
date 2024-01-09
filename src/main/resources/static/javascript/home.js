const cookieArr = document.cookie.split("=")
const userId = cookieArr[1];

const favoritesContainer = document.getElementById("favorites_container")

const baseUrl = `http://localhost:8080/api/v1/favorites/games/`

const headers = {
    'Content-Type': 'application/json'
}

function handleLogout(){
    let c = document.cookie.split(";");
    for(let i in c){
        document.cookie = /^[^=]+/.exec(c[i])[0]+"=;expires=Thu, 01 Jan 1970 00:00:00 GMT"
    }
}

async function addFavorites(obj) {
    const response = await fetch(`${baseUrl}/${userId}`,{
        method: "POST",
        body: JSON.stringify(obj),
        headers: headers
    })
        .catch(err => console.error(err.message))
    if (response.status === 200) {
        return getFavorites(userId)
    }
}

async function getFavorites(userId) {
    await fetch(`${baseUrl}/${userId}`, {
        method:"GET",
        headers:headers
    })
        .then(response => response.json())
        .then(data => createFavoritesCard(data))
        .catch(err => console.error(err))
}

const createFavoritesCard = (array) => {
    favoritesContainer.innerHTML = ''
    array.forEach(obj => {
        let favoritesCard = document.createElement("div")
        favoritesCard.classList.add("m-2")
        favoritesCard.innerHTML = `
        <div class="card d-flex" style="width: 18rem; height: 18rem;">
            <div class="card-body d-flex flex-column justify-content-between" style="height: fit-content">
                <p class="card-text">${obj.gameTitle}</p>
                <p class="card-text">${obj.publisher}</p>
                <p class="card-text">${obj.genre}</p>
                <p class="card-text">${obj.platform}</p>
                <p class="card-text">${obj.rating}</p>
                <div class="d-flex justify-content-between">
                    <button class="delete-btn">Delete</button>                
            </div>
        </div>
    `
        favoritesContainer.append(favoritesCard);
    })
}

getFavorites(userId);

submitForm.addEventListener("submit", handleSubmit)
