<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>내일의집</title>
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


  <link rel="stylesheet" href="${cp}/css/common.css" />
  <link rel="stylesheet" href="${cp}/css/searchPwd.css" />
  
  <!-- 부트스트랩 js -->
  <script defer src="${cp}/resource/js/bootstrap.bundle.js"></script>
  <script defer src="${cp}/js/common.js"></script>
  <script defer src="${cp}/js/searchPassword.js"></script>
</head>
<body>
 	<jsp:include page="/WEB-INF/page/include/header.jsp" />

	<input type="hidden" id="cp" value="${cp}" />

  <section class="user-search">
    <div class="inner">
      <ul class="user-menu">
        <li class="item"><a href="${cp}/user/search/em">아이디 찾기</a></li>
        <li class="item"><a href="${cp}/user/search/pw">비밀번호 찾기</a></li>

      </ul>
    </div>
  </section>

  <section class="edit">
    <div class="inner">
      <div class="box">

        <form id="formTag" action="${cp}/user/search/pw" method="post">
          <div class="email mb-1 row">
            <label for="email" class="form-label"></label>
            <input type="email" class="form-control fs-6" id="email" name="email" aria-describedby="emailHelp" placeholder="이메일">
            <div id="emailHelp" class="form-text"></div>
          </div>
          <div class="pwd mb-3 row">
            <label for="phone" class="form-label"></label>
            <input type="text" class="form-control fs-6" id="phone" name="phone" aria-describedby="pwdHelp" placeholder="연락처">
            <div id="phoneHelp" class="form-text"></div>
          </div>
          <div class="smt mb-2">
            <input type="button" class="smt btn btn-info" value="비밀번호 찾기"/>
          </div>
          <p id="result"></p>
        </form>
        
      </div>
    </div>

  </section>

 	<jsp:include page="/WEB-INF/page/include/footer.jsp" />
</body>
</html>