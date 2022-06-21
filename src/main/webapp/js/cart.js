const cartBtnEl = document.getElementById('btn-cart');
const cpEl = document.getElementById('cp').value;
const pnumEl = document.getElementById('pnum').value;

cartBtnEl.addEventListener('click', cartAddHandler);

function cartAddHandler(){
	let cartAdd = confirm('장바구니에 담으시겠습니까?');
	if(cartAdd){
		let xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function(){
			if(xhr.readyState == 4 && xhr.status == 200){
				let data = xhr.responseText;
				let json = JSON.parse(data);
				
				if(json.code == true){
					alert('장바구니에 추가되었습니다.');
				}else if(json.code == false){
					alert('장바구니 담기에 실패했습니다.');
				}else {
					alert('장바구니에 담는 도중 에러가 발생했습니다.');
				}
			}
		}
		xhr.open('get', cpEl+"/user/cart?pnum="+pnumEl, true);
		xhr.send();
	}else {
		alert('취소했습니다.');
		return;
	}
}