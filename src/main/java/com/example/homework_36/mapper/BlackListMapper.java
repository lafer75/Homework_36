package com.example.homework_36.mapper;

import com.example.homework_36.dto.BlackListDto;
import com.example.homework_36.entity.BlackList;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class BlackListMapper {
    ConvertTime convertTime = new ConvertTime();
    public BlackListDto mapBLRecordToBLRecordDto(BlackList blackList) {

        return BlackListDto.builder()
                .domain(blackList.getDomain())
                .createdAt(convertTime.longToString(blackList.getCreatedAt()))
                .build();
    }
    public List<BlackListDto> mapListBLRecordToListBLRecordDto(List<BlackList> blackLists) {

        List<BlackListDto> blackListDtos = new ArrayList<>();
        for (BlackList b : blackLists) {
            blackListDtos.add(mapBLRecordToBLRecordDto(b));
        }
        return blackListDtos;
    }

}

