package com.phucnv.elasticsearch.mapper;

import com.phucnv.elasticsearch.model.PersonDocument;
import com.phucnv.elasticsearch.model.dto.PersonDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonMapper {

  PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

  PersonDocument dtoToDocument(PersonDto dto);
}
