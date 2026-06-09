package com.tecsup.petclinic.webs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tecsup.petclinic.entities.Specialty;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class SpecialtyControllerTest {

    private static final ObjectMapper om =
            new ObjectMapper().findAndRegisterModules();

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testFindAllSpecialties() throws Exception {
        mockMvc.perform(get("/specialties"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testCreateSpecialty() throws Exception {

        Specialty specialty = new Specialty();
        specialty.setName("Surgery");

        mockMvc.perform(post("/specialties")
                        .content(om.writeValueAsString(specialty))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is("Surgery")));
    }

    @Test
    public void testFindSpecialtyById() throws Exception {

        Specialty specialty = new Specialty();
        specialty.setName("Radiology");

        String response = mockMvc.perform(post("/specialties")
                        .content(om.writeValueAsString(specialty))
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString();

        Integer id = om.readTree(response).get("id").asInt();

        mockMvc.perform(get("/specialties/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Radiology")));
    }

    @Test
    public void testDeleteSpecialty() throws Exception {

        Specialty specialty = new Specialty();
        specialty.setName("Dentistry");

        String response = mockMvc.perform(post("/specialties")
                        .content(om.writeValueAsString(specialty))
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString();

        Integer id = om.readTree(response).get("id").asInt();

        mockMvc.perform(delete("/specialties/" + id))
                .andExpect(status().isOk());
    }
}