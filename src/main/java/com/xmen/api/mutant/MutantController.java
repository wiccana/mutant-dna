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
		
		if(Mutant.isMutant(dna)){
			return new ResponseEntity<>("OK", HttpStatus.OK);
		}else{
			return new ResponseEntity<>("FORBIDDEN", HttpStatus.FORBIDDEN);
		}
		
    }
  

}