package com.areebbeigh.webclip.respositories;

import com.areebbeigh.webclip.dtos.PasteDto;
import com.areebbeigh.webclip.entities.PasteEntity;
import com.areebbeigh.webclip.entities.UserEntity;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.aggregation.UnwindOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PasteRepositoryCustomImpl implements PasteRepositoryCustom {
    private final MongoTemplate mongoTemplate;

    public PasteRepositoryCustomImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Page<PasteDto> findAllBy(Pageable page) {
        final long count = getPasteCount();
        // Sort by newest first
        final Sort latestSort = Sort.by("createdAt").descending();
        SortOperation sortOp = Aggregation.sort(
            page.getSort().isSorted() ? latestSort.and(page.getSort()) : latestSort
        );
        // Prefetch user details
        LookupOperation lookupOp = Aggregation.lookup(
                mongoTemplate.getCollectionName(UserEntity.class),
                "user",
                "_id",
                "user");
        UnwindOperation unwindOp = Aggregation.unwind("user");
        final List<PasteDto> res = mongoTemplate.aggregate(
                Aggregation.newAggregation(
                        sortOp, lookupOp, unwindOp,
                        Aggregation.skip(page.getPageSize() * page.getPageNumber()),
                        Aggregation.limit(page.getPageSize())),
                mongoTemplate.getCollectionName(PasteEntity.class),
                PasteDto.class).getMappedResults();

        return new PageImpl<>(res, page, count);
    }

    private long getPasteCount() {
        return mongoTemplate.count(new Query(), PasteEntity.class);
    }
}
