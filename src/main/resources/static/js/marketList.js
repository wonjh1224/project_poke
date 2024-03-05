let itemList;
const qty = 30
let cnt = 0
let itemZone = document.getElementById('itemZone')
let member
if(loginMemberId!=null){
    console.log(loginMemberId)
    getMemberFromServer().then(result=>{
        member=result
    })
}

async function getMemberFromServer(){
    config = {
        method:"post",
        headers:{
            "content-type" : "application/json; charset=utf-8"
        },
        body : JSON.stringify({memberId:loginMemberId})
    }
    const resp = await fetch('/member',config)
    const result = await resp.json()
    return result;
}

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
                <div class="item-box">
                    <div class="img-box">
                        <img src="${itemList[i].image}">
                    </div>
                    <div class="btn-box">
                        <button class="nes-btn buyBtn" type="button" data-itemId="${itemList[i].itemId}"  ${loginMemberId == itemList[i].memberId ? 'style="display:none"':''}>구매</button>
                    </div>
                    <div class="text-box">
                        <span>판매자</span><span>${itemList[i].nickname}</span>
                    </div>
                    <div class="text-box">
                        <span>가격</span><span>${itemList[i].price}</span>
                    </div>
    
                </div>
                `
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