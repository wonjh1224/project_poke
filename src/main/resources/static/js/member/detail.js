var modal = document.getElementById('modal');

var modalClose = document.getElementsByClassName("modal-close")[0];

let modalContent = document.getElementById('modal-content')

document.getElementById('farmAddBtn').addEventListener('click', ()=>{
    modal.style.display = "block";
})

modalClose.onclick = function() {
    modal.style.display = "none";
}

document.addEventListener('click',(e)=>{
    if(e.target.classList.contains('modal-close')){
        modal.style.display = "none";
    }
})

window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}

var maxChecked = 5;
var totalChecked = 0;

function countChecked(field){
    let postBtn = document.getElementById("postBtn")

    if(field.checked){
        totalChecked += 1;
    }else{
        totalChecked -= 1;
    }

    if(totalChecked > maxChecked){
        alert("최대 5개 가능")
        field.checked = false;
        totalChecked -= 1;
    }

    if(totalChecked == 5){
        postBtn.disabled = false;
    }

    if(totalChecked < 5){
        postBtn.disabled = true;
    }

}