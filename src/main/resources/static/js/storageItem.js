document.addEventListener('click', (e) => {
    if (e.target.classList.contains('useBtn')) {
        let storageId = e.target.dataset.storageid
        let itemName = e.target.dataset.name
        let data = {
            memberId: memberId,
            storageId: storageId
        }

        useItemInStorage(data).then(result => {
            document.querySelector('.anime-box').innerHTML=
            `
            <p class="msg">카드팩을 클릭해서 개봉하세요 !</p>
            <div class="btn-box">
                <button id="openBtn" class="nes-btn is-primary">모두 열기</button>
                <button id="skipBtn" class="nes-btn is-error">스킵하기</button>
            </div>
            `
            pokemonNames = [];
            for (pokemon of result) {
                let html = ""
                html += `
                <div class="card-pack">
                    <img class="pack-head" src="/image/cardpack/${itemName}_head.png">
                    <div class="pokemon-card ${pokemon.rarity=="전설" ? 'card-anime-color-legendary' : pokemon.rarity == "희귀" ? 'card-anime-color-rare' : pokemon.rarity == "고급" ? 'card-anime-color-uncommon' : ''}">
                        <div>
                            <img src="https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${pokemon.pokemonId}.png">  
                        </div>
                        <p class="nes-badge grade">
                `
                if(pokemon.rarity == "전설"){
                    html += `<span class="is-warning">전설</span>`
                }else if(pokemon.rarity == "희귀"){
                    html += `<span class="is-primary">희귀</span>`
                }else if(pokemon.rarity == "고급"){
                    html += `<span class="is-success">고급</span>`
                }else{
                    html += `<span class="is-error">일반</span>`
                }
                html += `        
                        </p>
                        <p class="nes-badge">
                            <span class="is-dark">${pokemon.name}</span>
                        </p>
                    </div>
                    <img class="pack-body" src="/image/cardpack/${itemName}_body.png">
                </div>
                `
                document.querySelector('.anime-box').innerHTML += html
                pokemonNames.push(pokemon.name)
            }
            document.querySelector('.anime-box').style.visibility="visible"
            spreadItems()
        })
    }
})

function skip(){
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
    if(e.target.id=="openBtn"){
        for(pack of document.querySelectorAll('.pack-body')){
            pack.click()
        }
    }
    if(e.target.id=="skipBtn"){
        skip()
    }
})


async function useItemInStorage(data) {
    try {
        const url = "/storage/use-item"
        const config = {
            method: "post",
            headers: {
                "content-type": "application/json; charset=utf-8"
            },
            body: JSON.stringify(data)
        }
        const resp = await fetch(url, config)
        const result = await resp.json();
        return result;
    } catch (error) {
        console.log(error)
    }
}

function spreadItems() {
    getItemListFromServer(memberId).then(result => {
        let itemBox = document.getElementById('itemBox')
        itemBox.innerHTML = ''
        if (result.length > 0) {
            for (let item of result) {
                itemBox.innerHTML += `
                <p>${item.name}</p>
                <img src="/image/cardpack/${item.name}.png">
                <button data-storageid="${item.storageId}" data-name="${item.name}" class="useBtn">사용하기</button>
                <hr>
                `
            }
        } else {
            itemBox.innerHTML = `<p>보유중인 상품이 없습니다.</p>`
        }
    })
}

async function getItemListFromServer(memberId) {
    try {
        const url = "/storage/item-list/" + memberId
        const resp = await fetch(url)
        const result = await resp.json();
        return result;
    } catch (error) {
        console.log(error)
    }
}
spreadItems()








