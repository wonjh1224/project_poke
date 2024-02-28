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
    let idSpan = document.getElementById('memberIdSpan')
    let memberId = idInput.value
    if(memberId == '' || memberId == null){
        idInput.className = "nes-input is-error"
        idSpan.className = "nes-text is-error"
        idSpan.innerText = " - 아이디를 입력하세요."
        return
    }
    if(memberId.includes(' ')){
        idInput.className = "nes-input is-error"
        idSpan.className = "nes-text is-error"
        idSpan.innerText = " - 띄어쓰기는 사용할 수 없습니다."
        return
    }
    idDuplicateCheck(memberId).then(result=>{
        if(result == "사용불가"){
            idInput.className = "nes-input is-error"
            idSpan.className = "nes-text is-error"
            idSpan.innerText = " - 이미 사용중인 아이디입니다."
            return
        }else if(result == "사용가능"){
            idInput.className = "nes-input is-success"
            idSpan.className = "nes-text is-success"
            idSpan.innerText = " - 사용 가능한 아이디입니다."
            return
        }
    })
})



//닉네임 중복체크
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
    if(nickname.includes(' ')){
        nickInput.className = "nes-input is-error"
        nickSpan.className = "nes-text is-error"
        nickSpan.innerText = " - 띄어쓰기는 사용할 수 없습니다."
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

function emailCheck(email_address){     
	email_regex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/i;
	if(!email_regex.test(email_address)){ 
		return false; 
	}else{
		return true;
	}
}

//회원가입 폼 검증
document.addEventListener('input',(e)=>{
    let pwInput = document.getElementById('password')
    let pwSpan = document.getElementById('passwordSpan')
    let pw2Input = document.getElementById('password2')
    let pw2Span = document.getElementById('password2Span')

    let emailInput = document.getElementById('email')
    let emailSpan = document.getElementById('emailSpan')

    
    if(pwInput.value != pw2Input.value){
        pwInput.classList.remove('is-success')
        pw2Input.classList.remove('is-success')

        pwInput.classList.add('is-error')
        pw2Input.classList.add('is-error')

        pwSpan.className = "nes-text is-error"
        pwSpan.innerText = " - 동일한 비밀번호를 입력해 주세요."
        pw2Span.className = "nes-text is-error"
        pw2Span.innerText = " - 동일한 비밀번호를 입력해 주세요."
    }
    if(pwInput.value == pw2Input.value && pw2Input.value != ''){
        pwInput.classList.remove('is-error')
        pw2Input.classList.remove('is-error')

        pwInput.classList.add('is-success')
        pw2Input.classList.add('is-success')

        pwSpan.className = "nes-text is-success"
        pw2Span.className = "nes-text is-success"

        pwSpan.innerText = ""
        pw2Span.innerText = ""
    }

    if(emailInput.value == ''){
        emailInput.className = "nes-input"
    }
    if(emailInput.value != '' && emailCheck(emailInput.value)){
        emailInput.className = "nes-input is-success"
        emailSpan.className = "nes-text is-success"
        emailSpan.innerText = ""
    }
    if(emailInput.value != '' && !emailCheck(emailInput.value)){
        emailInput.className = "nes-input is-error"
        emailSpan.className = "nes-text is-error"
        emailSpan.innerText = " - 이메일 형식이 올바르지 않습니다."
    }

    if(e.target.id == 'memberId' || e.target.id == 'nickname'){
        e.target.className = "nes-input"
        let span = e.target.id + "Span"
        document.getElementById(span).className = "nes-text"
        document.getElementById(span).innerText = ""
    }

    if(e.target.value.includes(' ')){
        alert("띄어쓰기는 사용할 수 없습니다.")
        e.target.value = ""
    }
})

document.getElementById('submitBtn').addEventListener('click',()=>{
    for(input of document.querySelectorAll(".nes-input")){
        if(!input.classList.contains('is-success')){
            alert('입력한 정보를 확인해 주세요.')
            input.focus()
            return
        }
    }
})



//프로필 사진 로직
document.addEventListener('change',(e)=>{
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