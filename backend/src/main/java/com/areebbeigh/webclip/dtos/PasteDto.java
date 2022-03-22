package com.areebbeigh.webclip.dtos;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class PasteDto {
    private String id;
    @NotNull
    @NotEmpty
    private String content;
    private Date createdAt;
    private UserDto user;
}
