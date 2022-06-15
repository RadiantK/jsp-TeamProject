<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <!-- HEADER -->
  <header>
    <div class="inner">
      <div class="logo">
        <a href="main.html" >
          <span class="title">내일의집</span>
        </a>
      </div>

      <ul class="main-menu">
        <li class="item">
          <div class="item__name">스토어</div>
          <div class="item__contents">
            <div class="contents__menu">
              <ul class="inner">
                <li>
                  <a href="">
                    <img src="${cp}/images/main/drop_funiture.png" alt="가구" />
                    <div>가구</div>
                  </a>
                </li>
                <li>
                  <a href="">
                    <img src="${cp}/images/main/drop_fabric.png" alt="패브릭" />
                    <div>패브릭</div>
                  </a>
                </li>
                <li>
                  <a href="">
                    <img src="${cp}/images/main/drop_light.png" alt="조명" />
                    <div>조명</div>
                  </a>
                </li>
                <li>
                  <a href="">
                    <img src="${cp}/images/main/drop_appliances.png" alt="가전제품" />
                    <div>가전제품</div>
                  </a>
                </li>
                <li>
                  <a href="">
                    <img src="${cp}/images/main/drop_kitchenware.png" alt="주방용품" />
                    <div>주방용품</div>
                  </a>
                </li>
              </ul>
            </div>
          </div>
        </li>
        <li class="item">
          <div class="item__name"><a href="${cp}/user/mypage/profile">마이페이지</a></div>
        </li>
        <li class="item">
          <div class="item__name"><a href="${cp }/board/Notice/List">고객센터</a></div>
        </li>
      </ul>


      <div class="sub-menu">
        <form action="login.html">
          <div class="search">
            <input type="text" id="search" name="search" />
            <input type="button" class="material-symbols-outlined" value="search" />
          </div>
        </form>
        <ul class="menu">
          <li>
            <a href="${cp}/orders/cart">
              <span class="material-symbols-outlined">
              shopping_cart
              </span>
            </a>
          </li>
          <c:choose>
          	<c:when test="${empty sessionId}">
          		<li>
		            <a href="${cp}/user/login">로그인</a>
		          </li>
		          <li>
		            <a href="join.html">회원가입</a>
		          </li>
          	</c:when>
          	<c:otherwise>
          		<li>
		            <a href="${cp}/user/logout">로그아웃</a>
		          </li>
          	</c:otherwise>
          </c:choose>
        </ul>
        
      </div>
    </div>

  </header>
  
  <!-- header가 fix이기때문에 마진을 줘야함 -->
  <section class="header__margin"></section>