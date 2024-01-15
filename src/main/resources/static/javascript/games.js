const cookieArr = document.cookie.split("=")
const userId = cookieArr[1];


const submitForm = document.getElementById("games-form")
const gamesContainer = document.getElementById("games-container")

//Modal
let gameTitleEdit = document.getElementById("gameTitle-input-edit")
let publisherEdit = document.getElementById("publisher-input-edit")
let genreEdit = document.getElementById("genre-input-edit")
let platformEdit = document.getElementById("platform-input-edit")
let ratingEdit = document.getElementById("rating-input-edit")
let updateGamesBtn = document.getElementById("update-games-button")


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

async function handleDelete(gamesId) {

    await fetch(`${baseUrl}/${gamesId}`, {

        method:"DELETE",
        body: getGamesById(gamesId),
        headers:headers

    })

        .catch(err => console.error(err))

    return getGames()
}

async function updateGames(gamesId) {

    let bodyObj = {

        id: gamesId,
        gameTitle: gameTitleEdit.value,
        publisher: publisherEdit.value,
        genre: genreEdit.value,
        platform: platformEdit.value,
        rating: ratingEdit.value

    }

    await  fetch(baseUrl, {

        method:"PUT",
        body: JSON.stringify(bodyObj),
        headers: headers

    })
        .catch(err => console.error(err))

    return getGames();
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

async function getGamesById(gamesId) {

    await fetch(`${baseUrl}/${gamesId}`, {

        method:"GET",
        headers:headers

    })

        .then(res => res.json())
        .then(data => populateModal(data))
        .catch(err => console.error(err.message))

}

const createGamesCard = (array) => {

    gamesContainer.innerHTML = ''
    array.forEach(obj => {

        let gamesCard = document.createElement("div")
        gamesCard.classList.add("m-2")
        gamesCard.innerHTML = `
        <div id="games-card" class="card d-flex" style="width: 18rem; height: 18rem;">
            <div class="card-body d-flex flex-column justify-content-between" style="height: fit-content">
                <p class="card-text">${obj.gameTitle}</p>
                <p class="card-text">${obj.publisher}</p>
                <p class="card-text">${obj.genre}</p>
                <p class="card-text">${obj.platform}</p>
                <p class="card-text">${obj.rating}</p>         
                 <div class="d-flex justify-content-between">
                 <button class="favorites-button" onclick="addGamesToFavorites(userId, ${obj.id})">⭐</button>
                 <button onclick="getGamesById(${obj.id})" type="button" class="btn btn-primary"
                 data-bs-toggle="modal" data-bs-target="#games-edit-modal">
                 Edit
                 </button>  
                 <button class="delete-games-button" onclick="handleDelete(${obj.id})">Delete</button>
            </div>
        </div>
    `
        gamesContainer.append(gamesCard);

    })
}

async function addGamesToFavorites(userId, gamesId) {

    const response = await fetch(`${baseUrl}/favorites/add/${userId}/${gamesId}`, {

        method: "POST",
        body: getGamesById(gamesId),
        headers: headers

    })

        .catch(err => console.error(err.message))
        if (response.status === 200) { 

        }
}

const populateModal = (obj) => {


    gameTitleEdit.innerText = ''
    publisherEdit.innerText = ''
    platformEdit.innerText = ''
    genreEdit.innerText = ''
    ratingEdit.innerText = ''
    gameTitleEdit.innerText = obj.gameTitle
    publisherEdit.innerText = obj.publisher
    platformEdit.innerText = obj.platform
    genreEdit.innerText = obj.genre
    ratingEdit.innerText = obj.rating

    updateGamesBtn.setAttribute(`data-games-id`, obj.id)
}

function openForm() {
    document.getElementById("myForm").style.display = "block";
}

function closeForm() {
    document.getElementById("myForm").style.display = "none";
}

getGames();

submitForm.addEventListener("submit", handleSubmit)

updateGamesBtn.addEventListener("click", (e) =>{
    let gamesId = e.target.getAttribute('data-games-id')
    updateGames(gamesId);
})


