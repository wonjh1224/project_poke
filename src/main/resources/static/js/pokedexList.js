let pokedexList
let added = false;
let cnt = 0;
let userPokemonIds = [];
const pokedexLine = document.getElementById('pokedexLine')
//1번 당 뿌릴 포켓몬 수
const qty = 29;
getPokedexList().then(result=>{
    pokedexList = result
    for(pokemon of pokedexList.userPokemons){
        userPokemonIds.push(pokemon.pokemonId)
    }

    spreadAll(qty)
    document.getElementById('h2').innerText=memberId+"의 도감페이지 ("+pokedexList.userPokemons.length+"/493)"

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

function spreadAll(qty,added=false){
    if(cnt==0){
        pokedexLine.innerHTML = ''
    }

    if(added){
        for(i=cnt;i<cnt+qty;i++){
            if(userPokemonIds.includes(pokedexList.allPokemons[i].pokemonId)){
                pokedexLine.innerHTML += 
                `
                <div>
                    <img src="${pokedexList.allPokemons[i].image}" class="color"></img>
                    <p>${pokedexList.allPokemons[i].name}</p>
                    <p>${pokedexList.allPokemons[i].flavor}</p>
                </div>
                `
            }
        }
    }else{
        for(i=cnt;i<cnt+qty;i++){
            pokedexLine.innerHTML += 
            `
            <div>
                <img src="${pokedexList.allPokemons[i].image}" class="${userPokemonIds.includes(pokedexList.allPokemons[i].pokemonId) ? 'color' : 'gray'}"></img>
                <p>${userPokemonIds.includes(pokedexList.allPokemons[i].pokemonId)? pokedexList.allPokemons[i].name : '???'}</p>
                <p>${userPokemonIds.includes(pokedexList.allPokemons[i].pokemonId)? pokedexList.allPokemons[i].flavor : ''}</p>
            </div>
            `
        }
    }
    cnt=cnt+qty
    console.log(cnt)
}

function spread(startIndex,endIndex){

    
    pokedexLine.innerHTML = ''
    for(i=startIndex; i<endIndex; i++){
        pokedexLine.innerHTML += 
        `
        <div>
            <img src="${pokedexList.allPokemons[i].image}" class="${userPokemonIds.includes(pokedexList.allPokemons[i].pokemonId) ? 'color' : 'gray'}"></img>
            <p>${userPokemonIds.includes(pokedexList.allPokemons[i].pokemonId)? pokedexList.allPokemons[i].name : '???'}</p>
            <p>${userPokemonIds.includes(pokedexList.allPokemons[i].pokemonId)? pokedexList.allPokemons[i].flavor : ''}</p>
        </div>
        `
    }

}








document.getElementById('more').addEventListener('click',()=>{
    if(cnt<493){
        spreadAll(qty,added)
    }
})
// document.addEventListener('scroll',()=>{
//     console.log(window.scrollX,window.scrollY)
// })



document.getElementById('genAll').addEventListener('click',()=>{
    cnt = 0
    added = false
    spreadAll(qty,added)
})




document.getElementById('added').addEventListener('click',()=>{
    cnt = 0
    added = true
    spreadAll(qty,added)
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
        added=false
        spreadAll(qty)
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
            pokedexLine.innerHTML +=
            `
            <div>
                <img src="${pokemon.image}" class="${userPokemonIds.includes(pokemon.pokemonId) ? 'color' : 'gray'}"></img>
                <p>${userPokemonIds.includes(pokemon.pokemonId)? pokemon.name : '???'}</p>
                <p>${userPokemonIds.includes(pokemon.pokemonId)? pokemon.flavor : ''}</p>
            </div>
            `
        }
    }else if(searchResult.length==0){
        pokedexLine.innerHTML = `<p>검색 결과가 없습니다.</p>`
    }
})