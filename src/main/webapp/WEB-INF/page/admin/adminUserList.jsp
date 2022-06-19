<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>내일의집</title>
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
  <link rel="stylesheet" href="${cp}/css/adminUserList.css">

  <!-- 부트스트랩 js -->
  <script defer src="${cp}/resource/js/bootstrap.bundle.js"></script>
  <script defer src="${cp}/js/common.js"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/page/include/header.jsp" />

  <section class="board">
    <div class="inner">

      <div class="board-title">
        <h2>회원목록</h2>
      </div>

      <form class="search-form">
        <select name="t">
          <option ${(param.t == "email") ? "selected" : ""} value="email">이메일</option>
          <option ${(param.t == "nickName") ? "selected" : ""} value="nickName">닉네임</option>
          <option ${(param.t == "available") ? "selected" : ""} value="available">탈퇴여부</option>
        </select>
        <input class="words" type="text" name="q" value="${param.q}" placeholder="검색어 입력" />
        <input class="smt btn btn-info" type="submit" value="검색"/>

      </form>

      <div class="board-list">
        <div class="top">
          <div class="num">번호</div>
          <div class="email">이메일</div>
          <div class="password">비밀번호</div>
          <div class="name">이름</div>
          <div class="nickName">닉네임</div>
          <div class="phone">연락처</div>
          <div class="address">주소</div>
          <div class="gender">성별</div>
          <div class="regDate">가입일</div>
          <div class="available">탈퇴</div>
          <div class="role">권한</div>
        </div>
        <c:forEach var="m" items="${list}">
	        <div>
							<div class="num">${m.memberNum}</div>
		          <div class="email">${m.email}</div>
		          <div class="password">${m.password}</div>
		          <div class="name">${m.name}</div>
		          <div class="nickName">${m.nickName}</div>
		          <div class="phone">${m.phone}</div>
		          <div class="address">${m.address}</div>
		          <div class="gender">${m.gender}</div>
		          <div class="regDate">${m.regDate}</div>
		          <div class="available">${m.available}</div>
		          <div class="role">${m.role}</div>
	        </div>
        </c:forEach>
        
      </div>
      
      <div class="board-page">
        <a href="${cp}/admin/user/list?p=1&t=${param.t}&q=${param.q}" class="bt first">
          <span class="material-symbols-outlined">
            keyboard_double_arrow_left
          </span>
        </a>
        
        <c:choose>
        	<c:when test="${page <= 1}">
        		<a href="javascript:void(0)" class="bt prev">
	        		<span class="material-symbols-outlined" onclick="alert('이전 페이지가 없습니다.');">
		            keyboard_arrow_left
		          </span>
	          </a>
        	</c:when>
        	<c:otherwise>
	        	<a href="${cp}/admin/user/list?p=${page-1}&t=${param.t}&q=${param.q}" class="bt prev">
		          <span class="material-symbols-outlined">
		            keyboard_arrow_left
		          </span>
		        </a>
        	</c:otherwise>
        </c:choose>
        
        
        <c:forEach var="i" begin="${startPage}" end="${endPage}" >
        	<c:choose>
        		<c:when test="${page == i}">
        			<a href="${cp}/admin/user/list?p=${i}&t=${param.t}&w=${param.q}" class="page">
        				<span style="color: #0000FF;">${i}</span>
        			</a>
        		</c:when>
        		<c:otherwise>
        			<a href="${cp}/admin/user/list?p=${i}&t=${param.t}&w=${param.q}" class="page">
        				<span style="color: #000;">${i}</span>
        			</a>
        		</c:otherwise>
        	</c:choose>
        </c:forEach>
        
        <c:choose>
        	<c:when test="${page >= pageCount}">
        		<a href="javascript:void(0)" class="bt next">
		          <span class="material-symbols-outlined" onclick="alert('다음페이지가 없습니다.');">
		            keyboard_arrow_right
		          </span>
						</a>
        	</c:when>
        	<c:otherwise>
	        	<a href="${cp}/admin/user/list?p=${page+1}&t=${param.t}&q=${param.q}" class="bt next">
		          <span class="material-symbols-outlined">
		            keyboard_arrow_right
		          </span>
		        </a>
        	</c:otherwise>
        </c:choose>
        
        <a href="${cp}/admin/user/list?p=${pageCount}&t=${param.t}&q=${param.q}" class="bt last">
          <span class="material-symbols-outlined">
            keyboard_double_arrow_right
          </span>
        </a>
      </div>

    </div>
  </section>

	<jsp:include page="/WEB-INF/page/include/footer.jsp" />

</body>
</html>