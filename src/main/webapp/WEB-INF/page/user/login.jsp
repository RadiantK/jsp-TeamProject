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

  <link rel="stylesheet" href="${cp}/css/login.css" />

  <script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
	<script defer src="${cp}/js/kakaoLogin.js"></script>
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
        <div class="email mb-1 row">
          <label for="email" class="form-label"></label>
          <input type="email" class="form-control fs-6" id="email" name="email" aria-describedby="emailHelp" placeholder="이메일">
          <c:if test="${not empty errorEmail}">
          	<div id="emailHelp" class="form-text">${errorEmail}</div>
          </c:if>
          <div id="emailHelp" class="form-text"></div>
        </div>
        <div class="pwd mb-3 row">
          <label for="pwd" class="form-label"></label>
          <input type="password" class="form-control fs-6" id="pwd" name="pwd" aria-describedby="pwdHelp" placeholder="비밀번호">
          <c:if test="${not empty errorPwd}">
         		<div id="pwdHelp" class="form-text">${errorPwd}</div>
          </c:if>
          <div id="pwdHelp" class="form-text"></div>
        </div>
        <div class="smt mb-2">
          <input type="button" class="submit btn btn-info" value="로그인"/>
        </div>
      </form>

      <div class="search">
        <a href="${cp}/user/search/em"><span>아이디 찾기</span></a>
        <a href="${cp}/user/search/pw"><span>비밀번호 찾기</span></a>
        <a href="${cp}/user/join"><span>회원가입</span></a>
      </div>

      <div class="sns">카카오로 간편 로그인/회원가입</div>
      <div class="kakao">
 	      <form id="kakaoForm" action="${cp}/user/kakao/login" method="post">
 	      	<input type="hidden" id="kakaoEmail" name="kakaoEmail" />
 	      	<input type="hidden" id="kakaoGender" name="kakaoGender" />
	        <a href="javascript:kakaoLogin()">
	        	<img src="${cp}/images/kakao_login.png" alt="카카오로그인">
	        </a>
        </form>
      </div>

      <div class="choose">
        <input type="button" value="비회원 주문 조회하기" />
      </div>

			<form action="${cp}/user/noneMember" id="noneFormTag" method="post">
        <div class="email mb-2">
          <input type="email" class="form-control fs-6" id="nonEmail" name="email" aria-describedby="emailHelp" placeholder="주문 이메일">
          <div id="emailHelp" class="form-text"></div>
        </div>
        <div class="pwd mb-3">
          <input type="password" class="form-control fs-6" id="nonPwd" name="pwd" aria-describedby="nonePwdHelp" placeholder="주문 비밀번호">
          <div id="nonePwdHelp" class="form-text"></div>
        </div>
        <div class="smt mb-2">
          <input type="button" class="submit nonSubmit btn btn-info" value="주문 조회"/>
        </div>
      </form>
    </div>
  </section>
</body>
</html>