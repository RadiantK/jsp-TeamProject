<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내일의집</title>
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
</head>
<body>

	<jsp:include page="/WEB-INF/page/include/header.jsp" />


	<main>
		<div class="container">
			<h1>자주묻는질문 등록하기</h1>
			<form action="${cp }/board/FAQ/insert" method="post">
				<div class="form-group">
					<label for="title">제목</label><br> <input type="text"
						class="form-control" id="title" placeholder="제목 입력" name="title">
				</div>
				<br>

				<div class="form-group">
					<label for="writer">작성자</label><br> <input type="text"
						class="form-control" id="writer" name="nickname" value="admin"
						readonly="readonly">
				</div>
				<br>

				<div class="form-group">
					<label for="content">내용</label><br>
					<textarea class="form-control" rows="10" id="content"
						name="content" placeholder="내용 작성(최대 500자)"></textarea>
				</div>
				<br> <br>
				<button type="submit" class="button btn--reverse"
					style="display: inline-block;">등록</button>
				<a href="${cp }/board/FAQ/List" class="button btn--reverse"
					style="display: inline-block;">취소</a>

			</form>
		</div>
	</main>

	<jsp:include page="/WEB-INF/page/include/footer.jsp" />
</body>
</html>