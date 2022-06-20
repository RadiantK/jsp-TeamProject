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
	     
<script defer src="${cp}/resource/js/bootstrap.bundle.js"></script>
<script defer src="${cp}/js/common.js"></script>
</head>
<body>
    
	<!-- HEADER -->
	<jsp:include page="/WEB-INF/page/include/header.jsp" />
     
	<!-- 장바구니 메인 -->
    <div class="pageWrap">
    
    <h2 class="orderTitle"> 장바구니 </h2>
 
<!-- 장바구니 비었을 때 
<div class="nullDiv">
  <img src="../images/cartNull.jpeg">
</div>
 -->
    
    <form name="cartForm">
    
    <table class="cartTable">
    	<thead>
    		<tr>
        	<th> <input type="checkbox" name="cart" onclick="selectAll(this)"> </th>
          <th style="width:400px"> 제품정보 </th>
          <th> 금액 </th>
          <th> 수량 </th>
          <th> 합계 </th>
          <th> 배송비 </th>
          <th> <input type="button" value="전체삭제" class="cartDelete" onclick=""> </th>
        </tr>
      </thead>
        <tr>
          <td> <input type="checkbox" name="cart"></td>
          <td>
	          <img src="./images/orderTest.png" class="itemImg"> 
	          <p class="itemName"> 두닷 콰트로 에어데스크 1000 </p>
          </td>
          <td> 10,000 </td>
          <td>
             <input type="button" name="plus" id="plus" class="amountBtn" value=" + " onclick="amountCal(this,1)">
             <input type="text" name="amount" id="amount" value="1">
             <input type="button" name="minus" id="minus" class="amountBtn" value=" － " onclick="amountCal(this,2)">
          </td>
          <td class="sumPrice"> 10,000 </td>
          <td> 2,500 </td>
          <td> <input type="button" value="삭제" class="cartDelete" onclick=""> </td>
        </tr>
        <tr>
          <td> <input type="checkbox" name="cart"></td>
          <td>
          <img src="./images/orderTest2.jpg" class="itemImg">
          <p class="itemName"> 시디즈 화이트쉘 </p>
          </td>
          <td> 15,000 </td>
          <td>
            <input type="button" name="plus" id="plus" class="amountBtn" value=" + " onclick="amountCal(this,1)">
            <input type="text" name="amount" id="amount" value="1">
            <input type="button" name="minus" id="minus" class="amountBtn" value=" － " onclick="amountCal(this,2)">
          </td>
          <td class="sumPrice"> 30,000 </td>
          <td> 2,500 </td>
          <td> <input type="button" value="삭제" class="cartDelete" onclick=""> </td>
        </tr>
      </table>
      

      <div class="cartPrice">
        총 3개의 상품금액  ￦ 40,000  +  배송비  ￦ 5,000  = <span class="totalPrice"> ￦ 45,000 </span>
      </div>
      </form>
    
      <div class="btnDiv">
        <a href="javascript:history.back();" class="btnWhite"> 이전으로 </a> 
        <a href="${cp}/orders/order" class="btnBlue"> 주문하기 </a>
      </div>
    
	</div> 
    
<script type="text/javascript">

// 수량 설정 
function amountCal(me, n){
	if(n==1){
    	let amount = me.nextElementSibling;
        amount.value = parseInt(amount.value) + 1;
    } else if (n==2){
    	let amount = me.previousElementSibling;
        if (amount.value=='1') return;
        amount.value = parseInt(amount.value) - 1;
    }
}

// 전체 체크 
function selectAll(selectAll){
	var boxes = document.getElementsByName('cart');
    boxes.forEach((checkbox) => {checkbox.checked=selectAll.checked});
}

      
</script>
    
   	<!-- FOOTER -->
	<jsp:include page="/WEB-INF/page/include/footer.jsp" />
    
    
</body>
</html>