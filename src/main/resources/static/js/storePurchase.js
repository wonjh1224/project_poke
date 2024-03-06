document.getElementById('purchaseBtn').addEventListener('click',()=>{
    purchaseItem().then(result=>{        
        if(result!="/error"){
            alert('구매했습니다.')
            if(confirm('카드팩 보관함으로 이동할까요?')){
                location.href=result
                return
            }
            location.href="/store"   
        }
    })
})


async function purchaseItem(){
    try {
        const url = "/store/purchase"
        const config = {
        method : "post",
        headers : {
            "content-type" : "application/json; charset=utf-8"
        },
        body : JSON.stringify({productId : productId, memberId : loginMemberId})
    }

    const resp = await fetch(url,config)
    const result = await resp.text()
    return result;
    } catch (error) {
        console.log(error)
    }
}
/*async function getMember(){
    try {
        const url = "/member"
        const config = {
            method : "post",
            headers : {
                "content-type" : "application/json; charset=utf-8"
            },
            body : JSON.stringify({memberId : loginMemberId})
        }
        const resp = await fetch(url,config)
        const result = await resp.json()
        return result;
    } catch (error) {
        console.log(error)
    }
}


async function spreadMemberPoint(){
    getMember().then(member=>{
        document.getElementById('memberPoint').innerText = "잔액 : " + member.point
        if(member.point<price){
            document.getElementById('purchaseBtn').disabled=true;
        }
    })
}*/
/*
spreadMemberPoint()*/

if(document.getElementById('purchaseBtn').classList.contains('is-disabled')){
    document.getElementById('purchaseBtn').disabled=true
}