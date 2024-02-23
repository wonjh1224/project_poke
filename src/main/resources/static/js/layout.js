document.getElementById('logoutBtn').addEventListener('click',()=>{
    logout()
    location.href="/"
})

async function logout(){
    const url = "/logout"
    const config = {
        method : "post"
    }
    fetch(url,config)
}