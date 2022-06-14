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
<link rel="stylesheet" href="${cp}/css/admin.css">	     

<script defer src="${cp}/resource/js/bootstrap.bundle.js"></script>
<script defer src="${cp}/js/common.js"></script>
</head>
<body>
    
	<!-- HEADER -->
	<jsp:include page="/WEB-INF/page/include/header.jsp" />

<!-- 바디 메인 -->
<div class="pageWrap">
    <h2 class="orderTitle"> [ADMIN] 전체 주문/결제내역 </h2>
    <table id="adminTable">
        <thead>
        <tr>
            <th> 주문일자 </th>
            <th> 주문번호 </th>
            <th> 주문회원 </th>
            <th> 주문제품 </th>
            <th> 결제금액 </th>
            <th> 주문상태 </th>
        </tr>
        </thead>
        <tr>
            <td> 2022.06.10 </td>
            <td> 12345678 </td>
            <td> admin </td>
            <td> <a href="${cp }/orders/orderdetailAdmin"> 두닷 콰트로 에어데스크 1000 </a></td>
            <td> 12,500 </td>
            <td> 결제완료 </td>
        </tr> 
        <tr>
          <td> 2022.06.12 </td>
          <td> 112312312 </td>
          <td> test </td>
          <td> <a href=""> 시디즈 의자 </a></td>
          <td> 89,000 </td>
          <td> 결제완료 </td>
      </tr> 
    </table>

</div>


   	<!-- FOOTER -->
	<jsp:include page="/WEB-INF/page/include/footer.jsp" />
    
    
</body>
</html>
