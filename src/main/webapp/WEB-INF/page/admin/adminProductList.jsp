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
                        <span><strong>전체 제품 목록</strong></span>
                    </div>
                    <div class="choice">
                        <select name="sort" class="tune">
                            <option value="">신제품순</option>
                            <option value="">재고순</option>
                            <option value="">리뷰많은순</option>
                            <option value="">낮은가격순</option>
                            <option value="">높은가격순</option>
                        </select>
                    </div>
                </div>
                <div class="cg-menu-list">
                    <ul>
                        <li><a href="" class="select">전체(0)</a></li>
                        <li><a href="">가구(0)</a></li>
                        <li><a href="">패브릭(0)</a></li>
                        <li><a href="">조명(0)</a></li>
                        <li><a href="">가전(0)</a></li>
                        <li><a href="">주방용품(0)</a></li>
                    </ul>
                </div>
            </div>
            <!-- 제품목록 시작 -->
            <div class="cg-item">
                <table>
                    <thead>
                        <tr>
                            <th width="50">제품사진</th>
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
                        <tr>
                            <td><div class="img-container"><img src="./images/sample.jpeg" alt=""></div></td>
                            <td><span class="prdName">제품명제품명제품명</span></td>
                            <td><span class="price">￦ 10,000</span></td>
                            <td><span class="price-sale">￦ 9,000</span><span> (10%)</span></td>
                            <td><span class="qty">100개</span></td>
                            <td><span class="date">2022.01.01</span></td>
                            <td><a href=""><span class="modify">수정</span></a></td>
                            <td><a href=""><span class="delete">삭제</span></a></td>
                        </tr>
                        <tr>
                            <td><div class="img-container"><img src="./images/sample.jpeg" alt=""></div></td>
                            <td><span class="prdName">제품명제품명제품명</span></td>
                            <td><span class="price">￦ 10,000</span></td>
                            <td><span class="price-sale">￦ 9,000</span><span> (10%)</span></td>
                            <td><span class="qty">100개</span></td>
                            <td><span class="date">2022.01.01</span></td>
                            <td><a href=""><span class="modify">수정</span></a></td>
                            <td><a href=""><span class="delete">삭제</span></a></td>
                        </tr>
                        <tr>
                            <td><div class="img-container"><img src="./images/sample.jpeg" alt=""></div></td>
                            <td><span class="prdName">제품명제품명제품명</span></td>
                            <td><span class="price">￦ 10,000</span></td>
                            <td><span class="price-sale">￦ 9,000</span><span> (10%)</span></td>
                            <td><span class="qty">100개</span></td>
                            <td><span class="date">2022.01.01</span></td>
                            <td><a href=""><span class="modify">수정</span></a></td>
                            <td><a href=""><span class="delete">삭제</span></a></td>
                        </tr>
                    </tbody>
                </table>
                
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