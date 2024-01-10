const cookieArr = document.cookie.split("=")
const userId = cookieArr[1];

const submitForm = document.getElementById("games-form")
const gamesContainer = document.getElementById("games-container")

const baseUrl = `http://localhost:8080/api/v1/games`

const headers = {
    'Content-Type': 'application/json'
}

const handleSubmit = async (e) => {
    e.preventDefault()

    let bodyObj  = {
        gameTitle: document.getElementById("gameTitle-input").value,
        publisher: document.getElementById("publisher-input").value,
        genre: document.getElementById("genre-input").value,
        platform: document.getElementById("platform-input").value,
        rating: document.getElementById("rating-input").value
    }
    await addGames(bodyObj);
    document.getElementById("gameTitle-input").value = ''
    document.getElementById("publisher-input").value = ''
    document.getElementById("genre-input").value = ''
    document.getElementById("platform-input").value = ''
    document.getElementById("rating-input").value = ''
}

async function addGames(obj) {
    const response = await fetch(`${baseUrl}/add`, {
        method: "POST",
        body: JSON.stringify(obj),
        headers: headers
    })
        .catch(err => console.error(err.message))
    if (response.status === 200) {
        return getGames()
    }
}

async function getGames() {
    await fetch(`${baseUrl}/`, {
        method:"GET",
        headers:headers
    })
        .then(response => response.json())
        .then(data => createGamesCard(data))
        .catch(err => console.error(err))
}

const createGamesCard = (array) => {
    gamesContainer.innerHTML = ''
    array.forEach(obj => {
        let gamesCard = document.createElement("div")
        gamesCard.classList.add("m-2")
        gamesCard.innerHTML = `
        <div class="card d-flex" style="width: 18rem; height: 18rem;">
            <div class="card-body d-flex flex-column justify-content-between" style="height: fit-content">
                <p class="card-text">${obj.gameTitle}</p>
                <p class="card-text">${obj.publisher}</p>
                <p class="card-text">${obj.genre}</p>
                <p class="card-text">${obj.platform}</p>
                <p class="card-text">${obj.rating}</p>
                <div class="justify-content-between d-flex"
                    <button class="delete-btn">Delete</button>
                </div>                
            </div>
        </div>
    `
        gamesContainer.append(gamesCard);
    })
}

submitForm.addEventListener("submit", handleSubmit)


fetch(`${baseUrl}/`, {
    method:"GET",
    headers:headers
})
    .then(response => response.json())
    .then(data => createGamesCard(data))
    .catch(err => console.error(err))
