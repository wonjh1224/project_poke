start()
function start() {
    document.querySelector('.anime-box').innerHTML=`
    <div class="btn-box">
        <button id="openBtn" class="nes-btn is-primary openBtn">열기</button>
        <button id="skipBtn" class="nes-btn is-error skipBtn">스킵</button>
    </div>
    <div class="card-pack">
        <img class="hd" src="/image/hd.png">
        <div class="pokemon-card card-anime">
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
        <img class="bd" src="/image/bd.png">
    </div>
    <div class="card-pack">
        <img class="hd" src="/image/hd.png">
        <div class="pokemon-card card-anime">
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
        <img class="bd" src="/image/bd.png">
    </div>
    <div class="card-pack">
        <img class="hd" src="/image/hd.png">
        <div class="pokemon-card card-anime">
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
        <img class="bd" src="/image/bd.png">
    </div>
    <div class="card-pack">
        <img class="hd" src="/image/hd.png">
        <div class="pokemon-card card-anime">
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
        <img class="bd" src="/image/bd.png">
    </div>
    <div class="card-pack">
        <img class="hd" src="/image/hd.png">
        <div class="pokemon-card card-anime">
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
        <img class="bd" src="/image/bd.png">
    </div>
    
    `
}


function stop(){
    document.querySelector('.anime-box').style.display="none"
}
document.getElementById('skipBtn').addEventListener('click',()=>{
    stop()
})