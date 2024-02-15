document.getElementById('chargeBtn').addEventListener('click',()=>{
    let point = document.getElementById('point').value
    chargePoint(point).then(result=>{
        alert(result+"원 충전 완료")
        if(url == null || url == ''){
            location.href="/"
        }else{
            location.href=url
        }        
    })    
})

async function chargePoint(point){
    try {
        const url = "/my/charge"
        const config = {
            method : "post",
            headers : {
                "content-type" : "application/json; charset=utf-8"
            },
            body : JSON.stringify({memberId : memberId,point : point})
        }
        const resp = await fetch(url,config)
        const result = await resp.text()
        return result
    } catch (error) {
        console.log(error)
    }
}