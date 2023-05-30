package com.example.homework_36.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "tags")
public class Tag {
    @Id
    @Column("id")
    private Integer id;

    @Column("name")
    private String name;

    @Column("created_at")
    private Long createdAt;
    public Tag(String name, Long dateAddTag) {
        this.name = name;
        this.createdAt = dateAddTag;
    }
}
