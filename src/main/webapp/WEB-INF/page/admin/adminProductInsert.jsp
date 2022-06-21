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
  <link rel="stylesheet" href="${cp}/css/admin_productinsert.css" />
  
  <!-- 부트스트랩 js -->
  <script defer src="${cp}/resource/js/bootstrap.bundle.js"></script>
  <script defer src="${cp}/js/base.js"></script>
</head>
<body>

<jsp:include page="/WEB-INF/page/include/header.jsp" />

<!-- 페이지 시작 -->
<div id="wrap">
    <div class="product-updateForm">

        <!-- 상단배너 -->
        <div class="cg-banner">
            <p>제품 등록</p>
            <p class="bn-desc">등록할 제품 정보를 입력하세요.</p>
        </div>
        
        <!-- 제품정보입력폼 -->
        <div class="cg-contents">
            <div class="img-container">
                <img src="./images/sample.jpeg" alt="">
                <input type="button" value="썸네일 첨부" class="button">
            </div>
            <div class="txt-container">

                <table>
                    <tr>
                        <th>카테고리</th>
                        <td>
                            <select name="category" id="bigCg">
                                <option value="">=======대분류=======</option>
                                <option value="">가구</option>
                                <option value="">패브릭</option>
                                <option value="">조명</option>
                                <option value="">가전</option>
                                <option value="">주방용품</option>
                            </select>
                        </td>
                        <td>
                            <!-- 대분류 선택시 활성화 + 해당하는 소분류 출력 -->
                            <select name="category" id="smallCg" disabled>
                                <option value="">=======소분류=======</option>
                                <option value="">침대</option>
                                <option value="">소파</option>
                                <option value="">식탁</option>
                                <option value="">의자</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th>상품명</th>
                        <td colspan="2"><input type="text" id="prdName"></td>
                    </tr>
                    <tr>
                        <th>상품설명</th>
                        <td colspan="2"><input type="text" id="shotDesc"></td>
                    </tr>
                    <tr>
                        <th>가격</th>
                        <td colspan="2"><input type="text" id="price"> 원</td>
                    </tr>
                    <tr>
                        <th>할인율</th>
                        <td colspan="2"><input type="text" id="priceOff"> %</td>
                    </tr>
                    <tr>
                        <th>재고</th>
                        <td colspan="2"><input type="text" id="qty"> 개</td>
                    </tr>
                    <tr>
                        <th>상세이미지</th>
                        <td colspan="2"><input type="button" value="상세이미지 첨부" class="button buttonTemp descImg"><span class="fileName">파일명</span></td>
                    </tr>
                </table>

                <input type="button" value="제품 등록하기" class="button buttonTemp insertBtn">
            </div>
        </div>
        
    </div>
</div>

<jsp:include page="/WEB-INF/page/include/footer.jsp" />

</body>
</html>