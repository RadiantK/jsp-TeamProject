<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
  <link rel="stylesheet" href="${cp}/css/deleteMember.css" />
  
  <!-- 부트스트랩 js -->
  <script defer src="${cp}/resource/js/bootstrap.bundle.js"></script>
  <script defer src="${cp}/js/common.js"></script>
  <script defer src="${cp}/js/deleteMember.js"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/page/include/header.jsp" />
	
	  <!-- 페이지 메뉴 -->
  <section class="mypage">
    <div class="inner">
      <ul class="user-menu">
        <li class="item"><a href="${cp}/user/mypage/profile">프로필</a></li>
        <li class="item"><a href="${cp}/orders/orderlistMypage">결제정보 확인</a></li>
        <li class="item"><a href="${cp}/QNA_List">문의내역 확인</a></li>
        <li class="item"><a href="${cp}/user/mypage/edit">회원정보 수정</a></li>
        <li class="item"><a href="${cp}/user/mypage/delmem">회원탈퇴</a></li>
      </ul>
    </div>
  </section>

	<input type="hidden" id="cp" value="${cp}" />
	
  <section class="delete">
    <div class="inner">
      <form action="${cp}/user/mypage/delmem" id="formTag" method="post">
        <div class="mb-5 row g-3 align-items-center">
          <div class="col-auto">
            <label for="pwd" class="text col-form-label">비밀번호</label>
          </div>
          <div class="col-auto">
            <input type="password" id="pwd" name="pwd" class="form-control" aria-describedby="pwdHelp" placeholder="비밀번호를 입력하세요.">
          </div>
          <div class="col-auto">
          	<c:if test="${not empty errorMsg}">
          		<span id="pwdHelp" class="form-text">${errorMsg}</span>
          	</c:if>
            <span id="pwdHelp" class="form-text"></span>
          </div>
        </div>

        <input type="button" class="smt btn btn-info" value="탈퇴하기" />
      </form>
    </div>
  </section>
	
 	<jsp:include page="/WEB-INF/page/include/footer.jsp" />
</body>
</html>