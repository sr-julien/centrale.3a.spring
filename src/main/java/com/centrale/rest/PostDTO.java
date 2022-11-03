package com.centrale.rest;

import com.centrale.rest.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostDTO {
    private String title;
    private String content;
}
