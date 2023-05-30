package com.example.homework_36.service;

import com.example.homework_36.entity.Link;
import com.example.homework_36.repository.LinkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class LinkService {
    private final LinkRepository linkRepository;
    public List<Link> findAll() {
        return this.linkRepository.findAll();
    }

    public Page<Link> findAll(int page, int pageSize) {
        return this.linkRepository.findAll(PageRequest.of(page, pageSize, Sort.unsorted()));
    }

    public List<Link> findByNameOr(String text) {
        return this.linkRepository.findAllByNameContainingIgnoreCaseOrValueContainingIgnoreCase(text, text);
    }

    public List<Link> findByNameAnd(String text) {
        return this.linkRepository.findAllByNameContainingIgnoreCaseAndValueContainingIgnoreCase(text, text);
    }
    public Optional<Link> findById(Integer id) {
        return this.linkRepository.findById(id);
    }
    public void update(Integer id, String name, String value, Integer category) {
        if (this.linkRepository.existsById(id)) {
            this.linkRepository.updateById(id, name, value, category);
        }
    }
    public void deleteById(Integer id) {
        this.linkRepository.deleteById(id);
    }

    public Link save(Link link) {
        return this.linkRepository.save(link);
    }

}
