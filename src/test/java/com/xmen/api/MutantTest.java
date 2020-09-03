package com.xmen.api;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


import com.xmen.api.mutant.Dna;
import com.xmen.api.mutant.Mutant;

@SpringBootTest
class MutantTest {

	@Test
	void contextLoads() {
	}

	@Test
	public void humanDNA_returnsFalse()throws Exception {

		String[] dna = {"ATAG","AATA","ACGC","AAAC"};
		Dna mutant = new Dna(dna);
		assertFalse( Mutant.isMutant(mutant) );

	}

	@Test
	public void mutantDNA_returnsTrue()throws Exception {

		String[] dna = {"ATAG","AAAA","ACGC","AAAC"};
		Dna mutant = new Dna(dna);
		assertTrue( Mutant.isMutant(mutant) );

	}

	@Test
	public void wrongStringLengthDNA_returnsFalse()throws Exception {

		String[] dna = {"ATAGRT","AAAA","ACGC","AaAC"};
		Dna mutant = new Dna(dna);
		assertFalse( Mutant.isValidDNA(mutant.getDna()) );

	}
	
	@Test
	public void wrongArrayLengthDNA_returnsFalse()throws Exception {

		String[] dna = {"ATAGRT","AAAA","ACGC"};
		Dna mutant = new Dna(dna);
		assertFalse( Mutant.isValidDNA(mutant.getDna()) );

	}

			

}
