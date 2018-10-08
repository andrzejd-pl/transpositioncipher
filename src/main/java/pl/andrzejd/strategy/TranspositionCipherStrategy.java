package pl.andrzejd.strategy;

import pl.andrzejd.Key;

import java.util.ArrayList;

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
}
