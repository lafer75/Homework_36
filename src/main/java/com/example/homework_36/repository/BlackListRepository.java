package com.example.homework_36.repository;

import com.example.homework_36.entity.BlackList;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface BlackListRepository extends ListCrudRepository<BlackList, Integer> {
        List<BlackList> findDomainContaining(String domain);
        @Modifying
        @Query("UPDATE \"black_list\" SET domain = :domain WHERE id = :id ")
        void updateById(
                @Param("id") Integer id,
                @Param("domain") String domain);
        boolean existsDomainEndsWithIgnoreCase(String domain);
}

