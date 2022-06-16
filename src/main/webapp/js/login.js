// 회원 로그인
const formEl = document.getElementById('formTag');
const submitEl = document.querySelector('.submit');

formEl.addEventListener('keypress', function(e){
  if(e.key == 'Enter') {
    loginHandler();
  }
})

submitEl.addEventListener('click', loginHandler);

function loginHandler(){

  let email = formEl.email;
  let pwd = formEl.pwd;

  if(email.value == ""){
    alert('이메일을 입력하세요');
    email.focus();
    return;
  }

  if(pwd.value == ""){
    alert("비밀번호를 입력하세요.");
    pwd.focus();
    return;
  }

  formEl.submit();
}



// 비회원 로그인
const noneFormEl = document.getElementById('noneFormTag');
const noneSubmitEl = document.querySelector('.nonSubmit');

const chooseEl = document.querySelector('.choose input');

chooseEl.addEventListener('click', function(){
  noneFormEl.style.display = "block";
}, {once: true})


noneFormEl.addEventListener('keypress', function(e){
  if(e.key == 'Enter') {
    noneLoginHandler();
  }
})

noneSubmitEl.addEventListener('click', noneLoginHandler);

function noneLoginHandler(){

  let email = noneFormEl.email;
  let pwd = noneFormEl.pwd;

  if(email.value == ""){
    alert('이메일을 입력하세요');
    email.focus();
    return;
  }

  if(pwd.value == ""){
    alert("비밀번호를 입력하세요.");
    pwd.focus();
    return;
  }

  noneFormEl.submit();
}