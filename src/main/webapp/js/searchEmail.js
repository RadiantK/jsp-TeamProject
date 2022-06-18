const formEl = document.getElementById('formTag');
const submitEl = document.querySelector('.smt');
const cpEl = document.getElementById('cp').value;

formEl.addEventListener('keypress', function(e){
  if(e.key == 'Enter') {
    searchEmailHandler();
  }
});

submitEl.addEventListener('click', searchEmailHandler);

function searchEmailHandler(){
  // 전화번호 정규식(숫자만 사용가능)
  let regExpPhone = /^[0-9]*$/;

  let name = formEl.name;
  let phone = formEl.phone;

  if(name.value == ""){
    alert('이름을 입력하세요');
    name.focus();
    return;
  }
  
  if(phone.value == ""){
    alert('연락처를 입력하세요');
    phone.focus();
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

  let xhr = new XMLHttpRequest();
  xhr.onreadystatechange = function(){
    if(xhr.readyState == 4 && xhr.status == 200){
      let data = xhr.responseText;
      let json = JSON.parse(data);

      let result = document.getElementById('result');
      if(json.code == true){
        result.innerHTML = "정보와 일치하는 이메일 : " + json.em 
      }else {
        alert('정보와 일치하는 이메일이 없습니다.');
      }
    }
  }
  xhr.open('post', cpEl+"/user/search/where/em");
  xhr.setRequestHeader('Content-Type', "application/x-www-form-urlencoded");
  let param = "name="+name.value + "&phone="+phone.value;
  xhr.send(param);
}