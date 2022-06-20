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
  <link rel="stylesheet" href="${cp}/css/category_detail.css" />
  <link rel="stylesheet" href="${cp}/css/review.css" />
  
  <!-- 부트스트랩 js -->
  <script defer src="${cp}/resource/js/bootstrap.bundle.js"></script>
  <script defer src="${cp}/js/base.js"></script>
  
  <script type="text/javascript">
  	
  	var xhr = null;
  	
  	window.onload = function(){
  		reviewList(1);
  	}
  
  	function ChangeValue(){
  		let qty = document.getElementById("qty").value;
  		let price = 0;
  		
  		if(${prd.discount } == 0){
  			price = ${prd.price };
  		}else{
  			price = ${prd.price - ( prd.price * prd.discount / 100 ) };
  		}
  		console.log("개수:"+qty);
  		console.log("개당가격:"+price);
  		
  		xhr = new XMLHttpRequest();
  		xhr.onreadystatechange = function(){
  			if(xhr.readyState==4 && xhr.status==200){
  				let data = xhr.responseText;
  				let json = JSON.parse(data);
  				let tPriceArea = document.getElementById("tPrice");
  				console.log("총금액:"+tPriceArea.innerHTML);
  				let tPrice = json.tPrice;
  				console.log("tPrice:"+tPrice);
  				
  				tPriceArea.innerHTML = tPrice+"원";
  			}
  		};
  		
  		xhr.open('get','${cp}/product/qty_change?qty='+qty+'&price='+price,true);
  		xhr.send();
  	}
  	
  	function reviewList(pageNum){
  		xhr = new XMLHttpRequest();
  		xhr.onreadystatechange = function(){
  			if(xhr.readyState==4 && xhr.status==200){
  				var result = xhr.responseText;
  				let reviewList = document.getElementById("review-list");
  				
  				let child = reviewList.childNodes;
  				for(let i=child.length-1;i>=0;i--){
  					let c = child.item(i);
  					reviewList.removeChild(c);
  				}
  				let data = JSON.parse(result);
  				let review = data.list;
  				
  				for(let i=0;i<review.length;i++){
  					let score = review[i].score;
  					let regdate = review[i].regdate;
  					let nickname = review[i].nickname;
  					let content = review[i].content;
  					let image = review[i].image;
  					
  					let imgLine = "<a href=''><img src='upload/" + image + "' alt='첨부이미지'></a>";
  					if(image == null || image == undefined || image == "" || !image){
  						imgLine = "";
  						console.log(imgLine);
  					}
  					let div = document.createElement("div");
  					div.className = "rev-container";
  					div.innerHTML = "<div class='rev-writer'>" +
					                	"<p class='star'>" + score + "</p>" +
					                    "<p class='date'>" + regdate + "</p>" +
					                    "<p class='nickname'>" + nickname + "</p>" +
					                "</div>" +
					                "<div class='rev-content'>" +
					                    "<p class='revMat'>" + content + "</p>" +
					                    "<div class='revImg'>" +
					                    	imgLine +
					                    "</div>" +
					                "</div>";
					reviewList.appendChild(div);
  				}
  				let startPage = data.startPage;
  				let endPage = data.endPage;
  				let pageCount = data.pageCount;
  				let pageHTML = "";
  				if(startPage>5){
  					pageHTML += "<a href='javascript:reviewList("+ (startPage-1) +")'>이전</a>";
  				}
  				for(let i=startPage;i<=endPage;i++){
  					if(i==pageNum){
  						pageHTML +="<a href='javascript:reviewList("+ i +")'><span style='color:black'><u> "+ i +"</u></span></a>";
					}else{
						pageHTML +="<a href='javascript:reviewList("+ i +")'><span style='color:gray'> "+ i +"</span></a>";
					}
  				}
  				if(endPage<pageCount){
  					pageHTML +="<a href='javascript:reviewList("+ (endPage+1) +")'>다음</a>";
				}
				var page = document.getElementById("rev-paging");
				page.innerHTML = pageHTML;
  			}
  		};
  		xhr.open('get','${cp}/product/detail/review?pnum=${pnum}&pageNum='+pageNum,true);
		xhr.send();
  	}
  	
  	/*
  	function addReview(){
  		let reviewTxt = document.getElementById("review-txt").value;
  		let score = document.getElementsByName("prd-point")[0].value;
  		let image = document.getElementById("review-img").value;
  		
  		String saveDir = "${cp}/upload";
		out.print("업로드경로:"+saveDir+"<br>");
		MultipartRequest mr = new MultipartRequest
  		
  		xhr = new XMLHttpRequest();
  		xhr.onreadystatechange = function(){
			if(xhr.readyState==4 && xhr.status==200){
				let data = xhr.responseText;
				let json = JSON.parse(data);
				if(json.code=='success'){
					alert('리뷰 등록 성공');
					reviewList(1);
				}else{
					alert('리뷰 등록 실패');
				}
			}
		};
		xhr.open('post','${cp}/product/detail/review/insert',true);
		xhr.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
		let param = "pnum=" + ${pnum} + "&content=" + reviewTxt + "&score=" + score + "&image=" + image;
		xhr.send(param);
  	} */
  
  </script>
