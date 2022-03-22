package com.areebbeigh.webclip.dtos;

import lombok.*;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@ToString
public class Credentials {
    @NotNull
    private String username;
    @NotNull
    private String password;
}
