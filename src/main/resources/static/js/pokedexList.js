console.log(memberId);

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

        for(pokemon of result.allPokemons){
            pokedexLine.innerHTML += `
                <div>
                	<a href="/pokedex/pokemon/${pokemon.pokemonId}">
                    <img src="${pokemon.image}" class="${userPokemonIds.includes(pokemon.pokemonId) ? 'color' : 'gray'} abc">
                	</a>
                    <p>${userPokemonIds.includes(pokemon.pokemonId)? pokemon.name : '???'}</p>
                    <p>${userPokemonIds.includes(pokemon.pokemonId)? pokemon.flavor : ''}</p>
                </div>
            `
            
            
        }
    })
}



