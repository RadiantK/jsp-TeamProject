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
  <link rel="stylesheet" href="${cp}/css/admin_productupdate.css" />
  
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
            <p>제품 관리</p>
            <p class="bn-desc">수정할 제품을 선택하세요.</p>
            <input type="button" value="제품 등록" class="button" onclick="location.href='${cp}/admin/product/insert'">
        </div>
        
        <!-- 제품목록박스 -->
        <div class="cg-contents">
            <!-- 소분류 카테고리 -->
            <div class="cg-menu">
                <div class="cg-menu-title">
                    <div class="list-txt">
                        <span><strong><a href="${cp }/admin/product/list?bcnum=0&scnum=0">전체 제품 목록</a>
                        <c:choose>
	                        <c:when test="${bcnum != 0 }">
	                         - ${btype }
	                        </c:when>
                        </c:choose>
                        </strong></span>
                    </div>
                    <div class="choice">
                        <select name="sort" class="tune" onchange="if(this.value) location.href=(this.value);">
                            <option value="">==정렬순==</option>
	                            <option value="${cp }/admin/product/list?bcnum=${bcnum }&scnum=${scnum }&sort=date">신제품순</option>
	                            <option value="${cp }/admin/product/list?bcnum=${bcnum }&scnum=${scnum }&sort=review">리뷰많은순</option>
	                            <option value="${cp }/admin/product/list?bcnum=${bcnum }&scnum=${scnum }&sort=lowPrice">낮은가격순</option>
	                            <option value="${cp }/admin/product/list?bcnum=${bcnum }&scnum=${scnum }&sort=highPrice">높은가격순</option>
                        </select>
                    </div>
                </div>
                <div class="cg-menu-list">
                    <ul><!-- 첫화면에선 전체상품+대분류 카테고리 출력 -->
	                    	<c:choose>
	                    		
		                    	<c:when test="${bcnum == 0 }">
		                    		<li><a href="${cp }/admin/product/list?bcnum=0&scnum=0" class="select">전체(${totCnt })</a></li>
		                    		<c:forEach var="bcg" items="${bcgList }">
			                        	<c:choose>
			                        		<c:when test="${bcg.bcategoryNum == bcnum }">
			                        			<li><a href="${cp }/admin/product/list?bcnum=${bcg.bcategoryNum }&scnum=${scnum }" class="select">${bcg.btype }(${pdao.getCountBcg(bcg.bcategoryNum) })</a></li>
			                        		</c:when>
			                        		<c:otherwise>
			                        			<li><a href="${cp }/admin/product/list?bcnum=${bcg.bcategoryNum }&scnum=${scnum }">${bcg.btype }(${pdao.getCountBcg(bcg.bcategoryNum) })</a></li>
			                        		</c:otherwise>
			                        	</c:choose>
	                        		</c:forEach>
		                    	</c:when>
		                    	
	                    		<c:otherwise>
	                    		
	                    			<c:choose>
	                    				<c:when test="${scnum == 0 }">
	                    					<li><a href="${cp }/admin/product/list?bcnum=${bcnum }&scnum=0" class="select">전체(${bcgCnt })</a></li>
	                    				</c:when>
	                    				<c:otherwise>
	                    					<li><a href="${cp }/admin/product/list?bcnum=${bcnum }&scnum=0">전체(${bcgCnt })</a></li>
	                    				</c:otherwise>
	                    			</c:choose>
	                    			
	                    			<c:forEach var="scg" items="${scgList }">
			                        	<c:choose>
			                        		<c:when test="${scg.scategoryNum == scnum }">
			                        			<li><a href="${cp }/admin/product/list?bcnum=${bcnum }&scnum=${scg.scategoryNum }" class="select">${scg.stype }(${pdao.getCountScg(scg.scategoryNum) })</a></li>
			                        		</c:when>
			                        		<c:otherwise>
			                        			<li><a href="${cp }/admin/product/list?bcnum=${bcnum }&scnum=${scg.scategoryNum }">${scg.stype }(${pdao.getCountScg(scg.scategoryNum) })</a></li>
			                        		</c:otherwise>
			                        	</c:choose>
	                        		</c:forEach>
	                    		</c:otherwise>
	                    		
	                    	</c:choose>
	                    	
                    </ul>
                </div>
            </div>
            <!-- 제품목록 시작 -->
            <div class="cg-item">
                <table>
                    <thead>
                        <tr>
                            <th width="100">제품사진</th>
                            <th width="400">제품명</th>
                            <th>가격</th>
                            <th>할인가격</th>
                            <th>재고수</th>
                            <th>입고일</th>
                            <th>수정</th>
                            <th>삭제</th>
                        </tr>
                    </thead>

                    <tbody>
                    	<c:forEach var="prd" items="${pList }">
                    	<tr>
                            <td><div class="img-container"><img src="${cp }/upload/product/${prd.image }" alt="${prd.pname }"></div></td>
                            <td><span class="prdName">${prd.pname }</span></td>
                            
                            <c:choose>
                             <c:when test="${prd.discount == 0 }">
                              <td><span class="price"><fmt:formatNumber value="${prd.price }" pattern="￦ #,###"/></span></td>
                              <td><span class="price-sale">-</span></td>
                             </c:when>
                             <c:otherwise>
                              <td><span class="price"><fmt:formatNumber value="${prd.price }" pattern="￦ #,###"/></span></td>
                              <td><span class="price-sale"><fmt:formatNumber value="${prd.price - ( prd.price * prd.discount / 100 ) }" pattern="￦ #,###"/> (${prd.discount }%)</span></td>
                             </c:otherwise>
                            </c:choose>
                            
                            <td><span class="qty">${prd.cnt }개</span></td>
                            <td><span class="date">${prd.regdate }</span></td>
                            <td><a href="${cp }/admin/product/update?pnum=${prd.productNum }"><span class="modify">수정</span></a></td>
                            <td><a href="${cp }/admin/product/delete?pnum=${prd.productNum }"><span class="delete">삭제</span></a></td>
                        </tr>
                    	</c:forEach>

                    </tbody>
                </table>
                
            </div>
            <!-- 페이징 -->
            <div class="board-paging">
	                <c:if test="${startPage>pageNumBox }">
	                	<a href="${cp }/admin/product/list?bcnum=${bcnum }&scnum=${scnum }&sort=${sort }&pageNum=${startPage-1 }"><span>이전</span></a>
	                </c:if>
	                <c:forEach var="i" begin="${startPage }" end="${endPage }">
	                	<c:choose>
	                		<c:when test="${pageNum==i }">
	                			<u><span>${i }</span></u>
	                		</c:when>
	                		<c:otherwise>
	                			<a href="${cp }/admin/product/list?bcnum=${bcnum }&scnum=${scnum }&sort=${sort }&pageNum=${i }">${i }</a>
	                		</c:otherwise>
	                	</c:choose>
	                </c:forEach>
	                <c:if test="${endPage<pageCount }">
	                	<a href="${cp }/admin/product/list?bcnum=${bcnum }&scnum=${scnum }&sort=${sort }&pageNum=${endPage+1 }"><span>다음</span></a>
	                </c:if>
            </div>
            
        </div>
        
    </div>
</div>

<jsp:include page="/WEB-INF/page/include/footer.jsp" />

</body>
</html>