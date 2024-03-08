let pokedexList
let added = false;
let cnt = 0;
let userPokemonIds = [];
const pokedexLine = document.getElementById('pokedexLine')
//1번 당 뿌릴 포켓몬 수
const qty = 80;
getPokedexList().then(result=>{
    pokedexList = result
    for(pokemon of pokedexList.userPokemons){
        userPokemonIds.push(pokemon.pokemonId)
    }

    spreadAll(qty)
    document.querySelector('.title').innerText+=" ("+pokedexList.userPokemons.length+"/493)"

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
            if(pokedexList.allPokemons[i]==null){
                break;
            }
            if(userPokemonIds.includes(pokedexList.allPokemons[i].pokemonId)){
                pokedexLine.innerHTML += `
                <tr>
                    <td>${pokedexList.allPokemons[i].pokemonId}</td>
                    <td><img src="/upload/icon/${pokedexList.allPokemons[i].pokemonId}.png" class="color"></img></td>
                    <td><span>${pokedexList.allPokemons[i].name}</span></td>
                    <td class="flavor"><span>${pokedexList.allPokemons[i].flavor}</span></td>
                    <td><span>${pokedexList.allPokemons[i].score}</span></td>
                </tr>
                `
            }
        }
    }else{
        for(i=cnt;i<cnt+qty;i++){
            if(pokedexList.allPokemons[i]==null){
                break;
            }
            pokedexLine.innerHTML += 
            `
            <tr>
                <td>${pokedexList.allPokemons[i].pokemonId}</td>
                <td><img src="/upload/icon/${pokedexList.allPokemons[i].pokemonId}.png" class="${userPokemonIds.includes(pokedexList.allPokemons[i].pokemonId) ? 'color' : 'gray'}"></img></td>
                <td><span>${userPokemonIds.includes(pokedexList.allPokemons[i].pokemonId)? pokedexList.allPokemons[i].name : '???'}</span></td>
                <td class="flavor"><span>${userPokemonIds.includes(pokedexList.allPokemons[i].pokemonId)? pokedexList.allPokemons[i].flavor : ''}</span></td>
                <td><span>${userPokemonIds.includes(pokedexList.allPokemons[i].pokemonId)? pokedexList.allPokemons[i].score : ''}</span></td>
            </tr>
            `
            
            
        }
    }
    cnt=cnt+qty
}

function spread(startIndex,endIndex){

    
    pokedexLine.innerHTML = ''
    for(i=startIndex; i<endIndex; i++){
        pokedexLine.innerHTML += 
        `
        <tr>
            <td>${pokedexList.allPokemons[i].pokemonId}</td>
            <td><img src="/upload/icon/${pokedexList.allPokemons[i].pokemonId}.png" class="${userPokemonIds.includes(pokedexList.allPokemons[i].pokemonId) ? 'color' : 'gray'}"></img></td>
            <td><span>${userPokemonIds.includes(pokedexList.allPokemons[i].pokemonId)? pokedexList.allPokemons[i].name : '???'}</span></td>
            <td class="flavor"><span>${userPokemonIds.includes(pokedexList.allPokemons[i].pokemonId)? pokedexList.allPokemons[i].flavor : ''}</span></td>
            <td><span>${userPokemonIds.includes(pokedexList.allPokemons[i].pokemonId)? pokedexList.allPokemons[i].score : ''}</span></td>
        </tr>
        `
    }

}








document.getElementById('more').addEventListener('click',()=>{
    if(cnt<493 && document.getElementById('search').value==''){
        spreadAll(qty,added)
    }
})
window.addEventListener('scroll',()=>{
    if(window.scrollY + window.innerHeight >= document.body.offsetHeight * 0.5){
        document.getElementById('more').click()
    }
})



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
    document.getElementById('search').value=''
    spread(0,151)
})
document.getElementById('gen2').addEventListener('click',()=>{
    document.getElementById('search').value=''
    spread(151,251)
})
document.getElementById('gen3').addEventListener('click',()=>{
    document.getElementById('search').value=''
    spread(251,386)
})
document.getElementById('gen4').addEventListener('click',()=>{
    document.getElementById('search').value=''
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
            <tr>
                <td>${pokemon.pokemonId}</td>
                <td><img src="/upload/icon/${pokemon.pokemonId}.png" class="${userPokemonIds.includes(pokemon.pokemonId) ? 'color' : 'gray'}"></img></td>
                <td><span>${userPokemonIds.includes(pokemon.pokemonId)? pokemon.name : '???'}</span></td>
                <td><span>${userPokemonIds.includes(pokemon.pokemonId)? pokemon.flavor : ''}</span></td>
                <td><span>${userPokemonIds.includes(pokemon.pokemonId)? pokemon.score : ''}</span></td>
            </tr>
            `
        }
    }else if(searchResult.length==0){
        pokedexLine.innerHTML = `<p>검색 결과가 없습니다.</p>`
    }
})
