package com.xmen.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import com.xmen.api.mutant.MutantController;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


@SpringBootTest
class MutantControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private MutantController mutantController;


    @BeforeEach
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(mutantController).build();
    }
    
	@Test
	public void postHumanDNA_returns403() throws Exception {

        mockMvc.perform(
            MockMvcRequestBuilders.post("/mutant")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"dna\":[\"ATAG\",\"AATA\",\"ACGC\",\"AAAC\"]}")
        )
        .andExpect(MockMvcResultMatchers.status().isForbidden());
	}


	@Test
	public void postMutantDNAv1_returns200() throws Exception {

        mockMvc.perform(
            MockMvcRequestBuilders.post("/mutant")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"dna\":[\"ATAG\",\"AAAA\",\"ACGC\",\"AAAC\"]}")
        )
        .andExpect(MockMvcResultMatchers.status().isOk());
    }
    
    @Test
	public void postMutantDNAv2_returns200() throws Exception {

        mockMvc.perform(
            MockMvcRequestBuilders.post("/mutant")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"dna\":[\"ATATGA\",\"ACCTTG\",\"GAGATG\",\"TTATGT\",\"TATATG\",\"AGTTAT\"]}")
        )
        .andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void postWrongStringLengthDNA_returns403()throws Exception {

        mockMvc.perform(
            MockMvcRequestBuilders.post("/mutant")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"dna\":[\"ATAG\",\"AATA\",\"AAAAAAA\",\"AAAC\"]}")
        )
        .andExpect(MockMvcResultMatchers.status().isForbidden());

	}
	
	@Test
	public void postWrongArrayLengthDNA_returns403()throws Exception {

        mockMvc.perform(
            MockMvcRequestBuilders.post("/mutant")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"dna\":[\"ATAG\",\"AATA\",\"ACGC\"]}")
        )
        .andExpect(MockMvcResultMatchers.status().isForbidden());

    }
    
    @Test
	public void postWrongJson_returns403()throws Exception {

        mockMvc.perform(
            MockMvcRequestBuilders.post("/mutant")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"\":\"\"}")
        )
        .andExpect(MockMvcResultMatchers.status().isForbidden());

    }

	

		

}
