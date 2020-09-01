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
		String[] dna = {"ATAG","AAaA","ACGC","AaAC"};

		Dna mutant = new Dna(dna);

		assertTrue( Mutant.isMutant(mutant) );

	}
		

}
