package com.excilys.mapper;

import java.util.List;
import java.util.stream.Collectors;

public interface IMapperDTO<T, U> {

	U mapModelToDTO(T model);
	
	T mapDTOToModel(U dto);
	
	default List<U> modelsToDto(List<T> models) {
		if (models == null) {
			throw new IllegalArgumentException("IMapperDTO - Invalid models");
		}		
		return models.stream().map(m -> mapModelToDTO(m)).collect(Collectors.toList());
	}
	
}
