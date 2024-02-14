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
spreadList()
function spreadList(){
    getPokedexList().then(result=>{
        console.log(result)
        let userPokemonIds = [];
        for(pokemon of result.userPokemons){
            userPokemonIds.push(pokemon.pokemonId)
        }
        console.log(userPokemonIds)
        let pokedexLine = document.getElementById('pokedexLine')
        pokedexLine.innerHTML = ''
        
        if(userPokemonIds.includes("6")){
            console.log("6666666")
        }
        for(pokemon of result.allPokemons){
            pokedexLine.innerHTML += `
                <div>
                    <img src="${pokemon.image}" class="${userPokemonIds.includes(pokemon.pokemonId) ? '' : 'gray'} abc">
                    <p>${pokemon.name}</p>
                    <p>${pokemon.flavor}</p>
                </div>
            `
        }
    })
}