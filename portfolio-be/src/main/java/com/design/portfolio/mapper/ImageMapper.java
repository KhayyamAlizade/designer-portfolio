package com.design.portfolio.mapper;

import com.design.portfolio.dto.MediaItemDTO;
import com.design.portfolio.entity.MediaItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ImageMapper {

    @Mapping(target = "id", source = "entity.id")
    @Mapping(target = "fileName", source = "entity.imageName")
    @Mapping(target = "title", source = "entity.title")
    @Mapping(target = "imagePath", source = "entity.imagePath")
    @Mapping(target = "groupIndex", source = "entity.groupIndex")
    @Mapping(target = "rowIndex", source = "entity.rowIndex")
    @Mapping(target = "columnIndex", source = "entity.columnIndex")
     MediaItemDTO toDTO(MediaItemEntity entity);

}
