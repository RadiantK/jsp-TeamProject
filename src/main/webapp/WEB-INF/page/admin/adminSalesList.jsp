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
  <link rel="stylesheet" href="${cp}/css/adminSalesList.css">

  <!-- 부트스트랩 js -->
  <script defer src="${cp}/resource/js/bootstrap.bundle.js"></script>
  <script defer src="${cp}/js/common.js"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/page/include/header.jsp" />

  <section class="board">
    <div class="inner">

      <div class="board-title">
        <h2>매출관리</h2>
      </div>

      <form class="search-form">
        <p>년도 범위 입력 &nbsp;
          <input class="words" type="text" name="startDate" value="" placeholder="시작 범위" />
          <input class="words" type="text" disabled value="~"/>
          <input class="words" type="text" name="endDate" value="" placeholder="종료 범위" />
          <input class="smt btn btn-info" type="submit" value="검색"/>
        </p>
      </form>

      <div class="sales">
        <ul class="amount">
          <li><span>결제 금액 : 1324</span></li>
          <li><span>결제취소 금액 : 2134</span></li>
          <li><span>총 매출액 : 54564</span></li>
        </ul>
      </div>

      <div class="board-list">
        <div class="top">
          <div class="num">번호</div>
          <div class="email">이메일</div>
          <div class="name">주문자</div>
          <div class="pnum">상품번호</div>
          <div class="cnt">수량</div>
          <div class="amount">결재금액</div>
          <div class="status">상태</div>
          <div class="regDate">주문일</div>
        </div>
        <div>
          <div class="num">번호</div>
          <div class="email">pweaf12342@naver.com</div>
          <div class="name">김자바</div>
          <div class="pnum">상품번호</div>
          <div class="cnt">수량</div>
          <div class="amount">100000</div>
          <div class="status">결재완료</div>
          <div class="regDate">2020-02-02</div>
        </div>

      </div>
      
      <div class="board-page">
        <a href="#" class="bt first">
          <span class="material-symbols-outlined">
            keyboard_double_arrow_left
          </span>
        </a>
        <a href="#" class="bt prev">
          <span class="material-symbols-outlined">
            keyboard_arrow_left
          </span>
        </a>
        <a href="" class="page">1</a>
        <a href="" class="page">2</a>
        <a href="" class="page">3</a>
        <a href="" class="page">4</a>
        <a href="" class="page">5</a>
        <a href="#" class="bt next">
          <span class="material-symbols-outlined">
            keyboard_arrow_right
          </span>
        </a>
        <a href="#" class="bt last">
          <span class="material-symbols-outlined">
            keyboard_double_arrow_right
          </span>
        </a>
      </div>

    </div>
  </section>

	<jsp:include page="/WEB-INF/page/include/footer.jsp" />

</body>
</html>