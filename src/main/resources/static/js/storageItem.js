document.addEventListener('click',(e)=>{
    if(e.target.classList.contains('useBtn')){
        let storageId = e.target.dataset.storageid
        let data = {
			memberId : memberId,
			storageId : storageId
		}
        useItemInStorage(data).then(result=>{
            console.log(result)
        })
    }
})

async function useItemInStorage(data){
    try{
        const url = "/storage/use-item"
        const config = {
            method : "post",
            headers : {
                "content-type" : "application/json; charset=utf-8"
            },
            body : JSON.stringify(data)
        }
        const resp = await fetch(url,config)
        const result = await resp.text();
        return result;
    }catch(error){
        console.log(error)
    }
}

