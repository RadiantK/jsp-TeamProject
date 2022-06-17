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
  <link rel="stylesheet" href="${cp}/css/category.css" />
  
  <!-- 부트스트랩 js -->
  <script defer src="${cp}/resource/js/bootstrap.bundle.js"></script>
  <script defer src="${cp}/js/common.js"></script>
  
</head>
<body>

	<jsp:include page="/WEB-INF/page/include/header.jsp" />
	
	<!-- 페이지 시작 -->
	<div id="wrap">
	    <div class="product-list">
	
	        <!-- 상단배너 -->
	        <div class="cg-banner">
	            <p>${bcg.btype }</p>
	        </div>
	        
	        <!-- 제품목록박스 -->
	        <div class="cg-contents">
	            <!-- 소분류 카테고리 -->
	            <div class="cg-menu">
	                <div class="cg-menu-title">
	                    <div class="list-txt">
	                        <span><strong>${bcg.btype }</strong></span>
	                    </div>
	                    <div class="choice">
	                        <select id="sort" class="tune" onchange="if(this.value) location.href=(this.value);">
	                        	<option value="">==정렬순==</option>
	                            <option value="?bcnum=${bcnum }&scnum=${scnum }&sort=date">신제품순</option>
	                            <option value="?bcnum=${bcnum }&scnum=${scnum }&sort=review">리뷰많은순</option>
	                            <option value="?bcnum=${bcnum }&scnum=${scnum }&sort=lowPrice">낮은가격순</option>
	                            <option value="?bcnum=${bcnum }&scnum=${scnum }&sort=highPrice">높은가격순</option>
	                        </select>
	                    </div>
	                </div>
	                <div class="cg-menu-list">
	                    <ul>
	                    	<c:choose>
		                    	<c:when test="${scnum == 0 }">
		                    		<li><a href="?bcnum=${bcnum }&scnum=0" class="select">전체(${bcgCnt })</a></li>
		                    	</c:when>
	                    		<c:otherwise>
	                    			<li><a href="?bcnum=${bcnum }&scnum=0">전체(${bcgCnt })</a></li>
	                    		</c:otherwise>
	                    	</c:choose>
	                        <c:forEach var="vo" items="${scgList }">
	                        	<c:choose>
	                        		<c:when test="${vo.scategoryNum == scnum }">
	                        			<li><a href="?bcnum=${bcnum }&scnum=${vo.scategoryNum }" class="select">${vo.stype }(${pdao.getCountScg(vo.scategoryNum) })</a></li>
	                        		</c:when>
	                        		<c:otherwise>
	                        			<li><a href="?bcnum=${bcnum }&scnum=${vo.scategoryNum }">${vo.stype }(${pdao.getCountScg(vo.scategoryNum) })</a></li>
	                        		</c:otherwise>
	                        	</c:choose>
	                        </c:forEach>
	                    </ul>
	                </div>
	            </div>
	            <!-- 제품목록 시작 -->
	            <div class="cg-item row">
	                <c:forEach var="vo" items="${pList }">
		                <div class="box cell">
		                  <div class="img-box">
		                    <a href=""><img src="../upload/product/thumbnail/${vo.image }" alt="${vo.pname }"></a>
		                  </div>
		                  <div class="txt-box">
		                    <a href="${cp }/product/detail?pnum=${vo.productNum }"> <!-- 상품 상세페이지 링크 -->
		                      <span class="prdName">${vo.pname }</span><br>
		                      <span class="shotDesc">${vo.pdesc }</span><br>
		                      <c:choose>
		                      	<c:when test="${vo.discount == 0 }">
		                      		<span class="price"><fmt:formatNumber value="${vo.price }" pattern="￦ #,###"/></span>
		                      	</c:when>
		                      	<c:otherwise>
		                      		<s><span class="price"><fmt:formatNumber value="${vo.price }" pattern="￦ #,###"/></span></s><br>
		                      		<span class="price"><fmt:formatNumber value="${vo.price - ( vo.price * vo.discount / 100 ) }" pattern="￦ #,###"/> (${vo.discount }%)</span>
		                      	</c:otherwise>
		                      </c:choose>
		                    </a>
		                  </div>
		                </div>
	                </c:forEach>
	                
	            </div>
	            <!-- 페이징 -->
	            <div class="board-paging">
	                <c:if test="${startPage>pageNumBox }">
	                	<a href="?bcnum=${bcnum }&scnum=${scnum }&sort=${sort }&pageNum=${startPage-1 }"><span>이전</span></a>
	                </c:if>
	                <c:forEach var="i" begin="${startPage }" end="${endPage }">
	                	<c:choose>
	                		<c:when test="${pageNum==i }">
	                			<span>${i }</span>
	                		</c:when>
	                		<c:otherwise>
	                			<a href="?bcnum=${bcnum }&scnum=${scnum }&sort=${sort }&pageNum=${i }">${i }</a>
	                		</c:otherwise>
	                	</c:choose>
	                </c:forEach>
	                <c:if test="${endPage<pageCount }">
	                	<a href="?bcnum=${bcnum }&scnum=${scnum }&sort=${sort }&pageNum=${endPage+1 }"><span>다음</span></a>
	                </c:if>
	            </div>
	            
	        </div>
	        
	    </div>
	</div>
	
	<jsp:include page="/WEB-INF/page/include/footer.jsp" />

</body>
</html>