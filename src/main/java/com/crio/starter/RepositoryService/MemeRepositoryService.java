package com.crio.starter.RepositoryService;

import java.util.List;
import com.crio.starter.dto.Memes;
import com.crio.starter.exchange.GetMemesResponse;


public interface MemeRepositoryService {
    List<Memes> findAllMemes();
    Memes findMemeById(int id);
    Memes addMemes(Memes memes);
    boolean checkDuplicate(Memes memes);
}
