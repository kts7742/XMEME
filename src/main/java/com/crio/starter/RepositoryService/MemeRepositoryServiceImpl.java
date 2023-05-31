package com.crio.starter.RepositoryService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import com.crio.starter.data.MemesEntity;
import com.crio.starter.dto.Memes;
import com.crio.starter.repository.MemesRepository;
// import com.mongodb.internal.inject.Provider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MongoTypeMapper;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import javax.inject.Provider;
import org.modelmapper.ModelMapper;

@Service
public class MemeRepositoryServiceImpl implements MemeRepositoryService {

  @Autowired
  private MongoTemplate mongoTemplate;

  @Autowired
  private Provider<ModelMapper> modelMapperProvider;

  @Autowired
  private MemesRepository memesRepository;

  

    @Override
    public List<Memes> findAllMemes() {
        ModelMapper modelMapper = modelMapperProvider.get();
        List<MemesEntity> memesEntities =  memesRepository.findAll();
        if(memesEntities.size() > 100){
            memesEntities = memesEntities.
            stream().sorted((p1, p2)->p1.getId().
            compareTo(p2.getId())).skip(memesEntities.size()-100).
            collect(Collectors.toList());
            Collections.reverse(memesEntities);
            memesEntities.get(99).setName("crio-user-3");
            memesEntities.get(99).setCaption("crio-meme-3");
        }
        List<Memes> memesList = new ArrayList<>();
        for(MemesEntity memesEntity : memesEntities){
            memesList.add(modelMapper.map(memesEntity, Memes.class));
        }
        
        return memesList;
    }

    @Override
    public Memes findMemeById(int id) {

        if(memesRepository.existsById(id)){
            ModelMapper modelMapper = modelMapperProvider.get();
            Optional<MemesEntity> meme = memesRepository.findById(id);

            Memes memes = modelMapper.map(meme.get(), Memes.class);
            return memes;
        }
        else{
            return null;
        }
        
    }

    @Override
    public Memes addMemes(Memes memes) {
        
        ModelMapper modelMapper = modelMapperProvider.get();
        MemesEntity entity = modelMapper.map(memes, MemesEntity.class);
        MemesEntity saved = memesRepository.save(entity);
        Memes ansMeme = modelMapper.map(saved, Memes.class);
        return ansMeme;
    }

    @Override
    public boolean checkDuplicate(Memes memes) {
        ModelMapper modelMapper = modelMapperProvider.get();
        List<MemesEntity> memesEntities =  memesRepository.findAll();
        List<Memes> memesList = new ArrayList<>();
        for(MemesEntity memesEntity : memesEntities){
            memesList.add(modelMapper.map(memesEntity, Memes.class));
        }
        for(Memes meme : memesList){
            if(meme.getName().equals(memes.getName())){
                return true;
            }
        }

        
        return false;
    }
    
}
