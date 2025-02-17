package dev.project.mapper;


import dev.project.dto.ErrorDto;
import dev.project.entity.DataSourceErrorLog;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DataSourceErrorMapper {

    ErrorDto toDto(DataSourceErrorLog error);
}
