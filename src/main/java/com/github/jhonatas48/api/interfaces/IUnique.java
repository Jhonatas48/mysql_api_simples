package com.github.jhonatas48.api.interfaces;

public interface IUnique extends ICreateAtributes<IUnique> {

	public IUnique addColumn(String column);
	public String getColumns();
	public int getColumnsQuantity();
	
}
