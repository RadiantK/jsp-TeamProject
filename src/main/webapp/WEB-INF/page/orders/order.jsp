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
	     
<script defer src="${cp}/resource/js/bootstrap.bundle.js"></script>
<script defer src="${cp}/js/common.js"></script>
</head>
<body>
    
	<!-- HEADER -->
	<jsp:include page="/WEB-INF/page/include/header.jsp" />

	<!-- 바디 메인 -->
	<div class="pageWrap">
	
  	<form id="orderForm" name="orderForm" onsubmit="return false()">
  
    <h2 class="orderTitle"> 주문/결제 </h2>

    <h3 class="title"> 제품 </h3>
    <div class="orderDiv">
    
    <table class="itemTable" style="width:100%">
      <thead>
        <th style="width:500px"> 제품정보 </th>
        <th> 수량 </th>
        <th> 금액 </th>
        <th> 할인가 </th>
        <th> 합계 </th>
        <th> 배송비 </th>
      </thead>

 	<!-- 제폼종류 개수만큼 반복 -->
 	<c:forEach var="i" items="${orderList}">
 	<input type="hidden" name="itemName" id="itemName" value="${i.itemName}">
 	<input type="hidden" name="itemPiece" id="itemPiece" value="${i.piece}">
 	<input type="hidden" name="itemNum" id="itemNum" value="${i.itemNum}">
 	<input type="hidden" name="itemTotal" id="itemTotal" value="${i.itemTotal}">
 	<input type="hidden" name="itemImg" id="itemImg" value="${i.itemImg}">
 
      <tr>
        <th>
          <img src="${cp}/images/${i.itemImg}" class="itemImg">
          <p class="itemName"> ${i.itemName} </p>
        </th>
        <td> ${i.piece} </td>
        <td>${i.price} </td>
        <td style="text-decoration:underline">${i.discountPrice} </td>
        <td class="sumPrice"> ${i.itemTotal} </td>
        <td style="color:darkgray"> 착불 </td>
      </tr> 
	</c:forEach>
	
    </table>
    
    <!-- 결제금액 계산내역 -->
    <div class="itemPrice">
      총 ${totalCnt}개의 상품의 합계  <strong>￦ ${total} </strong>
       + 배송비 착불 = <span class="totalPrice">  ￦ ${total}  </span>
    </div>
    </div>

    <h3 class="title"> 주문 고객 정보 </h3>
    <div class="orderDiv">
    
    <table class="orderTable">
      <tr>
        <th class="orderLabels"> 주문자명<p style="color:red; float:left;">*</p> </th>
        <td> <input type="text" name="orderName" id="orderName" value="${mname}" notNull="true" class="inputText"> </td>
      </tr>
      <tr>
        <th class="orderLabels"> 전화번호<p style="color:red; float:left;">*</p> </th>
        <td> <input type="text" name="orderPhone" id="orderPhone" value="${phone}" notNull="true" class="inputText"> </td>
      </tr>
      <tr>
        <th class="orderLabels"> 이메일 </th>
        <td> <input type="text" name="email1" id="email1" value="" class="inputText"> @
        <input type="text" name="email2" id="email2" value="" disabled>
        <select name="email" onchange="emailChange()">
          <option value="0"> 메일주소 선택 </option>
          <option value="naver.com"> naver.com </option>
          <option value="gmail.com"> gmail.com </option>
          <option value="hanmail.net"> hanmail.net </option>
          <option value="9"> 직접입력 </option>
        </select>
        </td>
      </tr>

		<!-- 비회원인 경우 주문비밀번호 입력창 -->
	    <c:choose>
		    <c:when test="${empty sessionId}">
		    <input type="hidden" name="mnum" value="A100">
		    
		    <tr>
		    	<th class="orderLabels"> 주문비밀번호 <p style="color:red; float:left;">*</p> </th>
		    	<td><input type="password" name="orderPwd" id="orderPwd" value="" notNull="true" class="inputText"></td>
		    </tr>
			</c:when>
	  
		    <c:otherwise>
				<input type="hidden" name="orderPwd" id="orderPwd" value="0" class="inputText">
			   	<input type="hidden" name="mnum" value="${mnum}">
		    </c:otherwise>
	    
	    </c:choose>
	
    </table>
    </div>

    <h3 class="title"> 배송 정보 </h3>
    <div class="orderDiv">
      <table class="shippingTable">
        <tr>
          <th class="orderLabels"> 배송지 확인 </th>
          <td> <input type="radio" name="shipping" class="checkAddr" onclick="copyInfo(1)"> 직접 입력
               <input type="radio" name="shipping" class="checkAddr" onclick="copyInfo(2)"> 주문자와 동일
          </td>
        </tr>
        <tr>
          <th class="orderLabels"> 받는 사람<p style="color:red; float:left;">*</p> </th>
          <td> <input type="text" name="receiveName" value="" id="receiveName" notNull="true" class="inputText"> </td>
        </tr>
        <tr>
          <th class="orderLabels"> 주소<p style="color:red; float:left;">*</p> </th>
          <td> <input type="button" name="kakaoBtn" id="kakaoBtn" value="주소찾기">
               <input type="text" name="kakaoZonecode" value="" id="kakaoZonecode" notNull="true" readonly="readonly">
          </td> 
        </tr>
        <tr>
            <th></th>
            <td><input type="text" name="kakaoAddr" value="" id="kakaoAddr" readonly="readonly" style="width:300px"></td>
        </tr>
        <tr>
          <th class="orderLabels"> 상세주소 </th>
          <th> <input type="text" name="detailAddr" value="" class="inputText"></th>
        </tr>
        <tr>
          <th class="orderLabels"> 전화번호<p style="color:red; float:left;">*</p> </th>
          <td> <input type="text" name="receivePhone" id="receivePhone" value="" notNull="true" class="inputText"></td>
        </tr>
        <tr>
          <th class="orderLabels"> 배송메시지 </th>
          <td> <input type="text" name="shippingMsg" value="" class="inputText"> </td>
        </tr>
      </table>
    </div>

    <h3 class="title"> 결제 정보 </h3>
    <div class="orderDiv">
    <input type="hidden" name="total" id="total" value="${total}">
    <input type="hidden" name="cartCnt" value="${cartCnt}">
    
    <table id="paymentTable">
      <tr>
        <th class="orderLabels"> 합계 금액 </th>
        <td> ￦ ${total} </td>
      </tr>
      <tr>
        <th class="orderLabels"> 배송비 </th>
        <td style="color:darkgray"> 착불(별도 문의) </td>
      </tr>
      <tr>
        <th class="orderLabels"> 최종 금액 </th>
        <th style="font-size:x-large"> ￦ ${total} </th>
      </tr>
    </table>
    </div>

    <h3 class="title"> 결제 수단 </h3>
    <div class="orderDiv2">
      <input type="radio" name="payOption" value="card" class="payOption"> 신용카드
      <input type="radio" name="payOption" value="2" class="payOption"> 실시간 계좌이체
      <input type="radio" name="payOption" value="2" class="payOption"> 무통장입금
      <input type="radio" name="payOption" value="kakaopay" class="payOption"> 카카오페이
    </div>

    <div id="checkDiv">
      <input type="checkbox" name="agree" id="agree"><p style="color:red; float:left;">(필수)</p> 구매상품 및 결제정보를 확인하였으며, 개인정보제공과 구매진행에 동의합니다. 
    </div>

    <div class="btnDiv">
      <input type="button" value="결제하기" id="payBtn" onclick="payModule()">
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

