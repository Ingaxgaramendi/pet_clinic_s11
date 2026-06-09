package com.tecsup.petclinic.webs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tecsup.petclinic.entities.Pet;
import com.tecsup.petclinic.entities.Visit;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class VisitControllerTest {

    private static final ObjectMapper om =
            new ObjectMapper().findAndRegisterModules();

    @Autowired
    private MockMvc mockMvc;

    private Visit buildVisit(Integer petId, String description) {
        Visit visit = new Visit();
        visit.setVisitDate(LocalDate.now());
        visit.setDescription(description);

        Pet pet = new Pet();
        pet.setId(petId); // IMPORTANTE: evita NULL PET_ID
        visit.setPet(pet);

        return visit;
    }

    @Test
    public void testFindAllVisits() throws Exception {
        mockMvc.perform(get("/visits"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testCreateVisit() throws Exception {

        Visit visit = buildVisit(1, "Check general health");

        mockMvc.perform(post("/visits")
                        .content(om.writeValueAsString(visit))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.description", is("Check general health")));
    }

    @Test
    public void testFindVisitById() throws Exception {

        Visit visit = buildVisit(1, "Vaccination");

        String response = mockMvc.perform(post("/visits")
                        .content(om.writeValueAsString(visit))
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString();

        Integer id = om.readTree(response).get("id").asInt();

        mockMvc.perform(get("/visits/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description", is("Vaccination")));
    }

    @Test
    public void testDeleteVisit() throws Exception {

        Visit visit = buildVisit(1, "Deworming");

        String response = mockMvc.perform(post("/visits")
                        .content(om.writeValueAsString(visit))
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString();

        Integer id = om.readTree(response).get("id").asInt();

        mockMvc.perform(delete("/visits/" + id))
                .andExpect(status().isOk());
    }
}