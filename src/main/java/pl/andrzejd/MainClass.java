package pl.andrzejd;

import pl.andrzejd.strategy.CipherStrategy;
import pl.andrzejd.strategy.TranspositionCipherStrategy;

public class MainClass {
    public static void main(String[] args) throws Exception {
        Key key = new Key.Builder()
                .setX(4)
                .setColumn(1)
                .setColumn(2)
                .setColumn(3)
                .setColumn(4)
                .build();
        CipherStrategy cipherStrategy = new TranspositionCipherStrategy();
        String encryptText = cipherStrategy.encrypt("", key);

        System.out.println(encryptText);

        System.out.println(cipherStrategy.decrypt(encryptText, key));
    }
}
