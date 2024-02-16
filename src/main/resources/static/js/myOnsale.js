spreadItems()
document.addEventListener('click',(e)=>{
    if(e.target.classList.contains('cancelBtn')){
        let itemId = e.target.dataset.itemid
        cancelSale(itemId).then((result)=>{
            if(result==itemId){
                alert("취소 완료")
                spreadItems()
            }
        })
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
        itemZone.innerHTML = ``
        for(let item of itemList){
            itemZone.innerHTML+=`
                
            <p><img src="${item.image}">이름 : ${item.name} | 가격 : ${item.price} | <button class="cancelBtn" data-itemId="${item.itemId}">판매 취소</button></p>
            `
        }
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