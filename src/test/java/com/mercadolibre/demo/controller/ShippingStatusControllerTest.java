package com.mercadolibre.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.demo.dto.TokenDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.transaction.Transactional;
import java.net.URI;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@AutoConfigureTestEntityManager
@SpringBootTest
@AutoConfigureMockMvc
public class ShippingStatusControllerTest {

    private URI uri;

    @Autowired
    MockMvc mockMvc;

    private TokenDTO tokenDTO;

    @BeforeEach
    public void testandoAutenticacao() throws Exception {
        String json = "{\"user\": \"filipe\", \"senha\": \"123\"}";
        uri = new URI("/auth");

        MvcResult resultContendoToken = mockMvc
                .perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk()).andReturn();
        tokenDTO = new ObjectMapper().readValue(resultContendoToken.getResponse().getContentAsString(), TokenDTO.class);
    }

    @Test
    public void testListPaymentGetAll() throws Exception {

        uri = new URI("/api/v1/fresh-products/shipping/status/list");

        assertNotNull(uri);

        MvcResult result = mockMvc.perform(
                        MockMvcRequestBuilders.get(uri).header("Authorization", tokenDTO.getTipo() + " " + tokenDTO.getToken()))
                .andExpect(status().isOk()).andReturn();

        String jsonRetorno = result.getResponse().getContentAsString();

        assertNotNull(jsonRetorno);
    }

    @Test
    public void testSaveShippingStatusWithSuccess() throws Exception {

        uri = new URI("/api/v1/fresh-products/shipping/status/save");

        assertNotNull(uri);

        String requestJson =  "{ \"name\":\"Seu produto esta com defeito!\", \"order\":8, \"status\":\"ATIVO\"}";

        MvcResult result = mockMvc.perform(
                        MockMvcRequestBuilders.post(uri)
                                .content(requestJson)
                                .header("Content-Type", "application/json")
                                .header("Authorization", tokenDTO.getTipo() + " " + tokenDTO.getToken()))
                .andExpect(status().isCreated()).andReturn();

        String responseJson = result.getResponse().getContentAsString();

        assertNotNull(responseJson);
    }

    @Test
    public void testSaveShippingStatusNOSuccess() throws Exception {

        uri = new URI("/api/v1/fresh-products/shipping/status/save");

        assertNotNull(uri);

        String requestJson =  "{ \"name\":\"null\", \"order\":, \"status\":\"null\"}";

        MvcResult result = mockMvc.perform(
                        MockMvcRequestBuilders.post(uri)
                                .content(requestJson)
                                .header("Content-Type", "application/json")
                                .header("Authorization", tokenDTO.getTipo() + " " + tokenDTO.getToken()))
                .andExpect(status().is(400)).andReturn();

        String responseJson = result.getResponse().getContentAsString();

        assertNotNull(responseJson);
    }


    @Test
    public void testUpdatShippingStatusWithSuccess() throws Exception {

        uri = new URI("/api/v1/fresh-products/shipping/status/update/1");

        assertNotNull(uri);

        String requestJson = "{ \"name\":\"Seu produto esta com defeito!\", \"order\":8, \"status\":\"ATIVO\"}";
        MvcResult result = mockMvc.perform(
                        MockMvcRequestBuilders.put(uri)
                                .content(requestJson)
                                .header("Content-Type", "application/json")
                                .header("Authorization", tokenDTO.getTipo() + " " + tokenDTO.getToken()))
                .andExpect(status().isCreated()).andReturn();

        String responseJson = result.getResponse().getContentAsString();

        assertNotNull(responseJson);

    }

    @Test
    public void testDeleteShippingStatusWithSuccess() throws Exception {

        uri = new URI("/api/v1/fresh-products/shipping/status/delete/1");

        assertNotNull(uri);

        MvcResult result = mockMvc.perform(
                            MockMvcRequestBuilders.delete(uri)
                                .header("Authorization", tokenDTO.getTipo() + " " + tokenDTO.getToken()))
                .andExpect(status().isOk()).andReturn();

        String responseJson = result.getResponse().getContentAsString();

        assertNotNull(responseJson);
    }

    @Test
    public void testDeleteShippingStatusNOWithSuccess() throws Exception {

        uri = new URI("/api/v1/fresh-products/shipping/status/delete/10000000000000000");

        assertNotNull(uri);

        MvcResult result = mockMvc.perform(
                        MockMvcRequestBuilders.delete(uri)
                                .header("Authorization", tokenDTO.getTipo() + " " + tokenDTO.getToken()))
                .andExpect(status().isNotFound()).andReturn();

        String responseJson = result.getResponse().getContentAsString();

        assertNotNull(responseJson);
    }

}