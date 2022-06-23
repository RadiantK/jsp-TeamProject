<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
  <link rel="stylesheet" href="${cp}/css/admin_productinsert.css" />
  
  <!-- 부트스트랩 js -->
  <script defer src="${cp}/resource/js/bootstrap.bundle.js"></script>
  <script defer src="${cp}/js/common.js"></script>
  
  <script type="text/javascript">
  
  // 대분류 셀렉트 변경되면 소분류 셀렉트 가능하도록
  function changeBcg(){
	  let bcgSelect = document.getElementById("bigCg");
	  let bcgNum = bcgSelect.options[bcgSelect.selectedIndex].value;
	  console.log("bcgNum:"+bcgNum);
	  
	  let scgSelect = document.getElementById("smallCg");
	  
	  let scgOptions = {
	  	furniture: ['침대','소파','식탁','의자'],
	  	fabric: ['이불','베개','러그','쿠션'],
	  	light: ['천장등','장스탠드','단스탠드','데스크스탠드'],
	  	appliances: ['냉장고','TV','세탁기','에어컨'],
	  	kitchen: ['그릇','냄비','프라이팬','주방잡화']
	  }
	  
	  let scgOption = [];
	  
	  if(bcgNum != "off"){
		  scgSelect.disabled = false;
		  switch (bcgNum) {
		case "1":
			scgOption = scgOptions.furniture;
			console.log("scgOption:"+scgOption);
			break;
		case "2":
			scgOption = scgOptions.fabric;
			console.log("scgOption:"+scgOption);
			break;
		case "3":
			scgOption = scgOptions.light;
			console.log("scgOption:"+scgOption);
			break;
		case "4":
			scgOption = scgOptions.appliances;
			console.log("scgOption:"+scgOption);
			break;
		case "5":
			scgOption = scgOptions.kitchen;
			console.log("scgOption:"+scgOption);
			break;
		}
		console.log("scgOption.length:"+scgOption.length);
		scgSelect.options.length = 1;
		
		for(var i=1;i<scgOption.length+1;i++){
			var option = document.createElement('option');
			option.innerText = scgOption[i-1];
			option.value = scgOption[i-1];
			scgSelect.append(option);
		}
		
	  }else{
		  scgSelect.options.length = 0;
		  var option = document.createElement('option');
		  option.innerText = "=======소분류=======";
		  option.value = "off";
		  scgSelect.append(option);
		  scgSelect.disabled = true;
	  }
  }
  
  window.onload = function(){
	  const thumImgBtn = document.getElementById("thumImgBtn"); // input button
	  const thumImgFile = document.getElementById("thumImgFile"); // input file
	  
	  thumImgFile.addEventListener("change", e => {
		  readImage(e.target);
	  });
	  
  }

  // button 클릭시 file 클릭 작동하도록
  function clickThumImgBtn(){
	  //thumImgBtn = document.getElementById("thumImgBtn");
	  //thumImgFile = document.getElementById("thumImgFile"); // => 왜 바깥으로 빼면 작동을 안하는지? 윈도우온로드 처리
	  thumImgFile.click();
  }
  
  
  // 썸네일 이미지 미리보기
  function readImage(input){
	  if(input.files && input.files[0]){
		  // 이미지 파일인지 확장자명 검사하기(나중에)
		  
		  const reader = new FileReader();
		  
		  reader.onload = e => {
			  const previewImage = document.getElementById("ThumImg");
			  previewImage.src = e.target.result;
			  console.log("previewImage.src : ",previewImage.src);
		  }
		  
		  reader.readAsDataURL(input.files[0]);
	  }
  }
  
  </script>
</head>
<body>

<jsp:include page="/WEB-INF/page/include/header.jsp" />

<!-- 페이지 시작 -->
<div id="wrap">
    <div class="product-updateForm">

        <!-- 상단배너 -->
        <div class="cg-banner">
            <p>제품 등록</p>
            <p class="bn-desc">등록할 제품 정보를 입력하세요.</p>
        </div>
        
        <!-- 제품정보입력폼 -->
        <form action="${cp}/admin/product/insertok" method="post" enctype="multipart/form-data">
        
        <div class="cg-contents">
        
            <div class="img-container">
                <img id="ThumImg" style="">
                <input type="file" id="thumImgFile" name="thumImgFile" required style="display: none"/>
                <input type="button" id="thumImgBtn" value="썸네일 첨부" class="button buttonTemp" onclick="clickThumImgBtn()">
            	<!-- <input type="button" value="썸네일 첨부" class="button buttonTemp" id="thumImgBtn" onclick="onclick=document.all.thumImgFile.click()"> -->
            </div>
            
            <div class="txt-container">
                <table>
                    <tr>
                        <th>카테고리</th>
                        <td><input type="text" id="bigCg" name="bigCg" placeholder="대분류 카테고리 입력"></td>
                        <td><input type="text" id="smallCg" name="smallCg" placeholder="소분류 카테고리 입력"></td>
                        <!-- 
                        <td>
                            <select name="category" id="bigCg" name="bigCg" onchange="changeBcg()">
                                <option value="off" selected>=======대분류=======</option>
                                <c:forEach var="bcg" items="${bcgList }">
                                	<option value="${bcg.bcategoryNum }">${bcg.btype }</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td>
                            대분류 선택시 활성화 + 해당하는 소분류 출력 
                            <select name="category" id="smallCg" name="smallCg" disabled="disabled">
                                <option value="off" selected>=======소분류=======</option>
                                대분류셀렉트 선택시 select에 option add 
                            </select>
                        </td>
                         -->
                    </tr>
                    <tr>
                        <th>상품명</th>
                        <td colspan="2"><input type="text" id="prdName" name="prdName"></td>
                    </tr>
                    <tr>
                        <th>상품설명</th>
                        <td colspan="2"><input type="text" id="shotDesc" name="shotDesc"></td>
                    </tr>
                    <tr>
                        <th>가격</th>
                        <td colspan="2"><input type="text" id="price" name="price"> 원</td>
                    </tr>
                    <tr>
                        <th>할인율</th>
                        <td colspan="2"><input type="text" id="priceOff" name="priceOff"> %</td>
                    </tr>
                    <tr>
                        <th>재고</th>
                        <td colspan="2"><input type="text" id="qty" name="qty"> 개</td>
                    </tr>
                    <tr>
                        <th>상세이미지</th>
                        <!-- <td colspan="2"><input type="button" value="상세이미지 첨부" class="button buttonTemp descImg"><span class="fileName">파일명</span></td> -->
                        <td colspan="2"><input type="file" id="descImg" name="descImg" required></td>
                    </tr>
                </table>
                <input type="submit" value="제품 등록하기" class="button buttonTemp insertBtn">
            </div>
            
        </div>
        
        </form>
    </div>
</div>

<jsp:include page="/WEB-INF/page/include/footer.jsp" />

</body>
</html>