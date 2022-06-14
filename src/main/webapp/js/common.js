// 검색기능 focus
const searchEl = document.querySelector('.search');
const searchInputEl = searchEl.querySelector('#search');

searchEl.addEventListener('click', function(){
  searchInputEl.focus();
});

searchInputEl.addEventListener('focus', function(){
  searchEl.classList.add('focused');
  searchInputEl.setAttribute('placeholder', '통합검색');
});

searchInputEl.addEventListener('blur', function(){
  searchEl.classList.remove('focused');
  searchInputEl.setAttribute('placeholder', '');
});

// 올해가 몇년도인지 확인
const thisYear = document.querySelector('.this-year');
thisYear.textContent = new Date().getFullYear(); // 2021이라는 숫자 반환
