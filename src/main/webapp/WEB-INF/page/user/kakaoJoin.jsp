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

  <link rel="stylesheet" href="${cp}/css/kakaoJoin.css" />
  
  <script defer src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
  
  <script defer src="${cp}/resource/js/bootstrap.bundle.js"></script>
  <script defer src="${cp}/js/kakao.js"></script>
  <script defer src="${cp}/js/kakaoJoin.js"></script>
</head>
<body>
  <!-- section -->
  <section>
    <div class="inner">
      <div class="head">
        <a href="${cp}/main">
          <img class="logo" src="${cp}/images/house-logo.png" alt="내일의집" />
          <p class="title">
            내일의집
          </p>
        </a>
      </div>

			<input type="hidden" id="cp" value="${cp}" />

      <div class="join">
        <p>KAKAO 회원가입</p>
      </div>

      <div class="box">
        <form action="${cp}/user/kakao/join" method="post" class="row g-3" id="frm">
          <input type="hidden" name="gender" />
          <div class="col-auto">
            <label for="email" class="form-label">이메일</label>
            <input type="email" class="form-control" id="email" name="email" value="${email}" readonly>
          </div>
          <div class="mb-1">
            <label for="nickName" class="form-label">닉네임</label>
            <input type="text" class="form-control" id="nickName" name="nickName" aria-describedby="nickNameHelp" placeholder="한글, 영어, 숫자만 사용가능"/>
            <div id="nickNameHelp" class="form-text"></div>
          </div>
          <input type="hidden" id="gender" name="gender" value="${gender}"/>
          <input type="button" class="smt btn btn-info" id="smtBtn" value="회원가입" />
        </form>
      </div>
    </div>
  </section>

</body>
</html>