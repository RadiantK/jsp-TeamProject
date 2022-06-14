// 배지 이벤트
const badgeEl = document.querySelector('header .badges');
// window : 브라우저 하나의 창 
// _.throttle(함수, 시간)
window.addEventListener('scroll', _.throttle(function(){
  console.log(window.scrollY);
  if(window.scrollY > 500) {
    // 배지 숨기기
    // gsap: 자바스크립트의 애니매이션을 처리해주는 라이브러리
    // gsap.to(요소, 지속시간s단위, 옵션);
    gsap.to(badgeEl, .6, {
      opacity: 0,
      display: 'none'
    });
  } else {
    // 배지 보이기
    gsap.to(badgeEl, .6, {
      opacity: 1,
      display: 'block'
    });
  }
}, 400));



// new Swiper(선택자, 옵션)
new Swiper('.interior .swiper', {
  direction: 'horizontal',
  slidesPerView: 1, // 한번에 보여줄 슬라이드 개수(기본값 1);
  loop: true,
  autoplay: {
    delay: 5000
  },
  pagination: {
    el: '.interior .swiper-pagination', // 페이지 번호 요소 선택자
    clickable: true // 사용자의 페이지 번호 요소 제어 가능 여부
  }
});