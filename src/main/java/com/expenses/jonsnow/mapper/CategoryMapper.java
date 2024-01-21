package com.expenses.jonsnow.mapper;

import com.expenses.jonsnow.dto.CategoryDTO;
import com.expenses.jonsnow.model.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper extends BaseMapper<Category, CategoryDTO,CategoryDTO> {

}
