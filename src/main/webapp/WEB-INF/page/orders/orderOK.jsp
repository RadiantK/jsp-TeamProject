<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>내일의집</title>
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

<!-- 주문 성공 -->
<div class="pageWrap">

<c:choose>
	<c:when test="${empty errorMsg}">
	<div id="imgDiv">
	    <img src="${cp}/images/orderOK.png" style="width:200px; height:200px;">
	</div>
	
	<div id="msgDiv"> 
	    <p id="msgTitle"> 주문해주셔서 감사합니다. </p>
	    <p> 상세 주문내역 및 진행상황은 <br>
	    <strong>마이페이지 > 주문내역</strong>에서 확인하실 수 있습니다. </p>
	</div>
	</c:when>

	<c:otherwise> 
	<div id="imgDiv"></div>
	
	<div id="msgDiv"> 
	    <p id="msgTitle"> 주문 실패 </p>
	    <p> 요청하신 주문이 정상적으로 완료되지 않았습니다. <br>
	    다시 시도하여 주시기 바랍니다. </p>
	</div>
	</c:otherwise>
</c:choose>

	<div class="btnDiv">
		<a href="${cp}/main" class="btnWhite"> 메인으로 </a> 
		<a href="${cp}/orders/orderlistMypage" class="btnBlue"> 주문내역확인 </a>
	</div>

</div>


	<!-- FOOTER -->
	<jsp:include page="/WEB-INF/page/include/footer.jsp" />
    
    
</body>
</html>