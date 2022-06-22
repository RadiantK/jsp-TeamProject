<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
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
				<div class="col-md-12">
					<table class="table table-hover">
						<thead>
							<tr>
								<th scope="col" class="text-center">문의번호</th>
								<th scope="col" class="text-center">작성자</th>
								<th scope="col" class="text-center">제목</th>
								<th scope="col" class="text-center">작성일</th>
								<th scope="col" class="text-center">문의상태</th>

							</tr>
						</thead>
						<tbody>
							<c:forEach var="vo" items="${list }">
								<tr>
									<td style="width: 10%" class="text-center">${vo.qnaNum }</td>
									<td style="width: 10%" class="text-center">${vo.nickname }</td>
									<td style="width: 60%" class="text-center"><a
										href="${cp }/board/QNA/Detail?qnaNum=${vo.qnaNum}">${vo.title }</a></td>
									<td style="width: 10%" class="text-center">${vo.regdate }</td>
									<c:choose>
									<%-- DB상 qnastate가 null일 경우 답변 대기중으로 변경, null 이 아닐 경우 해당 qnastate 값으로 변경 --%>
									<c:when test="${vo.qnastate==null}">
									<td style="width: 10%" class="text-center">답변대기중</td>
									</c:when>
									<c:otherwise>
									<td style="width: 10%" class="text-center">${vo.qnastate }</td>									
									</c:otherwise>
									</c:choose>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-12">
					<!-- 아이디가 admin이 아닐때만 보이도록 설정하기  -->
					<c:if test="${sessionId !='admin' }">
						<a href="${cp }/board/QNA/insert" class="button btn--reverse"
							style="float: right">1:1 문의하기</a>
					</c:if>
				</div>
			</div>


			<div id="paging" style="text-align: center; font-size: large;">
				<c:if test="${startPage>10 }">
					<a href="${cp }/board/QNA/List?pageNum=${startPage-1}">이전</a>
				</c:if>
				<c:forEach var="i" begin="${startPage }" end="${endPage }">
					<c:choose>
						<c:when test="${pageNum==i }">
							<a href="${cp }/board/QNA/List?pageNum=${i}"><span
								style="color: #35C5F0">${i }</span></a>
						</c:when>
						<c:otherwise>
							<a href="${cp }/board/QNA/List?pageNum=${i}"><span
								style="color: gray">${i }</span></a>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:if test="${endPage<pageCount }">
					<a href="${cp }/board/QNA/List?pageNum=${endPage+1}">다음</a>
				</c:if>
			</div>
	</main>

	<jsp:include page="/WEB-INF/page/include/footer.jsp" />
</body>
</html>