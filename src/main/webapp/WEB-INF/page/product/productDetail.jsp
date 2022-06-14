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
  <link rel="stylesheet" href="${cp}/css/category.css" />
  <link rel="stylesheet" href="${cp}/css/category_detail.css" />
  <link rel="stylesheet" href="${cp}/css/review.css" />
  
  <!-- 부트스트랩 js -->
  <script defer src="${cp}/resource/js/bootstrap.bundle.js"></script>
  <script defer src="${cp}/js/base.js"></script>
</head>
<body>

<jsp:include page="/WEB-INF/page/include/header.jsp" />

<!-- 페이지 시작 -->
<div id="wrap">
    <div class="product-detail">
        <div class="prd-box">
            <div class="prd-box-img"><img src="./images/sample.jpeg" alt=""></div>
            <div class="prd-box-txt">

              <div class="txt-top">
                <p class="detailCategory">홈ㅤ>ㅤ가구ㅤ>ㅤ침대</p>
                <p class="detailPrdName">제품명</p>
                <p class="detailShotDesc">#제품설명#제품설명#제품설명#제품설명</p>
              </div>

              <div class="txt-mid">
                <div class="reviewCountView">
                  <a href="">3개의 후기 보기</a>
                </div>
                <div class="help">
                  <div class="detailPrice">
                    <p class="leftLabel">판매가</p>
                    <p class="rightLabel">￦ 10,000</p>
                  </div>
                  <div class="detailPriceSale">
                    <p class="leftLabel">할인가</p>
                    <p class="rightLabel"><span class="pointTxt">￦ 9,000</span><span> (10%)</span></p>
                  </div>
                  <div class="detailPriceQty">
                    <p class="leftLabel">구매수량</p>
                    <p class="rightLabel">
                      <select name="qty" class="tune">
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                      </select>
                    </p>
                  </div>
                </div>
              </div>

              <div class="txt-bot">
                <div class="totPrice">
                  <p class="leftLabel">총 금액 </p>
                  <p class="rightLabel">￦ 9,000</p>
                </div>
                <div class="botBtn">
                  <input type="button" value="장바구니" id="btn-cart" class="button">
                  <input type="button" value="바로구매" id="btn-buy" class="button btn--reverse">
                </div>
              </div>
            </div>
        </div>

        <div class="prd-desc">
            <div class="prd-desc-content">
                <div class="prd-desc-navbar">
                  <a href="" class="on"><p>제품상세정보</p></a>
                  <a href=""><p>제품후기</p></a>
                  <a href=""><p>배송/교환 및 반품안내</p></a>
                </div>
                <div class="prd-desc-img"><img src="./images/detail_sample.jpg" alt="상세이미지"></div>
            </div>
            <div class="prd-desc-review">
              <div class="prd-desc-navbar">
                <a href=""><p>제품상세정보</p></a>
                <a href="" class="on"><p>제품후기</p></a>
                <a href=""><p>배송/교환 및 반품안내</p></a>
              </div>

              <form method="post" action="" id="reviewForm">
                <p class="reviewTitle">Product Reviews</p>
                <div class="review-point">
                  <span>평가</span>
                  <ul>
                    <li>
                      <input type="radio" name="prd-point" id="rating5">
                      <label for="rating5">★★★★★</label>
                    </li>
                    <li>
                      <input type="radio" name="prd-point" id="rating4">
                      <label for="rating4">★★★★☆</label>
                    </li>
                    <li>
                      <input type="radio" name="prd-point" id="rating3">
                      <label for="rating3">★★★☆☆</label>
                    </li>
                    <li>
                      <input type="radio" name="prd-point" id="rating2">
                      <label for="rating2">★★☆☆☆</label>
                    </li>
                    <li>
                      <input type="radio" name="prd-point" id="rating1">
                      <label for="rating1">★☆☆☆☆</label>
                    </li>
                  </ul>
                </div>
                <div class="review-textarea">
                    <textarea class="review-txt"></textarea>
                </div>
                <div class="review-btn">
                  <input type="button" value="사진첨부" class="button btn--reverse" id="btn-file">
                  <input type="submit" value="후기작성" class="button" id="btn-review">
                </div>
              </form>

              <div class="review-list">
                
                <!-- 리뷰1 -->
                <div class="rev-container">
                  <div class="rev-writer">
                    <p class="star">★★★★★</p>
                    <p class="date">2022.02.01</p>
                    <p class="nickname">강아지</p>
                  </div>
                  <div class="rev-content">
                    <p class="revMat">
                      제품평<br>
                      제품평제품평<br>
                      제품평제품평제품평<br>
                    </p>
                    <div class="revImg">
                      <a href=""><img src="./images/img01.jpg" alt="첨부이미지"></a>
                      <a href=""><img src="./images/img02.jpg" alt="첨부이미지"></a>
                    </div>
                  </div>
                </div>
                <!-- 리뷰2 -->
                <div class="rev-container">
                  <div class="rev-writer">
                    <p class="star">★★★★★</p>
                    <p class="date">2022.02.01</p>
                    <p class="nickname">비둘기</p>
                  </div>
                  <div class="rev-content">
                    <p class="revMat">
                      제품평<br>
                      제품평제품평<br>
                      제품평제품평제품평<br>
                    </p>
                    <div class="revImg">
                      <a href=""><img src="./images/img03.jpg" alt="첨부이미지"></a>
                    </div>
                  </div>
                </div>

                

              </div>
              <!-- 페이징 -->
              <div class="rev-paging">
                <ul class="help">
                  <li><a href="">1</a></li>
                  <li><a href="">2</a></li>
                  <li><a href="">3</a></li>
                 </ul>
              </div>
            </div>
            <div class="prd-desc-info">
              <div class="prd-desc-navbar">
                <a href=""><p>제품상세정보</p></a>
                <a href=""><p>제품후기</p></a>
                <a href="" class="on"><p>배송/교환 및 반품안내</p></a>
              </div>
              <div class="info-container">
                <span>
                반품/교환 사유에 따른 요청 가능 기간<br>
                반품 시 먼저 판매자와 연락하셔서 반품사유, 택배사, 배송비, 반품지 주소 등을 협의하신 후 반품상품을 발송해 주시기 바랍니다.<br>
                <br>
                1 구매자 단순 변심은 상품 수령 후 7일 이내 (구매자 반품배송비 부담)<br>
                2 표시/광고와 상이, 상품하자의 경우 상품 수령 후 3개월 이내 혹은 표시/광고와 다른 사실을 안 날로부터 30일 이내.<br>
                둘 중 하나 경과 시 반품/교환 불가 (판매자 반품배송비 부담)<br>
                <br>
                반품/교환 불가능 사유<br>
                아래와 같은 경우 반품/교환이 불가능합니다.<br>
                <br>
                1 반품요청기간이 지난 경우<br>
                2 구매자의 책임 있는 사유로 상품 등이 멸실 또는 훼손된 경우 (단, 상품의 내용을 확인하기 위하여 포장 등을 훼손한 경우는 제외)<br>
                3 포장을 개봉하였으나 포장이 훼손되어 상품가치가 현저히 상실된 경우 (예: 식품, 화장품)<br>
                4 구매자의 사용 또는 일부 소비에 의하여 상품의 가치가 현저히 감소한 경우 (라벨이 떨어진 의류 또는 태그가 떨어진 명품관 상품인 경우)<br>
                5 시간의 경과에 의하여 재판매가 곤란할 정도로 상품 등의 가치가 현저히 감소한 경우 (예: 식품, 화장품)<br>
                6 고객주문 확인 후 상품제작에 들어가는 주문제작상품<br>
                7 복제가 가능한 상품 등의 포장을 훼손한 경우 (CD/DVD/GAME/도서의 경우 포장 개봉 시)<br>
                </span>
              </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/page/include/footer.jsp" />
 
</body>
</html>