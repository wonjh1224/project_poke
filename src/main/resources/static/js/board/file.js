// -- file -- 
console.log("file.js in");
document.getElementById('trigger').addEventListener('click', ()=>{
    document.getElementById('files').click();
})

// 정규표현식을 사용하여 파일 형식제한 함수 만들기
// 파일 사이즈 체크(20M 사이즈 보다 크면 막기)
// 이미지 파일만 올리기(jpg, jpeg, gif, png, bmp)
const regExpImg = new RegExp("\.(jpg|jpeg|gif|png|bmp)$") //이미지 파일 패턴
const maxSize = 1024*1024*20; // 20MB 사이즈로 설정

function fileValidation(fileName, fileSize){
    if(!regExpImg.test(fileName)){
        return 0;
    }else if(fileSize > maxSize){
        return 0;
    }else{
        return 1;
    }
}

document.addEventListener('change', (e)=>{
    console.log(e.target);
    if(e.target.id == "files"){ //파일에 변화가 생겼다면
        // input file element에 저장된 file의 정보를 가져오는 property
        const fileObj = document.getElementById('files').files;
        console.log(fileObj);

        //한번 disabled 되면 버튼을 원래 상태로 복구
        document.getElementById('submitBtn').disabled=false;

        //첨부파일에 대한 정보를 fileZone에 기록
        let div = document.getElementById('fileZone');
        div.innerHTML=''; // 이전 값 지우기

        // * 여러 파일에 대한 검증을 모두 통과하기 위해서 * 로 각 파일마다 통과 여부를 확인
        let isOk = 1; //전체 파일 검증 결과
        let ul = `<ul>`;
        for(let file of fileObj){
            //개별 파일의 검증 결과
            let vaildResult = fileValidation(file.name, file.size);
            isOk *= vaildResult;
            ul += `<li>`;
            ul += `<div>${vaildResult? '업로드 가능' : '업로드 불가능'}</div>`;
            ul += `${file.name}`;
            ul += `<span>${Math.round(file.size/1024.0)}KB</span>`;
            ul += `<hr></li>`;
        }
        ul += `</ul>`;
        div.innerHTML = ul;

        console.log(isOk);

        if(isOk == 0){
            document.getElementById('submitBtn').disabled=true;
        }

    }
})