
function start() {
    document.querySelector('.anime-box').style.visibility="visible"
    document.querySelector('.anime-box').innerHTML=`
    <div class="btn-box">
        <button id="openBtn" class="nes-btn is-primary openBtn">열기</button>
        <button id="skipBtn" class="nes-btn is-error skipBtn">스킵</button>
    </div>
    <div class="card-pack">
        <img class="pack-head" src="/image/hd.png">
        <div class="pokemon-card card-anime-color-legend">
            <div>
                <img src="https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/493.png">  
            </div>
            <p class="nes-badge grade">
            <span class="is-warning">전설</span>
            </p>
            <p class="nes-badge">
            <span class="is-dark">아르세우스</span>
            </p>
        </div>
        <img class="pack-body" src="/image/bd.png">
    </div>
    <div class="card-pack">
        <img class="pack-head" src="/image/hd.png">
        <div class="pokemon-card card-anime-color-legend">
            <div>
                <img src="https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/493.png">  
            </div>
            <p class="nes-badge grade">
            <span class="is-warning">전설</span>
            </p>
            <p class="nes-badge">
            <span class="is-dark">아르세우스</span>
            </p>
        </div>
        <img class="pack-body" src="/image/bd.png">
    </div>
    <div class="card-pack">
        <img class="pack-head" src="/image/hd.png">
        <div class="pokemon-card card-anime-color-legend">
            <div>
                <img src="https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/493.png">  
            </div>
            <p class="nes-badge grade">
            <span class="is-warning">전설</span>
            </p>
            <p class="nes-badge">
            <span class="is-dark">아르세우스</span>
            </p>
        </div>
        <img class="pack-body" src="/image/bd.png">
    </div>
    <div class="card-pack">
        <img class="pack-head" src="/image/hd.png">
        <div class="pokemon-card card-anime-color-legend">
            <div>
                <img src="https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/493.png">  
            </div>
            <p class="nes-badge grade">
            <span class="is-warning">전설</span>
            </p>
            <p class="nes-badge">
            <span class="is-dark">아르세우스</span>
            </p>
        </div>
        <img class="pack-body" src="/image/bd.png">
    </div>
    <div class="card-pack">
        <img class="pack-head" src="/image/hd.png">
        <div class="pokemon-card card-anime-color-legend">
            <div>
                <img src="https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/493.png">  
            </div>
            <p class="nes-badge grade">
            <span class="is-warning">전설</span>
            </p>
            <p class="nes-badge">
            <span class="is-dark">아르세우스</span>
            </p>
        </div>
        <img class="pack-body" src="/image/bd.png">
    </div>
    
    `
}


function stop(){
    document.querySelector('.anime-box').style.visibility="hidden"
}


document.addEventListener('click',(e)=>{
    if(e.target.classList.contains('pack-body')||e.target.classList.contains('pack-head')){

        let div = e.target.closest('div')
        let head = div.querySelector('.pack-head')
        let body = div.querySelector('.pack-body')
        let card = div.querySelector('.pokemon-card')
        
        head.classList.add('card-anime-open')
        body.classList.add('card-anime-move')
    }
})