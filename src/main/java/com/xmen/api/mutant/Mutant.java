package com.xmen.api.mutant;

import java.util.regex.*;

public class Mutant{

    private static final int seqLength = 4;
    private static int found = 0;

    public static boolean isValidDNA(String[] input) {
        if (input.length < seqLength){
            return false;
        }
        Pattern validCharacterPattern = Pattern.compile("[ATGC]*");
        Pattern validLengthPattern = Pattern.compile(".{" + input.length + "}");
        for (int i = 0; i < input.length; i++) {
            if (!validCharacterPattern.matcher(input[i]).matches() || !validLengthPattern.matcher(input[i]).matches())
                return false;

        }
        return true;
    }

    public static boolean isMutant(Dna dna) {

        found = 0;
        
        if(searchHorizontalVertical(dna.getDna(), "HORIZONTAL") ||
            searchHorizontalVertical(dna.getDna(), "VERTICAL") ||
                    searchDiagonal(dna.getDna(), "1") || 
                    searchDiagonal(dna.getDna(), "2")
            ){
                return true;
            }else{
                return false;
            }

    }


    private static boolean searchHorizontalVertical(String[] dna, String direction) {
        String newSeq = "";
        int missingChars = seqLength;
        char currentChar = '.';
        char nextChar = '.';
        for (int i = 0; i < dna.length; i++) {
             for (int j = 0; j + missingChars <= dna.length; j++) {
                if (direction.equals("HORIZONTAL")) {
                    currentChar = dna[i].charAt(j);
                    nextChar = dna[i].charAt(j+1);
                }
                if (direction.equals("VERTICAL")) {
                    currentChar = dna[j].charAt(i);
                    nextChar = dna[j+1].charAt(i);
                }
                newSeq = compare(currentChar, nextChar, newSeq);
                if(found > 1) return true;
                missingChars = seqLength - newSeq.length();
            }
        }
        return false;
    }

       private static boolean searchDiagonal(String[] dna, String direction) {
        int missingChars = seqLength;
        String newSeq = "";

        char currentChar = '.';
        char nextChar = '.';
        // read first and last column
        int aux = 0;
        if (direction.equals("2"))
            aux = dna.length - 1;// FROM RIGTH
        for (int i = seqLength - 1, j = aux; i < dna.length; i++) {
            for (int a = i, b = j; a - missingChars + 1 >= 0; a--) {

               currentChar = dna[a].charAt(b);
               nextChar = getNextChar(direction,dna,a,b);
                
                newSeq = compare(currentChar, nextChar, newSeq);
                if(found > 1) return true;
                missingChars = seqLength - newSeq.length();
                // increase coord
                if (direction.equals("1"))
                    b++;
                if (direction.equals("2"))
                    b--;
            }

        }
        // RESET VARIABLES
        int i = dna.length - 1; // last row
        int j = 1;

        if (direction.equals("2"))
            j = dna.length - 2;
        while ((direction.equals("1") && j + missingChars <= dna.length)
                || (direction.equals("2") && j - missingChars >= -1)) {
            newSeq = "";

            for (int a = i, b = j; (direction.equals("1") && b + missingChars <= dna.length)
                    || (direction.equals("2") && b - missingChars >= -1); a--) {
  
                currentChar = dna[a].charAt(b);
                nextChar = getNextChar(direction,dna,a,b);
                newSeq = compare(currentChar, nextChar, newSeq);
                if(found > 1) return true;

                missingChars = seqLength - newSeq.length();
                if (direction.equals("1"))
                    b++;
                if (direction.equals("2"))
                    b--;
            }
            if (direction.equals("1"))
                j++;
            if (direction.equals("2"))
                j--;
        }
        return false;
    }

    private static char getNextChar(String direction, String[] dna, int a, int b) {
        if (direction.equals("1"))
         return dna[a - 1].charAt(b + 1);
        if (direction.equals("2"))
        return dna[a - 1].charAt(b - 1);

        return '.';
    }

    private static String compare(char currentChar, char nextChar, String newSeq) {
        if (currentChar == nextChar) {
            if (newSeq.length() < 1 || currentChar == newSeq.charAt(0)) {
                newSeq = newSeq + currentChar;
            } else {
                newSeq = "";
            }
        } else {
            newSeq = "";
        }
        if (newSeq.length() >= seqLength - 1) { // enough combinations found
            found++;
            newSeq = "";
        }
        return newSeq;
    }

   
}