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
 		
 		<c:if test="${not empty sessionId}">
 			<input type="hidden" id="sessionValue" value="login" />
 		</c:if>
 		<c:if test="${sessionId == null}">
 			<input type="hidden" id="sessionValue" value="nonMember" />
 		</c:if>
    
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
			<tbody>
<!-- 				<tr>
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
				</tr> -->
			</tbody>
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

// 장바구니리스트
window.onload = function(){
	cartList();
}

function cartList(){
	let sessionValue = document.getElementById('sessionValue').value;
	console.log(sessionValue);
	// 회원이면
	if(sessionValue == 'login'){
		let xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function(){
			if(xhr.readyState == 4 && xhr.status == 200){
				let data = xhr.responseText;
				let json = JSON.parse(data);
				console.log(json);
				
				let tbodyEl = document.querySelector('tbody');
				let cart = json.cart;
				
				if(cart.length != 0) {
					// 장바구니 목록 초기화
					let tbodyChildsEl = tbodyEl.childNodes;
					for(let i = tbodyChildsEl.length-1; i >= 0; i--) {
						let tc = tbodyChildsEl.item(i);
						tbodyEl.removeChild(tc);
					}
					
					// 장바구니 목록 출력
					for(let i = 0; i < cart.length; i++){
						let trEl = document.createElement('tr');
						trEl.innerHTML = 
						  "<td><input type='checkbox' name='cart' /></td>" +
						  "<td><img src='"+cart[i].img+"' class='itemImg' alt='상품이미지'/>" + 
						  "<p class='itemName'>"+cart[i].pname+"</p></td>" +
						  "<td>"+cart[i].price+"</td>" + 
						  "<td><input type='button' name='plus' class='amountBtn' value=' + ' onclick='amountCal(this,1)' />" +
						  "<input type='text' name='amount' id='amount' value='"+cart[i].cnt+"' />" + 
						  "<input type='button' name='minus' class='amountBtn' value=' - ' onclick='amountCal(this,2)' /></td>" + 
						  "<td class='sumPrice'>합계</td>" + 
						  "<td> 2,500 </td>" + 
						  "<td><input type='button' value='삭제' class='cartDelete' onclick='deleteCart("+cart[i].cartDetailNum+")' /></td>";
						  
						 tbodyEl.appendChild(trEl);
					}
				}else {
					let tbodyChildsEl = tbodyEl.childNodes;
					for(let i = tbodyChildsEl.length-1; i >= 0; i--) {
						let tc = tbodyChildsEl.item(i);
						tbodyEl.removeChild(tc);
					}
					// 장바구니 목록 출력
					let trEl = document.createElement('tr');
					trEl.innerHTML = 
					  "<td style='text-align: center; height: 50px;' colspan='7'>목록이 존재하지 않습니다.</td>";
					  
					 tbodyEl.appendChild(trEl);
				}
				
			}
		}
		xhr.open('get', "${cp}/user/cart/list", true);
		xhr.send();
		
	}else { // 비회원이면
		let xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function(){
			if(xhr.readyState == 4 && xhr.status == 200){
				let data = xhr.responseText;
				let json = JSON.parse(data);
				console.log(json);
				
				let tbodyEl = document.querySelector('tbody');
				let cart = json.cart;
				
				if(cart.length != 0) {
					// 장바구니 목록 초기화
					let tbodyChildsEl = tbodyEl.childNodes;
					for(let i = tbodyChildsEl.length-1; i >= 0; i--) {
						let tc = tbodyChildsEl.item(i);
						tbodyEl.removeChild(tc);
					}
					
					// 장바구니 목록 출력
					for(let i = 0; i < cart.length; i++){
						let trEl = document.createElement('tr');
						trEl.innerHTML = 
						  "<td><input type='checkbox' name='cart' /></td>" +
						  "<td><img src='"+cart[i].img+"' class='itemImg' alt='상품이미지'/>" + 
						  "<p class='itemName'>"+cart[i].pname+"</p></td>" +
						  "<td>"+cart[i].price+"</td>" + 
						  "<td><input type='button' name='plus' class='amountBtn' value=' + ' onclick='amountCal(this,1)' />" +
						  "<input type='text' name='amount' id='amount' value='1' />" + 
						  "<input type='button' name='minus' class='amountBtn' value=' - ' onclick='amountCal(this,2)' /></td>" + 
						  "<td class='sumPrice'>합계</td>" + 
						  "<td> 2,500 </td>" + 
						  "<td><input type='button' value='삭제' class='cartDelete' onclick='deleteCart("+cart[i].index+")' /></td>";
						  
						 tbodyEl.appendChild(trEl);
					}
				}else {
					let tbodyChildsEl = tbodyEl.childNodes;
					for(let i = tbodyChildsEl.length-1; i >= 0; i--) {
						let tc = tbodyChildsEl.item(i);
						tbodyEl.removeChild(tc);
					}
					// 장바구니 목록 출력
					let trEl = document.createElement('tr');
					trEl.innerHTML = 
						"<td style='text-align: center; height: 50px;' colspan='7'>목록이 존재하지 않습니다.</td>";
					  
					 tbodyEl.appendChild(trEl);
				}
			}
		}
		xhr.open('get', "${cp}/user/cart/list", true);
		xhr.send();
	}
	
}

function deleteCart(cdNum){
	let confirmMsg = confirm('장바구니 목록에서 제거하시겠습니까?');
	if(confirmMsg){
		let xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function() {
			if(xhr.readyState == 4 && xhr.status == 200){
				let data = xhr.responseText;
				let json = JSON.parse(data);
				
				if(json.code == true){
					alert('장바구니에서 제거되었습니다.');
					cartList();
				}else {
					alert('목록에서 제거에 실패했습니다.');
				}
			}
		}
		xhr.open('get', "${cp}/user/cart/delete?cnum="+cdNum, true);
		xhr.send();
		
	}else {
		alert('취소했습니다.')
	}
}
      
</script>
    
   	<!-- FOOTER -->
	<jsp:include page="/WEB-INF/page/include/footer.jsp" />
    
    
</body>
</html>