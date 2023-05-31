  package com.crio.starter.service;

import com.crio.starter.RepositoryService.MemeRepositoryService;
import com.crio.starter.RepositoryService.MemeRepositoryServiceImpl;
import com.crio.starter.dto.Memes;
import com.crio.starter.exchange.GetMemesResponse;
import com.crio.starter.exchange.ResponseDto;
import com.crio.starter.repository.MemesRepository;
import lombok.RequiredArgsConstructor;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
// @RequiredArgsConstructor
public class MemesServiceImpl implements MemesService {

  
  // private final MemesRepository greetingsRepository;
  
  @Autowired
  private MemeRepositoryService memeRepositoryService;

  @Override
  public Memes findMemeById(int id) {
    Memes meme;
    meme = memeRepositoryService.findMemeById(id);
    if(meme == null) {
      return null;
    }
    // GetMemesResponse getMemesResponse = new GetMemesResponse(meme);
    return meme;
  }

  @Override
  public GetMemesResponse findAllMemes() {
    List<Memes> memes;
    memes = memeRepositoryService.findAllMemes();
    if(memes == null) {
      GetMemesResponse getMemesResponse = new GetMemesResponse();
      return getMemesResponse;
    }
    GetMemesResponse getMemesResponse = new GetMemesResponse(memes);
    return getMemesResponse;
  }

  @Override
  public Memes addMemes(Memes meme) {
    Memes memes;
    memes = memeRepositoryService.addMemes(meme);
    // GetMemesResponse getMemesResponse = new GetMemesResponse(memes);
    return memes;
  }

  @Override
  public boolean checkDuplicate(Memes memes) {
    return memeRepositoryService.checkDuplicate(memes);
  }

  // public ResponseDto getMessage(String id) {
  //   return new ResponseDto(greetingsRepository.findByExtId(id).getMessage());
  // }
}
