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

<script type="text/javascript">
	window.onload=function(){
		noticeList(1); // 메소드 호출할때마다 보여질 페이지 == 1
}
	function noticeList(pageNum){
		let xhr=new XMLHttpRequest();
		xhr=new XMLHttpRequest();
		xhr.onreadystatechange=function(){
			if(xhr.readyState==4 && xhr.status==200){
				let noticeList=document.getElementById("noticeList");
				// 기존 공지 삭제
				let child=noticeList.childNodes;
				for(let i=child.length-1;i>=0;i--){
					let c=child.item(i);
					noticeList.removeChild(c);
				}
				let result=xhr.responseText;
				let data=JSON.parse(result);
				let notice=data.list;
				for(var i=0;i<notice.length;i++){
					var noticeNum=notice[i].noticeNum;
					var memberNum=notice[i].memberNum;
					var nickname=notice[i].nickname;
					var title=notice[i].title;
					var content=notice[i].content;
					var regdate=notice[i].regdate;
					var hit=notice[i].hit;
					// 테이블에서 tbody 중 tr 부분안에 td 넣기.
					var tr=document.createElement("tr");
					tr.innerHTML=				"<td style='width: 10%' class='text-center'>"+noticeNum+"</td>" +
												"<td style='width: 60%' class='text-center'><a href='${cp}/board/Notice/Detail?noticeNum="+noticeNum+"'>"+title+"</a></td>" +
												"<td style='width: 20%' class='text-center'>"+ regdate +"</td>" +
												"<td style='width: 10%' class='text-center'>"+ hit + "</td>" 
							noticeList.appendChild(tr);
										
				}
				let startPage = data.startPage;
  				let endPage = data.endPage;
  				let pageCount = data.pageCount;
  				let pageHTML = "";
  				if(startPage>10){
  					pageHTML += "<a href='javascript:noticeList("+ (startPage-1) +")'>이전</a>";
  				}
  				for(let i=startPage;i<=endPage;i++){
  					if(i==pageNum){
  						pageHTML +="<a href='javascript:noticeList("+ i +")'><span style='color:#35C5F0'><u> "+ i +"</u></span></a>";
					}else{
						pageHTML +="<a href='javascript:noticeList("+ i +")'><span style='color:gray'> "+ i +"</span></a>";
					}
  				}
  				if(endPage<pageCount){
  					pageHTML +="<a href='javascript:noticeList("+ (endPage+1) +")'>다음</a>";
				}
				var page = document.getElementById("paging");
				page.innerHTML = pageHTML;
			}
		};
		xhr.open('get','${cp}/board/Notice/List?pageNum='+ pageNum,true);
		xhr.send();
	}
</script>

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
								<th scope="col" class="text-center">공지번호</th>
								<th scope="col" class="text-center">제목</th>
								<th scope="col" class="text-center">작성일</th>
								<th scope="col" class="text-center">조회수</th>
							</tr>
						</thead>
						
						<tbody id="noticeList">
						<%-- JSON 처리로 변경
							<c:forEach var="vo" items="${list }">
								<tr>
									<td style="width: 10%" class="text-center">${vo.noticeNum }</td>
									<td style="width: 60%" class="text-center"><a
										href="${cp }/board/Notice/Detail?noticeNum=${vo.noticeNum}">${vo.title }</a></td>
									<td style="width: 20%" class="text-center">${vo.regdate }</td>
									<td style="width: 10%" class="text-center">${vo.hit }</td>
								</tr>
							</c:forEach>
						--%>	
						</tbody>						
					</table>
				</div>
			</div>
			

			<div class="row">
				<div class="col-xs-12">
					<!-- 세션에 담긴 Id가 관리자일 경우에만 공지 등록 가능하도록 설정 -->
					<c:if test="${sessionId=='admin' }">
						<a href="${cp }/board/Notice/insert" class="button btn--reverse"
							style="float: right">공지등록</a>
					</c:if>
				</div>
			</div>


			<!-- 목록 페이징 처리 -->
			<div id="paging" style="text-align: center; font-size: large;">
			<%--  JSON 처리로 변경
				<c:if test="${startPage>10 }">
					<a href="${cp }/board/Notice/List?pageNum=${startPage-1}">이전</a>
				</c:if>
				<c:forEach var="i" begin="${startPage }" end="${endPage }">
					<c:choose>
						<c:when test="${pageNum==i }">
							<a href="${cp }/board/Notice/List?pageNum=${i}"><span
								style="color: #35C5F0">${i }</span></a>
						</c:when>
						<c:otherwise>
							<a href="${cp }/board/Notice/List?pageNum=${i}"><span
								style="color: gray">${i }</span></a>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:if test="${endPage<pageCount }">
					<a href="${cp }/board/Notice/List?pageNum=${endPage+1}">다음</a>
				</c:if>
			--%>
			</div>

		</div>
	</main>

	<jsp:include page="/WEB-INF/page/include/footer.jsp" />


</body>
</html>