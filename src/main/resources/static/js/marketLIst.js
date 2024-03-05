let itemList;
const qty = 30
let cnt = 0
let itemZone = document.getElementById('itemZone')
getItemListFromServer().then(result=>{
    itemList = result
    spreadItems()
})

function spreadItems(){
    if(itemList.length>0){
        if(cnt == 0){
            itemZone.innerHTML=''
        }
        for(i = cnt; i < cnt + qty; i++){
            itemZone.innerHTML += `
                <hr>
                <img src="${itemList[i].image}">
                <p>판매자 : ${itemList[i].memberId}</p>
                <p>가격 : ${itemList[i].price}</p>
                `
                if(loginMemberId != itemList[i].memberId){
                    itemZone.innerHTML += `<button class="nes-btn" type="button" data-itemId="${itemList[i].itemId}" class="buyBtn">구매</button>`
                }
        }
        cnt = cnt+qty
    }else{
        itemZone.innerHTML=`<p>품목이 존재하지 않습니다.</p>`
    }
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