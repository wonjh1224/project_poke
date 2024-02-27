const regExpImg = new RegExp("\.(jpg|jpeg|png)$")
const maxSize = 1024*1024*5; 

// 프로필 사진 검증
function fileValidation(fileName, fileSize){
    if(!regExpImg.test(fileName)){
        return 0;
    }else if(fileSize > maxSize){
        return 0;
    }else{
        return 1;
    }
}

// 버튼 클릭시 input 열리는 로직
document.getElementById('trigger').addEventListener('click', ()=>{
    document.getElementById('profile').click()
})

//리셋버튼 클릭 로직
document.getElementById('resetBtn').addEventListener('click',()=>{
    document.getElementById('inputDiv').innerHTML=`<input type="file" name="profile" id="profile" accept="image/jpeg,image/jpg,image/png" style="display:none;" >`
    document.getElementById('img').innerHTML=`<img class="profileImage" src="/upload/profile/default/default.png">`
    document.getElementById('submitBtn').disabled = false
})

//프로필 사진 임시로 띄우기 위해 파일 저장 요청
async function saveTemporaryProfileImage(formData){
    try {
        const url = "/my/tmp-profile"
        const config = {
            method : "post",
            body : formData
        }
        const resp = await fetch(url,config)
        const result = await resp.text()
        return result
    } catch (error) {
        console.log(error)
    }
    
}
//아이디 중복체크 요청 메서드
async function idDuplicateCheck(memberId){
    const url = "/register/id-check"
    const config = {
        method : "post",
        body : memberId
    }
    const resp = await fetch(url,config)
    const result = await resp.text()
    return result;
}

//아이디 중복체크
document.getElementById('idCheckBtn').addEventListener('click',()=>{
    let idInput = document.getElementById('memberId')
    let idSpan = document.getElementById('idSpan')
    let memberId = idInput.value
    if(memberId == '' || memberId == null){
        idInput.classList.add('is-error')
        idSpan.classList.add('is-error')
        idSpan.innerText = " - 아이디를 입력하세요"
        return
    }
    idDuplicateCheck(memberId).then(result=>{
        if(result == "사용불가"){
            idInput.classList.add('is-error')
            idSpan.classList.add('is-error')
            idSpan.innerText = " - 이미 사용중인 아이디입니다."
            return
        }else if(result == "사용가능"){
            idInput.classList.remove('is-error')
            idSpan.classList.remove('is-error')
            idInput.classList.add('is-success')
            idSpan.classList.add('is-success')
            idSpan.innerText = " - 사용 가능한 아이디입니다."
            return
        }
    })
})

document.getElementById('nicknameCheckBtn').addEventListener('click',()=>{
    let nickInput = document.getElementById('nickname')
    let nickSpan = document.getElementById('nicknameSpan')
    let nickname = nickInput.value
    if(nickname == '' || nickname == null){
        nickInput.classList.add('is-error')
        nickSpan.classList.add('is-error')
        nickSpan.innerText = " - 닉네임을 입력하세요"
        return
    }
    nicknameDuplicateCheck(nickname).then(result=>{
        if(result == "사용불가"){
            nickInput.classList.add('is-error')
            nickSpan.classList.add('is-error')
            nickSpan.innerText = " - 이미 사용중인 닉네임입니다."
            return
        }else if(result == "사용가능"){
            nickInput.classList.remove('is-error')
            nickSpan.classList.remove('is-error')
            nickInput.classList.add('is-success')
            nickSpan.classList.add('is-success')
            nickSpan.innerText = " - 사용 가능한 닉네임입니다."
            return
        }
    })
})


async function nicknameDuplicateCheck(nickname){
    const url = "/register/nickname-check"
    const config = {
        method : "post",
        body : nickname
    }
    const resp = await fetch(url,config)
    const result = await resp.text()
    return result;
}

document.addEventListener('change',(e)=>{
    
    
    
    
    
    //프로필 사진 로직
    if(e.target.id=='profile'){
        document.getElementById('submitBtn').disabled = false
        let validResult = fileValidation(e.target.files[0].name,e.target.files[0].size)
        
        if(validResult==1){
            const formData = new FormData();
            formData.append("image", e.target.files[0])
            saveTemporaryProfileImage(formData).then(result=>{
                
                let img = document.getElementById('img')
                img.innerHTML = `<img class="profileImage" src="/upload/${result}">`
            })
        }else{
            document.getElementById('submitBtn').disabled = true
        }
    }
})