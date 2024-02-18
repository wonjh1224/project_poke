const regExpImg = new RegExp("\.(jpg|jpeg|png)$")
const maxSize = 1024*1024*5; 

function fileValidation(fileName, fileSize){
    if(!regExpImg.test(fileName)){
        return 0;
    }else if(fileSize > maxSize){
        return 0;
    }else{
        return 1;
    }
}

document.getElementById('trigger').addEventListener('click', ()=>{
    document.getElementById('profile').click()
})

document.getElementById('profile').addEventListener('change',(e)=>{
    document.getElementById('submitBtn').disabled=false
    let validResult = fileValidation(e.target.files[0].name,e.target.files[0].size)
    
    if(validResult==1){
        const formData = new FormData();
        formData.append("image", e.target.files[0])
        test(formData).then(result=>{
            console.log(result)
            let img = document.getElementById('img')
            img.innerHTML = `<img class="profileImage" src="/upload/${result}">`
        })
    }else{
        document.getElementById('submitBtn').disabled=true
    }

    

})



async function test(formData){
    try {
        const url = "/test"
        const config = {
            method : "post",
            body : formData
        }
        const resp = await fetch(url,config)
        const result = await resp.text()
        return result
    } catch (error) {
        console.log(error)
    }

}