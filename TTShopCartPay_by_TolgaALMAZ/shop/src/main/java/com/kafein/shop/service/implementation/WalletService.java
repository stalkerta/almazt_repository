package com.kafein.shop.service.implementation;

import com.kafein.shop.dto.Wallet;
import com.kafein.shop.entity.Item;
import com.kafein.shop.entity.ShoppingCart;
import com.kafein.shop.repository.ShoppingCartRepository;
import com.kafein.shop.service.ShopService;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WalletService implements ShopService {
		
    private final ShoppingCartRepository shoppingCartRepository;
    private static final String USER_ID = "userId";
    private static final String PAY_URI = "http://localhost:8005/wallet/withdraw-money-from-wallet/{userId}/{amount}";
    private static final String AMOUNT = "amount";
    private static final String PAYMENT_SUCCESSFUL = "PAYMENT_SUCCESSFUL";
    private static final String INSUFFICIENT_AMOUNT = "PAYMENT_INSUFFICIENT_AMOUNT";

    public WalletService(ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }
	
    //Tolga ALMAZ: PayCart service takes cartID parameter and use it to find shoppingCart to be paid. The total price of items in shopping cart is calculated.
    // payFromWallets method is used to consume WALLET API's withdraw-money-from-wallet service.
    @Override
    public String payCart(Long cartID, Long userId) throws Exception {
	    String result = null;
	        ShoppingCart shoppingCart = shoppingCartRepository.findShoppingCartById(cartID); 
	        Set<Item> cartItems = shoppingCart.getItems();
	        BigDecimal totalPrice = new BigDecimal(0);
	        for(Item item : cartItems) {
	        	totalPrice = totalPrice.add(item.getPrice());
	        }
	        if(PAYMENT_SUCCESSFUL.equalsIgnoreCase(payFromWallets(userId, totalPrice.longValue()))) {
		        shoppingCart.setItems(null);
		        shoppingCartRepository.save(shoppingCart);
		        result = PAYMENT_SUCCESSFUL;
	        }else {
	        	result = INSUFFICIENT_AMOUNT;
	        }	        
	    return result;
    }
    
    //Tolga ALMAZ: payFromWallets method is newly introduced to pay price of items in shoppingCart from most suitable wallet/wallets by consuming Wallet API's pay service.
    private String payFromWallets(Long userId, Long amount) throws Exception {
    	RestTemplate restTemplate = new RestTemplate();
	    Map<String, Long> params = new HashMap<String, Long>();
	    params.put(USER_ID, userId);
	    params.put(AMOUNT, amount);
        HttpHeaders headers = new HttpHeaders();
        Wallet wallet = new Wallet();
        HttpEntity<Wallet> requestEntity = new HttpEntity<Wallet>(wallet, headers);
		ResponseEntity<String> response = restTemplate.exchange(PAY_URI, HttpMethod.PUT, requestEntity, String.class, params);
        return response.getBody();
    }
}
