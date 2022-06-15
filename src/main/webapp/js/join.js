const formEl = document.getElementById('formTag');
const submitEl = document.getElementById('smtBtn');

formEl.addEventListener('keypress', function(e){
  if(e.key == 'Enter'){
    joinHandler();
  }
});

submitEl.addEventListener('click', joinHandler);

function joinHandler() {
  // 이메일 정규식
  let regExpEmail = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
  // 비밀번호 정규식 (하나의 숫자, 하나의 문자 이상 사용, 8자 이상)
  let regExpPwd = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/;
  // 이름 정규식(한글만 사용가능)
  let regExpName = /^[가-힣]*$/;
  // 전화번호 정규식(숫자만 사용가능)
  let regExpPhone = /^[0-9]*$/;
  // 닉네임 정규식(숫자,한글,영문자만 사용가능)
  let regExpNickName = /^[0-9가-힣a-zA-Z]*$/;
  let regExpPostcode = /^[0-9]*$/;
  let regExpAddress = /^[0-9가-힣 ]*$/;

let email = formEl.email;
  let pwd = formEl.pwd;
  let confirmPwd = formEl.confirmPwd;
  let name = formEl.name;
  let nickName = formEl.nickName;
  let phone = formEl.phone;
  let postcode = formEl.postcode;
  let address = formEl.address;

  if(!regExpEmail.test(email.value)) {
    alert('이메일 양식에 맞지 않습니다.');
    email.focus();
    return;
  }

  if(!regExpPwd.test(pwd.value)){
    alert('비밀번호 양식에 맞지 않습니다.');
    pwd.focus();
    return;
  }

  if(confirmPwd.value != pwd.value){
    // alert('비밀번호와 비밀번호 확인이 일치하지 않습니다.');
    document.getElementById('confirmPwdHelp').innerHTML = "비밀번호와 비밀번호 확인이 일치하지 않습니다.";
    confirmPwd.focus();
    return;
  }

  if(name.value == "") {
    alert('이름을 입력해주세요.');
    name.focus();
    return;
  }

  if(!regExpName.test(name.value)){
    alert('이름은 한글로만 입력해주세요.');
    name.focus();
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
  
  if(phone.value.length < 10 || phone.value.length > 11){
    alert('잘못된 길이의 번호입니다.');
    phone.focus();
    return;
  }

  if(!regExpPhone.test(phone.value)){
    alert("번호는 숫자로만 입력해주세요.");
    phone.focus();
    return;
  }

  if(postcode.value == "") {
    alert('우편번호를 입력하세요');
    postcode.focus();
    return;
  }

  if(!regExpPostcode.test(postcode.value)){
    alert('우편번호는 숫자만 사용 가능합니다.');
    postcode.focus();
    return;
  }

  if(address.value == "") {
    alert('주소를 입력해주세요.');
    address.focus();
    return;
  }

  if(!regExpAddress.test(address.value)){
    alert('주소는 한글과 숫자만 입력할 수 있습니다.');
    address.focus();
    return;
  }

  formEl.submit();
}




// 이메일 중복검사
const dupleEl = document.querySelector('.duple');
const cpEl = document.getElementById('cp').value;

dupleEl.addEventListener('click', emailCheckHandler);

function emailCheckHandler() {
  let regExpEmail = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
  let email = formEl.email;

  if(!regExpEmail.test(email.value)) {
    alert('이메일 양식에 맞지 않습니다.');
    email.focus();
    return;
  }

  let xhr = new XMLHttpRequest();
  xhr.onreadystatechange = function(){
    if(xhr.readyState == 4 && xhr.status == 200){
      let data = xhr.responseText;

      let json = JSON.parse(data);

      if(json.code == true){
        alert('중복된 이메일이 존재합니다.');
      }else {
        alert('사용가능한 이메일입니다.')
      }
    }
  }
  xhr.open('post', cpEl+"/user/join/check_email", true);
  xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
  let param = 'email='+email.value;
  xhr.send(param);
}




// 닉네임 중복 검사(ajax);
const nickNameEl = document.getElementById('nickName');
const nickNameHelpEl = document.getElementById('nickNameHelp');

nickNameEl.addEventListener('keyup', nickNameCheckHandler);

function nickNameCheckHandler(){
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
        nickNameHelp.innerHTML = '중복된 닉네임입니다.';
      }else {
        nickNameHelp.innerHTML = '사용가능한 닉네임입니다.';
      }
    }
  }
  xhr.open('post', cpEl+"/user/join/check_nick", true);
  xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
  let param = "nickName="+nickNameEl.value;
  xhr.send(param);
}