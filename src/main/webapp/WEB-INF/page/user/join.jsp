<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>YourHouse</title>
  <!-- material-icon -->
  <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
  <!-- 부트스트랩css -->
  <link rel="stylesheet" href="${cp}/resource/css/bootstrap.min.css" />
  <!-- 구글폰트  -->
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <!-- Roboto -->
  <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap" rel="stylesheet">
  <!-- Jua -->
  <link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet">


  <link rel="stylesheet" href="./css/join.css" />
  

  <script defer src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
  <script defer src="${cp}/js/kakao.js"></script>
  
  <script defer src="${cp}/resource/js/bootstrap.bundle.js"></script>
  <script defer src="${cp}/js/join.js"></script>
</head>
<body>
  <!-- section -->
  <section>
    <div class="inner">
      <div class="head">
        <a href="main.html">
          <img class="logo" src="./images/house-logo.png" alt="내일의집" />
          <p class="title">
            내일의집
          </p>
        </a>
      </div>

      <div class="join">
        <p>회원가입</p>
      </div>

      <div class="sns">카카오로 간편 로그인/회원가입</div>
      <div class="kakao">
        <a href="javascript:void(0)"><img src="./images/kakao_login.png" alt="카카오로그인"></a>
      </div>

      <div class="container-md">
        <form id="formTag" class="row g-3" action="main.html" method="post" action="main.html">
          <div class="col-auto">
            <label for="email" class="form-label">Email</label>
            <input type="email" class="form-control" id="email" name="email" placeholder="email">
          </div>
          <div class="col-auto">
            <input type="button" class="duple btn btn-info" value="중복확인" />
          </div>
          <div class="mb-1">
            <label for="pwd" class="form-label">비밀번호</label>
            <input type="password" class="form-control form-control-sm" id="pwd" name="pwd" aria-describedby="pwdHelp" />
            <div id="pwdHelp" class="form-text">영문자 숫자를 포함한 8자 이상</div>
          </div>
          <div class="mb-1">
            <label for="confirmPwd" class="form-label">비밀번호 확인</label>
            <input type="password" class="form-control form-control-sm" id="confirmPwd" name="confirmPwd" aria-describedby="confirmPwdHelp"/>
            <div id="confirmPwdHelp" class="form-text"></div>
          </div>
          <div class="mb-1">
            <label for="name" class="form-label">이름</label>
            <input type="text" class="form-control form-control-sm" id="name" name="name" aria-describedby="nameHelp"/>
            <div id="nameHelp" class="form-text">이름이 올바르지 않습니다</div>
          </div>
          <div class="mb-1">
            <label for="nickName" class="form-label">닉네임</label>
            <input type="text" class="form-control form-control-sm" id="nickName" name="nickName" aria-describedby="nickNameHelp"/>
            <div id="nickNameHelp" class="form-text">닉네임이 올바르지 않습니다</div>
          </div>
          <div class="mb-2">
            <label for="phone" class="form-label">연락처</label>
            <input type="text" class="form-control form-control-sm" id="phone" name="phone" aria-describedby="phoneHelp"/>
            <div id="phoneHelp" class="form-text">연락처는 -를 제외한 숫자로만 입력해주세요 </div>
          </div>
          <div class="mb-3">
            <label for="address" class="form-label">주소</label>
            <div class="post">
              <input type="text" id="postcode" class="form-control form-control-sm mb-2" placeholder="우편번호" />
              <input type="button" onclick="execDaumPostcode()" value="우편번호 찾기">
            </div>
            <input type="text" id="address" class="form-control form-control-sm mb-2" placeholder="주소">
            <input type="text" id="detailAddress" class="form-control form-control-sm mb-2" placeholder="상세주소">
            <!-- <input type="text" id="extraAddress" class="form-control form-control-sm" placeholder="참고항목"> -->
          </div>
          <div class="mb-3">성별 : &nbsp;
            <div class="form-check form-check-inline">
              <input class="form-check-input" type="radio" name="gender" id="gender1" value="male" checked>
              <label class="form-check-label" for="gender1">남자</label>
            </div>
            <div class="form-check form-check-inline">
              <input class="form-check-input" type="radio" name="gender" id="gender2" value="female">
              <label class="form-check-label" for="gender2">여자</label>
            </div>
          </div>
          <input type="button" class="smt btn btn-info" id="smtBtn" value="회원가입" />
        </form>

        <span class="question">이미 아이디가 있으신가요? <a href="login.html">로그인</a></span> 
      </div>
    </div>
  </section>

</body>
</html>