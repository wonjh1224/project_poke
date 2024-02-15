console.log("comment/register.js");
console.log(bnoVal);

document.getElementById('cmtRegBtn').addEventListener('click', (e) => {
	let cmtText = document.getElementById("cmtText");
	if (cmtText.value == null || cmtText.value == '') {
		alert("댓글을 입력해주세요");
		cmtText.focus();
	} else {
		let cmtData = {
			bno: bnoVal,
			writer: document.getElementById("cmtWriter").innerText,
			content: cmtText.value
		}
		console.log(cmtData);
		commentRegister(cmtData).then(result => {
			if (result == "1") {
				alert("댓글 등록 성공");
				cmtText.value = '';
				spreadCommentList(bnoVal);
			}
		})
	}


})

async function commentRegister(cmtData) {
	try {
		const url = "/comment/register"
		const config = {
			method: "post",
			headers: {
				"content-type": "application/json; charset=utf-8"
			},
			body: JSON.stringify(cmtData)
		}
		const resp = await fetch(url, config);
		const result = await resp.text();
		return result;
	} catch (error) {
		console.log(error);
	}
}

//댓글 리스트 받아오기
async function getCommentList(bno) {
	try {
		const url = "/comment/list/" + bno;

		const resp = await fetch(url);
		const result = await resp.json();
		console.log("list : ", result);
		return result;
	} catch (error) {
		console.log(error);
	}
}
//댓글 뿌리기
function spreadCommentList(bno) {
	getCommentList(bno).then(result => {
		console.log(result);
		const ul = document.getElementById('cmtListArea');
		if (result.length > 0) {
			ul.innerHTML = '';
			for (let cvo of result) {
				let li = `<li data-cno=${cvo.cno}>`
				li += `<div>`
				li += `<div class="writer">${cvo.writer}</div>`
				li += `<input type="text" value="${cvo.content}" class="cmtContent" readonly>`
				li += `</div>`
				li += `<button type="button" class="change">수정</button>`
				li += `<button type="button" class="del">삭제</button>`
				li += `</li><hr>`
				ul.innerHTML += li;
			}
		} else {
			li = `<li>댓글이 없습니다.</li>`
			ul.innerHTML = li;
		}
	})
}

document.addEventListener('click', (e) => {
	if (e.target.classList.contains("change")) {

		let li = e.target.closest('li');
		let cmtContent = li.querySelector('.writer').nextSibling;

		cmtContent.focus();
		cmtContent.readOnly = false;

		let cmtModBtn = e.target.closest('button');
		console.log(cmtModBtn)

		//진짜 수정(전송) 버튼 으로 변경
		cmtModBtn.setAttribute('class', 'cmtModBtn');
		cmtModBtn.innerText = "수정하기";
		//삭제 버튼 숨기기
		let cmtDelBtn = cmtModBtn.nextSibling;
		console.log(cmtDelBtn);
		cmtDelBtn.style.display = 'none';

	} else if (e.target.classList.contains("cmtModBtn")) { //수정(전송 버튼)클릭 시
		let li = e.target.closest('li');
		let cno = li.dataset.cno;
		let cmtContent = li.querySelector('.writer').nextSibling;

		const cmtData = {
			cno: cno,
			content: cmtContent.value
		}
		console.log(cmtData);
		modifyComment(cmtData).then(result => {
			if (confirm("댓글을 수정하겠습니까?")) {
				if (result == '1') {
					alert("수정 완료");
					//수정(전송)후 다시 change버튼 으로 변경
					let cmtModBtn = e.target.closest('button');
					cmtModBtn.setAttribute('class', 'change');
					cmtModBtn.innerText = "수정";
					//삭제 버튼 표시
					let cmtDelBtn = cmtModBtn.nextSibling;
					cmtDelBtn.style.display = 'inline';
				}
			}
		})
	} else if (e.target.classList.contains("del")) {
		let li = e.target.closest('li')
		let cno = li.dataset.cno;
		console.log(cno);
		if (confirm('댓글을 삭제하겠습니까?')) {
			deleteComment(cno).then(result => {
				if (result == '1') {
					spreadCommentList(bnoVal);
					alert("삭제 성공")
				}
			})
		}
	}

})

async function modifyComment(cmtDataMod) {
	try {
		const url = "/comment/modify"
		const config = {
			method: "post",
			headers: {
				"content-type": "application/json; charset=utf-8"
			},
			body: JSON.stringify(cmtDataMod)
		}
		const resp = await fetch(url, config);
		const result = await resp.text();
		return result;
	} catch (error) {
		console.log(error);
	}
}

async function deleteComment(cno) {
	try {
		const url = "/comment/delete/" + cno;
		const config = {
			method: 'delete'
		}
		const resp = await fetch(url, config);
		const result = await resp.text(); // -> 1
		return result;
	} catch (error) {
		console.log(error);
	}
}



