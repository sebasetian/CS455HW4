// Name: Shih-Yao Lin
// USC NetID: shihyaol
// CS 455 PA4
// Spring 2018
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Using the information of ScoreTable Class to calculate the subset of rack,
 * This class will call AnagramDictionary class and Rack class to find all legal words
 * from the letter of racks, and ScoreTable class to calculate the value of these words
 * It depends on AnagramDictionary,Rack and ScoreTable class.
 */
public class ScoreCalculator {
    private Rack rack;
    private AnagramDictionary dic;
    private ScoreTable st;
    private HashMap<Integer,ArrayList<String>> legalWordsScoreList;

    /**
     * The Constructor
     * Initializes AnagramDictionary and ScoreTable object.
     * @param File the file path of dictionary. Used for initializing the AnagramDictionary object.
     * @throws FileNotFoundException if the dictionary doesn't exist, it will throw FileNotFoundException
     */
    public ScoreCalculator(String File) throws FileNotFoundException {

        dic = new AnagramDictionary(File);
        st = new ScoreTable();
    }

    public Set<Map.Entry<Integer,ArrayList<String>>> getAnagramSet(){

        //return a deep copy of legalWordsScoreList instead of itself
        //to make sure rackSubset can not be modified in the other classes
        TreeMap<Integer,ArrayList<String>> forReturn = new TreeMap<>();
        for(Map.Entry<Integer,ArrayList<String>> ent: legalWordsScoreList.entrySet()){
            forReturn.put(ent.getKey(),new ArrayList<>(ent.getValue()));
        }
        return forReturn.entrySet();
    }

    /**
     * Sets a new rack from the input and initialize the corresponding legal words and score list of the rack
     * @param inputStr the letters of rack, passed from WordFinder class
     */
    public void setRack(String inputStr) {

        this.rack = new Rack(inputStr);
        legalWordsScoreList = new HashMap<>();
    }

    public String getRackStr(){
        return rack.getRack();
    }

    /**
     * Calculate the score of all legal words from the rack
     * Uses a TreeMap to store the words and corresponding values.
     * @return the number of total legal words
     */
    public int calculateRack(){
        int totalSize = 0;
        // get all the subset string of rack
        ArrayList<String> rackSubset = rack.getSubset();

        for(String subsetStr : rackSubset) {
            // find the legal words of subset strings
            ArrayList<String> legalWords = dic.getAnagramsOf(subsetStr);
            if(legalWords.size() == 0){
                continue;
            }

            // To obtain a list sorted by score in descending order,
            // make the number of the score negative and use them as keys in legalWordsScoreList Map
            // and reverse the sign of keys before printing the list out
            int score = -st.calculateScore(legalWords.get(0));
            if(legalWordsScoreList.containsKey(score)){
                legalWordsScoreList.get(score).addAll(legalWords);
            }else {
                legalWordsScoreList.put(score, legalWords);
            }
            totalSize += legalWords.size();
        }
        return totalSize;
    }

}
