package com.xmen.api.mutant;

import java.util.regex.*;

public class Mutant{

    //CONFIGURATION
    private static final int seqLength = 4;
    private static String regex = "[ATGC]*";
    
    enum Direction {
        HORIZONTAL,
        VERTICAL,
        LEFT_RIGHT,
        RIGHT_LEFT
    }


    //Checks if valid input
    public static boolean isValidDNA(String[] input) {
        if (input.length < seqLength){
            return false;
        }
        Pattern validCharacterPattern = Pattern.compile(regex);
        Pattern validLengthPattern = Pattern.compile(".{" + input.length + "}");
        for (int i = 0; i < input.length; i++) {
            if (!validCharacterPattern.matcher(input[i]).matches() || !validLengthPattern.matcher(input[i]).matches())
                return false;

        }
        return true;
    }

    //Checks for Mutant DNA
    public static boolean isMutant(Dna dna) {

        int found = 0;

        found = searchHorizontalVertical(dna.getDna(), Direction.HORIZONTAL, found);
        if (found > 1) return true;

        found = searchHorizontalVertical(dna.getDna(), Direction.VERTICAL, found);
        if (found > 1) return true;

        found =  searchDiagonal(dna.getDna(), Direction.LEFT_RIGHT, found);
        if (found > 1) return true;

        found = searchDiagonal(dna.getDna(), Direction.RIGHT_LEFT, found);
        if (found > 1) return true;

        return false;

    }


    /*
        Private Methods
    */
    private static int searchHorizontalVertical(String[] dna, Direction direction, int found) {
        String newSeq = "";
        int missingChars = seqLength;
        char currentChar = '.';
        char nextChar = '.';
        for (int i = 0; i < dna.length; i++) {
             for (int j = 0; j + missingChars <= dna.length; j++) {
                if (direction == Direction.HORIZONTAL) {
                    currentChar = dna[i].charAt(j);
                    nextChar = dna[i].charAt(j+1);
                }
                if (direction == Direction.VERTICAL) {
                    currentChar = dna[j].charAt(i);
                    nextChar = dna[j+1].charAt(i);
                }
                newSeq = compareChars(currentChar, nextChar, newSeq);
                if (newSeq.length() >= seqLength - 1) { // equal characters sequence found
                    found++;
                    newSeq = "";
                }
                if(found > 1) return found;
                missingChars = seqLength - newSeq.length();
            }
        }
        return found;
    }

    private static int searchDiagonal(String[] dna, Direction direction, int found) {
        int missingChars = seqLength;
        String newSeq = "";
        char currentChar = '.';
        char nextChar = '.';

        // reads first and last column
        int aux = 0;
        if (direction == Direction.RIGHT_LEFT)
            aux = dna.length - 1;// from right
        for (int i = seqLength - 1, j = aux; i < dna.length; i++) {
            for (int a = i, b = j; a - missingChars + 1 >= 0; a--) {

               currentChar = dna[a].charAt(b);
               nextChar = getNextChar(direction,dna,a,b);
                
                newSeq = compareChars(currentChar, nextChar, newSeq);
                if (newSeq.length() >= seqLength - 1) { // equal characters sequence found
                    found++;
                    newSeq = "";
                }
                if(found > 1) return found;
                missingChars = seqLength - newSeq.length();
                // increase coord
                if (direction == Direction.LEFT_RIGHT)
                    b++;
                if (direction == Direction.RIGHT_LEFT)
                    b--;
            }

        }
        // RESET VARIABLES
        int i = dna.length - 1; // last row
        int j = 1;

        // reads from last row
        if (direction == Direction.RIGHT_LEFT)
            j = dna.length - 2;
        while ((direction == Direction.LEFT_RIGHT && j + missingChars <= dna.length)
                || (direction == Direction.RIGHT_LEFT && j - missingChars >= -1)) {
            newSeq = "";

            for (int a = i, b = j; (direction == Direction.LEFT_RIGHT && b + missingChars <= dna.length)
                    || (direction == Direction.RIGHT_LEFT && b - missingChars >= -1); a--) {
  
                currentChar = dna[a].charAt(b);
                nextChar = getNextChar(direction,dna,a,b);
                newSeq = compareChars(currentChar, nextChar, newSeq);
                if (newSeq.length() >= seqLength - 1) { // equal characters sequence found
                    found++;
                    newSeq = "";
                }
                if(found > 1) return found;

                missingChars = seqLength - newSeq.length();
                if (direction == Direction.LEFT_RIGHT)
                    b++;
                if (direction == Direction.RIGHT_LEFT)
                    b--;
            }
            if (direction == Direction.LEFT_RIGHT)
                j++;
            if (direction == Direction.RIGHT_LEFT)
                j--;
        }
        return found;
    }

    /*
        Auxiliary Methods
    */
    private static char getNextChar(Direction direction, String[] dna, int a, int b) {
        if (direction == Direction.LEFT_RIGHT)
         return dna[a - 1].charAt(b + 1);
        // else -> Direction.RIGHT_LEFT
        return dna[a - 1].charAt(b - 1);
    }

    private static String compareChars(char currentChar, char nextChar, String newSeq) {
        if (currentChar == nextChar) {
            if (newSeq.length() < 1 || currentChar == newSeq.charAt(0)) {
                newSeq = newSeq + currentChar;
            } else {
                newSeq = "";
            }
        } else {
            newSeq = "";
        }
       
        return newSeq;
    }

   
}