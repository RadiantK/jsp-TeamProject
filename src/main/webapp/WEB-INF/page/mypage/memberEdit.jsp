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


  <link rel="stylesheet" href="${cp}/css/common.css" />
  <link rel="stylesheet" href="${cp}/css/memberEdit.css" />
  
  <!-- 부트스트랩 js -->
  <script defer src="${cp}/resource/js/bootstrap.bundle.js"></script>
  <script defer src="${cp}/js/common.js"></script>
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
      </ul>
    </div>
  </section>


  <section class="edit">
    <div class="inner">
      <form action="" id="frm">
        <div class="mb-5 row g-3 align-items-center">
          <div class="col-auto">
            <label for="email" class="text col-form-label">이메일</label>
          </div>
          <div class="col-auto">
            <input type="email" id="email" name="email" class="form-control" readonly />
          </div>
        </div>

        <div class="mb-5 row g-3 align-items-center">
          <div class="col-auto">
            <label for="pwd" class="text col-form-label">새 비밀번호</label>
          </div>
          <div class="col-auto">
            <input type="password" id="pwd" class="form-control" name="pwd" aria-describedby="pwdHelp">
          </div>
          <div class="col-auto">
            <span id="pwdHelp" class="form-text">
              Must be 8-20 characters long.
            </span>
          </div>
        </div>
        <div class="mb-5 row g-3 align-items-center">
          <div class="col-auto">
            <label for="confirmPwd" class="text col-form-label">새 비밀번호 확인</label>
          </div>
          <div class="col-auto">
            <input type="password" id="confirmPwd" name="confirmPwd" class="form-control" aria-describedby="confirmPwdHelp">
          </div>
          <div class="col-auto">
            <span id="confirmPwdHelp" class="form-text">
              Must be 8-20 characters long.
            </span>
          </div>
        </div>
        <div class="mb-5 row g-3 align-items-center">
          <div class="col-auto">
            <label for="nickName" class="text col-form-label">닉네임</label>
          </div>
          <div class="col-auto">
            <input type="text" id="nickName" name="nickName" class="form-control" aria-describedby="nickNameHelp">
          </div>
          <div class="col-auto">
            <span id="nickNameHelp" class="form-text">
              Must be 8-20 characters long.
            </span>
          </div>
        </div>
        <div class="mb-5 row g-3 align-items-center">
          <div class="col-auto">
            <label for="phone" class="text col-form-label">연락처</label>
          </div>
          <div class="col-auto">
            <input type="text" id="phone" name="phone" class="form-control" aria-describedby="phoneHelp">
          </div>
          <div class="col-auto">
            <span id="phoneHelp" class="form-text">
              Must be 8-20 characters long.
            </span>
          </div>
        </div>
        <div class="mb-5 row g-3 align-items-center">
          <div class="col-auto">
            <label for="regDate" class="text col-form-label">가입일</label>
          </div>
          <div class="col-auto">
            <input type="text" id="regDate" name="regDate" class="form-control" readonly/>
          </div>
        </div>
        <div class="mb-5">성별 : &nbsp;
          <div class="form-check form-check-inline">
            <input class="form-check-input" type="radio" name="gender" id="gender1" value="male" checked>
            <label class="form-check-label" for="gender1">남자</label>
          </div>
          <div class="form-check form-check-inline">
            <input class="form-check-input" type="radio" name="gender" id="gender2" value="female">
            <label class="form-check-label" for="gender2">여자</label>
          </div>
        </div>
        <input type="button" class="smt btn btn-info" value="정보수정" />
      </form>
    </div>
  </section>
	
 	<jsp:include page="/WEB-INF/page/include/footer.jsp" />
</body>
</html>