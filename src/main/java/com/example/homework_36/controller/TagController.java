package com.example.homework_36.controller;


import com.example.homework_36.dto.TagDto;
import com.example.homework_36.entity.Tag;
import com.example.homework_36.mapper.TagMapper;
import com.example.homework_36.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.List;

    @RestController
    @RequestMapping("/tag")
    @RequiredArgsConstructor
    public class TagController {

        private final TagService tagService;

        private final TagMapper tagMapper;
        @GetMapping("/all")
        public List<TagDto> findAll() {
            return this.tagMapper.mapListTagToListTagDto(tagService.findAll());
        }
        @GetMapping("/find_by_name")
        public List<TagDto> findByName(@RequestParam("text") String text) {
            return this.tagMapper.mapListTagToListTagDto(tagService.findByName(text));
        }
        @PostMapping("/add")
        @ResponseStatus(HttpStatus.CREATED)
        public Tag add(@RequestParam("name") String name) {
            Long dateAddNote = ZonedDateTime.now().toInstant().toEpochMilli();
            return tagService.save(new Tag(name, dateAddNote));
        }
        @GetMapping("/update")
        public void updateById(@RequestParam("id") Integer id,
                               @RequestParam("name") String name) {
            tagService.update(id, name);
        }
        @GetMapping("/delete")
        public void deleteById(@RequestParam("id") Integer id) {
            tagService.deleteById(id);
        }

    }

