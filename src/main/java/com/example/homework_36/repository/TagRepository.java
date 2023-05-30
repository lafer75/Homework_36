package com.example.homework_36.repository;

import com.example.homework_36.entity.Tag;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TagRepository extends ListCrudRepository<Tag, Integer> {
    List<Tag> findByNameContaining(String name);
    @Modifying
    @Query("UPDATE \"tags\" SET name = :name WHERE id = :id ")
    void updateById(
            @Param("id") Integer id,
            @Param("name") String name);
}