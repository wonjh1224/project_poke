console.log(document.body.offsetHeight)


if(document.body.offsetHeight<=870){
    document.body.style.backgroundImage = "url('/image/bgimg.png')";
    document.querySelector('footer').style.backgroundImage = "none";
}

async function logout(){
    const url = "/logout"
    const config = {
        method : "post"
    }
    fetch(url,config)
    location.href="/"
}


document.getElementById('profileImage').addEventListener('click',()=>{
    if(document.getElementById('dropdown-content').style.display=='block'){
        document.getElementById('dropdown-content').style.display='none'
    }else{
        document.getElementById('dropdown-content').style.display='block'
    }

})
