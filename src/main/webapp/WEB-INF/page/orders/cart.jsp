<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<script defer src="${cp}/js/cartList.js"></script>

</head>
<body>
    
	<!-- HEADER -->
	<jsp:include page="/WEB-INF/page/include/header.jsp" />
     
	<!-- 장바구니 메인 -->
    <div class="pageWrap">
    <input type="hidden" value="${cp}" id="cp" />
    
    <h2 class="orderTitle"> 장바구니 </h2>
 
 		<c:if test="${not empty sessionId}">
 			<input type="hidden" id="sessionValue" value="login" />
 		</c:if>
 		<c:if test="${sessionId == null}">
 			<input type="hidden" id="sessionValue" value="nonMember" />
 		</c:if>
    
    <form name="cartForm" onsubmit="return false()">
    
    <table class="cartTable">
			<thead>
				<tr>
					<th> <input type="checkbox" name="chkAll" onclick="selectAll(this)">
					</th>
					<th style="width:400px"> 제품정보 </th>
					<th> 금액 </th>
					<th> 수량 </th>
					<th> 합계 </th>
					<th> 배송비 </th>
					<th> <input type="button" value="전체삭제" class="cartDelete" onclick=""> </th>
				</tr>
			</thead>
			<tbody>
<!-- 				<tr>
					<td> <input type="checkbox" name="chk[]">
					<input type="hidden" name="pnum[]" value="cart[i].productNum"></td>
					<td>				
						<img src="./images/orderTest.png" class="itemImg"> 
						<p class="itemName"> 두닷 콰트로 에어데스크 1000 </p>
					</td>
					<td> 10,000 </td>
					<td>
						<input type="button" name="plus" id="plus" class="pieceBtn" value=" + " onclick="pieceCal(this,1)">
						<input type="text" name="piece[]" id="piece" value="1">
						<input type="button" name="minus" id="minus" class="pieceBtn" value=" － " onclick="pieceCal(this,2)">
					</td>
					<td class="sumPrice"> 10,000 </td>
					<td> 2,500 </td>
					<td> <input type="button" value="삭제" class="cartDelete" onclick=""> </td>
				</tr> -->
			</tbody>
    </table>
      

      <div class="cartPrice">
        총 3개의 상품금액  ￦ 40,000  +  배송비  착불  = <span class="totalPrice"> ￦ 45,000 </span>
      </div>
      </form>
    
      <div class="btnDiv">
        <a href="javascript:history.back();" class="btnWhite"> 이전으로 </a> 
        <input type="submit" id="orderBtn" value="구매하기" onclick="orders(this)" class="btnUpdate">
      </div>
    
	</div> 
    
    
   	<!-- FOOTER -->
	<jsp:include page="/WEB-INF/page/include/footer.jsp" />
    
    
</body>
</html>