document.getElementById('purchaseBtn').addEventListener('click',()=>{
    purchaseItem().then(result=>{        
        if(result!="/error"){
            alert('구매완료')
        }
        
        location.href=result
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

async function getMember(){
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
        console.log(result)
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
}

spreadMemberPoint()