package com.ecommerce.Ecommerce.utils;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class UtilityProvider {

	public String generateToken() {
		int leftLimit = 97; // letter 'a'
	    int rightLimit = 122; // letter 'z'
	    int targetStringLength = 24;
	    Random random = new Random();
	    StringBuilder buffer = new StringBuilder(targetStringLength);
	    for (int i = 0; i < targetStringLength; i++) {
	        int randomLimitedInt = leftLimit + (int) 
	          (random.nextFloat() * (rightLimit - leftLimit + 1));
	        buffer.append((char) randomLimitedInt);
	    }
	    String token = buffer.toString();

	    return token;
	}
}