</head>
<body>

<jsp:include page="/WEB-INF/page/include/header.jsp" />

<!-- 페이지 시작 -->
<div id="wrap">
    <div class="product-detail">
        <div class="prd-box">
            <div class="prd-box-img"><img src="../upload/product/thumbnail/${prd.image }" alt=""></div>
            <div class="prd-box-txt">

              <div class="txt-top">
                <p class="detailCategory">홈ㅤ>ㅤ${btype }ㅤ>ㅤ${stype }</p>
                <p class="detailPrdName">${prd.pname }</p>
                <p class="detailShotDesc">${prd.pdesc }</p>
              </div>

              <div class="txt-mid">
                <div class="reviewCountView">
                  <a href="#prd-desc-review"><u>${rcnt }</u>개의 후기 보기</a>
                </div>
                <div class="help">
                  <c:choose>
                  	<c:when test="${prd.discount == 0 }">
                  		<div class="detailPrice">
		                    <p class="leftLabel">판매가</p>
		                    <p class="rightLabel price"><fmt:formatNumber value="${prd.price }" pattern="￦ #,###"/></p>
		                </div>
                  	</c:when>
                  	<c:otherwise>
                  		<div class="detailPrice">
		                    <p class="leftLabel">판매가</p>
		                    <p class="rightLabel"><s><fmt:formatNumber value="${prd.price }" pattern="￦ #,###"/></s></p>
		                </div>
                  		<div class="detailPriceSale">
		                    <p class="leftLabel">할인가</p>
		                    <p class="rightLabel"><span class="pointTxt price"><fmt:formatNumber value="${prd.price - ( prd.price * prd.discount / 100 ) }" pattern="￦ #,###"/></span><span> (${prd.discount }%)</span></p>
	                  	</div>
                  	</c:otherwise>
                  </c:choose>
                  <div class="detailPriceQty">
                    <p class="leftLabel">구매수량</p>
                    <p class="rightLabel">
                      <select id="qty" class="tune" onchange="ChangeValue()">
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
                  <p class="rightLabel" id="tPrice">
	                  	<fmt:formatNumber value="${prd.price - ( prd.price * prd.discount / 100 ) * qtyValue }" pattern="####"/>원
                  </p>
                </div>
                <div class="botBtn">
                  <input type="button" value="장바구니" id="btn-cart" class="button">
                  <input type="button" value="바로구매" id="btn-buy" class="button btn--reverse">
                </div>
              </div>
            </div>
        </div>

        <div class="prd-desc">
            <div class="prd-desc-content" id="prd-desc-content">
                <div class="prd-desc-navbar">
                  <a href="#prd-desc-content" class="on"><p>제품상세정보</p></a>
                  <a href="#prd-desc-review"><p>제품후기</p></a>
                  <a href="#prd-desc-info"><p>배송/교환 및 반품안내</p></a>
                </div>
                <div class="prd-desc-img"><img src="./images/detail_sample.jpg" alt="상세이미지"></div>
            </div>
            <div class="prd-desc-review" id="prd-desc-review">
              <div class="prd-desc-navbar">
                  <a href="#prd-desc-content"><p>제품상세정보</p></a>
                  <a href="#prd-desc-review" class="on"><p>제품후기</p></a>
                  <a href="#prd-desc-info"><p>배송/교환 및 반품안내</p></a>
              </div>

              <form method="post" action="addReview()" id="reviewForm">
                <p class="reviewTitle">Product Reviews</p>
                <div class="review-point">
                  <span>평가</span>
                  <ul>
                    <li>
                      <input type="radio" name="prd-point" id="rating5" checked="checked">
                      <label for="rating5" >★★★★★</label>
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
                    <textarea class="review-txt" id="review-txt"></textarea>
                </div>
                <div class="review-btn">
                  <label class="button btn--reverse" for="input-file">사진 첨부</label>
                  <input type="file" name="review-img" id="input-file" style="display:none"/>
                  <input type="button" value="후기작성" class="button" id="btn-review" onclick="addReview()">
                </div>
              </form>
              
			  <!-- 리뷰 -->
              <div class="review-list" id="review-list">

              </div>
              
              <!-- 페이징 -->
              <div class="rev-paging" id="rev-paging">
              </div>
              
            </div>
            <div class="prd-desc-info" id="prd-desc-info">
              <div class="prd-desc-navbar">
                  <a href="#prd-desc-content"><p>제품상세정보</p></a>
                  <a href="#prd-desc-review"><p>제품후기</p></a>
                  <a href="#prd-desc-info" class="on"><p>배송/교환 및 반품안내</p></a>
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