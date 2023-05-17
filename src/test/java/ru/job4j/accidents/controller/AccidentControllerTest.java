package ru.job4j.accidents.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import ru.job4j.Main;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.service.AccidentService;

import static org.mockito.Mockito.verify;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
class AccidentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccidentService accidentService;

    @Test
    @WithMockUser
    public void shouldReturnAccidentsMessage() throws Exception {
        this.mockMvc.perform(get("/accidents"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("accident/accidents"));
    }

    @Test
    @WithMockUser
    public void shouldReturnAddFormMessage() throws Exception {
        this.mockMvc.perform(get("/accidents/formAdd"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("accident/create"));
    }

    @Test
    @WithMockUser
    public void whenCreateAccident() throws Exception {
        LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("name", "Нарушение пдд.");
        requestParams.add("text", "Сбили человека");
        requestParams.add("address", "Улица");
        requestParams.add("type.id", "1");
        this.mockMvc.perform(post("/accidents/create")
                        .params(requestParams))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        ArgumentCaptor<Accident> argument = ArgumentCaptor.forClass(Accident.class);
        verify(accidentService).add(argument.capture(), any());
        assertThat("Нарушение пдд.".equals(argument.getValue().getName())).isTrue();

    }

    @Test
    @WithMockUser
    public void whenAccidentNotFoundById() throws Exception {
        this.mockMvc.perform(get("/accidents/formEdit")
                        .param("id", "0"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("accident/accidents"));
    }

}