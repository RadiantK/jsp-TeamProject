const formEl = document.getElementById('formTag');
const submitEl = document.querySelector('.smt');
const cpEl = document.getElementById('cp').value;

formEl.addEventListener('keypress', function(e){
  if(e.key == 'Enter'){
  	e.preventDefault();
  }
});

submitEl.addEventListener('click', deleteMyAccountHandler);

function deleteMyAccountHandler(){
  let passwordEl = document.getElementById('pwd');
  if(passwordEl.value == ""){
    alert("비밀번호를 입력하세요.");
    passwordEl.focus();
    return;
  }

  let checkMsg = confirm('회원탈퇴하시겠습니까?');
  if(checkMsg){
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function(){
      if(xhr.readyState == 4 && xhr.status == 200){
        let data = xhr.responseText;

        let json = JSON.parse(data);
        let pwdHelp = document.getElementById('pwdHelp');
        
				console.log(json);
        if(json.code == true){
          alert('회원탈퇴 되었습니다.');
          document.getElementById('formTag').submit();
        }else {
          pwdHelp.innerHTML = '비밀번호가 일치하지 않습니다.';
        }
      }
    }
    xhr.open('post' , cpEl+"/user/mypage/check_pwd", true);
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    let param = "pwd=" + passwordEl.value;
    xhr.send(param);
  } else {
    alert('취소되었습니다.');
    return;
  }
}