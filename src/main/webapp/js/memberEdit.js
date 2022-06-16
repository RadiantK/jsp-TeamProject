const formEl = document.getElementById('frm');
const submitEl = document.querySelector('.smt');

formEl.addEventListener('keypress', function(e){
  if(e.key == 'Enter'){
    editHandler();
  }
});

submitEl.addEventListener('click', editHandler);

function editHandler(){
  // 비밀번호 정규식
  let regExpPwd = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/;
  // 닉네임 정규식(숫자,한글,영문자만 사용가능)
  let regExpNickName = /^[0-9가-힣a-zA-Z]*$/;
  // 전화번호 정규식(숫자만 사용가능)
  let regExpPhone = /^[0-9]*$/;
  
  let pwd = formEl.pwd;
  let confirmPwd = formEl.confirmPwd;
  let nickName = formEl.nickName;
  let phone = formEl.phone;

  if(!regExpPwd.test(pwd.value)){
    alert('비밀번호 양식에 맞지 않습니다.');
    pwd.focus();
    return;
  }

  if(confirmPwd.value != pwd.value){
    alert('비밀번호와 비밀번호 확인이 일치하지 않습니다.');
    confirmPwd.focus();
    return;
  }

  // if(nickName.value ) ajax
  if(nickName.value == "") {
    alert('닉네임을 입력해주세요.');
    nickName.focus();
    return;
  }

  if(!regExpNickName.test(nickName.value)){
    alert('닉네임은 특수문자를 제외한 한글, 영어, 숫자만 사용가능합니다.');
    nickName.focus();
    return;
  }

  if(!regExpPhone.test(phone.value)){
    alert("번호는 숫자로만 입력해주세요.");
    phone.focus();
    return;
  }

  if(phone.value.length < 10 || phone.value.length > 11){
    alert('잘못된 길이의 번호입니다.');
    phone.focus();
    return;
  }

  formEl.submit();
}


// 회원정보 수정시 닉네임 중복검사
const nickNameEl = document.getElementById('nickName');
const nickNameHelpEl = document.getElementById('nickNameHelp');
const cpEl = document.getElementById('cp').value;

nickNameEl.addEventListener('keyup', nickNameCheckHandler);

function nickNameCheckHandler() {
  if(nickNameEl.value.length < 2){
    nickNameHelpEl.innerHTML = "닉네임은 2자 이상 사용가능합니다.";
    return;
  }
  let regExpNickName = /^[0-9가-힣a-zA-Z]*$/;
  if(!regExpNickName.test(nickNameEl.value)){
    nickNameHelpEl.innerHTML = '닉네임은 특수문자를 제외한 한글, 영어, 숫자만 사용가능합니다.';
    return;
  }

  let xhr = new XMLHttpRequest();
  xhr.onreadystatechange = function(){
    if(xhr.readyState == 4 && xhr.status == 200){
      let data = xhr.responseText;
      let json = JSON.parse(data);

      let nickNameHelp = document.getElementById('nickNameHelp');

      if(json.code == true) {
        if(json.nick == 'myNickName'){
          nickNameHelp.innerHTML = '현재 자신의 닉네임입니다.';
        }else {
          nickNameHelp.innerHTML = '중복된 닉네임입니다.';
        }
      }else {
        nickNameHelp.innerHTML = '사용가능한 닉네임입니다.';
      }
    }
  }
  xhr.open('post', cpEl+"/user/check_nick", true);
  xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
  let param = "nickName="+nickNameEl.value;
  xhr.send(param);
}