let pokedexList
let cnt = 0;
const pokedexLine = document.getElementById('pokedexLine')

//1번 당 뿌릴 포켓몬 수
const qty = 50;
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
spreadList(qty)
function spreadList(qty){
    getPokedexList().then(result=>{
        console.log(cnt)
        pokedexList = result
        let userPokemonIds = [];
        for(pokemon of result.userPokemons){
            userPokemonIds.push(pokemon.pokemonId)
        }
        
        if(cnt==0){
            pokedexLine.innerHTML = ''
        }
        for(i=cnt;i<cnt+qty;i++){
            pokedexLine.innerHTML += `
                <div>
                    <img src="${result.allPokemons[i].image}" class="${userPokemonIds.includes(result.allPokemons[i].pokemonId) ? 'color' : 'gray'} abc"></img>
                    <p>${userPokemonIds.includes(result.allPokemons[i].pokemonId)? result.allPokemons[i].name : '???'}</p>
                    <p>${userPokemonIds.includes(result.allPokemons[i].pokemonId)? result.allPokemons[i].flavor : ''}</p>
                </div>
            `
        }
        cnt=cnt+qty
    
    })
}

document.getElementById('more').addEventListener('click',()=>{
    spreadList(qty)
})
// document.addEventListener('scroll',()=>{
//     console.log(window.scrollX,window.scrollY)
// })



document.getElementById('gen0').addEventListener('click',()=>{
    cnt = 0
    spreadList(qty)
})

document.getElementById('gen1').addEventListener('click',()=>{

    let userPokemonIds = [];
    for(pokemon of pokedexList.userPokemons){
        userPokemonIds.push(pokemon.pokemonId)
    }

    pokedexLine.innerHTML = ''
    for(i=0; i<151; i++){
        pokedexLine.innerHTML += `
        <div>
            <img src="${pokedexList.allPokemons[i].image}" class="${userPokemonIds.includes(pokedexList.allPokemons[i].pokemonId) ? 'color' : 'gray'} abc"></img>
            <p>${userPokemonIds.includes(pokedexList.allPokemons[i].pokemonId)? pokedexList.allPokemons[i].name : '???'}</p>
            <p>${userPokemonIds.includes(pokedexList.allPokemons[i].pokemonId)? pokedexList.allPokemons[i].flavor : ''}</p>
        </div>
        `
    }
})
document.getElementById('gen2').addEventListener('click',()=>{

    let userPokemonIds = [];
    for(pokemon of pokedexList.userPokemons){
        userPokemonIds.push(pokemon.pokemonId)
    }

    pokedexLine.innerHTML = ''
    for(i=151; i<251; i++){
        pokedexLine.innerHTML += `
        <div>
            <img src="${pokedexList.allPokemons[i].image}" class="${userPokemonIds.includes(pokedexList.allPokemons[i].pokemonId) ? 'color' : 'gray'} abc"></img>
            <p>${userPokemonIds.includes(pokedexList.allPokemons[i].pokemonId)? pokedexList.allPokemons[i].name : '???'}</p>
            <p>${userPokemonIds.includes(pokedexList.allPokemons[i].pokemonId)? pokedexList.allPokemons[i].flavor : ''}</p>
        </div>
        `
    }
})
document.getElementById('gen3').addEventListener('click',()=>{

    let userPokemonIds = [];
    for(pokemon of pokedexList.userPokemons){
        userPokemonIds.push(pokemon.pokemonId)
    }

    pokedexLine.innerHTML = ''
    for(i=251; i<386; i++){
        pokedexLine.innerHTML += `
        <div>
            <img src="${pokedexList.allPokemons[i].image}" class="${userPokemonIds.includes(pokedexList.allPokemons[i].pokemonId) ? 'color' : 'gray'} abc"></img>
            <p>${userPokemonIds.includes(pokedexList.allPokemons[i].pokemonId)? pokedexList.allPokemons[i].name : '???'}</p>
            <p>${userPokemonIds.includes(pokedexList.allPokemons[i].pokemonId)? pokedexList.allPokemons[i].flavor : ''}</p>
        </div>
        `
    }
})
document.getElementById('gen4').addEventListener('click',()=>{

    let userPokemonIds = [];
    for(pokemon of pokedexList.userPokemons){
        userPokemonIds.push(pokemon.pokemonId)
    }

    pokedexLine.innerHTML = ''
    for(i=386; i<493; i++){
        pokedexLine.innerHTML += `
        <div>
            <img src="${pokedexList.allPokemons[i].image}" class="${userPokemonIds.includes(pokedexList.allPokemons[i].pokemonId) ? 'color' : 'gray'} abc"></img>
            <p>${userPokemonIds.includes(pokedexList.allPokemons[i].pokemonId)? pokedexList.allPokemons[i].name : '???'}</p>
            <p>${userPokemonIds.includes(pokedexList.allPokemons[i].pokemonId)? pokedexList.allPokemons[i].flavor : ''}</p>
        </div>
        `
    }
})




document.addEventListener('input',(e)=>{
    let searchResult = []
    if(e.target.value==''){
        cnt = 0
        spreadList(qty)
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