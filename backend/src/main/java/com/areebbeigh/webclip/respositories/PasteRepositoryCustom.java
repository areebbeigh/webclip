package com.areebbeigh.webclip.respositories;

import com.areebbeigh.webclip.dtos.PasteDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Aggregation;

public interface PasteRepositoryCustom {
    // Use a PasteDto, not PasteEntity, to prefetch all associated
    // UserEntiy objects instead of n+1 eager fetches
    Page<PasteDto> findAllBy(Pageable page);
}
