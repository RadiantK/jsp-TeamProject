<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
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
<link rel="stylesheet" href="${cp}/css/orders/orderMain.css"/>
<link rel="stylesheet" href="${cp}/css/admin.css">	     

<script defer src="${cp}/resource/js/bootstrap.bundle.js"></script>
<script defer src="${cp}/js/common.js"></script>
</head>
<body>
    
	<!-- HEADER -->
	<jsp:include page="/WEB-INF/page/include/header.jsp" />


<!-- 바디 메인 -->
<div class="pageWrap">
<form id="orderForm" name="orderForm" action="${cp}/admin/orderUpdateAdmin" method="post">
  <h2 class="orderTitle"> [ADMIN] 주문/결제 수정 </h2>

  <h3 class="title"> 주문 상세 </h3> <p> (주문일자: ${orders.regdate } | 주문번호 : ${orders.orderNum}) </p>
  <input type="hidden" value="${orders.orderNum}">
  <div class="orderDiv">
  <table class="itemTable" style="width:100%">
  <thead>
    <tr>
      <th style="width:70%"> 제품정보 </th>
      <th> 결제금액 </th>
      <th> 주문상태 </th>
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
		<img src="${cp}/images/${i.image}" class="itemImg"> 
        <p class="itemName"><strong> ${i.pname} </strong><br> ${i.total}원 | ${i.piece}개 </p>
	</td>
	</tr>
	</c:forEach>   	
    </table>
    </td>
    
    <td rowspan="${cnt}"> ${orders.amount} </td>
    <td rowspan="${cnt}"> <input type="text" name="orderState" id="orderState" value="${orders.orderState}" style="width:60px" disabled><br>
    <select name="orderStateChange" id="orderStateChange">
    	<option value="결제완료"> 결제완료 </option>
        <option value="배송준비"> 배송준비 </option>
        <option value="배송중"> 배송중 </option>
        <option value="배송완료"> 배송완료 </option>
    </select>
    </td>
  </tr>

  </table>
</div>

    <h3 class="title"> 주문 고객 정보 </h3>
    <div class="orderDiv">
    <table id="orderTable">
      <tr>
        <th class="orderLabels"> 주문자명 </th>
        <th> <input type="text" name="orderName" id="orderName" value="${orders.name}" class="inputText"> </th>
      </tr>
      <tr>
        <th class="orderLabels"> 전화번호 </th>
        <th> <input type="text" name="orderPhone" value="${orders.phone}" class="inputText"> </th>
      </tr>
      <tr>
        <th class="orderLabels"> 이메일 </th>
        <th> <input type="text" name="email" value="${orders.email}" class="inputText">
        </th>
      </tr>
    </table>
    </div>

    <h3 class="title"> 배송 정보 </h3>
    <div class="orderDiv">
      <table id="shippingTable">
        <tr>
          <th class="orderLabels"> 받는 사람 </th>
          <th> <input type="text" name="receiveName" value="${delivery.delName}" class="inputText"> </th>
        </tr>
        <tr>
          <th class="orderLabels"> 기존 주소 </th>
          <th> <input type="text" name="orgAddr" value="${delivery.address}" class="inputText" readonly="readonly"> </th>
        </tr>
        <tr>
          <th class="orderLabels"> 변경 주소 </th>
          <th> <input type="button" name="kakaoBtn" id="kakaoBtn" value="주소찾기">
               <input type="text" name="kakaoZonecode" value="" id="kakaoZonecode" readonly="readonly">
          </th> 
          <tr>
            <th> </th>
            <th> <input type="text" name="kakaoAddr" value="" id="kakaoAddr" readonly="readonly" style="width:300px"></th>
          </tr>
        <tr>
          <th class="orderLabels"> 상세 주소 </th>
          <th> <input type="text" name="detailAddr" value="" class="inputText"></th>
        </tr>
        <tr>
          <th class="orderLabels"> 전화번호 </th>
          <th> <input type="text" name="receivePhone" value="${delivery.delPhone}" class="inputText"></th>
        </tr>
        <tr>
          <th class="orderLabels"> 배송메시지 </th>
          <th> <input type="text" name="shippingMsg" value="${delivery.delMsg}" class="inputText"> </th>
        </tr>

      </table>
    </div>

    <h3 class="title"> 결제 정보 </h3>
    <div class="orderDiv">
    <table id="paymentTable">
      <tr>
        <th class="orderLabels"> 합계 금액 </th>
        <th> ￦ ${orders.amount} </th>
      </tr>
      <tr>
        <th class="orderLabels"> 배송비 </th>
        <th style="color:gray"> 착불(별도 문의)</th>
      </tr>
      <tr>
        <th class="orderLabels"> 최종 금액 </th>
        <th style="font-size:x-large"> ￦ ${orders.amount} </th>
      </tr>
    </table>
    </div>

      <div class="btnDiv">
        <a href="javascript:history.back();" class="btnWhite"> 이전으로 </a> 
        <input type="submit" value="수정하기" class="btnUpdate">
      </div>
</form>
</div>

<!-- 카카오주소 api -->
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
  document.getElementById("kakaoBtn").addEventListener("click", function(){
    new daum.Postcode({
        oncomplete: function(data) {
            // 주소검색 결과를 클릭했을때 실행할 내용
            document.getElementById("kakaoZonecode").value = data.zonecode
            document.getElementById("kakaoAddr").value = data.address; // 주소넣기
            document.querySelector("input[name=detailAddr]").focus(); // 상세주소 포커싱
        }
    }).open();
  }); 
</script>


   	<!-- FOOTER -->
	<jsp:include page="/WEB-INF/page/include/footer.jsp" />
    
    
</body>
</html>
