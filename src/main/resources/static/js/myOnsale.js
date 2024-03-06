spreadItems()
document.addEventListener('click',(e)=>{
    if(e.target.classList.contains('cancelBtn')){
        let itemId = e.target.dataset.itemid
        if(confirm('등록을 취소할까요?')){
            cancelSale(itemId).then((result)=>{
                if(result==itemId){
                    alert("취소했습니다.")
                    spreadItems()
                }
            })
        }

    }
})

async function cancelSale(itemId){
    try {
        const url = "/market/"+itemId
        const config = {
            method : "delete",
        }
        const resp = await fetch(url,config)
        const result = await resp.text()
        return result
    } catch (error) {
        console.log(error)
    }
}

function spreadItems(){
    getItemListFromServer().then(itemList=>{
        let itemZone = document.getElementById('itemZone')
        if(!itemList.length>0){
            itemZone.innerHTML = '판매중인 물품이 없습니다.'
            return
        }
        let html = `
        <div class="nes-table-responsive">
        <table class="nes-table is-bordered is-centered">
          <thead>
            <tr>
              <th></th>
              <th>이름</th>
              <th>가격</th>
              <th></th>
            </tr>
          </thead>
          <tbody>
        `
        for(let item of itemList){
            html += `
            <tr>
                <td><img src="/upload/icon/${item.pokemonId}.png"></td>
                <td>${item.name}</td>
                <td>${item.price}</td>
                <td><button class="cancelBtn nes-btn is-error" data-itemId="${item.itemId}">판매 취소</button></td>
            </tr>
            `
        }
        html += `
        </tbody>
        </table>
        </div>`
        itemZone.innerHTML += html
    })
}

async function getItemListFromServer(){
    try {
        const url = "/my/market/on-sale/list"
        const config = {
            method : "post",
            body : memberId
        }
        const resp = await fetch(url,config)
        const result = await resp.json()
        return result
    } catch (error) {
        console.log(error)
    }
}