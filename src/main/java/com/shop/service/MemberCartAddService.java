package com.shop.service;

import com.shop.dao.CartDao;
import com.shop.dao.CartDetailDao;
import com.shop.dao.MemberDao;
import com.shop.dao.ProductDao;
import com.shop.dto.Member;
import com.shop.dto.Product;

public class MemberCartAddService {

	private CartDao cartDao;
	private CartDetailDao cartDetailDao;
	private ProductDao productDao;
	private MemberDao memberDao;
	
	public MemberCartAddService(CartDao cartDao, CartDetailDao cartDetailDao, ProductDao productDao,
			MemberDao memberDao) {
		this.cartDao = cartDao;
		this.cartDetailDao = cartDetailDao;
		this.productDao = productDao;
		this.memberDao = memberDao;
	}

	// 장바구니 추가
	public int addCart(int pnum, String email) {
		// 회원번호 얻기
		Member member = memberDao.selectOne(email);
		// 상품정보 얻어오기
		Product product = productDao.selectOne(pnum);
		
		
		
		return 1;
	}
}
