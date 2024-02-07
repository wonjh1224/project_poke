console.log("boardRegister.js in");

document.getElementById('submitBtn').addEventListener('click', (e)=>{
    console.log(e.target);
    let title = document.getElementById("title");
    let writer = document.getElementById("writer");
    let content = document.getElementById("content");
	
	if(title.value != null && writer.value != "" && content.value != ""){
        alert("게시글 작성!")
		document.getElementById('regForm').submit();
	}else{
		alert("제목이나 내용을 입력해주세요")
	}
	
})
