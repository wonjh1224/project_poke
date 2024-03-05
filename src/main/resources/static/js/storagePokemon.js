//한번에 뿌릴 개수
const qty = 48;

//현재 뿌려질 인덱스 담는 변수
let cnt = 0;
let pokemonList;

let pokemonBox = document.getElementById('pokemonBox')

//최초 1회 뿌리기
 getPokemonListFromServer().then(result=>{
    pokemonList = result
    spreadPokemons()
})


function spreadPokemons(){
    if(pokemonList.length>0){
        if(cnt == 0){
            pokemonBox.innerHTML = ''
        }
        for(i=cnt;i<cnt+qty;i++){
            if(pokemonList[i]==null){
                document.getElementById('more').disabled = true
                break;
            }
            let html = ""
            html +=  `
            <div class="pokemon-card-cover" data-storageId="${pokemonList[i].storageId}" data-pokemonId="${pokemonList[i].pokemonId}" data-name="${pokemonList[i].name}" data-image="${pokemonList[i].image}">
                <div class="pokemon-card-wrap">
                    <button class="nes-btn is-warning pokedexBtn" onclick="registerPokedex(${pokemonList[i].storageId},'${pokemonList[i].name}')">도감 등록</button>
                    <button class="nes-btn is-success marketBtn" onclick="registerMarket(${pokemonList[i].storageId},${pokemonList[i].pokemonId},'${pokemonList[i].name}','${pokemonList[i].image}')">거래소 등록</button>
                </div>
                <div class="pokemon-card">
                    <div>
                        <img src="${pokemonList[i].image}">
                    </div>
                    
                    <p class="nes-badge rarity">
            `
            if(pokemonList[i].rarity == "전설"){
                html += `<span class="is-warning">전설</span>`
            }else if(pokemonList[i].rarity == "희귀"){
                html += `<span class="is-primary">희귀</span>`
            }else if(pokemonList[i].rarity == "고급"){
                html += `<span class="is-success">고급</span>`
            }else{
                html += `<span class="is-error">일반</span>`
            }
            html += `        
                    </p>
                    <p class="nes-badge">
                        <span class="is-dark">${pokemonList[i].name}</span>
                    </p>
                </div>
            </div>
            `
            pokemonBox.innerHTML += html
        }
        cnt=cnt+qty
    }else{
        pokemonBox.innerHTML = `<p>보유중인 포켓몬이 없습니다.</p>`
    }
}

document.getElementById('more').addEventListener('click',()=>{
    if(document.getElementById('searchBox').value==''){
        spreadPokemons()
    }
})
window.addEventListener('scroll',()=>{
    if(window.scrollY + window.innerHeight >= document.body.offsetHeight * 0.9){
        document.getElementById('more').click()
    }
})




function spread(){
    cnt=0
    document.getElementById('more').disabled = false
    if(document.getElementById('notAddedBtn').checked){
        getNotAddedPokemonListFromServer().then(result=>{
            pokemonList = result
            spreadPokemons()
        })
    }else {
        getPokemonListFromServer().then(result=>{
            pokemonList = result
            spreadPokemons()
        })
    }
}

//도감에 등록 안 된 것만 뿌리기
document.getElementById('notAddedBtn').addEventListener('click',()=>{
    spread()
})

async function getNotAddedPokemonListFromServer(){
    try {
        const url = "/storage/not-added-pokemon-list/"+memberId
        const resp = await fetch(url)
        const result = await resp.json()
        return result;
    } catch (error) {
        console.log(error)
    }
}


async function getPokemonListFromServer(){
    try {
        const url = "/storage/pokemon-list/"+memberId
        const resp = await fetch(url)
        const result = await resp.json();
        return result;
    } catch (error) {
        console.log(error)
    }
}

