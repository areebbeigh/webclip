package com.areebbeigh.webclip.respositories;

import com.areebbeigh.webclip.dtos.PasteDto;
import com.areebbeigh.webclip.entities.PasteEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface PasteRepository extends MongoRepository<PasteEntity, String>, PasteRepositoryCustom {
}
