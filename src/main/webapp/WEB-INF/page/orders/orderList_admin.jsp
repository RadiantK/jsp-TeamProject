<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>  
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
    <h2 class="orderTitle"> 전체 주문/결제내역 </h2>
    <table id="adminTable">
        <thead>
        <tr>
            <th> 주문일자 </th>
            <th> 주문번호 </th>
            <th> 회원번호 </th>
            <th> 주문아이디(이메일) </th>
            <th> 주문자명 </th>
            <th> 결제금액 </th>
            <th> 주문상태 </th>
            <th></th>
        </tr>
        </thead>
        
        <c:forEach var="i" items="${orderList}">
        <tr>
            <td> ${i.regdate } </td>
            <td><a href="${cp}/admin/orderdetailAdmin?orderNum=${i.orderNum}">${i.orderNum } </a></td>
            <td> ${i.memberNum}</td>
            <td> ${i.email } </td>
            <td> ${i.name } </td>
            <td> <f:formatNumber value="${i.amount}" pattern="#,###"/> </td>
            <td> ${i.orderState } </td>
            <td><a href="${cp}/admin/orderdetailAdmin?orderNum=${i.orderNum}" style="color:darkgray"> 상세보기 </a></td>
        </tr> 
 		</c:forEach>
    </table>

 <!-- 하단 페이지 목록 -->
 <div class="nullDiv">
 	<c:if test="${startPage>5}">
	<a href="${cp}/orders/orderlistAdmin?pageNum=${pageNum-1}&orderState=${orderState}" class="preBtn"> < </a>
	</c:if>
	<c:if test="${startPage<5}">
	<a class="preBtn"> < </a>
	</c:if>
	
	<c:forEach var="i" begin="${startPage}" end="${endPage}">
	<c:choose>
	<c:when test="${pageNum==i}">
	  <a href="${cp}/orders/orderlistAdmin?pageNum=${i}&orderState=${orderState}" class="curPage"> ${i} </a>
	</c:when>
	<c:otherwise>
	  <a href="${cp}/orders/orderlistAdmin?pageNum=${i}&orderState=${orderState}" class="otherPage"> ${i} </a>
	</c:otherwise>
	</c:choose>
	</c:forEach>

	<c:if test="${endPage<pageCnt}">
	<a href="${cp}/orders/orderlistAdmin?pageNum=${endPage+1}&orderState=${orderState}" class="nextBtn"> > </a>
	</c:if>
	<c:if test="${endPage>=pageCnt}">
	<a class="nextBtn"> > </a>
	</c:if>	
</div>


<div class="searchDiv">
	<form action="${cp}/admin/orderlistAdmin" method="post">
 	<select class="selectDiv" name="col">
	    <option value="order_num"
	    	<c:if test="${param.col=='order_num'}">selected</c:if>> 주문번호 </option>
	    <option value="email"
        	<c:if test="${param.col=='email'}">selected</c:if>> 주문아이디 </option>
	    <option value="name"
	       	<c:if test="${param.col=='name'}">selected</c:if>> 주문자명 </option>
	    <option value="orderstate"
	        <c:if test="${param.col=='orderstate'}">selected</c:if>> 주문상태 </option>
  	</select>
  	<input type="text" value="${param.keyword}" name="keyword" class="searchText">
  	<input type="submit" value="검색" class="searchBtn">
  </form>
</div>


</div>


   	<!-- FOOTER -->
	<jsp:include page="/WEB-INF/page/include/footer.jsp" />
    
    
</body>
</html>
