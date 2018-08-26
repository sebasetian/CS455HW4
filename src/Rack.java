// Name: Shih-Yao Lin
// USC NetID: shihyaol
// CS 455 PA4
// Spring 2018

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * A Rack of Scrabble tiles
 * stores the letters and all subset of rack
 */

public class Rack {
    private ArrayList<String> rackSubset;
    private String input;

    /**
     * The Constructor
     * create a new rack and find the subset of the rack
     *
     * @param input the letters of rack
     */
    public Rack(String input) {
        rackSubset = new ArrayList<>();

        //ignore all non-letter characters
        this.input = input.replaceAll("\\W|\\d", "");

        char[] charsSorted = this.input.toCharArray();
        Arrays.sort(charsSorted);
        this.input = String.valueOf(charsSorted);

        //find all subsets
        rackSubset = findSubsets(this.input);
    }

    public String getRack() {
        return input;
    }

    public ArrayList<String> getSubset() {

        //return a deep copy of rackSubset instead of itself
        //to make sure rackSubset can not be modified in the other classes
        return new ArrayList<>(rackSubset);
    }

    /**
     * Finds all subsets of rack by utilizing allSubsets method.
     * a string of unique letters of rack and the multiplicity of each letter from unique
     * as the parameters of allSubsets
     *
     * @param str the letters of rack
     * @return the list of all subsets of rack
     */
    private ArrayList<String> findSubsets(String str) {
        Map<Character, Integer> strMap = new HashMap<>();
        //find the unique letters and the multiplicity using a HashMap

        for (char c : str.toCharArray()) {
            if (!strMap.containsKey(c)) {
                strMap.put(c, 1);
            } else {
                strMap.put(c, strMap.get(c) + 1);
            }
        }

        String unique = "";
        int[] mult = new int[strMap.values().size()];
        int multIndex = 0;

        for (Map.Entry<Character, Integer> entry : strMap.entrySet()) {
            unique += entry.getKey();
            mult[multIndex++] = entry.getValue();
        }

        //k == 0 means from the first letter of rack
        return allSubsets(unique, mult, 0);
    }


    /**
     * Finds all subsets of the multiset starting at position k in unique and mult.
     * unique and mult describe a multiset such that mult[i] is the multiplicity of the char
     * unique.charAt(i).
     * PRE: mult.length must be at least as big as unique.length()
     * 0 <= k <= unique.length()
     *
     * @param unique a string of unique letters
     * @param mult   the multiplicity of each letter from unique.
     * @param k      the smallest index of unique and mult to consider.
     * @return all subsets of the indicated multiset
     * @author Claire Bono
     */
    private static ArrayList<String> allSubsets(String unique, int[] mult, int k) {
        ArrayList<String> allCombos = new ArrayList<>();

        if (k == unique.length()) {  // multiset is empty
            allCombos.add("");
            return allCombos;
        }

        // get all subsets of the multiset without the first unique char
        ArrayList<String> restCombos = allSubsets(unique, mult, k + 1);

        // prepend all possible numbers of the first char (i.e., the one at position k)
        // to the front of each string in restCombos.  Suppose that char is 'a'...

        String firstPart = "";          // in outer loop firstPart takes on the values: "", "a", "aa", ...
        for (int n = 0; n <= mult[k]; n++) {
            for (int i = 0; i < restCombos.size(); i++) {  // for each of the subsets
                // we found in the recursive call
                // create and add a new string with n 'a's in front of that subset
                allCombos.add(firstPart + restCombos.get(i));
            }
            firstPart += unique.charAt(k);  // append another instance of 'a' to the first part
        }

        return allCombos;
    }


}
