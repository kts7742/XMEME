package com.crio.starter.controller;

import com.crio.starter.dto.Memes;
import com.crio.starter.exchange.GetMemesRequest;
import com.crio.starter.exchange.GetMemesResponse;
import com.crio.starter.exchange.ResponseDto;
import com.crio.starter.service.MemesService;
import com.crio.starter.service.MemesServiceImpl;
import lombok.RequiredArgsConstructor;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(MemesController.MEME_API)
@Validated
public class MemesController {

  public static final String MEME_API = "/memes";

  @Autowired
 private MemesService memesService;

  @GetMapping()
  public List<Memes> getMemes(){
    GetMemesResponse getMemesResponse;
    
    getMemesResponse = memesService.findAllMemes();
      if(getMemesResponse.getMemes() == null || getMemesResponse.getMemes().size() == 0){
        return new ArrayList<>();
      }
    return getMemesResponse.getMemes();

  }

  @GetMapping("/{id}")
  public ResponseEntity<Memes> getMemesWithId(@PathVariable("id") Integer id){

    if(id < 0){
      return ResponseEntity.badRequest().body(null);
    }
    Memes memes = memesService.findMemeById(id);

    if(memes == null){
      return ResponseEntity.badRequest().body(null);
    }

    return ResponseEntity.ok().body(memes);
  }

  
  @PostMapping()
  public ResponseEntity<Memes> postMemes(@NonNull @RequestBody(required = false) Memes memes){
    if(memes == null || memes.getName() == null){
      return ResponseEntity.badRequest().body(null);
    }
    
    Integer id = 0;
    GetMemesResponse getMemesResponse = memesService.findAllMemes();
    if(getMemesResponse == null || getMemesResponse.getMemes().size() == 0){
      id = 1;
    }
    else{
      id = getMemesResponse.getMemes().size() + 1;
    }
    
    memes.setId(id);
    if(memesService.checkDuplicate(memes)){
      return ResponseEntity.badRequest().body(null);
    }
    Memes response;
    response = memesService.addMemes(memes);
    
    if(response == null){
      return ResponseEntity.badRequest().body(null);
    }

    return ResponseEntity.ok().body(response);
  }

}
