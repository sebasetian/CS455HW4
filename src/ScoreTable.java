// Name: Shih-Yao Lin
// USC NetID: shihyaol
// CS 455 PA4
// Spring 2018

/**
 * Store the score information about Scrabble scores for scrabble letters and words.
 * (1 point)-A, E, I, O, U, L, N, S, T, R
 * (2 points)-D, G
 * (3 points)-B, C, M, P
 * (4 points)-F, H, V, W, Y
 * (5 points)-K
 * (8 points)- J, X
 * (10 points)-Q, Z
 */
public class ScoreTable {
    private int[] table;

    /**
     * store the score data(hard-coded) with its index:
     * table[0] store the score for a(A),
     * table[1] store the score for b(B) and so forth
     */
    public ScoreTable() {
        table = new int[26];

        table[charNum('a')] = table[charNum('e')] = table[charNum('i')]
                = table[charNum('o')] = table[charNum('u')]
                = table[charNum('l')] = table[charNum('n')] 
                = table[charNum('s')] = table[charNum('t')] = table[charNum('r')] = 1;
        
        table[charNum('d')] = table[charNum('g')] = 2;
        
        table[charNum('b')] = table[charNum('c')] = 
                table[charNum('m')] = table[charNum('p')] = 3;
        
        table[charNum('f')] = table[charNum('h')] = 
                table[charNum('v')] = table[charNum('w')] = 
                        table[charNum('y')] = 4;
        
        table[charNum('k')] = 5;
        
        table[charNum('j')] = table[charNum('x')] = 8;
        
        table[charNum('q')] = table[charNum('z')] = 10;
    }

    /**
     * for clarity purposes, convert a char to a corresponding integer( 'a' == 0, 'b' == 1....)
     * @param c the char needed to be converted
     * @return the corresponding integer
     */
    private int charNum(char c){
        return c - 'a';
    }

    /**
     * calculate the corresponding Scrabble score of legal words from racks
     * @param words the string that we need to calculate the corresponding score
     * @return the corresponding score
     */
    public int calculateScore(String words){
        //if the string is not legal(ex: empty or containing non-letter char) return 0
        if(words.length() == 0 || words.matches(".*\\W.*")){
            return 0;
        }

        int Ans = 0;
        // calculate the score with table array, treat all chars as an int and subtracting 'a' from them
        // (or 'A' if the char is uppercase)
        for(char c: words.toCharArray()){

            int index = (c - 'a');
            if(index < 0){
                index = (c - 'A');
            }
            Ans += table[index];
        }
        return Ans;
    }
}
