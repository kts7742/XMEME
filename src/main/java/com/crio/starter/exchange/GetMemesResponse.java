package com.crio.starter.exchange;

import java.util.List;
import com.crio.starter.dto.Memes;

public class GetMemesResponse {
    List<Memes> memes;
    Memes meme;
    public GetMemesResponse(List<Memes> memes){
        this.memes = memes;
    }
    public GetMemesResponse(){
        
    }
    // public GetMemesResponse(Memes meme){
    //     this.meme = meme;
    // }
    public List<Memes> getMemes(){
        return memes;
    }
    // public Memes getMeme(){
    //     return meme;
    // }
    // public int getSize(){
    //     return memes.size();
    // }
}
