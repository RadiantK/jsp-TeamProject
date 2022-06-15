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

  <!-- 마이페이지 주문/결제정보 -->
  <div class="pageWrap">

  <div class="cntDiv">
    <table class="cntTable">
      <tr>
        <th class="cntTitle"> 입금대기 
        <br> <span class="cnt"> 0 </span>
        </th>
        <td class="arrow"> > </td>
        
        <th class="cntTitle"> 결제완료 
        <br> <span class="cnt"> 1 </span>
        </th>
        <td class="arrow"> > </td>
        
        <th class="cntTitle"> 배송중 
          <br> <span class="cnt"> 1 </span>
          </th>
          <td class="arrow"> > </td>

        <th class="cntTitle"> 배송완료 
        <br> <span class="cnt"> 0 </span>
        </th>
        <td class="arrow"> > </td>

        <th class="cntTitle"> 주문취소
          <br> <span class="cnt"> 0 </span>
        </th>
      </tr>
    </table>
  </div>

  <div class="listDiv">
    <table class="listTable">
        <thead>
        <tr>
            <th colspan="2" class="tableOrdernum"> 주문일자: 2022.06.10 | 주문번호: 12345678 </th>
            <th class="goOrderdetail"> <a href="${cp }/orders/orderdetailMypage" style="color:darkgray"> 상세보기 > </a> </th>
        </tr>
        </thead>
        <tr>
            <th>
            <img src="../images/orderTest.png" class="itemImg">
            </th>
            <th class="itemName"> <a href="${cp }/orders/orderdetailMypage">두닷 콰트로 에어데스크 1000 외 </a>
              <br> <span class="itemPay"> 결제금액 ￦ 45,000 </span>
            </th>
            <th> <p class="orderState"> 결제완료 </p> </th>
        </tr>
    </table>
  </div>

  <div class="listDiv">
    <table class="listTable">
        <thead>
        <tr>
            <th colspan="2" class="tableOrdernum"> 주문일자: 2022.06.12 | 주문번호: 43219865 </th>
            <th class="goOrderdetail"> <a href="orderDetail_mypage.html" style="color:darkgray"> 상세보기 > </a> </th>
        </tr>
        </thead>
        <tr>
            <th>
            <img src="../images/orderTest2.jpg" class="itemImg">
            </th>
            <th class="itemName"> <a href=""> 시디즈 화이트쉘 </a>
              <br> <span class="itemPay"> 결제금액 ￦ 30,000 </span>
            </th>
            <th><p class="orderState"> 배송중 </p></th>
        </tr>
    </table>
  </div>
 
 <div class="nullDiv">
  <span class="preBtn"> < </span>
  <span class="curPage"> 1 </span>
  <span class="otherPage"> 2 </span>
  <span class="nextBtn"> > </span>
</div>

<div class="searchDiv">
  <select class="selectDiv">
    <option> 주문번호 </option>
    <option> 주문회원 </option>
    <option> 주문제품 </option>
  </select>
  <input type="text" class="searchText">
  <input type="button" value="검색" class="searchBtn">
</div>

 </div>

   	<!-- FOOTER -->
	<jsp:include page="/WEB-INF/page/include/footer.jsp" />
    
    
</body>
</html>