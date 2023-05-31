package com.crio.starter.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import com.crio.starter.App;
import com.crio.starter.RepositoryService.MemeRepositoryService;
import com.crio.starter.data.MemesEntity;
import com.crio.starter.dto.Memes;
import com.crio.starter.exchange.GetMemesResponse;
import com.crio.starter.exchange.ResponseDto;
import com.crio.starter.repository.MemesRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest(classes = {App.class})
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.STRICT_STUBS)

class MemesServiceTest {

  @Mock
  private MemesRepository memesRepository;

  @InjectMocks
  protected MemesServiceImpl memesService;

  @MockBean
  protected MemeRepositoryService memeRepositoryServiceMock;

  // @BeforeEach
  // public void addMemesToDatabase(){
  //   memesService.addMemes(getMemes("1", "Dhoni", "https", "Legend"));
  //   memesService.addMemes(getMemes("2", "Kohli", "http", "King"));
  // }

  private Memes getMemes(String name, String caption, String url) {
    Memes memes = new Memes();
    memes.setCaption(caption);
    // memes.setId(id);
    memes.setName(name);
    memes.setUrl(url);
    return memes;
  }

  @Test
  void getMemesById() {
    MemesEntity memesEntity = getMemesEntity(1, "Dhoni", "https", "Legend");
    // Mockito.doReturn(memesEntity)
    //     .when(memesRepository).findById("1").orElse(null);
      
    Memes actual = memesService.findMemeById(1);

    assertEquals(memesEntity, actual);
    // ResponseDto responseDto = memesService.getMessage("001");

    // Memes expected = new Memes("Welcome");
    // assertEquals(expected, responseDto);

  }

  @Test
  void addMemes(){
    Memes meme1 = getMemes("Dhoni", "https", "Legend");
    // Memes meme2 = getMemes("2", "SRK", "https", "Legend");
    // assertEquals("1", memes.getId());
    when(memeRepositoryServiceMock.addMemes(meme1)).thenReturn(meme1);
    
    Memes getMemesResponse = memesService.addMemes(meme1);
    // Memes meme = memeRepositoryService.addMemes(memes);
    // assertEquals("1", meme.getId());
    assertEquals("1", getMemesResponse.getId());
    
  }

  private MemesEntity getMemesEntity(Integer id, String name, String url, String caption) {
    MemesEntity memesEntity = new MemesEntity();
    memesEntity.setId(id);
    memesEntity.setName(name);
    memesEntity.setCaption(caption);
    memesEntity.setUrl(url);
    return memesEntity;
  }
}