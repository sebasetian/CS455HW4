// Name: Shih-Yao Lin
// USC NetID: shihyaol
// CS 455 PA4
// Spring 2018

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * This contains the main method.
 * This class will have a main that's responsible for processing the command-line argument,
 * and handling any error processing.
 * This class will call ScoreCalculator class to find all words and their score from racks,
 * and it only depends on the ScoreCalculator.
 */
public class WordFinder {

    public static void main(String[] args) {
        String file;
        if (args.length == 0) {
            file = "sowpods.txt";
        } else {
            file = args[0];
        }
        try {
            Scanner input = new Scanner(System.in);
            ScoreCalculator calculator = new ScoreCalculator(file);
            System.out.println("Type . to quit.");
            System.out.print("Rack? ");
            while (input.hasNext()) {
                String rackStr = input.next();
                if(!rackStr.equals(".")) {
                    findCombine(rackStr, calculator);
                    System.out.print("Rack? ");
                } else {
                    break;
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println(file + " doesn't exist");
        }
    }
    /**
     * Finds a list of legal words from dictionary that can be formed from the letter of rack
     *
     * @param inputStr the letters of rack
     * @param calculator the ScoreCalculator object that is responsible for
     *                  calculating the "value" of the legal words from rack
     */
    private static void findCombine(String inputStr, ScoreCalculator calculator){
        //set a new rack
        calculator.setRack(inputStr);
        //count the number of words
        int wordsCount = calculator.calculateRack();
        System.out.println("We can make " + wordsCount
                + " words from \"" + calculator.getRackStr() + "\"");
        if (wordsCount != 0) {
            System.out.println("All of the words with their scores (sorted by score):");
            for (Map.Entry<Integer, ArrayList<String>> analist : calculator.getAnagramSet()) {
                //reverse the value of key so that we can get a list sorted in descending order
                int score = -analist.getKey();
                TreeSet<String> anas = new TreeSet<>(analist.getValue());
                for (String ana : anas) {
                    System.out.println(score + ": " + ana);
                }
            }
        }
    }
}
