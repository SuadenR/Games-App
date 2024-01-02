const cookieArr = document.cookie.split("=")
const userId = cookieArr[1];

const baseUrl = "http://localhost:8080/api/v1"

const headers = {
    'Content-Type': 'application/json'
}

function handleLogout(){
    let c = document.cookie.split(";");
    for(let i in c){
        document.cookie = /^[^=]+/.exec(c[i])[0]+"=;expires=Thu, 01 Jan 1970 00:00:00 GMT"
    }
}

