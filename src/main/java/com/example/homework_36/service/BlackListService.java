package com.example.homework_36.service;

import com.example.homework_36.entity.BlackList;
import com.example.homework_36.repository.BlackListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BlackListService {
    private final BlackListRepository blackListRepository;
    public List<BlackList> findAll() {
        return this.blackListRepository.findAll();
    }
    public List<BlackList> findByName(String text) {
        return this.blackListRepository.findDomainContaining(text);
    }
    public BlackList save(BlackList record) {
        return this.blackListRepository.save(record);
    }
    public void update(Integer id, String domain) {
        if (this.blackListRepository.existsById(id)) {
            this.blackListRepository.updateById(id, domain);
        }
    }
    public void deleteById(Integer id) {
        this.blackListRepository.deleteById(id);
    }
    public boolean existsByDomain(String domain) {
        return this.blackListRepository.existsDomainEndsWithIgnoreCase(domain);
    }
}

