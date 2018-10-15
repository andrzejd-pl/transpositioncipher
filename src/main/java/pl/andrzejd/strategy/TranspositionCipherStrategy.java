package pl.andrzejd.strategy;

import pl.andrzejd.Key;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TranspositionCipherStrategy implements CipherStrategy {
    @Override
    public String encrypt(String text, Key key) {
        ArrayList<ArrayList<Character>> matrixTransposition = new ArrayList<>();
        matrixTransposition.add(new ArrayList<>());
        int index = 0;
        StringBuilder encode = new StringBuilder();

        for (char character : text.toCharArray()) {
            if (matrixTransposition.get(index).size() == key.getX()) {
                matrixTransposition.add(new ArrayList<>());
                index++;
            }

            matrixTransposition.get(index).add(character);
        }

        for (Integer column : key.getColumns()) {
            for (ArrayList row : matrixTransposition) {
                if (row.size() < column) {
                    continue;
                }

                encode.append(row.get(column - 1));
            }
        }

        return encode.toString();
    }

    @Override
    public String decrypt(String text, Key key) {
        int fullRows = text.length() / key.getX();
        int notFullRows = text.length() % key.getX();
        ArrayList<ArrayList<Character>> matrixTransposition = (ArrayList<ArrayList<Character>>) Stream
                .generate(ArrayList<Character>::new)
                .limit(fullRows + (notFullRows > 0 ? 1 : 0))
                .collect(Collectors.toList());
        StringBuilder decryptedString = new StringBuilder();
        CharacterIterator textIterator = new StringCharacterIterator(text);

        for (Integer column : key.getColumns()) {
            if (textIterator.current() == CharacterIterator.DONE) {
                break;
            }

            for (int i = 0; i < fullRows + ((column <= notFullRows) ? 1 : 0); i++) {
                matrixTransposition.get(i).add(column - 1, textIterator.current());
                textIterator.next();
            }
        }

        matrixTransposition.forEach(row -> row.forEach(decryptedString::append));

        return decryptedString.toString();
    }
}
