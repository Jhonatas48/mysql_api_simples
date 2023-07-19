package com.github.jhonatas48.api.models.atributes;

import java.util.ArrayList;
import java.util.List;

import com.github.jhonatas48.api.interfaces.IUnique;
import com.github.jhonatas48.api.models.enums.CreateAtributes;
import com.github.jhonatas48.api.models.utils.Checkers;

public class Unique implements IUnique{

	private List<String>columns = new ArrayList<>();
	public Unique() {
	}
	@Override
	public IUnique getAtribute() {
		
		return this;
	}
	@Override
	public CreateAtributes getType() {
		
		return CreateAtributes.UNIQUE;
	}
	@Override
	public IUnique addColumn(String column) {
		Checkers.validateStringNotNull(column, "column");
		if(columns.contains(column)){
			return this;
		}
		columns.add(column);
		return this;
	}
	public int getColumnsQuantity() {
		return columns.size();
	}
	
	@Override
	public String getColumns() {
		String columnsFormated = "";
	    boolean first = true;
		for(String column:columns) {
			
			    if(first) {
			    	columnsFormated+=column;
			    	first= false;
			    }else {
			    	columnsFormated+=","+column;
			    }
				
		}
		return columnsFormated;
	}

}
