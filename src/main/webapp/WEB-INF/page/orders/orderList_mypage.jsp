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
<link rel="stylesheet" href="${cp}/css/mypage.css" />
<link rel="stylesheet" href="${cp}/css/orders/orderMain.css"/>
	     
<script defer src="${cp}/resource/js/bootstrap.bundle.js"></script>
<script defer src="${cp}/js/common.js"></script>
<script defer src="${cp}/js/deleteMember.js"></script>
</head>
<body>
    
	<!-- HEADER -->
	<jsp:include page="/WEB-INF/page/include/header.jsp" />

	<input type="hidden" id="cp" value="${cp}" />
	
	<!-- 마이페이지 카테고리 메뉴 -->
  <section class="mypage">
    <div class="inner">
      <ul class="user-menu">
        <li class="item"><a href="${cp}/user/mypage/profile">프로필</a></li>
        <li class="item"><a href="${cp}/orders/orderlistMypage">결제정보 확인</a></li>
        <li class="item"><a href="${cp}/board/QNA/List">문의내역 확인</a></li>
        <li class="item"><a href="${cp}/user/mypage/edit">회원정보 수정</a></li>
        <li class="item"><a href="javascript:deleteMyAccountHandler()">회원탈퇴</a></li>
      </ul>
    </div>
  </section>

  <!-- 마이페이지 주문/결제정보 -->
  <div class="pageWrap">

	<!-- 주문상태별 조회 -->
  	<div class="cntDiv">
    <table class="cntTable">
      <tr>
        
        <th class="cntTitle"> 결제완료 
        <br> <a href="${cp}/orders/orderlistMypage?orderState=결제완료" class="cnt"> ${paid} </a>
        </th>
        <td class="arrow"> > </td>
        
        <th class="cntTitle"> 배송준비 
   	    <br> <a href="${cp}/orders/orderlistMypage?orderState=배송준비" class="cnt"> ${prepare} </a>
        <td class="arrow"> > </td>
        
        <th class="cntTitle"> 배송중 
        <br> <a href="${cp}/orders/orderlistMypage?orderState=배송중" class="cnt"> ${inTransit} </a>
          </th>
          <td class="arrow"> > </td>
          
        <th class="cntTitle"> 배송완료 
        <br> <a href="${cp}/orders/orderlistMypage?orderState=배송완료" class="cnt"> ${delivered} </a>
        </th>
        <td class="arrow"> > </td>

        <th class="cntTitle"> 주문취소
        <br> <a href="${cp}/orders/orderlistMypage?orderState=주문취소" class="cnt"> ${cancle} </a>
        </th>
      </tr>
    </table>
  </div>

	<!-- 주문내역 -->
	<c:forEach var="i" items="${orderList}">
  	<div class="listDiv">
    <table class="listTable">
        <thead>
        <tr>
            <th class="tableOrdernum"> 주문일자: ${i.regdate } | 주문번호: ${i.orderNum} </th>
            <th class="goOrderdetail"> <a href="${cp}/orders/orderdetailMypage?orderNum=${i.orderNum}" style="color:darkgray"> 상세보기 > </a> </th>
        </tr>
        </thead>
        
        <tr>
        	<th>
                <table style="width:100%">
                <c:set var="cnt" value="1"/>
	        	<c:forEach var="j" items="${orderDetail}">
	        	<c:if test="${i.orderNum == j.orderNum}">
		        <c:set var="cnt" value="${cnt+1}"/>
	                <tr>
	                <td>
	                <img src="${cp}/upload/product/${j.image}" class="itemImg"><br>
	                <span class="itemName"><br><a href="${cp}/orders/orderdetailMypage?orderNum=${j.orderNum}">${j.pname}</a></span><br>
	                <span class="itemPay"> ￦ <f:formatNumber value="${j.total}" pattern="#,###"/> </span>
	                </td>
	                </tr>
	            </c:if>
	            </c:forEach>
	            
               </table>
            </th>
        	<th rowspan="${cnt}"><p class="orderState">${i.orderState}</p> </th>
        </tr>
        
    </table>
 	</div>
  	</c:forEach>

 <!-- 하단 페이지 목록 -->
 <div class="nullDiv">
 	<c:if test="${startPage>5}">
	<a href="${cp}/orders/orderlistMypage?pageNum=${pageNum-1}&orderState=${orderState}" class="preBtn"> < </a>
	</c:if>
	<c:if test="${startPage<5}">
	<a class="preBtn"> < </a>
	</c:if>
	
	<c:forEach var="i" begin="${startPage}" end="${endPage}">
	<c:choose>
	<c:when test="${pageNum==i}">
	  <a href="${cp}/orders/orderlistMypage?pageNum=${i}&orderState=${orderState}" class="curPage"> ${i} </a>
	</c:when>
	<c:otherwise>
	  <a href="${cp}/orders/orderlistMypage?pageNum=${i}&orderState=${orderState}" class="otherPage"> ${i} </a>
	</c:otherwise>
	</c:choose>
	</c:forEach>

	<c:if test="${endPage<pageCnt}">
	<a href="${cp}/orders/orderlistMypage?pageNum=${endPage+1}&orderState=${orderState}" class="nextBtn"> > </a>
	</c:if>
	<c:if test="${endPage>=pageCnt}">
	<a class="nextBtn"> > </a>
	</c:if>	
</div>

 </div>

   	<!-- FOOTER -->
	<jsp:include page="/WEB-INF/page/include/footer.jsp" />
        
</body>
</html>