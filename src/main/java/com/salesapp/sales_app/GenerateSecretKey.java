package com.salesapp.sales_app;

import java.security.SecureRandom;
import java.util.Base64;

public class GenerateSecretKey {
    public static void main(String[] args) {
        // Create a SecureRandom instance for cryptographically secure random number generation
        SecureRandom random = new SecureRandom();
        
        // Create a byte array of 32 bytes (256 bits)
        byte[] secretKey = new byte[32];  // 32 bytes = 256 bits
        
        // Fill the byte array with random bytes
        random.nextBytes(secretKey);
        
        // Encode the byte array into Base64 for easier handling as a string
        String secretKeyBase64 = Base64.getEncoder().encodeToString(secretKey);
        
        // Output the generated secret key
        System.out.println("Generated Secret Key: " + secretKeyBase64);
    }
}

