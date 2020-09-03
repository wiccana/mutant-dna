package com.xmen.api.mutant;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
public class MutantController {

	@PostMapping(path = "/mutant")
	public ResponseEntity<Dna> isMutantController(@RequestBody Dna dna) {

		//Valid input
		if(dna == null || dna.getDna() == null){
			return new ResponseEntity<>(dna, HttpStatus.FORBIDDEN);
		}
		if(!Mutant.isValidDNA(dna.getDna())){
			return new ResponseEntity<>(dna, HttpStatus.FORBIDDEN);
		}

		//Check if Mutant DNA		
		if(Mutant.isMutant(dna)){
			return new ResponseEntity<>(dna, HttpStatus.OK);
		}else{
			return new ResponseEntity<>(dna, HttpStatus.FORBIDDEN);
		}
		
    }
  

}