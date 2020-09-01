package com.xmen.api.mutant;

// public class Dna {
//     private String [] dna;

//     public String [] getDna(){
//         return dna;
//     }

//     public void setDna(String []dna){
//         this.dna = dna;
//     }
    
// }

public class Dna
{
    public Dna(String[] dna){
        setDna(dna);
    }

    private String[] dna;

    public String[] getDna()
    {
        return dna;
    }

    public void setDna(String[] dna)
    {
        this.dna = dna;
    }

    // @Override
    // public String toString()
    // {
    //     return "ClassPojo [“dna” = "+dna+"]";
    // }
}