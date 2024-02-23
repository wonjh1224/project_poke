
function spreadItems(){
    getItemListFromServer().then(result=>{
        let itemZone = document.getElementById('itemZone')
        itemZone.innerHTML=''
        if(result.length>0){
            for(let item of result){
                itemZone.innerHTML += `
                    <hr>
                    <img src="${item.image}">
                    <p>판매자 : ${item.memberId}</p>
                    <p>가격 : ${item.price}</p>
                    `
                    if(loginMemberId != item.memberId){
                        itemZone.innerHTML += `<button type="button" data-itemId="${item.itemId}" class="buyBtn">구매</button>`
                    }
                    
                
            }
        }else{
            itemZone.innerHTML=`<p>품목이 존재하지 않습니다.</p>`
        }
    })
}

async function getItemListFromServer(){
    const url = "/market/list"
    const resp = await fetch(url)
    const result = await resp.json()
    return result;
}

spreadItems()

document.addEventListener('click',(e)=>{
    if(e.target.classList.contains('buyBtn')){
        if(confirm("정말 구매하시겠습니까?")){
            buyItem(e.target.dataset.itemid).then(result=>{
                if(result=='ok'){
                    alert('구매했습니다.')
                    spreadItems()
                }else{
                    alert('구매실패')
                    location.href=result
                }
            })
        }
    }
})

async function buyItem(itemId){
    const url = "/market/purchase"
    const config = {
        method : "post",
        headers :{
            "content-type" : "application/json; charset=utf-8"
        },
        body : JSON.stringify({itemId:itemId,memberId:loginMemberId})
    }
    const resp = await fetch(url,config)
    const result = await resp.text()
    return result;
}