package pl.andrzejd.strategy;

import pl.andrzejd.Key;

public interface CipherStrategy {
    String encrypt(String text, Key key);
    String decrypt(String text, Key key);
}
