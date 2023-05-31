package com.crio.starter.repository;

import java.util.List;
import java.util.Optional;
import com.crio.starter.data.MemesEntity;
import com.crio.starter.dto.Memes;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemesRepository extends MongoRepository<MemesEntity, Integer> {

}
