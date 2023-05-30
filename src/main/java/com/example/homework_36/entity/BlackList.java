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
@Table(name = "black_list")
public class BlackList {
    @Id
    @Column("id")
    private Integer id;

    @Column("domain")
    private String domain;

    @Column("created_at")
    private Long createdAt;

    public BlackList(String domain, Long dateAddBLRecord) {
        this.domain = domain;
        this.createdAt = dateAddBLRecord;
    }
}

