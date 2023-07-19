package com.github.jhonatas48.api.interfaces;

import com.github.jhonatas48.api.models.enums.CreateAtributes;

@SuppressWarnings("rawtypes")
public interface ICreateAtributes<T extends ICreateAtributes> {

	public T getAtribute();
	public CreateAtributes getType();
}
