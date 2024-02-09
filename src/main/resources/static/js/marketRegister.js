//Get the modal
var modal = document.getElementById('modal');

// Get the button that opens the modal
var modalOpen = document.getElementById("modal-open");

// Get the <span> element that closes the modal
var modalClose = document.getElementsByClassName("modal-close")[0];

// When the user clicks the button, open the modal 
modalOpen.onclick = function() {
    modal.style.display = "block";
}

// When the user clicks on <span> (x), close the modal
modalClose.onclick = function() {
    modal.style.display = "none";
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}


document.addEventListener('click', (e) =>{
    if(e.target.classList.contains('pokemon')){
        let storageId = e.target.dataset.storageid
        let name;
        let image;
        let pokemonIdInput = document.getElementById('pokemonId');
        let memberIdInput = document.getElementById('memberId');
        let storageIdInput = document.getElementById('storageId')
        for(let pokemon of list){
            if(pokemon.storageId == storageId){
                name = pokemon.name
                image = pokemon.image
                pokemonIdInput.value = pokemon.pokemonId
                memberIdInput.value = pokemon.memberId  
                storageIdInput.value = storageId
                break;
            }
        }
        modal.style.display = "none";
        let div = document.getElementById('pokemonDetail')
        div.innerHTML = `
        <img src="${image}">
        <p>${name}</p>
        `
    }
})
