<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
					<h1 style="color: #35C5F0">1:1문의</h1>
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
							<td colspan="2" style="width: 20%">${vo.nickname }</td>
							<td colspan="2" style="width: 60%">${vo.regdate }</td>
								<c:choose>
								<%-- 문의상태에 대한 값이 null 일때 답변대기중 표시 null 이 아니면 DB에 저장된 qnastate 값 표시하기 --%>
									<c:when test="${vo.qnastate==null}">
										<td style="width: 10%" class="text-center">답변대기중</td>
									</c:when>
									<c:otherwise>
										<td style="width: 10%" class="text-center">${vo.qnastate }</td>									
									</c:otherwise>
								</c:choose>
						</tr>

						<c:if test="${vo.image != null}">
							<!-- 첨부파일이 있을 때만 파일 보이도록 설정 -->
							<tr>
								<td colspan="10"><img src="${cp }/upload/${vo.image }"><br>
								</td>
							</tr>
						</c:if>

						<tr>
							<td colspan="10" style="height: 400px; text-align: left;">
								<br>
								<br>
								<pre>${vo.content }</pre><br>
							</td>
						</tr>
					</tbody>
				</table>
				<!-- 답변이 있을 때만 보이도록 설정 -->
				<c:if test="${dto.content != null }">
					<div id="commList">
						<table class="table">
							<thead>
								<tr>
									<td colspan="3" style="width: 100%"><h1>${dto.title }</h1></td>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td colspan="2" style="width: 30%">${dto.nickname }</td>
									<td colspan="2" style="width: 70%">${dto.regdate }</td>
								</tr>
								<tr>
									<td colspan="10" style="height: 400px; text-align: left;">
										<br>
										<br>
										<pre>${dto.content }</pre><br>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</c:if>
				<a href="${cp }/board/QNA/List" class="button btn--reverse">목록</a>&nbsp;&nbsp;
				<c:if test="${sessionId =='admin' and dto.content != null }">
					<a href="${cp }/board/QNA/Comment/Update?qnaNum=${vo.qnaNum}"
						class="button btn--reverse">수정</a> &nbsp;&nbsp;
					<a onclick="return confirm('삭제하시겠습니까?')"
						href="${cp }/board/QNA/Comment/Delete?qnaNum=${vo.qnaNum}"
						class="button btn--reverse">삭제</a>
				</c:if>
				
				<!-- 세션에 담긴 Id가 관리자인 경우 답글을 달 수 있도록 설정 -->
				<c:if test="${sessionId =='admin' }">
					<div class="container" style="margin-top: 20px;">
						<h1>1:1문의 답변등록</h1>
						<form action="${cp }/board/QNA/Comment/insert?qnaNum=${vo.qnaNum}"
							method="post">
							<div class="form-group">
								<label for="title">제목</label><br> <input type="text"
									class="form-control" id="title" value="[re]${vo.title }에 관한 답변"
									name="title"><br>
							</div>

							<div class="form-group">
								<label for="writer">작성자</label><br> <input type="text"
									class="form-control" id="nickname" name="nickname"
									value="${sessionId }" readonly="readonly"><br>
							</div>

							<div class="form-group">
								<label for="content">내용</label><br>
								<textarea class="form-control" rows="10" id="content"
									name="content" placeholder="내용 작성(최대 500자)"></textarea><br>
							</div>
							<br>
							<button type="submit" class="button btn--reverse">답변등록</button>
						</form>
					</div>
					
					<!-- 세션에 담긴 Id가 관리자가 아닐 경우 자신의 글 수정 삭제 가능. -->
				</c:if>
				<c:if test="${sessionId !='admin' }">
					<a href="${cp }/board/QNA/Update?qnaNum=${vo.qnaNum}"
						class="button btn--reverse">수정</a> &nbsp;&nbsp;
					<a onclick="return confirm('삭제하시겠습니까?')"
						href="${cp }/board/QNA/Delete?qnaNum=${vo.qnaNum}"
						class="button btn--reverse">삭제</a>
				</c:if>
			</div>
		</div>
	</main>

	<jsp:include page="/WEB-INF/page/include/footer.jsp" />
</body>
</html>