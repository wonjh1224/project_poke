let pokedexList

let cnt = 0;
const pokedexLine = document.getElementById('pokedexLine')

//1번 당 뿌릴 포켓몬 수
const qty = 50;
getPokedexList().then(result=>{
    pokedexList = result
    spreadAll(qty)
})
async function getPokedexList(){
    try {
        const url = "/pokedex/list"
        const config = {
            method : "post",
            body : memberId
        }
        const resp = await fetch(url,config)
        const result = await resp.json()
        return result;
    } catch (error) {
        console.log(error)
    }
}

function spreadAll(qty){
    let userPokemonIds = [];
    for(pokemon of pokedexList.userPokemons){
        userPokemonIds.push(pokemon.pokemonId)
    }
    
    if(cnt==0){
        pokedexLine.innerHTML = ''
    }
    for(i=cnt;i<cnt+qty;i++){
        pokedexLine.innerHTML += `
            <div>
                <img src="${pokedexList.allPokemons[i].image}" class="${userPokemonIds.includes(pokedexList.allPokemons[i].pokemonId) ? 'color' : 'gray'} abc"></img>
                <p>${userPokemonIds.includes(pokedexList.allPokemons[i].pokemonId)? pokedexList.allPokemons[i].name : '???'}</p>
                <p>${userPokemonIds.includes(pokedexList.allPokemons[i].pokemonId)? pokedexList.allPokemons[i].flavor : ''}</p>
            </div>
        `
    }
    cnt=cnt+qty
}

function spread(startIndex,endIndex){
    let userPokemonIds = [];
    for(pokemon of pokedexList.userPokemons){
        userPokemonIds.push(pokemon.pokemonId)
    }
    
    pokedexLine.innerHTML = ''
    for(i=startIndex; i<endIndex; i++){
        pokedexLine.innerHTML += `
        <div>
            <img src="${pokedexList.allPokemons[i].image}" class="${userPokemonIds.includes(pokedexList.allPokemons[i].pokemonId) ? 'color' : 'gray'} abc"></img>
            <p>${userPokemonIds.includes(pokedexList.allPokemons[i].pokemonId)? pokedexList.allPokemons[i].name : '???'}</p>
            <p>${userPokemonIds.includes(pokedexList.allPokemons[i].pokemonId)? pokedexList.allPokemons[i].flavor : ''}</p>
        </div>
        `
    }

}








document.getElementById('more').addEventListener('click',()=>{
    spreadAll(qty)
})
// document.addEventListener('scroll',()=>{
//     console.log(window.scrollX,window.scrollY)
// })



document.getElementById('genAll').addEventListener('click',()=>{
    cnt = 0
    spreadAll
(qty)
})






document.getElementById('gen1').addEventListener('click',()=>{
    spread(0,151)
})
document.getElementById('gen2').addEventListener('click',()=>{
    spread(151,251)
})
document.getElementById('gen3').addEventListener('click',()=>{
    spread(251,386)
})
document.getElementById('gen4').addEventListener('click',()=>{
    spread(386,493)
})




document.addEventListener('input',(e)=>{
    let searchResult = []
    if(e.target.value==''){
        cnt = 0
        spreadAll
    (qty)
        return;
    }
    for(pokemon of pokedexList.allPokemons){
        if(pokemon.name.includes(e.target.value) || pokemon.pokemonId == (e.target.value)){
            searchResult.push(pokemon)
        }
    }
    pokedexLine.innerHTML = ''
    if(searchResult.length>0){

        for(pokemon of searchResult){
            pokedexLine.innerHTML +=`
                <div class="modal-open" data-storageId="${pokemon.storageId}" data-pokemonId="${pokemon.pokemonId}" data-name="${pokemon.name}" data-image="${pokemon.image}" style="border:1px solid black; width:200px;float:left">
                <img src="${pokemon.image}">
                <p>[${pokemon.pokemonId}] ${pokemon.name}</p>
                </div>
                `
        }
    }else if(searchResult.length==0){
        pokedexLine.innerHTML = `<p>검색 결과가 없습니다.</p>`
    }
})