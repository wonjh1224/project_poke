//한번에 뿌릴 개수
const qty = 50;

//현재 뿌려질 인덱스 담는 변수
let cnt = 0;
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
                let html = ""
                html +=  `
                <div class="modal-open pokemon-card-cover" data-storageId="${pokemon.storageId}" data-pokemonId="${pokemon.pokemonId}" data-name="${pokemon.name}" data-image="${pokemon.image}">
                    <div class="pokemon-card-wrap"></div>
                    <div class="pokemon-card">
                        <div>
                            <img src="${pokemon.image}">
                        </div>
                        
                        <p class="nes-badge rarity">
                `
                if(pokemon.rarity == "전설"){
                    html += `<span class="is-warning">전설</span>`
                }else if(pokemon.rarity == "희귀"){
                    html += `<span class="is-primary">희귀</span>`
                }else if(pokemon.rarity == "고급"){
                    html += `<span class="is-success">고급</span>`
                }else{
                    html += `<span class="is-error">일반</span>`
                }
                html += `        
                        </p>
                        <p class="nes-badge">
                            <span class="is-dark">${pokemon.name}</span>
                        </p>
                    </div>
                </div>
                `
                pokemonBox.innerHTML += html
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
                    let html = ""
                html +=  `
                <div class="modal-open pokemon-card-cover" data-storageId="${pokemon.storageId}" data-pokemonId="${pokemon.pokemonId}" data-name="${pokemon.name}" data-image="${pokemon.image}">
                    <div class="pokemon-card-wrap"></div>
                    <div class="pokemon-card">
                        <div>
                            <img src="${pokemon.image}">
                        </div>
                        
                        <p class="nes-badge rarity">
                `
                if(pokemon.rarity == "전설"){
                    html += `<span class="is-warning">전설</span>`
                }else if(pokemon.rarity == "희귀"){
                    html += `<span class="is-primary">희귀</span>`
                }else if(pokemon.rarity == "고급"){
                    html += `<span class="is-success">고급</span>`
                }else{
                    html += `<span class="is-error">일반</span>`
                }
                html += `        
                        </p>
                        <p class="nes-badge">
                            <span class="is-dark">${pokemon.name}</span>
                        </p>
                    </div>
                </div>
                `
                pokemonBox.innerHTML += html
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
            let html = ""
            html +=  `
            <div class="modal-open pokemon-card-cover" data-storageId="${pokemon.storageId}" data-pokemonId="${pokemon.pokemonId}" data-name="${pokemon.name}" data-image="${pokemon.image}">
                <div class="pokemon-card-wrap"></div>
                <div class="pokemon-card">
                    <div>
                        <img src="${pokemon.image}">
                    </div>
                    
                    <p class="nes-badge rarity">
            `
            if(pokemon.rarity == "전설"){
                html += `<span class="is-warning">전설</span>`
            }else if(pokemon.rarity == "희귀"){
                html += `<span class="is-primary">희귀</span>`
            }else if(pokemon.rarity == "고급"){
                html += `<span class="is-success">고급</span>`
            }else{
                html += `<span class="is-error">일반</span>`
            }
            html += `        
                    </p>
                    <p class="nes-badge">
                        <span class="is-dark">${pokemon.name}</span>
                    </p>
                </div>
            </div>
            `
            pokemonBox.innerHTML += html
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

