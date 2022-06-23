<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
			<div class="row">
				<div class="col-xs-12">
					<h1 style="color: #35C5F0">공지사항</h1>
					<br>
				</div>
				<div class="col-xs-12">
					<p>내일의 집의 새로운 소식입니다.</p>
					<br>
				</div>
				<table class="table">
					<thead>
						<tr>
							<td colspan="3" style="width: 100%"><h1>${vo.title }</h1></td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="2" style="width: 15%">${vo.nickname }</td>
							<td colspan="2" style="width: 70%">${vo.regdate }</td>
							<td colspan="2" style="width: 15%">조회수&nbsp; ${vo.hit  }</td>
						</tr>
						<tr>
							<td colspan="10" style="height: 400px; text-align: left;">
							<br>
							<br>
							<pre>${vo.content}</pre>
							</td>
						</tr>
					</tbody>
				</table>
				<div class="btn-group">
					<a href="${cp }/board/Notice/Listmove" class="button btn--reverse">목록</a>&nbsp;&nbsp;
					<!-- 세션에 담긴 Id가 관리자인 경우에만 공지내용 수정 및 삭제가능. -->
					<c:if test="${sessionId=='admin' }">
						<a href="${cp }/board/Notice/Update?noticeNum=${vo.noticeNum}"
							class="button btn--reverse">수정</a> &nbsp;&nbsp;
					<a onclick="return confirm('삭제하시겠습니까?')"
							href="${cp }/board/Notice/Delete?noticeNum=${vo.noticeNum}"
							class="button btn--reverse">삭제</a>
					</c:if>

				</div>
			</div>
		</div>
	</main>

	<jsp:include page="/WEB-INF/page/include/footer.jsp" />
</body>
</html>