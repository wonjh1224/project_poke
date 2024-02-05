console.log("boardRegister.js in");

document.getElementById('regBtn').addEventListener('click', (e)=>{
    console.log(e.target);
    let title = document.getElementById("title");
    let writer = document.getElementById("writer");
    let content = document.getElementById("content");
    
    if(title.value == ""){
		alert("제목을 입력해주세요")
	}
	if(writer.value == ""){
		alert("작성자를 입력해주세요")
	}
	if(content.value == ""){
		alert("내용을 입력해주세요")
	}
	
	if(title.value != "" && writer.value != "" && content.value != ""){
        alert("게시글 작성!")
		document.getElementById('regForm').submit();
	}
	
})