//포켓몬 검색기능
document.addEventListener('input',(e)=>{
    if(e.target.id!='searchBox'){
        return
    }
    if(e.target.value==''){
        spread()
        return
    }
    let searchResult = []
    for(pokemon of pokemonList){
        if(pokemon.name.includes(e.target.value) || pokemon.pokemonId == (e.target.value)){
            searchResult.push(pokemon)
        }
    }
    pokemonBox.innerHTML = ''
    if(searchResult.length>0){

        for(pokemon of searchResult){
            let html = ""
            html +=  `
            <div class="pokemon-card-cover" data-storageId="${pokemon.storageId}" data-pokemonId="${pokemon.pokemonId}" data-name="${pokemon.name}" data-image="${pokemon.image}">
                <div class="pokemon-card-wrap">
                    <button class="nes-btn is-warning pokedexBtn" onclick="registerPokedex(${pokemonList[i].storageId},'${pokemonList[i].name}')">도감 등록</button>
                    <button class="nes-btn is-success marketBtn" onclick="registerMarket(${pokemonList[i].storageId},${pokemonList[i].pokemonId},'${pokemonList[i].name}','${pokemonList[i].image}')">거래소 등록</button>
                </div>
                <div class="pokemon-card">
                    <div>
                        <img src="${pokemon.image}">
                    </div>
                    
                    <p class="nes-badge rarity">
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
            </div>
            `
            pokemonBox.innerHTML += html
        }
    }else if(searchResult.length==0 && pokemonList.length>0){
        pokemonBox.innerHTML = `<p>검색 결과가 없습니다.</p>`
    }else{
        pokemonBox.innerHTML = `<p>보유중인 포켓몬이 없습니다.</p>`
    }
})



//Get the modal
var modal = document.getElementById('modal');

// // Get the button that opens the modal
// var modalOpen = document.getElementById("modal-open");

// Get the <span> element that closes the modal
var modalClose = document.getElementsByClassName("modal-close")[0];

// When the user clicks the button, open the modal 
// modalOpen.onclick = function() {
//     modal.style.display = "block";
// }

let modalContent = document.getElementById('modal-content')




function registerMarket(storageId,pokemonId,name,image){
    modalContent.innerHTML = `
            <span class="modal-close">&times;</span>
            <div id="modal-box">
                <img src="${image}">
                <p>이름 : ${name}</p>
                <p>가격 : <input id="price" type="text" name="price" class="nes-input"> <button class="nes-btn" type="button" onclick="formSubmit('${name}',${storageId},${pokemonId},'${memberId}')"> 등록</button></p>
                
            </div>
        `
    modal.style.display = "block";
}

async function formSubmit(name,storageId,pokemonId,memberId){
    if(document.getElementById('price').value==''){
        alert('가격을 입력해 주세요')
        document.getElementById('price').focus()
        return
    }
    if(document.getElementById('price').value > 9999999){
        alert('최대 9,999,999까지 입력 가능합니다.')
        return
    }
    
    if(confirm(name+' - 거래소에 등록할까요?')){
        let formData = new FormData();
        formData.append('storageId',storageId)
        formData.append('pokemonId',pokemonId)
        formData.append('memberId',memberId)
        formData.append('price',document.getElementById('price').value)
        try {
            const url = "/market"
            const config = {
                method : "post",
                body : formData
            }
            const resp = await fetch(url,config)
            const result = await resp.json()
        } catch (error) {
            console.log(error)
        }
        alert('등록 완료')
        modal.style.display = "none";
        spread()
    }
}


function registerPokedex(storageId,name){
    if(confirm(name+' - 도감에 등록할까요?')){
        postPokedex(storageId).then(result=>{
            if(result=='ok'){
                alert('도감에 등록했습니다.')
            }else if(result=='already'){
                alert("이미 등록된 포켓몬입니다.")
            }
            modal.style.display = 'none';
            spread()
            
        })
    }
}

async function postPokedex(storageId){
    try {
        const url = "/pokedex"
        const config = {
            method : "post",
            body : storageId
        }
        const resp = await fetch(url,config)
        const result = resp.text()
        return result;
    } catch (error) {
        console.log(error)
    }
}

// When the user clicks on <span> (x), close the modal
modalClose.onclick = function() {
    modal.style.display = "none";
}

document.addEventListener('click',(e)=>{
    if(e.target.classList.contains('modal-close')){
        modal.style.display = "none";
    }
})

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}

