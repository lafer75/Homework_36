package com.example.homework_36.controller;

import com.example.homework_36.dto.BlackListDto;
import com.example.homework_36.entity.BlackList;
import com.example.homework_36.mapper.BlackListMapper;
import com.example.homework_36.service.BlackListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.List;

    @RestController
    @RequestMapping("/bl_record")
    @RequiredArgsConstructor
    public class BlackListController {

        private final BlackListService blackListService;

        private final BlackListMapper blackListMapper;

        @GetMapping("/all")
        public List<BlackListDto> findAll() {
            return this.blackListMapper.mapListBLRecordToListBLRecordDto(blackListService.findAll());
        }

        @GetMapping("/find_by_domain")
        public List<BlackListDto> findByName(@RequestParam("text") String text) {
            return this.blackListMapper.mapListBLRecordToListBLRecordDto(blackListService.findByName(text));
        }

        @PostMapping("/add")
        @ResponseStatus(HttpStatus.CREATED)
        public BlackList add(@RequestParam("domain") String domain) {

            Long dateAddNote = ZonedDateTime.now().toInstant().toEpochMilli();

            return blackListService.save(new BlackList(domain, dateAddNote));
        }

        @GetMapping("/update")
        public void updateById(@RequestParam("id") Integer id,
                               @RequestParam("domain") String domain) {
            blackListService.update(id, domain);
        }

        @GetMapping("/delete")
        public void deleteById(@RequestParam("id") Integer id) {
            blackListService.deleteById(id);
        }
    }

