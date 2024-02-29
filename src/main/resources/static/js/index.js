/**
 * 
 */

function start() {
    document.querySelector('.img-box').innerHTML=`
    <img class="hd" src="/image/hd.png">
    <div class="pokemon-card">
        <img src="https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/493.png">
        
    </div>
    <img class="bd" src="/image/bd.png">
    `
}

function stop(){
    document.querySelector('.img-box').innerHTML=``
}