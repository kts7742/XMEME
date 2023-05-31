package com.crio.starter.service;

import com.crio.starter.dto.Memes;
import com.crio.starter.exchange.GetMemesResponse;

public interface MemesService {
    public Memes findMemeById(int id);
    public GetMemesResponse findAllMemes();
    public Memes addMemes(Memes memes);
    public boolean checkDuplicate(Memes memes);
}
