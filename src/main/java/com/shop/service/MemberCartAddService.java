package com.shop.service;

import com.shop.dao.CartDao;
import com.shop.dao.CartDetailDao;
import com.shop.dao.MemberDao;
import com.shop.dao.ProductDao;
import com.shop.dto.Cart;
import com.shop.dto.CartDetail;
import com.shop.dto.Member;
import com.shop.dto.Product;
import com.shop.exception.CartException;

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
		Product p = productDao.selectOne(pnum);
		
		System.out.println(member.getMemberNum());
		Cart cart = cartDao.selectOne(member.getMemberNum());
		// 장바구니에 등록된 회원정보가 없으면 회원정보를 등록
		if(cart == null) {
			// 장바구니를 처음 사용하는 회원이면 장바구니에 회원정보 등록
			int n = cartDao.insert(member.getMemberNum());
			System.out.println(n);
			if(n < 1) {
				throw new CartException();
			}else {
				cart = cartDao.selectOne(member.getMemberNum());
				System.out.println(cart);
			}
		}
		
		// 장바구니 상세정보에 상품정보를 넣음
		int realPrice = 
				(int) (p.getPrice() - (p.getPrice() * (p.getDiscount() / 100.0)));
		System.out.println(realPrice);
		CartDetail detail = new CartDetail(
				0, cart.getCartNum(), p.getProductNum(), 
				p.getPname(), 1, 
				realPrice);
		
		int n = cartDetailDao.insert(detail);
		if(n < 1) {
			throw new CartException();
		}
		
		
		return n;
	}
}
