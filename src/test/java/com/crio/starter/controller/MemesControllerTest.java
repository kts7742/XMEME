package com.crio.starter.controller;

import ch.qos.logback.core.net.ObjectWriter;
import ch.qos.logback.core.status.Status;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import com.crio.starter.App;
import com.crio.starter.RepositoryService.MemeRepositoryService;
import com.crio.starter.dto.Memes;
import com.crio.starter.exchange.ResponseDto;
import com.crio.starter.service.MemesService;
import com.crio.starter.service.MemesServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.util.UriComponentsBuilder;
import org.mockito.quality.Strictness;


@AutoConfigureMockMvc
@SpringBootTest
@MockitoSettings(strictness = Strictness.STRICT_STUBS)
class MemesControllerTest {

  ObjectMapper objectMapper = new ObjectMapper();
  // ObjectWriter objectWriter = (ObjectWriter) objectMapper.writer();

  // private MockMvc mockMvc;

  private static final String Meme_API_URI = "/memes/{id}";

  @Autowired
  private MockMvc mvc;

  @MockBean
  private MemesService memesService;

  @InjectMocks
  private MemesController memesController;

  @MockBean
  private MemeRepositoryService memeRepositoryServiceMock;


  @Test
  void invalidNegativeId() throws Exception {
    //given
    // Mockito.doReturn(new ResponseDto("Hello Java"))
    //     .when(greetingsService).getMessage("001");

    // when
    URI uri = UriComponentsBuilder
        .fromPath(Meme_API_URI)
        .queryParam("id", "-1")
        .build().toUri();

      // assertEquals(Meme_API_URI + "?id=-1", uri.toString());

    MockHttpServletResponse response = mvc.perform(
        get(uri.toString()).accept(APPLICATION_JSON_VALUE)
    ).andReturn().getResponse();

    assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());

    //then
    // String responseStr = response.getContentAsString();
    // ObjectMapper mapper = new ObjectMapper();
    // ResponseDto responseDto = mapper.readValue(responseStr, ResponseDto.class);
    // ResponseDto ref = new ResponseDto("Hello Java");

    // assertEquals(responseDto, ref);
    // Mockito.verify(greetingsService, Mockito.times(1)).getMessage("001");
  }

  @Test
  void validPost() {
    
    Memes memes = getMemes(1, "Dhoni", "Legend", "http");
    Mockito.when(memeRepositoryServiceMock.addMemes(memes)).thenReturn(memes);

    String content = "";
    try {
      content = objectMapper.writeValueAsString(memes);
    } catch (JsonProcessingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    // URI uri = UriComponentsBuilder
    //     .fromPath("/memes")
    //     .build().toUri();
        
    // MockHttpServletResponse response = mvc.perform(
    //     post(uri.toString()).accept(APPLICATION_JSON_UTF8)
    // ).andReturn().getResponse();

    // assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());

    MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/memes")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .content(content);

    
    MvcResult result = null;
    try {
      result = mvc.perform(mockRequest)
      .andReturn();
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    String ans = "";
    try {
       ans = result.getResponse().getContentAsString();
    } catch (UnsupportedEncodingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    assertEquals(content, ans);
    
  }

  private Memes getMemes(int id, String name, String caption, String url) {
    Memes memes = new Memes();
    memes.setCaption(caption);
    memes.setId(id);
    memes.setName(name);
    memes.setUrl(url);
    return memes;
  }
}