package com.example.homework_36.mapper;

import com.example.homework_36.dto.LinkDto;
import com.example.homework_36.entity.Link;
import com.example.homework_36.entity.Tag;
import com.example.homework_36.entity.TagTwo;
import com.example.homework_36.service.TagService;
import org.springframework.stereotype.Component;
import java.util.*;

@Component
public class LinkMapper {
    ConvertTime convertTime = new ConvertTime();
    private final TagService tagService;
    public LinkMapper(TagService tagService) {
        this.tagService = tagService;
    }
    public LinkDto mapLinkToLinkDto(Link link) {
        return LinkDto.builder()
                .name(link.getName())
                .value(link.getValue())
                .category(link.getCategory())
                .tags(convertTagsToTagTwo(link.getTagtwo()))
                .createdAt(convertTime.longToString(link.getCreatedAt()))
                .build();
    }
    public List<LinkDto> mapListLinkToListLinkDto(List<Link> list) {
        List<LinkDto> linkDtoList = new ArrayList<>();
        for (Link l : list) {
            linkDtoList.add(mapLinkToLinkDto(l));
        }
        return linkDtoList;
    }
    private Set<Tag> convertTagsToTagTwo(Set<TagTwo> tagTwos){

        Set<Tag> tags = new HashSet<>();

        for(TagTwo tt: tagTwos){
            Integer id = tt.getId();
            Optional<Tag> tag = tagService.findByID(id);
            tags.add(tag.orElseThrow(() -> new IllegalArgumentException("!!! No Tags !!!")));
        }
        return tags;
    }
}
