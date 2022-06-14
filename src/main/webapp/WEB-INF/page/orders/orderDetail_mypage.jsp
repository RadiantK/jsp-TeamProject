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
<link rel="stylesheet" href="${cp}/css/mypage.css" />
<link rel="stylesheet" href="${cp}/css/orders/orderMain.css"/>
	     
<script defer src="${cp}/resource/js/bootstrap.bundle.js"></script>
<script defer src="${cp}/js/common.js"></script>
</head>
<body>
    
	<!-- HEADER -->
	<jsp:include page="/WEB-INF/page/include/header.jsp" />

	<!-- 마이페이지 카테고리 -->
  <section class="mypage">
    <div class="inner">
      <ul class="user-menu">
        <li class="item"><a href="userEdit.html">프로필</a></li>
        <li class="item"><a href="${cp}/orders/orderlistMypage">결제정보 확인</a></li>
        <li class="item"><a href="">문의내역 확인</a></li>
        <li class="item"><a href="userEdit.html">회원정보 수정</a></li>
      </ul>
    </div>
  </section>

<!-- 바디 메인 -->
<div class="pageWrap">

    <h3 class="title"> 주문 상세 </h3> <p> (주문일자: 2022.06.10 | 주문번호 : 12345678) </p>

    <table class="detail_itemTable">
      <thead>
        <tr>
          <th style="width:65%"> 제품정보 </th>
          <th> 결제금액 </th>
          <th> 주문상태 </th>
          <th> 주문취소 </th>
        </tr>
      </thead>
        <tr>
          <td>
          <img src="../images/orderTest.png" class="itemImg"> 
          <p class="itemName"><strong>두닷 콰트로 에어데스크 1000 </strong><br> 10,000원 / 1개</p>
          </td>
          <td rowspan="2"> 45,000 </td>
          <td rowspan="2"> 결제완료 </td>
          <td rowspan="2"> <input type="button" name="" value="주문취소" class="btnCancle"></td>
        </tr>
        <tr>
          <td>
          <img src="../images/orderTest2.jpg" class="itemImg">
          <p class="itemName"><strong>시디즈 화이트쉘</strong><br> 15,000원 / 2개 </p>
          </td>
          <td></td>
          <td></td>
          <td></td>
        </tr>
    </table>

    <h3 class="title"> 주문고객 정보 </h3>
    <table class="detailTable">
      <tr>
        <th class="orderLabels"> 주문자명 </th>
        <td> 홍길동 </td>
      </tr>
      <tr>
        <th class="orderLabels"> 전화번호 </th>
        <td> 010-1234-5678 </td>
      </tr>
      <tr>
        <th class="orderLabels"> 이메일 </th>
        <td> abc@aaa.com </td>
      </tr>
    </table>

    <h3 class="title"> 배송지 정보 </h3>
    <table class="detailTable">
      <tr>
        <th class="orderLabels"> 수취인명 </th>
        <td> 홍길동 </td>
      </tr>
      <tr>
        <th class="orderLabels"> 주소 </th>
        <td> (12345) 서울특별시 강남구 </td>
      </tr>
      <tr>
        <th class="orderLabels"> 전화번호 </th>
        <td> 010-1234-5678 </td>
      </tr>
      <tr>
        <th class="orderLabels"> 배송메시지 </th>
        <td> 배송 전 미리 연락주세요 </td>
      </tr>
    </table>

    <h3 class="title"> 결제 정보 </h3>
    <table class="detailTable">
      <tr>
        <th class="orderLabels"> 상품 합계금액 </th>
        <td> ￦ 10,000 </td>
      </tr>
      <tr>
        <th class="orderLabels"> 배송비 </th>
        <td> ￦ 2,500 </td>
      </tr>
      <tr>
        <th class="orderLabels"> 최종 결제금액 </th>
        <td style="font-size: x-large;"> ￦ 12,500 </td>
      </tr>
    </table>

    <div class="btnDiv">
    	<a href="javascript:history.back();" class="btnWhite"> 이전으로 </a> 
   	</div>
</div>


   	<!-- FOOTER -->
	<jsp:include page="/WEB-INF/page/include/footer.jsp" />
    
    
</body>
</html>