package com.example.homework_36.dto;

import com.example.homework_36.entity.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LinkDto {
    private String name;
    private String value;
    private Integer category;
    private Set<Tag> tags;
    private String createdAt;
}
