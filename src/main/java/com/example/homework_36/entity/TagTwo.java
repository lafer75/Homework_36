package com.example.homework_36.entity;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@NoArgsConstructor
@Data
@Table(name = "links_tags")
public class TagTwo {
    @Column("tag_id")
    private Integer id;
    public TagTwo(Integer id) {
        this.id = id;
    }
    public Integer getId() {
        return id;
    }
}
