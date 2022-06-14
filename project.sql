CREATE TABLE member ( -- 회원
    member_num VARCHAR2(10) PRIMARY KEY, -- 회원번호
    email VARCHAR2(30), -- 이메일
    password VARCHAR2(30), -- 비밀번호
    name VARCHAR2(20), -- 이름
    nickname VARCHAR2(10), -- 닉네임
    phone VARCHAR2(20), -- 연락처
    address VARCHAR2(100), -- 주소
    gender VARCHAR2(10), -- 성별(male, female)
    regdate DATE DEFAULT SYSDATE, -- 가입일
    available NUMBER(1) DEFAULT 1, -- 탈퇴여부(1일반, 0탈퇴)
    role NUMBER(1) DEFAULT 1 -- 권한(1일반, 2관리자)
);

CREATE TABLE cart ( -- 장바구니
    cart_num NUMBER(7) PRIMARY KEY, -- 장바구니번호
    member_num VARCHAR2(10) REFERENCES member(member_num) -- 회원번호
);

CREATE TABLE cartdetail ( -- 장바구니 상세
    cartdetail_num NUMBER(7) PRIMARY KEY, -- 장바구니상세번호
    cart_num NUMBER(7) REFERENCES cart(cart_num), -- 장바구니번호
    product_num NUMBER(7), -- 상품번호
    pname VARCHAR2(100), -- 상품명
    cnt NUMBER(5), -- 수량
    price NUMBER(10) -- 가격
);

CREATE TABLE bcategory ( -- 상품카테고리
    bcategory_num NUMBER(7) PRIMARY KEY, -- 대분류 카테고리 번호
    btype VARCHAR2(30) UNIQUE-- 대분류명
);

CREATE TABLE scategory ( -- 상품카테고리
    scategory_num NUMBER(7) PRIMARY KEY, -- 소분류 카테고리 번호
    btype VARCHAR2(30) REFERENCES bcategory(btype), -- 대분류명
    stype VARCHAR2(30) -- 소분류명
);

CREATE TABLE product ( -- 상품
    product_num NUMBER(7) PRIMARY KEY, -- 상품번호
    category_num NUMBER(7) REFERENCES scategory(scategory_num), -- 카테고리번호
    code VARCHAR2(30), -- 상품코드
    pname VARCHAR2(100), -- 상품명
    price NUMBER(10), -- 가격
    discount NUMBER(3), -- 할인률
    cnt NUMBER(4), -- 재고
    regdate DATE default SYSDATE, -- 등록일
    image VARCHAR2(50) -- 상품이미지
);

CREATE TABLE productDetail ( -- 상품상세
    productdetail_num NUMBER(7) PRIMARY KEY, -- 상품상세정보번호
    product_num NUMBER(7) REFERENCES product(product_num), -- 상품번호
    images VARCHAR2(50) -- 사용될 이미지들
);

CREATE TABLE review ( -- 상품리뷰
    review_num NUMBER(7) PRIMARY KEY, -- 리뷰번호
    product_num NUMBER(7) REFERENCES product(product_num), --상품번호
    nickname VARCHAR2(30) REFERENCES member(nickname), --작성자(닉네임)
    image VARCHAR2(50), -- 첨부파일
    content VARCHAR2(150), -- 리뷰내용
    score NUMBER(1), -- 평점
    regdate DATE DEFAULT SYSDATE -- 등록일
);

CREATE TABLE ORDERS ( -- 주문서
    order_num NUMBER(7) PRIMARY KEY, -- 주문서번호
    email  VARCHAR2(30) REFERENCES member(email), -- 회원이메일
    name VARCHAR2(20), -- 주문자명
    phone VARCHAR2(20), -- 전화번호
    address VARCHAR2(100), -- 주소,
    amount NUMBER(10), -- 결제금액
    regdate DATE DEFAULT SYSDATE, -- 주문일
    orderpwd VARCHAR2(20) -- 주문비밀번호
);

CREATE TABLE orderdetail ( -- 주문서상세
    detailorder_num NUMBER(7) PRIMARY KEY, -- 상세주문서번호
    order_num NUMBER(7) REFERENCES orders(order_num), -- 주문서번호
    pnum NUMBER(7), -- 상품번호
    pname VARCHAR2(100), -- 상품명
    cnt NUMBER(4) -- 구매수량
);

