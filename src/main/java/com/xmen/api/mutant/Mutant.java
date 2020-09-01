package com.xmen.api.mutant;

import java.util.regex.*;

public class Mutant{

    private static final int seqLength = 4;
    private static int found = 0;
    private static char[][] matrix;
    
    public static boolean isMutant (Dna dna) {

        found = 0;
        
        if ( checkInput(dna.getDna())){
            if(searchHorizontalVertical(matrix, "HORIZONTAL") ||
            searchHorizontalVertical(matrix, "VERTICAL") ||
                    searchDiagonal(matrix, "1") || 
                    searchDiagonal(matrix, "2")
            ){
              // System.out.println("INPUT MUTANTE");
                return true;
            }else{
               // System.out.println("INPUT HUMANO");
                return false;
            }
        }else{
           // System.out.println("INPUT ERROR");
            return false;
        }

    }

    private static boolean searchHorizontalVertical(char[][] matrix, String direction) {
        String newSeq = "";
        int missingChars = seqLength;
        char currentChar = '.';
        char nextChar = '.';
        for (int i = 0; i < matrix.length; i++) {
             for (int j = 0; j + missingChars <= matrix.length; j++) {
                if (direction.equals("HORIZONTAL")) {
                    currentChar = matrix[i][j];
                    nextChar = matrix[i][j+1];
                }
                if (direction.equals("VERTICAL")) {
                    currentChar = matrix[j][i];
                    nextChar = matrix[j+1][i];
                }
                newSeq = compare(currentChar, nextChar, newSeq);
                if(found > 1) return true;
                missingChars = seqLength - newSeq.length();
            }
        }
        return false;
    }

       private static boolean searchDiagonal(char[][] matrix, String direction) {
        int missingChars = seqLength;
        String newSeq = "";

        char currentChar = '.';
        char nextChar = '.';
        // read first and last column
        int aux = 0;
        if (direction.equals("2"))
            aux = matrix.length - 1;// FROM RIGTH
        for (int i = seqLength - 1, j = aux; i < matrix.length; i++) {
            for (int a = i, b = j; a - missingChars + 1 >= 0; a--) {

               currentChar = matrix[a][b];
               nextChar = getNextChar(direction,matrix,a,b);
                
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
        int i = matrix.length - 1; // last row
        int j = 1;

        if (direction.equals("2"))
            j = matrix.length - 2;
        while ((direction.equals("1") && j + missingChars <= matrix.length)
                || (direction.equals("2") && j - missingChars >= -1)) {
            newSeq = "";

            for (int a = i, b = j; (direction.equals("1") && b + missingChars <= matrix.length)
                    || (direction.equals("2") && b - missingChars >= -1); a--) {
  
                currentChar = matrix[a][b];
                nextChar = getNextChar(direction,matrix,a,b);
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

    private static char getNextChar(String direction, char[][] matrix2, int a, int b) {
        if (direction.equals("1"))
         return matrix[a - 1][b + 1];
        if (direction.equals("2"))
        return matrix[a - 1][b - 1];
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
        //System.out.println(currentChar);
        return newSeq;
    }


    private static boolean checkInput(String[] input) {
        if (input.length < seqLength){
            return false;
        }
        char[][] auxMatrix = new char[input.length][input.length];
        Pattern validCharacterPattern = Pattern.compile("[atgcATGC]*");
        Pattern validLengthPattern = Pattern.compile(".{" + input.length + "}");
        for (int i = 0; i < input.length; i++) {
            if (!validCharacterPattern.matcher(input[i]).matches() || !validLengthPattern.matcher(input[i]).matches()) {
                return false;
            } else {
                auxMatrix[i] = input[i].toUpperCase().toCharArray();
            }
        }
        matrix = auxMatrix;
        return true;
    }

}