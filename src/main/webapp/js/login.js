// 비회원 로그인 인풋 생성
const loginEl = document.querySelector('.inner');
const chooseEl = document.querySelector('.choose input');

chooseEl.addEventListener('click', function() {

  let noneFormEl = document.createElement('form');
  noneFormEl.setAttribute('id', 'noneFormTag');
  noneFormEl.setAttribute('action', 'main.html');
  noneFormEl.setAttribute('method', 'post');

  let firstDivEl = document.createElement('div');
  firstDivEl.setAttribute('class', 'email mb-2 row w-25');

  let inputEmailEl = document.createElement('input');
  inputEmailEl.setAttribute('class', 'form-control fs-6');
  inputEmailEl.setAttribute('type', 'email');
  inputEmailEl.setAttribute('id', 'email');
  inputEmailEl.setAttribute('name', 'email');
  inputEmailEl.setAttribute('aria-describedby', 'emailHelp');
  inputEmailEl.setAttribute('placeholder', '이메일');

  let emailHelp = document.createElement('div');
  emailHelp.setAttribute('id', 'emailHelp');
  emailHelp.setAttribute('class', 'form-text');

  firstDivEl.appendChild(inputEmailEl);
  firstDivEl.appendChild(emailHelp);
  noneFormEl.appendChild(firstDivEl);

  let secondDivEl = document.createElement('div');
  secondDivEl.setAttribute('class', 'pwd mb-3 row w-25');

  let inputPwdEl = document.createElement('input');
  inputPwdEl.setAttribute('class', 'form-control fs-6');
  inputPwdEl.setAttribute('type', 'password');
  inputPwdEl.setAttribute('id', 'pwd');
  inputPwdEl.setAttribute('name', 'pwd');
  inputPwdEl.setAttribute('aria-describedby', 'pwdHelp');
  inputPwdEl.setAttribute('placeholder', '주문 비밀번호');

  let pwdHelp = document.createElement('div');
  pwdHelp.setAttribute('id', 'pwdHelp');
  pwdHelp.setAttribute('class', 'form-text');

  secondDivEl.appendChild(inputPwdEl);
  secondDivEl.appendChild(pwdHelp);
  noneFormEl.appendChild(secondDivEl);

  let thirdInputEl = document.createElement('div');
  thirdInputEl.setAttribute('class', 'smt mb-2 row w-25');

  let submitBtn = document.createElement('input');
  submitBtn.setAttribute('type', 'button');
  submitBtn.setAttribute('class', 'submit nonSubmit btn btn-info');
  submitBtn.setAttribute('value', '주문 조회');

  thirdInputEl.appendChild(submitBtn);
  noneFormEl.appendChild(thirdInputEl);

  loginEl.appendChild(noneFormEl);
}, {once: true});



// 회원 로그인
const formEl = document.getElementById('formTag');
const submitEl = document.querySelector('.submit');

submitEl.addEventListener('click', function(){
  if(formEl.email.value == ""){
    alert('이메일을 입력하세요');
    formEl.email.focus();
    return;
  }
  if(formEl.pwd.value == ""){
    alert('비밀번호를 입력하세요.');
    formEl.pwd.focus();
    return;
  }

  formEl.submit();
});
