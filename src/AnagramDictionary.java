// Name: Shih-Yao Lin
// USC NetID: shihyaol
// CS 455 PA4
// Spring 2018

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


/**
 * A dictionary of all anagram sets.
 * Note: the processing is case-sensitive; so if the dictionary has all lower
 * case words, you will likely want any string you test to have all lower case
 * letters too, and likewise if the dictionary words are all upper case.
 */

public class AnagramDictionary {

    private volatile HashMap<String, ArrayList<String>> anaDictionary;
    /**
     * Creates an anagram dictionary from the list of words given in the file
     * indicated by fileName.
     * PRE: The strings in the file are unique.
     *
     * @param fileName the name of the file to read from
     * @throws FileNotFoundException if the file is not found
     */
    public AnagramDictionary(String fileName) throws FileNotFoundException {
        File Dic = new File(fileName);
        anaDictionary = new HashMap<>();
        try(Scanner wordsInDic = new Scanner(Dic)) {
            readData(wordsInDic);
        }
    }

    /**
     * Gets all anagrams of the given string. This method is case-sensitive.
     * E.g. "CARE" and "race" would not be recognized as anagrams.
     *
     * @param s string to process
     * @return a list of the anagrams of s
     */
    public ArrayList<String> getAnagramsOf(String s) {
        //sort the string
        char[] sChar = s.toCharArray();
        Arrays.sort(sChar);
        String sKey = String.valueOf(sChar);
        //find the anagrams from anaDictionary map by using the sorted string as key
        // to seek the value in Map
        if (anaDictionary.get(sKey) != null) {
            return new ArrayList<>(anaDictionary.get(sKey));
        }

        return new ArrayList<>();
    }

    /**
     * reads all the words in dictionary to anaDictionary map
     * transforms the words into sorted string as canonical form and uses them as key,
     * the corresponding value is a list of all the words that have the same canonical form
     * client can easliy figure out the anagram by comparing the canonical version of them for equality
     * and retrieve the list in O(1+)
     *
     * @param wordsInDic all the words in dictionary
     */
    private void readData(Scanner wordsInDic) {
        while (wordsInDic.hasNext()) {
            //sort the string
            String nextWord = wordsInDic.next();
            char[] charsSorted = nextWord.toCharArray();
            Arrays.sort(charsSorted);
            String nextWordSorted = String.valueOf(charsSorted);

            //uses TreeSet for making a list of words in alphabetical order.
            if (!anaDictionary.containsKey(nextWordSorted)) {
                anaDictionary.put(nextWordSorted, new ArrayList<String>());
            }
                anaDictionary.get(nextWordSorted).add(nextWord);
        }
    }


}
