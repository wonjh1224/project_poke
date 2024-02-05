console.log("modify.js");

document.getElementById("modBtn").addEventListener('click', () => {
    //제목, 내용 수정가능하게 변경
    document.getElementById('title').readOnly = false;
    document.getElementById('content').readOnly = false;
   
    //수정(전송) 버튼 생성
    let modBtn = document.createElement('button');
    modBtn.setAttribute('type', 'button');
    modBtn.setAttribute('id', 'submit');
    modBtn.innerText='수정 하기';
    document.getElementById('modForm').appendChild(modBtn);

    //기존 디테일 페이지에 있는 수정, 삭제 버튼 삭제
    document.getElementById('modBtn').remove();
    document.getElementById('delBtn').remove();

    document.getElementById('submit').addEventListener('click',()=>{
        if(confirm('게시글을 수정하시겠습니까?')){
            modBtn.setAttribute('type', 'submit');
        }
    })

})

document.getElementById("delBtn").addEventListener('click', ()=>{
    if(confirm('게시글을 삭제하시겠습니까?')){
        document.getElementById('delForm').submit();
    }
})

