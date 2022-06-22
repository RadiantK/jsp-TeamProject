<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>  
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
<script defer src="${cp}/js/deleteMember.js"></script>
<script defer src="${cp}/js/orders.js"></script>
</head>
<body>

	<!-- HEADER -->
	<jsp:include page="/WEB-INF/page/include/header.jsp" />

	<input type="hidden" id="cp" value="${cp}" />
	
	<!-- 마이페이지 카테고리 메뉴 -->
  <section class="mypage">
    <div class="inner">
      <ul class="user-menu">
        <li class="item"><a href="${cp}/user/mypage/profile">프로필</a></li>
        <li class="item"><a href="${cp}/orders/orderlistMypage">결제정보 확인</a></li>
        <li class="item"><a href="${cp}/QNA_List">문의내역 확인</a></li>
        <li class="item"><a href="${cp}/user/mypage/edit">회원정보 수정</a></li>
        <li class="item"><a href="javascript:deleteMyAccountHandler()">회원탈퇴</a></li>
      </ul>
    </div>
  </section>

<!-- 바디 메인 -->
<div class="pageWrap">

    <h3 class="title"> 주문 상세 </h3> <p> (주문일자: ${orders.regdate} | 주문번호 : ${orders.orderNum}) </p>

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
            <table style="width:100%">
            <c:set var="cnt" value="1"/>
	      	<c:forEach var="i" items="${details}">
	       	<c:set var="cnt" value="${cnt+1}"/>
            
	        	<tr>
	            <td>
	            	<img src="${cp}/upload/product/thumbnail/${i.image}" class="itemImg"> 
          			<p class="itemName"><strong> ${i.pname} </strong>
          			<br> <f:formatNumber value="${i.total}" pattern="#,###"/> 원 | ${i.piece}개 </p>
	            </td>
	           	</tr>
	           	
	        </c:forEach>   	
            </table>
            </td>
           	<td rowspan="${cnt}"> <f:formatNumber value="${orders.amount}" pattern="#,###"/> 원 </td>
          	<td rowspan="${cnt}" id="orderState">${orders.orderState}</td>
          	<td rowspan="2"> <input type="button" name="" value="주문취소" class="btnCancle" onclick="myCancle(${orders.orderNum});"></td>
         </tr>
        
    </table>

    <h3 class="title"> 주문고객 정보 </h3>
    <table class="detailTable">
      <tr>
        <th class="orderLabels"> 주문자명 </th>
        <td> ${orders.name} </td>
      </tr>
      <tr>
        <th class="orderLabels"> 전화번호 </th>
        <td> ${orders.phone} </td>
      </tr>
      <tr>
        <th class="orderLabels"> 이메일 </th>
        <td> ${orders.email } </td>
      </tr>
    </table>

    <h3 class="title"> 배송지 정보 </h3>
    <table class="detailTable">
      <tr>
        <th class="orderLabels"> 수취인명 </th>
        <td> ${delivery.delName } </td>
      </tr>
      <tr>
        <th class="orderLabels"> 주소 </th>
        <td> ${delivery.address } </td>
      </tr>
      <tr>
        <th class="orderLabels"> 전화번호 </th>
        <td> ${delivery.delPhone } </td>
      </tr>
      <tr>
        <th class="orderLabels"> 배송메시지 </th>
        <td> ${delivery.delMsg } </td>
      </tr>
    </table>

    <h3 class="title"> 결제 정보 </h3>
    <table class="detailTable">
       <tr>
        <th class="orderLabels"> 상품 합계금액 </th>
        <td> ￦ <f:formatNumber value="${orders.amount}" pattern="#,###"/> </td>
      </tr>
      <tr>
        <th class="orderLabels"> 결제수단 </th>
        <td style="color:gray"> ${payment.means } </td>
      </tr>
      <tr>
        <th class="orderLabels"> 배송비 </th>
        <td style="color:gray"> 착불(별도 문의) </td>
      </tr>
      <tr>
        <th class="orderLabels"> 최종 결제금액 </th>
        <td style="font-size: x-large;"> ￦ <f:formatNumber value="${orders.amount}" pattern="#,###"/> </td>
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