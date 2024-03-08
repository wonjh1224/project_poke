console.log('ranking.register');

document.getElementById('insert').addEventListener('click',(e)=>{
    console.log(e.target);
    firstUpdateRanking();
})

async function firstUpdateRanking(){
	try {
        const url = "/ranking/update"
        const config = {
            method : "post"
        }
        const resp = await fetch(url, config)
        const result = await resp.text()
        return result
    } catch (error) {
        console.log(error);
    }
}





