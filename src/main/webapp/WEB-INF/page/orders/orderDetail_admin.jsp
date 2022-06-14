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
<form id="orderForm" name="orderForm" action="#" method="post">
  <h2 class="orderTitle"> [ADMIN] 주문/결제 수정 </h2>

  <h3 class="title"> 주문 상세 </h3> <p> (주문일자: 2022.06.10 | 주문번호 : 12345678) </p>
 
  <div class="orderDiv">
  <table class="itemTable" style="width:100%">
  <thead>
    <tr>
      <th style="width:70%"> 제품정보 </th>
      <th> 결제금액 </th>
      <th> 주문상태 </th>
      <th> 주문취소 </th>
    </tr>
  </thead>

  <tr>
    <td>
    <img src="../images/orderTest.png" class="itemImg">
    <p class="itemName"><strong>두닷 콰트로 에어데스크 1000</strong><br> 10,000원 / 1개 </p>
    </td>
    <td rowspan="2"> 12,500 </td>
    <td rowspan="2"> <input type="text" name="orderState" id="orderState" value="결제완료" style="width:60px" disabled> <br>
        <select name="orderStateChange" id="orderStateChange">
          <option value="결제완료"> 결제완료 </option>
          <option value="배송준비"> 배송준비 </option>
          <option value="배송중"> 배송중 </option>
          <option value="배송완료"> 배송완료 </option>
        </select>
    </td>
    <td rowspan="2"> <input type="button" name="" value="주문취소" class="btnCancle"> </td>
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
</div>

    <h3 class="title"> 주문 고객 정보 </h3>
    <div class="orderDiv">
    <table id="orderTable">
      <tr>
        <th class="orderLabels"> 주문자명 </th>
        <th> <input type="text" name="orderName" id="orderName" value="홍길동" class="inputText"> </th>
      </tr>
      <tr>
        <th class="orderLabels"> 전화번호 </th>
        <th> <input type="text" name="orderPhone" value="010-1234-5678" class="inputText"> </th>
      </tr>
      <tr>
        <th class="orderLabels"> 이메일 </th>
        <th> <input type="text" name="email1" value="abc@abc.com" class="inputText">
        </th>
      </tr>
    </table>
    </div>

    <h3 class="title"> 배송 정보 </h3>
    <div class="orderDiv">
      <table id="shippingTable">
        <tr>
          <th class="orderLabels"> 받는 사람 </th>
          <th> <input type="text" name="receiveName" value="홍길동" class="inputText"> </th>
        </tr>
        <tr>
          <th class="orderLabels"> 주소 </th>
          <th> <input type="button" name="kakaoBtn" id="kakaoBtn" value="주소찾기">
               <input type="text" name="kakaoAddr" value="12345" id="kakaoZonecode" readonly="readonly">
          </th> 
          <tr>
            <th> </th>
            <th> <input type="text" name="kakaoAddr" value="서울특별시 강남구" id="kakaoAddr" readonly="readonly" style="width:300px"></th>
          </tr>
        <tr>
          <th class="orderLabels"> 상세주소 </th>
          <th> <input type="text" name="detailAddr" value="테헤란로 123번길 45" class="inputText"></th>
        </tr>
        <tr>
          <th class="orderLabels"> 전화번호 </th>
          <th> <input type="text" name="receivePhone" value="010-1234-5678" class="inputText"></th>
        </tr>
        <tr>
          <th class="orderLabels"> 배송메시지 </th>
          <th> <input type="text" name="shippingMsg" value="배송 전 미리 연락주세요" class="inputText"> </th>
        </tr>

      </table>
    </div>

    <h3 class="title"> 결제 정보 </h3>
    <div class="orderDiv">
    <table id="paymentTable">
      <tr>
        <th class="orderLabels"> 합계 금액 </th>
        <th> ￦ 10,000 </th>
      </tr>
      <tr>
        <th class="orderLabels"> 배송비 </th>
        <th> ￦ 2,500 </th>
      </tr>
      <tr>
        <th class="orderLabels"> 최종 금액 </th>
        <th style="font-size:x-large"> ￦ 12,500 </th>
      </tr>
    </table>
    </div>

      <div class="btnDiv">
        <a href="javascript:history.back();" class="btnWhite"> 이전으로 </a> 
        <a href="" class="btnBlue"> 수정하기 </a>
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
