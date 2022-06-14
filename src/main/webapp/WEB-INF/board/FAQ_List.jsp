<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자주묻는내용</title>
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
  <link rel="stylesheet" href="${cp}/css/main.css" />
   <link rel="stylesheet" href="${cp}/css/board.css" />
  
  <!-- Swiper -->
  <link rel="stylesheet" href="https://unpkg.com/swiper@8/swiper-bundle.min.css" />
  <script src="https://unpkg.com/swiper@8/swiper-bundle.min.js"></script>
  <!-- lodash -->
  <script src="https://cdnjs.cloudflare.com/ajax/libs/lodash.js/4.17.21/lodash.min.js" integrity="sha512-WFN04846sdKMIP5LKNphMaWzU7YpMyCU245etK3g/2ARYbPK9Ub18eG+ljU96qKRCWh+quCY7yefSmlkQw1ANQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
  <!-- gsap -->
  <script src="https://cdnjs.cloudflare.com/ajax/libs/gsap/3.10.4/gsap.min.js" integrity="sha512-VEBjfxWUOyzl0bAwh4gdLEaQyDYPvLrZql3pw1ifgb6fhEvZl9iDDehwHZ+dsMzA0Jfww8Xt7COSZuJ/slxc4Q==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
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
						<a href="${cp }/Notice_List">공지사항</a>&nbsp;&nbsp;&nbsp;&nbsp;<span>|</span>&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="${cp }/QNA_List">1:1문의</a>&nbsp;&nbsp;&nbsp;&nbsp;<span>|</span>&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="${cp }/FAQ_List">자주묻는질문</a>
					</div>
					<br>
				</div>
			</div>

			<div class="container w-50">
				<div class="d-flex align-items-center">
					<input class="form-control" type="search" placeholder="자주묻는 질문 검색" name="keyword">
					<button id="searchBtn" class="button btn--reverse" type="submit"
						style="margin-left: 5px">검색</button>
				</div>
			</div><br>


			<div class="row">
				<div class="col-md-12">
					<table class="table table-hover">
						<thead>
							<tr>
								<th scope="col" class="text-center">글번호</th>
								<th scope="col" class="text-center">제목</th>
								<th scope="col" class="text-center">작성일</th>

							</tr>
						</thead>
						<tbody>
							<tr>
								<td style="width: 10%" class="text-center">1</td>
								<td style="width: 70%" class="text-center"><a href="" onclick="showDetail()">환불처리는
										어떻게 진행되나요??</a></td>
								<td style="width: 20%" class="text-center">2022/06/09
									14:20:33</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>

			<div class="row">
				<div class="col-xs-12">
					<!-- 아이디가 admin일때만 보이도록 설정하기-->
					<c:if test="${param.id=='admin' }">
					<a href="${cp }/FAQ_insertForm" class="button btn--reverse" style="float: right">FAQ 등록</a>
					 </c:if> 
				</div>
			</div>


			<!--  페이징 처리하기 코드 
<div>
	<c:if test="${startPage>10 }">
		<a href="${cp }/board/list?pageNum=${startPage-1}">이전</a>
	</c:if>
	<c:forEach var="i" begin="${startPage }" end="${endPage }">
		<c:choose>
			<c:when test="${pageNum==i }">
				<a href="${cp }/board/list?pageNum=${i}"><span style="color:red">${i }</span></a>			
			</c:when>
			<c:otherwise>
				<a href="${cp }/board/list?pageNum=${i}"><span style="color:gray">${i }</span></a>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	<c:if test="">
		<a href="">다음</a>
	</c:if>
</div>
-->
		</div>
	</main>

<jsp:include page="/WEB-INF/page/include/footer.jsp" />
</body>
</html>