package com.xmen.api;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


import com.xmen.api.mutant.Dna;
import com.xmen.api.mutant.Mutant;

@SpringBootTest
class ApiApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void postHumanDNA_403IsReceived()throws Exception {

		//Given
		String[] dna = {"ATAG","AATA","ACGC","AaAC"};

		Dna mutant = new Dna(dna);

		assertFalse( Mutant.isMutant(mutant) );

	}

	@Test
	public void postMutantDNA_200IsReceived()throws Exception {

		//Given
		String[] dna = {"ATAG","AAAA","ACGC","AAAC"};

		Dna mutant = new Dna(dna);

		assertTrue( Mutant.isMutant(mutant) );

	}

	@Test
	public void postWrongStringLengthDNA_403IsReceived()throws Exception {

		//Given
		String[] dna = {"ATAGRT","AAAA","ACGC","AaAC"};

		Dna mutant = new Dna(dna);

		assertFalse( Mutant.isValidDNA(mutant.getDna()) );

	}
	
	@Test
	public void postWrongArrayLengthDNA_403IsReceived()throws Exception {

		//Given
		String[] dna = {"ATAGRT","AAAA","ACGC"};

		Dna mutant = new Dna(dna);

		assertFalse( Mutant.isValidDNA(mutant.getDna()) );

	}
	
		

}