CREATE TABLE delivery ( -- 배송정보
    delivery_num NUMBER(7) primary key, -- 배송번호
    order_num NUMBER(7) REFERENCES orders(order_num), -- 주문번호
    request VARCHAR2(50), -- 요청사항
    delidate DATE DEFAULT SYSDATE, --배송일
    status VARCHAR2(20) -- 배송상태
);

CREATE TABLE payment ( -- 결제정보
    payment_num NUMBER(7) PRIMARY KEY, -- 결제번호
    order_num NUMBER(7) REFERENCES orders(order_num), -- 주문번호
    means VARCHAR2(30), -- 결제수단
    amount NUMBER(10), --결제금액
    regdate DATE DEFAULT SYSDATE, -- 결제일
    status VARCHAR2(20) -- 결제상태
);

CREATE TABLE qna ( -- 1:1문의
    qna_num NUMBER(7) PRIMARY KEY, -- 문의번호
    nickname VARCHAR2(30) REFERENCES member(nickname), --작성자(닉네임)
    title VARCHAR2(50), -- 제목
    content VARCHAR2(500), -- 내용
    image VARCHAR2(50), -- 첨부파일
    regdate DATE DEFAULT SYSDATE -- 작성일
);

CREATE TABLE qnacomment ( -- 문의답변
    qnacomment_num NUMBER(7) PRIMARY KEY, -- 답변번호
    qna_num NUMBER(7) REFERENCES qna(qna_num), -- 문의번호
    nickname VARCHAR2(30) REFERENCES member(nickname), --작성자(닉네임)
    title VARCHAR2(50), -- 제목
    content VARCHAR2(500), -- 내용
    regdate DATE DEFAULT SYSDATE -- 작성일
);

CREATE TABLE notice ( -- 공지사항
    notice_num NUMBER(7) PRIMARY KEY, -- 공지번호
    nickname VARCHAR2(30) REFERENCES member(nickname), --작성자(닉네임)
    title VARCHAR2(50), -- 제목
    content VARCHAR2(500), -- 내용
    regdate DATE DEFAULT SYSDATE, -- 작성일
    hit NUMBER(8) DEFAULT 0 -- 조회수
);

CREATE TABLE faq ( -- 자주묻는질문
    faq_num NUMBER(7) PRIMARY KEY, -- 공지번호
    nickname VARCHAR2(30) REFERENCES member(nickname), --작성자(닉네임)
    title VARCHAR2(50), -- 제목
    content VARCHAR2(500), -- 내용
    regdate DATE DEFAULT SYSDATE -- 작성일
);


-- 테이블 드랍
DROP TABLE member;
DROP TABLE cart;
DROP TABLE bcategory;
DROP TABLE cartdetail;
DROP TABLE scategory;
DROP TABLE product;
DROP TABLE productdetail;
DROP TABLE review;
DROP TABLE orders;
DROP TABLE orderdetail;
DROP TABLE delivery;
DROP TABLE payment;
DROP TABLE qna;
DROP TABLE qnacomment;
DROP TABLE notice;
DROP TABLE faq;


-- 시퀀스 생성
CREATE SEQUENCE seq_member;
CREATE SEQUENCE seq_cart;
CREATE SEQUENCE seq_cartdetail;
CREATE SEQUENCE seq_bcategory;
CREATE SEQUENCE seq_scategory;
CREATE SEQUENCE seq_product;
CREATE SEQUENCE seq_productDetail;
CREATE SEQUENCE seq_review;
CREATE SEQUENCE seq_orders;
CREATE SEQUENCE seq_orderdetail;
CREATE SEQUENCE seq_delivery;
CREATE SEQUENCE seq_payment;
CREATE SEQUENCE seq_qna;
CREATE SEQUENCE seq_qnacomment;
CREATE SEQUENCE seq_notice;
CREATE SEQUENCE seq_faq;


--시퀀스 드랍
DROP SEQUENCE seq_member;
DROP SEQUENCE seq_cart;
DROP SEQUENCE seq_cartdetail;
DROP SEQUENCE seq_bcategory;
DROP SEQUENCE seq_scategory;
DROP SEQUENCE seq_product;
DROP SEQUENCE seq_productDetail;
DROP SEQUENCE seq_review;
DROP SEQUENCE seq_orders;
DROP SEQUENCE seq_orderdetail;
DROP SEQUENCE seq_delivery;
DROP SEQUENCE seq_payment;
DROP SEQUENCE seq_qna;
DROP SEQUENCE seq_qnacomment;
DROP SEQUENCE seq_notice;
DROP SEQUENCE seq_faq;