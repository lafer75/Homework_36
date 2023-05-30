package com.example.homework_36.mapper;

import com.example.homework_36.dto.TagDto;
import com.example.homework_36.entity.Tag;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
@Component
public class TagMapper {
    ConvertTime convertTime = new ConvertTime();
    public TagDto mapTagToTagDto(Tag tag) {
        return TagDto.builder()
                .name(tag.getName())
                .createdAt(convertTime.longToString(tag.getCreatedAt()))
                .build();
    }
    public List<TagDto> mapListTagToListTagDto(List<Tag> list) {
        List<TagDto> tagDtoList = new ArrayList<>();
        for (Tag t : list) {
            tagDtoList.add(mapTagToTagDto(t));
        }
        return tagDtoList;
    }
}