<!-- 아임포트 결제모듈 -->
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>

<script>

function payModule(){
  // 필수입력값
  var objs = $("form[name=orderForm]").find("[notNull]");

  for(var i=0; i<objs.length; i++){
    if(objs.eq(i).val() == "") {
    alert("필수 항목을 입력해주세요. ");
    return false;
    }
  }

  // 결제수단
  var payOption = $("input:radio[name=payOption]:checked");

  if(payOption.length < 1){
    alert("결제 수단을 선택해주세요.");
    return false;
  }
  if(payOption.val() == "2"){
    alert("현재 신용카드 및 카카오페이 결제만 가능합니다.")
    return false;
  }

  // 제공동의
  if(agree.checked==false){
    alert("결제 진행 필수사항에 동의해주세요.");
    return false;
  }

  // 결제모듈 
  IMP.init('imp49001285'); // 가맹점 식별코드
  // IMP.request_pay(param, callback) 결제창 호출
  IMP.request_pay({ // param
	  name: $("#itemName").val(), // 결제창에서 보이는 상품명
	  amount: $("#total").val(), // 가격
	  buyer_name: $("#orderName").val(), // 주문자명 
	  buyer_tel: $("#orderPhone").val(), // 주문자 전화번호
	  buyer_email: $("#email1").val() + "@" + $("#email2").val() // 주문자 이메일 

  }, function (rsp) { // callback 
    if (rsp.success) {
      alert('결제 성공하였습니다. 결제 금액: ' + rsp.paid_amount);
      orderForm.action = '${cp}/orders/orderOK';
      orderForm.method = 'post';
      orderForm.submit();

  } else {
      alert('결제 실패하였습니다. ' + rsp.error_msg);
    }
  });
}
  
</script>

<script type="text/javascript">
  // 이메일 선택
  function emailChange(){
    let emailChange = document.orderForm.email.options[document.orderForm.email.selectedIndex].value;
    let email2 = document.getElementById("email2");
    if(emailChange=='0'){ // 선택창
      email2.disabled = true;
      email2.value="";
    } else if(emailChange=='9') { // 직접입력
      email2.disabled=false;
      email2.value="";
      email2.focus();
    } else {
      email2.disabled=false;
      email2.value=emailChange;
      email2.readonly="readonly";
    }
  }

  // 주문정보 직접입력/동일 체크
  function copyInfo(n){
    let orderName = document.getElementById("orderName");
    let orderPhone = document.getElementById("orderPhone");
    let receiveName = document.getElementById("receiveName");
    let receivePhone = document.getElementById("receivePhone");

    if(n=='1'){
      receiveName.value = "";
      receivePhone.value = "";
    } else {
      receiveName.value = orderName.value;
      receivePhone.value = orderPhone.value;
    }
  }

</script>

   	<!-- FOOTER -->
	<jsp:include page="/WEB-INF/page/include/footer.jsp" />
    
    
</body>
</html>
