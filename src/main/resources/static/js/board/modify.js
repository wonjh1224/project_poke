console.log("modify.js");

document.getElementById("changeBtn").addEventListener('click', () => {
	//제목, 내용 수정가능하게 변경
	document.getElementById('title').readOnly = false;
	document.getElementById('content').readOnly = false;

	//사진 등록 버튼 노출
	let fileDiv = document.getElementById('fileDiv');
	fileDiv.style.display = 'block';

	//사진 삭제 버튼 노출
	/*fileDelBtn.style.display = 'block';*/
	let fileDelBtns = document.querySelectorAll('.fileDelBtn');
	for (let btn of fileDelBtns) {
		btn.style.display = 'block'
	}

    document.addEventListener('click', (e)=>{
        if(e.target.classList.contains("fileDelBtn")){
            let li = e.target.closest('li');
            li.remove();
            let uuid = e.target.getAttribute("data-uuid");
            let uuids = document.getElementById('uuids');
            uuids.value += uuid+",";
      
        }
    })


	//수정(전송) 버튼 생성
	let modBtn = document.createElement('button');
	modBtn.setAttribute('type', 'button');
	modBtn.setAttribute('id', 'submitBtn');
	modBtn.innerText = '수정 하기';
	document.getElementById('modForm').appendChild(modBtn);

	//기존 디테일 페이지에 있는 수정, 삭제 버튼 삭제
	document.getElementById('changeBtn').remove();
	document.getElementById('delBtn').remove();

	document.getElementById('submitBtn').addEventListener('click', () => {
		if (confirm('게시글을 수정하시겠습니까?')) {
			modBtn.setAttribute('type', 'submit');
		}
	})

})

document.getElementById("delBtn").addEventListener('click', () => {
	if (confirm('게시글을 삭제하시겠습니까?')) {
		document.getElementById('delForm').submit();
	}
})

