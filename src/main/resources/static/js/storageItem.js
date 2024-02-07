document.addEventListener('click',(e)=>{
    if(e.target.classList.contains('useBtn')){
        let storageId = e.target.dataset.storageid
        console.log(storageId)
        useItemInStorage(storageId).then(result=>{
            console.log(result)
        })
    }
})

async function useItemInStorage(storageId){
    try{
        const url = "/storage/use-item"
        const config = {
            method : "post",
            body : storageId
        }
        const resp = await fetch(url,config)
        const result = await resp.text();
        return result;
    }catch(error){
        console.log(error)
    }
}

