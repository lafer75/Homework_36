package com.example.homework_36.controller;

import com.example.homework_36.dto.LinkDto;
import com.example.homework_36.entity.Link;
import com.example.homework_36.entity.Tag;
import com.example.homework_36.mapper.LinkMapper;
import com.example.homework_36.service.BlackListService;
import com.example.homework_36.service.CategoryService;
import com.example.homework_36.service.LinkService;
import com.example.homework_36.service.TagService;
import com.example.homework_36.entity.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.ZonedDateTime;
import java.util.*;

@RestController
@RequestMapping("/link")
@RequiredArgsConstructor
public class LinkController {

    private final LinkService linkService;

    private final LinkMapper linkMapper;

    private final TagService tagService;

    private final CategoryService categoryService;

    private final BlackListService blackListService;

    @GetMapping("/all")
    public List<LinkDto> findAll() {
        List<LinkDto> linkDtoList = this.linkMapper.mapListLinkToListLinkDto(linkService.findAll());
        if (!linkDtoList.isEmpty()) {
            return linkDtoList;
        } else throw new IllegalArgumentException("no links");
    }

    @GetMapping("/all_p")
    public List<LinkDto> findAll(
            @RequestParam("page") Integer page,
            @RequestParam("pageSize") Integer pageSize) {
        List<LinkDto> linkDtoList = linkService.findAll(
                        page != null ? page : 0,
                        pageSize != null ? pageSize : 5)
                .stream()
                .map(this.linkMapper::mapLinkToLinkDto)
                .toList();
        if (!linkDtoList.isEmpty()) {
            return linkDtoList;
        } else throw new IllegalArgumentException("!!! No Links !!!");
    }

    @GetMapping("/find_by_name_or")
    public List<LinkDto> findByNameOr(@RequestParam("text") String text) {
        List<LinkDto> linkDtoListNameAndValue = this.linkMapper.mapListLinkToListLinkDto(linkService.findByNameOr(text));
        if (!linkDtoListNameAndValue.isEmpty()) {
            return linkDtoListNameAndValue;
        } else throw new IllegalArgumentException("!!! No Name; No Value !!!");

    }

    @GetMapping("/find_by_name_and")
    public List<LinkDto> findByNameAnd(@RequestParam("text") String text) {
        List<LinkDto> linkDtoList = this.linkMapper.mapListLinkToListLinkDto(linkService.findByNameAnd(text));
        if (!linkDtoList.isEmpty()) {
            return linkDtoList;
        } else throw new IllegalArgumentException("!!! No Names & No Values !!!");
    }

    @GetMapping("/find_by_category")
    public List<LinkDto> findByCategory(@RequestParam("text") String text) {
        List<LinkDto> linkDtoList = new ArrayList<>();
        List<Category> list = categoryService.findByName(text);
        if (!list.isEmpty()) {
            for (Category c : list) {
                Integer id = c.getId();
                if (id != null) {
                    LinkDto linkDto = this.linkMapper.mapLinkToLinkDto(
                            linkService.findById(id)
                                    .orElseThrow(() -> new IllegalArgumentException("!!! No  Category !!!")));
                    linkDtoList.add(linkDto);
                }
            }
        } else throw new IllegalArgumentException("!!! No category !!!");
        return linkDtoList;
    }

    @GetMapping("/find_by_tag")
    public List<LinkDto> findByTag(@RequestParam("text") String text) {
        List<LinkDto> linkDtoList = new ArrayList<>();

        List<Tag> list = tagService.findByName(text);

        if (!list.isEmpty()) {
            for (Tag t : list) {
                Integer id = t.getId();
                if (id != null) {
                    LinkDto linkDto = this.linkMapper.mapLinkToLinkDto(linkService.findById(id).orElseThrow(() -> new IllegalArgumentException("!!! No tag !!!")));
                    linkDtoList.add(linkDto);
                }
            }
        } else throw new IllegalArgumentException("!!! No tag !!!");

        return linkDtoList;
    }

    @GetMapping("/update")
    public void updateById(@RequestParam("id") Integer id,
                           @RequestParam("name") String name,
                           @RequestParam("value") String value,
                           @RequestParam("category") String category) {
        List<Category> categories = categoryService.findALL();
        Integer categoryId;
        boolean found = false;
        for (Category c : categories) {
            if (Objects.equals(c.getName(), category)) {
                found = true;
                categoryId = c.getId();
                linkService.update(id, name, value, categoryId);
            }
        }
        if (!found) {
            throw new IllegalArgumentException("!!! Category " + category + " is not created !!!");
        }
    }

    @GetMapping("/delete")
    public void deleteById(@RequestParam("id") Integer id) {
        linkService.deleteById(id);
    }

    @PostMapping(value = "/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Link add(@RequestParam("name") String name,
                    @RequestParam("value") String value,
                    @RequestParam("category") Integer category,
                    @RequestParam(value = "tags") String... arrStringsFromParam) {
        try {
            URL url = new URL(value);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("!!! Value " + value + " is wrong URL !!!");
        }
        boolean isInBlackList = blackListService.existsByDomain(value);
        if (isInBlackList) {
            throw new IllegalArgumentException("!!! Value " + value + " in Black List !!!");
        }

        List<Category> categories = categoryService.findALL();
        boolean isCategoryFound = false;

        for (Category c : categories) {
            if (Objects.equals(c.getId(), category)) {
                isCategoryFound = true;
                break;
            }
        }
        if (!isCategoryFound) {
            throw new IllegalArgumentException("!!! Category " + category + " is not created !!!");
        }

        List<Tag> tagsFromDB = tagService.findAll();

        Set<String> stringParamNotInDB = new HashSet<>();

        Map<String, Tag> paramsAndTagsInDB = new HashMap<>();

        for (String s : arrStringsFromParam) {
            for (Tag t : tagsFromDB) {
                if (Objects.equals(t.getName(), s)) {
                    paramsAndTagsInDB.put(s, t);
                } else
                    stringParamNotInDB.add(s);
            }
        }
        if (paramsAndTagsInDB.size() == arrStringsFromParam.length) {
            Link link = new Link();
            link.setName(name);
            link.setValue(value);
            link.setCategory(category);
            Long dateNow = ZonedDateTime.now().toInstant().toEpochMilli();
            link.setCreatedAt(dateNow);
            for (Tag t : paramsAndTagsInDB.values()) {
                link.addTag(t);
            }
            return linkService.save(link);
        } else {
            List<String> tagNames = new ArrayList<>();
            for (Tag t : tagsFromDB) {
                tagNames.add(t.getName());
            }
            stringParamNotInDB.removeIf(tagNames::contains);
            throw new IllegalArgumentException("!!! Tags " + stringParamNotInDB + " is not created !!!");
        }
    }
}

