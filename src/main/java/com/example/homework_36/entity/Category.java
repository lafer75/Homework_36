package com.example.homework_36.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "categories")
public class Category {
    @Id
    @Column("id")
    private Integer id;

    @Column("name")
    private String name;

    @Column("created_at")
    private Long createdAt;
    public Category(String name, Long dateAddCategory) {
        this.name = name;
        this.createdAt = dateAddCategory;
    }
}
