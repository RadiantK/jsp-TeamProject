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
  let regExpName = /^[가-힣]*$/;
  let regExpPhone = /^[0-9]*$/;
  let regExpAddress = /^[0-9가-힣]*$/;

  let email = formEl.email;
  let pwd = formEl.pwd;
  let confirmPwd = formEl.confirmPwd;
  let name = formEl.name;
  let nickName = formEl.nickName;
  let phone = formEl.phone;
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
    document.getElementById('confirmPwdHelp').style.color="red";
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