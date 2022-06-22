const cpEl = document.getElementById('cp').value;

// 마이페이지(회원) 주문취소
function myCancle(onum){
	let myOs = document.getElementById("orderState");

	if(confirm("주문을 취소하시겠습니까?")){
		let xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function(){
			if(xhr.readyState == 4 && xhr.status == 200){
				let data = xhr.responseText;
				let json = JSON.parse(data);
				
				if(json.code == true){
					alert(json.msg);
					myOs.innerHTML = '주문취소';
					
				}else if(json.code == false){
					alert(json.msg);
				}else {
					alert('오류가 발생했습니다. 다시 시도해주세요. ');
				}
			}
		}
		xhr.open('get', cpEl+'/orders/cancle?orderNum=' + onum + '&orderState=' + myOs.innerHTML, true);
		xhr.send();
	}else return;
}


// 관리자 주문취소
function adminCancle(onum){
	let adminOs = document.getElementById("orderState");
	console.log(onum, adminOs);
			
	if(confirm("주문을 취소하시겠습니까?")){
		let xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function(){
			if(xhr.readyState == 4 && xhr.status == 200){
				let data = xhr.responseText;
				let json = JSON.parse(data);
				
				if(json.code == true){
					alert(json.msg);
					adminOs.value = '주문취소';
						
				}else if(json.code == false){
					alert(json.msg);

				}else {
					alert('오류가 발생했습니다. 다시 시도해주세요. ');
				}
			}
		}
		xhr.open('get', cpEl+'/orders/cancle?orderNum=' + onum + '&orderState=' + adminOs.value, true);
		xhr.send();
		
		}else return;
}

// 카카오 주소찾기 
function kakaoAddress(){
	new daum.Postcode({
    	oncomplete: function(data) {
            // 주소검색 결과를 클릭했을때 실행할 내용
            document.getElementById("kakaoZonecode").value = data.zonecode
            document.getElementById("kakaoAddr").value = data.address; // 주소넣기
            document.querySelector("input[name=detailAddr]").focus(); // 상세주소 포커싱
   		}
	}).open();
}


//아임포트	
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
      orderForm.action = cpEl+'/orders/orderOK';
      orderForm.method = 'post';
      orderForm.submit();

  } else {
      alert('결제 실패하였습니다. ' + rsp.error_msg);
    }
  });
}
  
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

