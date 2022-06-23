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
  <link rel="stylesheet" href="${cp}/css/searchEmail.css" />
  
  <!-- 부트스트랩 js -->
  <script defer src="${cp}/resource/js/bootstrap.bundle.js"></script>
  <script defer src="${cp}/js/common.js"></script>
  <script defer src="${cp}/js/searchEmail.js"></script>
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

        <form id="formTag" action="${cp}/user/search/em" method="post">
          <div class="name mb-1 row">
            <label for="name" class="form-label"></label>
            <input type="text" class="form-control fs-6" id="name" name="name" aria-describedby="emailHelp" placeholder="이름">
            <div id="nameHelp" class="form-text"></div>
          </div>
          <div class="pwd mb-3 row">
            <label for="phone" class="form-label"></label>
            <input type="text" class="form-control fs-6" id="phone" name="phone" aria-describedby="pwdHelp" placeholder="연락처(숫자만 입력)">
            <div id="pwdHelp" class="form-text"></div>
          </div>
          <div class="smt mb-2">
            <input type="button" class="smt btn btn-info" value="아이디 찾기"/>
          </div>
          <p id="result"></p>
        </form>
        
      </div>
    </div>

  </section>

 	<jsp:include page="/WEB-INF/page/include/footer.jsp" />
</body>
</html>