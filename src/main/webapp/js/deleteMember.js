const pathEl = document.getElementById('cp').value;

function deleteMyAccountHandler(){
  let checkMsg = confirm('회원탈퇴 하시겠습니까?');
  if(checkMsg){
		alert('회원탈퇴 되었습니다.');
		location.href = pathEl+"/user/mypage/delmem";
		return;
  } else {
    alert('취소 되었습니다.');
    return;
  }
}