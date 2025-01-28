package ezemgil.urlShortener.util;

import ezemgil.urlShortener.exception.KeyGenerationException;
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
            byte[] hash = digest.digest(inputBytes);
            return encodeToBase62(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new KeyGenerationException();
        }
    }

    private static String encodeToBase62(byte[] input) {
        final String charset = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder(Sha256KeyGenerator.HASH_LENGTH);
        for (int i = 0; i < Sha256KeyGenerator.HASH_LENGTH; i++) {
            int index = (input[i] & 0xFF) % charset.length();
            sb.append(charset.charAt(index));
        }
        return sb.toString();
    }
}
