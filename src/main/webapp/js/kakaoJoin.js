// 닉네임 중복 검사(ajax);
const formEl = document.getElementById('frm');
const nickNameEl = document.getElementById('nickName');
const nickNameHelpEl = document.getElementById('nickNameHelp');
const cpEl = document.getElementById('cp').value;
const submitEl = document.querySelector('.smt');

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
  xhr.open('post', cpEl+"/user/check_nick", true);
  xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
  let param = "nickName="+nickNameEl.value;
  xhr.send(param);
}

submitEl.addEventListener('click', joinHandler);

function joinHandler(){
  if(nickNameEl.value == ""){
    alert('닉네임을 입력하지 않았습니다.');
    return;
  }
  
   let xhr = new XMLHttpRequest();
  xhr.onreadystatechange = function(){
    if(xhr.readyState == 4 && xhr.status == 200){
      let data = xhr.responseText;
      let json = JSON.parse(data);

      if(json.code == true) {
        alert('중복된 닉네임이 입력되었습니다.');
      }else {
        document.getElementById('frm').submit();
      }
    }
  }
  xhr.open('post', cpEl+"/user/check_nick", true);
  xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
  let param = "nickName="+nickNameEl.value;
  xhr.send(param);
}