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
        let userPokemonIds = [];
        for(pokemon of result.userPokemons){
            userPokemonIds.push(pokemon.pokemonId)
        }

        let pokedexLine = document.getElementById('pokedexLine')
        pokedexLine.innerHTML = ''
        let cnt = 0;
        for(pokemon of result.allPokemons){
            pokedexLine.innerHTML += `
                <div>
                    <img src="/upload/pokemon/${pokemon.pokemonId}.png" class="${userPokemonIds.includes(pokemon.pokemonId) ? 'color' : 'gray'} abc">
                    <p>${userPokemonIds.includes(pokemon.pokemonId)? pokemon.name : '???'}</p>
                    <p>${userPokemonIds.includes(pokemon.pokemonId)? pokemon.flavor : ''}</p>
                </div>
            `
            cnt++;
            if(cnt==5){
                break;
            }
        }
    })
}