package com.tw.joi.delivery.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(LoadTestController.class)
class LoadTestControllerTest {

  @Autowired private MockMvc mockMvc;

  @Test
  void shouldReturnOkForPing() throws Exception {
    mockMvc
        .perform(MockMvcRequestBuilders.get("/loadtest/ping"))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.status", Is.is("OK")));
  }
}
