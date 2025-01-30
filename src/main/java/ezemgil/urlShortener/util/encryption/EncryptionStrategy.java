package ezemgil.urlShortener.util.encryption;

public interface EncryptionStrategy {

    String encrypt(String text);

    String decrypt(String text);
}
