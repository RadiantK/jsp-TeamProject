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
  
  
</head>
<body>

	<jsp:include page="/WEB-INF/page/include/header.jsp" />
	
	<!-- 페이지 시작 -->
	<div id="wrap">
	    <div class="product-list">
	
	        <!-- 상단배너 -->
	        <div class="cg-banner">
	            <p>가구</p>
	        </div>
	        
	        <!-- 제품목록박스 -->
	        <div class="cg-contents">
	            <!-- 소분류 카테고리 -->
	            <div class="cg-menu">
	                <div class="cg-menu-title">
	                    <div class="list-txt">
	                        <span><strong>가구</strong></span>
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
	                        <li><a href="" class="select">전체(0)</a></li>
	                        <li><a href="">침대(0)</a></li>
	                        <li><a href="">소파(0)</a></li>
	                        <li><a href="">식탁(0)</a></li>
	                        <li><a href="">의자(0)</a></li>
	                    </ul>
	                </div>
	            </div>
	            <!-- 제품목록 시작 -->
	            <div class="cg-item row">
	                
	                <div class="box cell">
	                  <div class="img-box">
	                    <a href=""><img src="../images/product/sample.jpeg" alt="제품명"></a>
	                  </div>
	                  <div class="txt-box">
	                    <a href="">
	                      <span class="prdName">제품명</span><br>
	                      <span class="shotDesc">#제품설명#제품설명#제품설명</span><br>
	                      <span class="price">￦ 10,000</span>
	                    </a>
	                  </div>
	                </div>
	                <div class="box cell">
	                  <div class="img-box">
	                    <a href=""><img src="../images/product/sample.jpeg" alt="제품명"></a>
	                  </div>
	                  <div class="txt-box">
	                    <a href="">
	                      <span class="prdName">제품명</span><br>
	                      <span class="shotDesc">#제품설명#제품설명#제품설명</span><br>
	                      <span class="price">￦ 10,000</span>
	                    </a>
	                  </div>
	                </div>
	                <div class="box cell">
	                  <div class="img-box">
	                    <a href=""><img src="../images/product/sample.jpeg" alt="제품명"></a>
	                  </div>
	                  <div class="txt-box">
	                    <a href="">
	                      <span class="prdName">제품명</span><br>
	                      <span class="shotDesc">#제품설명#제품설명#제품설명</span><br>
	                      <span class="price">￦ 10,000</span>
	                    </a>
	                  </div>
	                </div>
	                <div class="box cell">
	                  <div class="img-box">
	                    <a href=""><img src="../images/product/sample.jpeg" alt="제품명"></a>
	                  </div>
	                  <div class="txt-box">
	                    <a href="">
	                      <span class="prdName">제품명</span><br>
	                      <span class="shotDesc">#제품설명#제품설명#제품설명</span><br>
	                      <span class="price">￦ 10,000</span>
	                    </a>
	                  </div>
	                </div>
	                
	                <div class="box cell">
	                  <div class="img-box">
	                    <a href=""><img src="../images/product/sample.jpeg" alt="제품명"></a>
	                  </div>
	                  <div class="txt-box">
	                    <a href="">
	                      <span class="prdName">제품명</span><br>
	                      <span class="shotDesc">#제품설명#제품설명#제품설명</span><br>
	                      <span class="price">￦ 10,000</span>
	                    </a>
	                  </div>
	                </div>
	                <div class="box cell">
	                  <div class="img-box">
	                    <a href=""><img src="../images/product/sample.jpeg" alt="제품명"></a>
	                  </div>
	                  <div class="txt-box">
	                    <a href="">
	                      <span class="prdName">제품명</span><br>
	                      <span class="shotDesc">#제품설명#제품설명#제품설명</span><br>
	                      <span class="price">￦ 10,000</span>
	                    </a>
	                  </div>
	                </div>
	                <div class="box cell">
	                  <div class="img-box">
	                    <a href=""><img src="../images/product/sample.jpeg" alt="제품명"></a>
	                  </div>
	                  <div class="txt-box">
	                    <a href="">
	                      <span class="prdName">제품명</span><br>
	                      <span class="shotDesc">#제품설명#제품설명#제품설명</span><br>
	                      <span class="price">￦ 10,000</span>
	                    </a>
	                  </div>
	                </div>
	                <div class="box cell">
	                  <div class="img-box">
	                    <a href=""><img src="../images/product/sample.jpeg" alt="제품명"></a>
	                  </div>
	                  <div class="txt-box">
	                    <a href="">
	                      <span class="prdName">제품명</span><br>
	                      <span class="shotDesc">#제품설명#제품설명#제품설명</span><br>
	                      <span class="price">￦ 10,000</span>
	                    </a>
	                  </div>
	                </div>
	                
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