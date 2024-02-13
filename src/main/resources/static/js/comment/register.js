console.log("comment/register.js");
console.log(bnoVal);

document.getElementById('cmtRegBtn').addEventListener('click', (e)=>{
    let cmtText = document.getElementById("cmtText");
    if(cmtText.value == null || cmtText.value == ''){
		alert("댓글을 입력해주세요");
        cmtText.focus();
	}else{
		let cmtData = {
			bno : bnoVal,
			writer : document.getElementById("cmtWriter").innerText,
            content : cmtText.value
		}
        console.log(cmtData);
        commentRegister(cmtData).then(result=>{
            if(result == "1"){
                alert("댓글 등록 성공");
            }
        })
	}
    
    
})

async function commentRegister(cmtData){
    try {
        const url = "/comment/register"
		const config = {
			method:"post",
			headers : {
				"content-type" : "application/json; charset=utf-8"
			},
			body : JSON.stringify(cmtData)
		}
		const resp = await fetch(url, config);
		const result = await resp.text();
		return result;
    } catch (error) {
        console.log(error);
    }
}

async function getCommentList(bno){
    
}

function spreadCommentList(bno){

}