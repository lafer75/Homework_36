package com.example.homework_36.repository;

import com.example.homework_36.entity.Link;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LinkRepository extends ListCrudRepository<Link, Integer>, PagingAndSortingRepository<Link, Integer> {
    List<Link> findAllByNameContainingIgnoreCaseOrValueContainingIgnoreCase(String name, String value);

    List<Link> findAllByNameContainingIgnoreCaseAndValueContainingIgnoreCase(String name, String value);

    List<Link> findAllCategory(Integer id);

    @Query("select l.* from links l join links_tags lt on l.id = lt.link_id where lt.tag_id = :id")
    List<Link> findTagId(Integer id);

    @Modifying
    @Query("UPDATE \"links\" SET name = :name, value = :value, category_id = :category  WHERE id = :id")
    void updateById(
            @Param("id") Integer id,
            @Param("name") String name,
            @Param("value") String value,
            @Param("category") Integer category);
}