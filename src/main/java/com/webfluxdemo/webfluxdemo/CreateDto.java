package com.webfluxdemo.webfluxdemo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateDto {

    private String name;
    private String email;
    private String enabled;

}
