package com.xmen.api.mutant;

public class Dna
{
    public Dna(){
        
    }

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

}