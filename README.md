# 인테리어 쇼핑몰

## 사용환경
- JAVA(JDK 11)
- Eclipse IDE
- TOMCAT(9버전)
- ORACLE(18C XE)
- BootStrap(5.1)
- ERD cloud
<br/>
<br/>

## ORACLE설정
데이터베이스 사용자 설정
```sql
ALTER SESSION SET “_oracle_script”=true; -- 생성할 사용자명에 C##을 붙이지 않기위해서 사용
CREATE USER jsp identified by jsp;
GRANT CONNECT, RESOURCE, DBA TO jsp;
```
프로젝트의 테이블 및 시퀀스의 확인은 Repository의 project.sql 파일을 통해 확인.
<br/>
<br/>

## 프로젝트 개요
주제 선정 -> DB 모델링(정규화) -> 페이지 HTML, CSS 작업(레이아웃) -> JAVA 기본설정(JNDI, ORACLE 연동) -> JSP웹 페이지 기능 구현(MVC2 패턴) -> 프로젝트 마무리
<br/>

**관리자**
- 관리자는 회원정보 관리, 쇼핑몰 전체 매출 관리 및 차트 확인, 상품 관리, 결제내역 관리, 고객센터 처리를 함.

**사용자**
- 사용자는 사이트의 회원가입, 로그인 기능, 마이페이지, 상품목록 및 상품 구매, 제품 구매 및 결제(회원, 비회원 분류), 고객센터 사용을 함.
<br/>
<br/>

## 프로젝트 ER 다이어그램
![project-erd](https://user-images.githubusercontent.com/95058915/175250586-d02c327f-a767-4a8e-8f06-07899a42ccb2.png)
<br/>
<br/>

## 자바스크립트 사용 라이브러리
**Swiper**
- [Swiper](https://swiperjs.com/)는 슬라이드 처리 라이브러리입니다.
- [Getting Started With Swiper](https://swiperjs.com/get-started)
<br/>

**Chart.js**
- [Chart](https://www.chartjs.org/)는 차트를 제어할 수 있도록 도와주는 라이브러리 입니다.
- [Getting Started With Chart.js](https://www.chartjs.org/docs/latest/getting-started/)
<br/>
<br/>


## 사용 외부 라이브러리
- OJDBC 8 (오라클 Connect)
- [JSTL](https://tomcat.apache.org/download-taglibs.cgi) : JSP Standard Tag Libaray
- [cos.jar](http://www.servlets.com/) : 파일 전송 라이브러리
- [JSON.jar](https://github.com/stleary/JSON-java) : AJAX의 JSON처리를 편리하게 도와주는 라이브러리

## 사용 API
- [KAKAO API](https://developers.kakao.com/) : 카카오 로그인 처리를 위한 API사용
- [다음(KAKAO) 주소 API)](https://postcode.map.daum.net/guide) : 우편번호 서비스
- [아임포트](https://www.iamport.kr/) : 결제 처리

## 코드 유의사항
- js폴더의 kakaoLogin.txt파일을 자신의 카카오 자바스크립트 앱키를 사용하여 kakaoLogin.js 폴더로 변환해서 사용할 것
- js폴더의 orders.js폴더의 i'm port 앱 키를 자신의 앱 키를 사용할 
