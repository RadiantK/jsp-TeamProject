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
  <link rel="stylesheet" href="${cp}/css/adminSalesList.css">

  <!-- 부트스트랩 js -->
  <script defer src="${cp}/resource/js/bootstrap.bundle.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
  <script defer src="${cp}/js/common.js"></script>
</head>
<body>
	<jsp:include page="/WEB-INF/page/include/header.jsp" />

  <section class="board">
    <div class="inner">

      <div class="board-title">
        <h2>매출관리</h2>
      </div>

      <form class="search-form" id="formTag" action="">

          <select id="year" name="year">
            <option ${(empty pamam.year || param.year == "all") ? "selected" : "" }value="all">all</option>
            <option ${(param.year=="2018") ? "selected" : ""} value="2018">2018</option>
            <option ${(param.year=="2019") ? "selected" : ""} value="2019">2019</option>
            <option ${(param.year=="2020") ? "selected" : ""} value="2020">2020</option>
            <option ${(param.year=="2021") ? "selected" : ""} value="2021">2021</option>
            <option ${(param.year=="2022") ? "selected" : ""} value="2022">2022</option>
          </select>
          <input class="words" type="text" id="month" name="month" value="${param.month}" placeholder="월 입력" />
          <input class="smt btn btn-info" type="button" value="검색"/>

      </form>

      <div class="sales">
        <ul class="amount">
          <li><span>결제 금액 : ${totalSales}</span></li>
          <li><span>결제취소 금액 : ${cancelPayment}</span></li>
          <li><span>총 매출액 : ${completePayment}</span></li>
        </ul>
      </div>

      <div class="board-list">
        <div class="top">
          <div class="num">번호</div>
          <div class="email">이메일</div>
          <div class="name">주문자</div>
          <div class="pnum">상품번호</div>
          <div class="cnt">수량</div>
          <div class="amount">결재금액</div>
          <div class="status">상태</div>
          <div class="regDate">주문일</div>
        </div>
        <c:forEach var="s" items="${list}">
	        <div>
	          <div class="num">${s.num}</div>
	          <div class="email">${s.email}</div>
	          <div class="name">${s.name}</div>
	          <div class="pnum">${s.pnum}</div>
	          <div class="cnt">${s.cnt}</div>
	          <div class="amount">${s.amount}</div>
	          <div class="status">${s.state}</div>
	          <div class="regDate">${s.orderDate}</div>
	        </div>
				</c:forEach>
      </div>
      
      <div class="board-page">
        <a href="${cp}/admin/sales/list?p=1&year=${param.year}&month=${param.month}" class="bt first">
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
	        	<a href="${cp}/admin/sales/list?p=${page-1}&year=${param.year}&month=${param.month}" class="bt prev">
		          <span class="material-symbols-outlined">
		            keyboard_arrow_left
		          </span>
		        </a>
        	</c:otherwise>
        </c:choose>
        
        
        <c:forEach var="i" begin="${startPage}" end="${endPage}" >
        	<c:choose>
        		<c:when test="${page == i}">
        			<a href="${cp}/admin/sales/list?p=${i}&year=${param.year}&month=${param.month}" class="page">
        				<span style="color: #0000FF;">${i}</span>
        			</a>
        		</c:when>
        		<c:otherwise>
        			<a href="${cp}/admin/sales/list?p=${i}&year=${param.year}&month=${param.month}" class="page">
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
	        	<a href="${cp}/admin/sales/list?p=${page+1}&year=${param.year}&month=${param.month}" class="bt next">
		          <span class="material-symbols-outlined">
		            keyboard_arrow_right
		          </span>
		        </a>
        	</c:otherwise>
        </c:choose>
        
        <a href="${cp}/admin/sales/list?p=${pageCount}&year=${param.year}&month=${param.month}" class="bt last">
          <span class="material-symbols-outlined">
            keyboard_double_arrow_right
          </span>
        </a>
      </div>

			<c:forEach var="c" items="${chart}">
			<div>
				<input type="hidden" class="day" name="day" value="${c.day}">
				<input type="hidden" class="price" name="price" value="${c.price}">
			</div>
			</c:forEach>	
			
      <div class="box">
        <canvas id="chart"></canvas>
      </div>

    </div>
  </section>

  <script>
    const formEl = document.getElementById('formTag');
    const selectEl = document.getElementById('year');
    const submitEl = document.querySelector('.smt');
    const monthEl = document.getElementById('month');

    monthEl.addEventListener('keypress', function(e){
      if(e.key == 'Enter'){
        searchHandler();
        e.preventDefault();
      }
    })

    selectEl.addEventListener('change', function(){
      formEl.submit();
    });

    submitEl.addEventListener('click', searchHandler);

    function searchHandler(){
      // 숫자만 사용가능
      let regExpWord = /^[0-9]*$/;

      let month = formEl.month;

      if(formEl.year.value == 'all'){
        alert('년도를 선택하세요');
        return;
      }

      if(!regExpWord.test(month.value)){
        alert('숫자만 입력가능합니다.');
        month.focus();
        return;
      }
      formEl.submit();
    }

    const dayEl = document.getElementsByClassName('day');
    const priceEl = document.getElementsByClassName('price');
    
    const labels = [];
    
    for(let i = 0; i < dayEl.length; i++){
      labels[i] = dayEl[i].value;
    }

    const temp = [];

    for(let i = 0; i < priceEl.length; i++){
      temp[i] = priceEl[i].value;
    }

    const data = {
      labels: labels,
      datasets: [{
        label: '',
        backgroundColor: '#6A5ACD',
        borderColor: '#A390EE',
        data: temp,
      }]
    };

    const config = {
      type: 'line',
      data: data,
      options: {
        plugins: {
            legend: {
                display: false,
            },
            title: {
                display: true,
                text: '${param.year} 매출 현황',
                font: {
                  size: 30
                },
                padding: {
                  top: 10,
                  bottom: 10
                }
            }
        },
    }
    };
    Chart.defaults.font.size = 14
    
    const myChart = new Chart(
      document.getElementById('chart'),
      config
    );
  </script>

	<jsp:include page="/WEB-INF/page/include/footer.jsp" />

</body>
</html>