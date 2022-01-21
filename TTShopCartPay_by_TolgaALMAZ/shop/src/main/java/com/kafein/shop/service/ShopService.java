package com.kafein.shop.service;

public interface ShopService {

	//Tolga ALMAZ: PayCart service signature is introduced.
	String payCart(Long cartID, Long userID) throws Exception;
}
