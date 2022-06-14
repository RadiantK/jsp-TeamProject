<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
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
  <link rel="stylesheet" href="${cp}/css/main.css" />
  
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

  <!-- interior 슬라이드 -->
  <section class="interior">
    <div class="inner">

      <div class="swiper">
        <div class="swiper-wrapper">
          <div class="swiper-slide">
            <img src="./images/main/interior1.jpg" alt="인테리어 이미지" />
            <span class="text">모던한 인테리어</span>
            <a href="javascript:void(0)" class="button">보러가기</a>
          </div>
          <div class="swiper-slide">
            <img src="./images/main/interior2.jpg" alt="인테리어 이미지" />
            <span class="text">심플한 침실 인테리어</span>
            <a href="javascript:void(0)" class="button">보러가기</a>
          </div>
          <div class="swiper-slide">
            <img src="./images/main/interior3.jpg" alt="인테리어 이미지" />
            <span class="text">엣지있는 침실 인테리어</span>
            <a href="javascript:void(0)" class="button">보러가기</a>
          </div>
          <div class="swiper-slide">
            <img src="./images/main/interior4.jpg" alt="인테리어 이미지" />
            <span class="text">깔끔한 거실 인테리어</span>
            <a href="javascript:void(0)" class="button">보러가기</a>
          </div>
          <div class="swiper-slide">
            <img src="./images/main/interior5.jpg" alt="인테리어 이미지" />
            <span class="text">자연스럽고 심플한 인테리어</span>
            <a href="javascript:void(0)" class="button">보러가기</a>
          </div>
          <div class="swiper-slide">
            <img src="./images/main/interior6.jpg" alt="인테리어 이미지" />
            <span class="text">분위기있는 거실 인테리어</span>
            <a href="javascript:void(0)" class="button">보러가기</a>
          </div>
          <div class="swiper-slide">
            <img src="./images/main/interior7.jpg" alt="인테리어 이미지" />
            <span class="text">깔끔하고 세련된 인테리어</span>
            <a href="javascript:void(0)" class="button">보러가기</a>
          </div>
          <div class="swiper-slide">
            <img src="./images/main/interior8.jpg" alt="인테리어 이미지" />
            <span class="text">심플한 욕실 디자인</span>
            <a href="javascript:void(0)" class="button">보러가기</a>
          </div>
        </div>

        <div class="swiper-pagination"></div>
      </div>

    </div>
  </section>


  <!-- Quick menu -->
  <section class="quick">
    <div class="inner">
      <ul class="quick-menu">
        <li class="item">
          <a href="javascript:void(0)">
            <img src="./images/main/menu1.png" alt="메뉴">
            <div>쇼핑하기</div>
          </a>
        </li>
        <li class="item">
          <a href="javascript:void(0)">
            <img src="./images/main/menu2.png" alt="메뉴">
            <div>여름초특가</div>
          </a>
        </li>
        <li class="item">
          <a href="javascript:void(0)">
            <img src="./images/main/menu3.png" alt="메뉴">
            <div>N평집들이</div>
          </a>
        </li>
        <li class="item">
          <a href="javascript:void(0)">
            <img src="./images/main/menu4.png" alt="메뉴">
            <div>공간별사진</div>
          </a>
        </li>
        <li class="item">
          <a href="javascript:void(0)">
            <img src="./images/main/menu5.png" alt="메뉴">
            <div>시공업체</div>
          </a>
        </li>
        <li class="item">
          <a href="javascript:void(0)">
            <img src="./images/main/menu6.png" alt="메뉴">
            <div>쉬운이사</div>
          </a>
        </li>
        <li class="item">
          <a href="javascript:void(0)">
            <img src="./images/main/menu7.png" alt="메뉴">
            <div>오늘의딜</div>
          </a>
        </li>
        <li class="item">
          <a href="javascript:void(0)">
            <img src="./images/main/menu8.png" alt="메뉴">
            <div>빠른배송</div>
          </a>
        </li>
        <li class="item">
          <a href="javascript:void(0)">
            <img src="./images/main/menu9.png" alt="메뉴">
            <div>집순이로그</div>
          </a>
        </li>
      </ul>
    </div>
  </section>

  <section class="sns">
    <div class="inner">
      <p class="title">오늘의 스토리</p>
      <ul class="sns-menu">
        <li class="item">
          <a href="javascript:void(0)">
            <img src="images/main/story1.jpg" alt="스토리" />
            <div>기본적이고 심플하고 깔끔한 거실 디자인. 눕고싶은 공간 만들기</div>
            <p>koko1234</p>
          </a>
        </li>
        <li class="item">
          <a href="javascript:void(0)">
            <img src="images/main/story2.jpg" alt="스토리" />
            <div>벽난로와 균형잡힌 가구 세팅으로 분위기있는 공간 만들기</div>
            <p>settingMind</p>
          </a>
        </li>
        <li class="item">
          <a href="javascript:void(0)">
            <img src="images/main/story3.jpg" alt="스토리" />
            <div>식사에 누구보다 진심. 거실을 분위기 있게 다이닝</div>
            <p>diningFeeling</p>
          </a>
        </li>
      </ul>
    </div>

    <div class="event">
      <a href="javascript:void(0)">
        <img src="./images/main/event1.png" alt="이벤트">
      </a>
    </div>
  </section>

	<jsp:include page="/WEB-INF/page/include/footer.jsp" />

</body>
</html>