package com.cgi.ectp.readbook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;
import static java.lang.Character.toLowerCase;


public class Read {

/*
    private static final List<Character> validChar = List.of(
            'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
            'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
            'À', 'È', 'Ù', 'à', 'è', 'ù', 'Á', 'É', 'Í', 'Ó', 'Ú', 'á', 'é', 'í', 'ó', 'ú', 'Â', 'Ê', 'Î', 'Ô', 'Û', 'â', 'ê', 'î', 'ô', 'û',
            'Ä', 'Ë', 'Ï', 'Ö', 'Ü', 'Ÿ', 'ä', 'ë', 'ï', 'ö', 'ü', 'ÿ', 'Æ', 'Œ', 'Ç', 'Ñ', 'æ', 'œ', 'ç', 'ñ', 'ẞ', 'ß', '-', '`', '\'','ý'
    );
*/
    private static final List<Integer> VALID_CHARS_INT = List.of(
            65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100,
            101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 228, 246,
            252, 223, 196, 214, 220, 7838, 249, 251, 252, 255, 224, 226, 230, 231, 233, 232, 234, 235, 239, 238, 244, 339, 217, 219,
            220, 376, 192, 194, 198, 199, 201, 200, 202, 203, 207, 206, 212, 338, 225, 233, 237, 241, 243, 250, 252, 193, 201, 205,
            209, 211, 218, 220, 39, 96, 8217, 700
    );
    /*
    public static Map<String, Integer> sortByValue(Map<String, Integer> pMap) {
        List<Map.Entry<String, Integer>> sortedList = new LinkedList<Map.Entry<String, Integer>>(pMap.entrySet());
        Collections.sort(
                sortedList,
                (i1, i2) -> (i1.getValue() == i2.getValue()) ? i2.getKey().compareTo(i1.getKey()) : i2.getValue().compareTo(i1.getValue()));
        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> aa : sortedList) {
            sortedMap.put(aa.getKey(), aa.getValue());
        }
        return sortedMap;
    }
*/
    public static void main(String[] args) {

        final Map<String, Integer> wordList = new HashMap<>(); //to store words
        int num;

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8))) {
            StringBuffer aWord = new StringBuffer(); //the final word to be added to map
            while(((num = br.read())!= -1) || (aWord.length() > 0)) { //read returns an integer, and while you're not at the end of the file
                if (VALID_CHARS_INT.contains(num)) {
                    aWord.append(toLowerCase((char) num));
                } else {
                    if (aWord.length() > 0) { //and if it's a word
                            wordList.put(aWord.toString(), wordList.getOrDefault(aWord.toString(), 0) + 1);
                            aWord.setLength(0);//clear buffer
                        }
                    }
                }
            wordList.entrySet().stream()
                    .sorted((e1, e2) -> (e2.getValue() == e1.getValue()) ? e1.getKey().compareTo(e2.getKey()) : e2.getValue().compareTo(e1.getValue()))
                    .map(e -> (e.getKey() + ": " + e.getValue()))
                    .forEach(s -> System.out.println(s));
            // this :101
            // the  :101
        } catch (IOException ioException) {
            System.out.println("Exception");
        }
    }
}
