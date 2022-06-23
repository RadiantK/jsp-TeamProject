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
  <link rel="stylesheet" href="${cp}/css/category.css" />
  
  <!-- 부트스트랩 js -->
  <script defer src="${cp}/resource/js/bootstrap.bundle.js"></script>
  <script defer src="${cp}/js/common.js"></script>
  
  <style type="text/css">
  .cg-banner{
		background: url('../.././images/product/banner/1.jpg') repeat 50% 0;
	}
  </style>
  
</head>
<body>

	<jsp:include page="/WEB-INF/page/include/header.jsp" />
	
	<!-- 페이지 시작 -->
	<div id="wrap">
	    <div class="product-list">
	
	        <!-- 상단배너 -->
	        <div class="cg-banner">
	            <p>전체 품목</p>
	        </div>
	        
	        <!-- 제품목록박스 -->
	        <div class="cg-contents">
	            <!-- 소분류 카테고리 -->
	            <div class="cg-menu">
	                <div class="cg-menu-title">
	                    <div class="list-txt">
	                        <span><strong></strong></span>
	                    </div>
	                    <div class="choice">
	                    	<form action="${cp}/search/product/list" id="form" >
	                        <select id="sort" class="tune" onchange="if(this.value) location.href=(this.value);">
	                        	<option value="">==정렬순==</option>
	                            <option value="?sort=regdate&word=${param.word}">신제품순</option>
	                            <option value="?sort=ascprice&word=${param.word}">낮은가격순</option>
	                            <option value="?sort=descprice&word=${param.word}">높은가격순</option>
	                        </select>
                        </form>
	                    </div>
	                </div>
	                <div class="cg-menu-list">
	                    <ul>
                   			<li><a href="">전체()</a></li>
	                    </ul>
	                </div>
	            </div>
	            <!-- 제품목록 시작 -->
	            <div class="cg-item row">
	                <c:forEach var="vo" items="${list}">
		                <div class="box cell">
		                  <div class="img-box">
		                    <a href="${cp }/product/detail?pnum=${vo.productNum }"><img src="../.././upload/product/${vo.image }" alt="${vo.pname }"></a>
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
	            		<c:choose>
		                <c:when test="${page <= 1}">
		                	<a href="javascript:void(0)">
		                		<span onclick="alert('이전페이지가 없습니다.')">이전</span>
		                	</a>
		                </c:when>
		                <c:otherwise>
		                	<a href="?sort=${param.sort}&word=${param.word}&p=${page-1}"><span>이전</span></a>
		                </c:otherwise>
	                </c:choose>
	                
	                <c:forEach var="i" begin="${startPage}" end="${endPage}">
	                	<c:choose>
	                		<c:when test="${page == i}">
	                			<u><span>${i}</span></u>
	                		</c:when>
	                		<c:otherwise>
	                			<a href="?sort=${param.sort}&word=${param.word}&p=${i}">${i}</a>
	                		</c:otherwise>
	                	</c:choose>
	                </c:forEach>
	                
	                <c:choose>
		                <c:when test="${page >= pageCount}">
		                	<a href="javascript:void(0)">
		                		<span onclick="alert('다음페이지가 없습니다.')">다음</span>
		                	</a>
		                </c:when>
		                <c:otherwise>
		                	<a href="?sort=${param.sort}&word=${param.word}&p=${page+1}"><span>다음</span></a>
		                </c:otherwise>
	                </c:choose>
	            </div>
	            
	        </div>
	        
	    </div>
	</div>
	
	<jsp:include page="/WEB-INF/page/include/footer.jsp" />

</body>
</html>