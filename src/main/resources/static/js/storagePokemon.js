// <img src="${pokemon.image}">
// <img style="width:80px;height:60px;image-rendering:pixelated"src="/upload/icon/${pokemon.pokemonId}.png">
let pokemonList;
spreadPokemons()
let pokemonBox = document.getElementById('pokemonBox')

//최초 1회 뿌리기
function spreadPokemons(){
    getPokemonListFromServer().then(result=>{
        pokemonList = result
        if(result.length>0){
            pokemonBox.innerHTML = ''
            for(let pokemon of result){
                pokemonBox.innerHTML +=  `
                <div class="modal-open pokemon-card" data-storageId="${pokemon.storageId}" data-pokemonId="${pokemon.pokemonId}" data-name="${pokemon.name}" data-image="${pokemon.image}">
                    <img src="${pokemon.image}">
                    <p>[${pokemon.pokemonId}] ${pokemon.name}</p>
                </div>
                `
            }
        }else{
            pokemonBox.innerHTML = `<p>보유중인 포켓몬이 없습니다.</p>`
        }
    })
}

function spread(){
    if(document.getElementById('notAddedBtn').checked){
        getNotAddedPokemonListFromServer().then(result=>{
            pokemonList = result
            if(result.length>0){
                pokemonBox.innerHTML = ''
                for(let pokemon of result){
                    pokemonBox.innerHTML +=  `
                    <div class="modal-open" data-storageId="${pokemon.storageId}" data-pokemonId="${pokemon.pokemonId}" data-name="${pokemon.name}" data-image="${pokemon.image}" style="border:1px solid black; width:200px;float:left">
                        <img src="${pokemon.image}">
                        <p>[${pokemon.pokemonId}] ${pokemon.name}</p>
                    </div>
                    `
                }
            }else{
                pokemonBox.innerHTML = `<p>보유중인 포켓몬이 없습니다.</p>`
            }
        })
    }else {
        spreadPokemons()
    }
}

//도감에 등록 안 된 것만 뿌리기
document.getElementById('notAddedBtn').addEventListener('click',()=>{
    spread()
})

async function getNotAddedPokemonListFromServer(){
    try {
        const url = "/storage/not-added-pokemon-list/"+memberId
        const resp = await fetch(url)
        const result = await resp.json()
        return result;
    } catch (error) {
        console.log(error)
    }
}


async function getPokemonListFromServer(){
    try {
        const url = "/storage/pokemon-list/"+memberId
        const resp = await fetch(url)
        const result = await resp.json();
        return result;
    } catch (error) {
        console.log(error)
    }
}

//포켓몬 검색기능
document.addEventListener('input',(e)=>{
    let searchResult = []
    for(pokemon of pokemonList){
        if(pokemon.name.includes(e.target.value) || pokemon.pokemonId == (e.target.value)){
            searchResult.push(pokemon)
        }
    }
    pokemonBox.innerHTML = ''
    if(searchResult.length>0){

        for(pokemon of searchResult){
            pokemonBox.innerHTML +=`
                <div class="modal-open" data-storageId="${pokemon.storageId}" data-pokemonId="${pokemon.pokemonId}" data-name="${pokemon.name}" data-image="${pokemon.image}" style="border:1px solid black; width:200px;float:left">
                <img src="${pokemon.image}">
                <p>[${pokemon.pokemonId}] ${pokemon.name}</p>
                </div>
                `
        }
    }else if(searchResult.length==0 && pokemonList.length>0){
        pokemonBox.innerHTML = `<p>검색 결과가 없습니다.</p>`
    }else{
        pokemonBox.innerHTML = `<p>보유중인 포켓몬이 없습니다.</p>`
    }
})



//Get the modal
var modal = document.getElementById('modal');

// // Get the button that opens the modal
// var modalOpen = document.getElementById("modal-open");

// Get the <span> element that closes the modal
var modalClose = document.getElementsByClassName("modal-close")[0];

// When the user clicks the button, open the modal 
// modalOpen.onclick = function() {
//     modal.style.display = "block";
// }

let modalContent = document.getElementById('modal-content')

document.addEventListener('click',(e)=>{
    let div = e.target.closest('div')
    if(div.classList.contains('modal-open')){
        let storageId = div.dataset.storageid
        let pokemonId = div.dataset.pokemonid
        let name = div.dataset.name
        let image = div.dataset.image
        modalContent.innerHTML = `
            <span class="modal-close">&times;</span>
            <img src="${image}">
            <p>${name}</p>
            <button type="button" onclick="registerPokedex(${storageId})">도감등록</button>
        `
        modal.style.display = "block";
    }
})

function registerPokedex(storageId){
	postPokedex(storageId).then(result=>{
		if(result=='ok'){
            alert('등록성공')
        }else if(result=='already'){
            alert("이미 등록된 포켓몬입니다.")
        }
        modal.style.display = 'none';
        spread()

	})
}

async function postPokedex(storageId){
    try {
        const url = "/pokedex"
        const config = {
            method : "post",
            body : storageId
        }
        const resp = await fetch(url,config)
        const result = resp.text()
        return result;
    } catch (error) {
        console.log(error)
    }
}

// When the user clicks on <span> (x), close the modal
modalClose.onclick = function() {
    modal.style.display = "none";
}

document.addEventListener('click',(e)=>{
    if(e.target.classList.contains('modal-close')){
        modal.style.display = "none";
    }
})

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}

