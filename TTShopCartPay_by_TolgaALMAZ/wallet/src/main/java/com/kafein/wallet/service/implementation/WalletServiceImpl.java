package com.kafein.wallet.service.implementation;

import com.kafein.wallet.entity.Wallet;
import com.kafein.wallet.repository.WalletRepository;
import com.kafein.wallet.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import java.util.List;

@Service
public class WalletServiceImpl implements WalletService {

    @Autowired
    WalletRepository walletRepository;
    
    private static final String PAYMENT_SUCCESSFUL = "PAYMENT_SUCCESSFUL";
    private static final String INSUFFICIENT_AMOUNT = "PAYMENT_INSUFFICIENT_AMOUNT";

    @Override
    public Wallet getWalletById(Long id) {
        if (id == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
        }
        return walletRepository.findWalletById(id);
    }

    @Override
    public List<Wallet> getWalletsByUserId(Long userId) {
        if (userId == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
        }
        return walletRepository.findAllByUserId(userId);
    }
    
    //Tolga ALMAZ: processPayment service is newly introduced. It finds wallets of given user and process payment from most suitable wallet/wallets.
    @Override
    public String processPayment(Long userId, Long amount) throws Exception {
    	List<Wallet> walletList = getWalletsByUserId(userId);
    	return payment(walletList, amount);
    }
    
    //Tolga ALMAZ: payment method is newly introduced. It try to pay from single wallet first, then try to pay partially from multiple wallets.
    private String payment(List<Wallet> walletList, Long amount) throws Exception {
    	Long walletAmount =0L;
    	String paymentResult = PAYMENT_SUCCESSFUL;

        if(!processSuitableWallet(walletList, amount, walletAmount)) {
        	if(walletAmount >= amount) {
        		processMultipleWallet(walletList, amount);
        	}else {
        		paymentResult = INSUFFICIENT_AMOUNT;
        	}
        }
        return paymentResult;
    }
    
    //Tolga ALMAZ: processSuitableWallet method is newly introduced. It finds most suitable single wallet for payment.
	private boolean processSuitableWallet(List<Wallet> walletList, Long amount, Long walletAmount) throws Exception {
		boolean suitableWalletFound = false;

		for (Wallet wallet : walletList) {
			if (wallet.getAmount().compareTo(amount) > 0 || wallet.getAmount().compareTo(amount) == 0) {
				wallet.setAmount(wallet.getAmount() - amount);
				walletRepository.save(wallet);
				suitableWalletFound = true;
				break;
			} else {
				walletAmount = +wallet.getAmount();
			}
		}
		return suitableWalletFound;
	}
    
    //Tolga ALMAZ: processMultipleWallet method is newly introduced. It provides to pay amount from wallets partially.
    private void processMultipleWallet(List<Wallet> walletList, Long amount) throws Exception{
    	Long leftAmountForPay = 0L;
    	boolean isWalletEnough = false;
    	
		for(Wallet wallet : walletList) {
			if(wallet.getAmount().compareTo(amount)<0){
				leftAmountForPay = amount - wallet.getAmount();
				wallet.setAmount(0L);
			}else {
				wallet.setAmount(wallet.getAmount() - leftAmountForPay);
				isWalletEnough = true;
			}
			walletRepository.save(wallet);
			
			if(isWalletEnough) {
				break;
			}
		}
    }
}
