<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
  <script defer src="${cp}/js/base.js"></script>
  
  <script type="text/javascript">
  
  let xhr = null;
  
  window.onload = function(){
	  scgList();
  }
  
  function scgList(){
	  xhr = new XMLHttpRequest();
	  xhr.onreadystatechange = function(){
		  if(xhr.readyState==4 && xhr.status==200){
			  var result = xhr.responseText;
			  let cgMenuList = document.getElementsByClassName("cg-menu-list")[0];
			  
		  }
	  }
  }
  
  </script>
  
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
	                        <select name="sort" class="tune">
	                            <option value="date">신제품순</option>
	                            <option value="review">리뷰많은순</option>
	                            <option value="lowPrice">낮은가격순</option>
	                            <option value="highPrice">높은가격순</option>
	                        </select>
	                    </div>
	                </div>
	                <div class="cg-menu-list">
	                    <ul>
	                        <li><a href="" class="select">전체(${pdao.getCountBcg(bcg.bcategoryNum) })</a></li>
	                        <c:forEach var="vo" items="${scgList }">
	                        	<li><a href="">${vo.stype }(${pdao.getCountScg(vo.scategoryNum) })</a></li>
	                        </c:forEach>
	                    </ul>
	                </div>
	            </div>
	            <!-- 제품목록 시작 -->
	            <div class="cg-item row">
	                <c:forEach var="vo" items="${pdao.selectBcg(bcg.bcategoryNum) }">
		                <div class="box cell">
		                  <div class="img-box">
		                    <a href=""><img src="../upload/product/thumbnail/${vo.image }" alt="${vo.pname }"></a>
		                  </div>
		                  <div class="txt-box">
		                    <a href="">
		                      <span class="prdName">${vo.pname }</span><br>
		                      <span class="shotDesc">#제품설명#제품설명#제품설명</span><br>
		                      <span class="price">￦ ${vo.price }</span>
		                    </a>
		                  </div>
		                </div>
	                </c:forEach>
	                
	            </div>
	            <!-- 페이징 -->
	            <div class="board-paging">
	                <ul class="help">
	                    <li><a href="">1</a></li>
	                    <li><a href="">2</a></li>
	                    <li><a href="">3</a></li>
	                </ul>
	            </div>
	            
	        </div>
	        
	    </div>
	</div>
	
	<jsp:include page="/WEB-INF/page/include/footer.jsp" />

</body>
</html>