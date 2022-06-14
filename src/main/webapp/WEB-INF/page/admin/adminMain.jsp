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
  <link rel="stylesheet" href="${cp}/css/admin.css">

  <!-- 부트스트랩 js -->
  <script defer src="${cp}/resource/js/bootstrap.bundle.js"></script>
  <script defer src="${cp}/js/common.js"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/page/include/header.jsp" />

  <!-- 관리자용 메뉴 -->
  <section class="admin">
    <div class="inner">
      <ul class="admin-menu">
        <li class="item">
          <a href="userManagement.html">
            <span class="material-symbols-outlined">
              account_circle
            </span>
            <div class="content">회원관리</div>
          </a>
        </li>
        <li class="item">
          <a href="salesManagement.html">
            <span class="material-symbols-outlined">
              paid
            </span>
            <div class="content">매출관리</div>
          </a>
        </li>
        <li class="item">
          <a href="">
            <span class="material-symbols-outlined">
              inventory_2
            </span>
            <div class="content">제품관리</div>
          </a>
        </li>
        <li class="item">
          <a href="">
            <span class="material-symbols-outlined">
              receipt_long
            </span>
            <div class="content">결제/배송</div>
          </a>
        </li>
        <li class="item">
          <a href="${cp }/QNA_List">
            <span class="material-symbols-outlined">
              contact_support
            </span>
            <div class="content">문의관리</div>
          </a>
        </li>
      </ul>
    </div>
  </section>

	<jsp:include page="/WEB-INF/page/include/footer.jsp" />

</body>
</html>