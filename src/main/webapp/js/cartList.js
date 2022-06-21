const cpEl = document.getElementById('cp').value;

// 구매하기
function orders(form){
	
	var chks = form.elements['chk[]'].length;
	console.log(chks);
	
	if (typeof(chks)=='0') {
		alert ("주문할 상품을 선택해주세요.");
		return false;
	} else {
		for (i=0; i<chks; i++){
			if(form.elements['chk[]'].checked==false){
				form.elements['pnum[]'][i].disabled=true;
				form.elements['piece[]'][i].disabled=true;
			}
		}
	}
	form.action = cpEl+'/orders/order';
    form.method = 'post';
    form.submit();
}

// 수량 설정 
function pieceCal(me, n){
	if(n==1){
    	let piece = me.nextElementSibling;
        piece.value = parseInt(piece.value) + 1;
    } else if (n==2){
    	let piece = me.previousElementSibling;
        if (piece.value=='1') return;
        piece.value = parseInt(piece.value) - 1;
    }
}

// 합계금액 변경


// 전체 체크 
function selectAll(cartAll){
	var boxes = document.getElementsByName('chk[]');
    boxes.forEach((checkbox) => {checkbox.checked=cartAll.checked});
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
						  "<td><input type='checkbox' name='chk[]' />" +
						  "<input type='hidden' name='pnum[]' value='"+cart[i].productNum+"'></td>" +
						  "<td><img src='"+cpEl+"/images/"+cart[i].img+"' class='itemImg' alt='상품이미지'/>"
						  "<p class='itemName'>"+cart[i].pname+"</p></td>" +
						  "<td>"+cart[i].price+"</td>" + 
						  "<td><input type='button' name='plus' class='pieceBtn' value=' + ' onclick='pieceCal(this,1)' />" +
						  "<input type='text' name='piece[]' id='piece' value='1' />" + 
						  "<input type='button' name='minus' class='pieceBtn' value=' - ' onclick='pieceCal(this,2)' /></td>" + 
						  "<td class='sumPrice'>합계</td>" + 
						  "<td> 착불 </td>" + 
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
		xhr.open('get', cpEl+"/user/cart/list", true);
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
						  "<td><input type='checkbox' name='chk[]' />" +
						  "<input type='hidden' name='pnum[]' value='"+cart[i].productNum+"'></td>" +
						  "<td><img src='"+cpEl+"/images/"+cart[i].img+"' class='itemImg' alt='상품이미지'/>" +
						  "<p class='itemName'>"+cart[i].pname+"</p></td>" +
						  "<td>"+cart[i].price+"</td>" + 
						  "<td><input type='button' name='plus' class='pieceBtn' value=' + ' onclick='pieceCal(this,1)' />" +
						  "<input type='text' name='piece[]' id='piece' value='1' />" + 
						  "<input type='button' name='minus' class='pieceBtn' value=' - ' onclick='pieceCal(this,2)' /></td>" + 
						  "<td class='sumPrice'>합계</td>" + 
						  "<td> 착불 </td>" + 
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
		xhr.open('get', cpEl+"/user/cart/list", true);
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
		xhr.open('get', cpEl+"/user/cart/delete?cnum="+cdNum, true);
		xhr.send();
		
	}else {
		alert('취소했습니다.')
	}
}

