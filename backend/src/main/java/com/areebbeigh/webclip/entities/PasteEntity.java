package com.areebbeigh.webclip.entities;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Document
@NoArgsConstructor
@Data
public class PasteEntity {
    @Id
    private String id;
    private String content;
    @DocumentReference
    private UserEntity user;
    @CreatedDate
    private Date createdAt;
}
