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

  <link rel="stylesheet" href="${cp}/css/login.css" />

  <script defer src="${cp}/resource/js/bootstrap.bundle.js"></script>
  <script defer src="${cp}/js/login.js"></script>
</head>
<body>
  <!-- section -->
  <section class="login">
    <div class="inner">
      <div>
        <a href="${cp}/main">
          <img class="logo" src="${cp}/images/house-logo.png" alt="내일의집" />
          <p class="title">
            내일의집
          </p>
        </a>
      </div>

      <form id="formTag" action="${cp}/user/login" method="post">
        <div class="email mb-1 row w-25">
          <label for="email" class="form-label"></label>
          <input type="email" class="form-control fs-6" id="email" name="email" aria-describedby="emailHelp" placeholder="이메일">
          <div id="emailHelp" class="form-text">이메일이 올바르지 않습니다</div>
        </div>
        <div class="pwd mb-3 row w-25">
          <label for="pwd" class="form-label"></label>
          <input type="password" class="form-control fs-6" id="pwd" name="pwd" aria-describedby="pwdHelp" placeholder="비밀번호">
          <div id="pwdHelp" class="form-text">비밀번호가 올바르지 않습니다.</div>
        </div>
        <div class="smt mb-2 row w-25">
          <input type="button" class="submit btn btn-info" value="로그인"/>
        </div>
      </form>

      <div class="search">
        <a href=""><span>아이디 찾기</span></a>
        <a href=""><span>비밀번호 재설정</span></a>
        <a href=""><span>회원가입</span></a>
      </div>

      <div class="sns">카카오로 간편 로그인/회원가입</div>
      <div class="kakao">
        <a href=""><img src="${cp}/images/kakao_login.png" alt="카카오로그인"></a>
      </div>

      <div class="choose">
        <input type="button" value="비회원 주문 조회하기" />
      </div>

    </div>
  </section>
</body>
</html>