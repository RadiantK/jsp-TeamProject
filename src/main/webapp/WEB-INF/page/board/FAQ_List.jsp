<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자주묻는내용</title>
<!-- material-icon -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
<!-- 부트스트랩css -->
<link rel="stylesheet" href="${cp}/resource/css/bootstrap.min.css" />
<!-- 구글폰트  -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<!-- Roboto -->
<link
	href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;700&display=swap"
	rel="stylesheet">
<!-- Jua -->
<link href="https://fonts.googleapis.com/css2?family=Jua&display=swap"
	rel="stylesheet">

<link rel="stylesheet" href="${cp}/css/common.css" />
<link rel="stylesheet" href="${cp}/css/main.css" />
<link rel="stylesheet" href="${cp}/css/board.css" />

<!-- Swiper -->
<link rel="stylesheet"
	href="https://unpkg.com/swiper@8/swiper-bundle.min.css" />
<script src="https://unpkg.com/swiper@8/swiper-bundle.min.js"></script>
<!-- lodash -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/lodash.js/4.17.21/lodash.min.js"
	integrity="sha512-WFN04846sdKMIP5LKNphMaWzU7YpMyCU245etK3g/2ARYbPK9Ub18eG+ljU96qKRCWh+quCY7yefSmlkQw1ANQ=="
	crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<!-- gsap -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/gsap/3.10.4/gsap.min.js"
	integrity="sha512-VEBjfxWUOyzl0bAwh4gdLEaQyDYPvLrZql3pw1ifgb6fhEvZl9iDDehwHZ+dsMzA0Jfww8Xt7COSZuJ/slxc4Q=="
	crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<!-- bootstrap css -->
<script defer src="${cp}/resource/js/bootstrap.bundle.js"></script>
<script defer src="${cp}/js/common.js"></script>
<script defer src="${cp}/js/main.js"></script>
<style>
details {
	margin: 5px 0 10px;
}

details>summary {
	width: 900px;
	margin: auto;
	background: #FFFFFF;
	color: black; padding : 10px;
	outline: 0;
	border-radius: 5px;
	cursor: pointer;
	transition: background 0.5s;
	text-align: left;
	padding: 10px; outline : 0; border-radius : 5px; cursor : pointer;
	transition : background 0.5s; text-align : left;
	box-shadow: 1px 1px 1px gray;
}

details>summary::-webkit-details-marker {
	background: #FFFFFF;
	color: black;
	background-size: contain;
	transform: rotate3d(0, 0, 1, 90deg);
	transition: transform 0.25s;
}

details[open]>summary::-webkit-details-marker {
	transform: rotate3d(0, 0, 1, 180deg);
}

details[open]>summary {
	background: #FFFFFF;
	font-weight: bold;
}

details[open]>summary ~ * {
	animation: reveal 0.5s;
}

.tpt {
	background: rgb(247, 249, 250);
	color: black;
	margin: 5px 0 10px;
	padding: 5px 10px;
	line-height: 25px;
	border-radius: 5px;
	box-shadow: 1px 1px 2px gray;
}

@
keyframes reveal {from { opacity:0;
	transform: translate3d(0, -30px, 0);
}

to {
	opacity: 1;
	transform: translate3d(0, 0, 0);
}
}
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/page/include/header.jsp" />


	<!-- Main -->
	<main>
		<div class="container">
			<div class="row">
				<div class="col-xs-12" style="text-align: center;">
					<h1>고객센터</h1>
					<br>
					<div id="boardCategory">
						<a href="${cp }/board/Notice/Listmove">공지사항</a>&nbsp;&nbsp;&nbsp;&nbsp;<span>|</span>&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="${cp }/board/QNA/List">1:1문의</a>&nbsp;&nbsp;&nbsp;&nbsp;<span>|</span>&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="${cp }/board/FAQ/List">자주묻는질문</a>
					</div>
					<br>
				</div>
			</div>


			<div class="row">
				<div class="col-md-12"
					style="display: inline-block; padding-top: 60px; width: 600px; margin: auto;">
					<form action="${cp }/board/FAQ/List" method="post">
						<div>
							<select name="field">
								<option value="title"
									<c:if test="${field =='title' }">selected</c:if>>제목</option>
								<option value="content"
									<c:if test="${field =='content' }">selected</c:if>>내용</option>
							</select> &nbsp;
							<input type="text" class="form-control-row rounded-pill"
								style="width: 450px; height: 40px; text-align: center;"
								placeholder="자주묻는 질문 검색" aria-describedby="button-addon2"
								name="keyword" value=${keyword }>
						</div>
					</form>
				</div>
			</div>
			<br>

			<c:forEach var="vo" items="${list }">
				<details>
					<summary>${vo.title }</summary>
					<div class="tpt"
						style="width: 900px; margin: auto; margin-top: 10px;">
						<pre>${vo.content }</pre>
					</div>
					<br>

					<c:if test="${sessionId=='admin' }">
						<a href="${cp }/board/FAQ/Update?faqNum=${vo.faqNum}"
							class="button btn--reverse" style="display: inline-block; margin-left: 205px;">수정</a> &nbsp;&nbsp;
						<a onclick="return confirm('삭제하시겠습니까?')"
							href="${cp }/board/FAQ/Delete?faqNum=${vo.faqNum}"
							class="button btn--reverse" style="display: inline-block;">삭제</a>
					</c:if>

				</details>
			</c:forEach>

			<div id="paging" style="text-align: center; font-size: large;">
				<c:if test="${startPage>10 }">
					<a href="${cp }/board/FAQ/List?pageNum=${startPage-1}">이전</a>
				</c:if>
				<c:forEach var="i" begin="${startPage }" end="${endPage }">
					<c:choose>
						<c:when test="${pageNum==i }">
							<a
								href="${cp }/board/FAQ/List?pageNum=${i}&field=${field}&keyword=${keyword}"><span
								style="color: #35C5F0">${i }</span></a>
						</c:when>
						<c:otherwise>
							<a
								href="${cp }/board/FAQ/List?pageNum=${i}&field=${field}&keyword=${keyword}"><span
								style="color: gray">${i }</span></a>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:if test="${endPage<pageCount }">
					<a href="${cp }/board/FAQ/List?pageNum=${endPage+1}">다음</a>
				</c:if>
			</div>
			<div>
				<a href="${cp }/board/FAQ/List" class="button btn--reverse"
					style="margin-left: 205px; display: inline-block;">전체 보기</a> &nbsp;&nbsp;
				<c:if test="${sessionId =='admin' }">
					<a href="${cp }/board/FAQ/insert" class="button btn--reverse" style="display: inline-block;">FAQ 등록</a>
				</c:if>
			</div>

		</div>
	</main>

	<jsp:include page="/WEB-INF/page/include/footer.jsp" />
</body>
</html>