package ezemgil.url_shortener.util;

import ezemgil.url_shortener.exception.KeyGenerationException;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

@Component
public class Sha256KeyGenerator implements KeyGeneratorStrategy {
    private static final int HASH_LENGTH = 8;

    @Override
    public String generateKey() {
        try {
            String input = String.valueOf(LocalDateTime.now());
            byte[] inputBytes = input.getBytes(StandardCharsets.UTF_8);

            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(inputBytes);

            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.substring(0, HASH_LENGTH);
        } catch (NoSuchAlgorithmException e) {
            throw new KeyGenerationException();
        }
    }
}
