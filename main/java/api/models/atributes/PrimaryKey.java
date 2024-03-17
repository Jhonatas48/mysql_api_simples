package api.models.atributes;

import api.interfaces.createatrrubutes.IPrimaryKey;
import api.models.enums.CreateAtributes;

public class PrimaryKey implements IPrimaryKey{

	private boolean autoIncrement;
	private String columnName;
	private String value;
	@Override
	public IPrimaryKey getAtribute() {
		
		return this;
	}

	@Override
	public CreateAtributes getType() {
		
		return CreateAtributes.PRIMARY_KEY;
	}

	@Override
	public IPrimaryKey addAutoIncrement() {
		this.autoIncrement=true;
		return this;
	}

	@Override
	public IPrimaryKey setColumnName(String columnName) {
		this.columnName=columnName;
		return this;
	}

	@Override
	public String getColumnName() {
		
		return this.columnName;
	}

	@Override
	public boolean isAutoIncrement() {
		
		return autoIncrement;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
