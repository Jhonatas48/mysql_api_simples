package api.models.atributes;

import api.interfaces.IForeignKey;
import api.models.enums.CreateAtributes;

public class ForeignKey implements IForeignKey{

	private String columnName;
	private String tableForeign;
	private String columnForeign;
	public ForeignKey() {
		
	}
	public String getColumnForeign() {
		return columnForeign;
	}
	public IForeignKey setColumnForeign(String columnForeign) {
		this.columnForeign = columnForeign;
		return this;
	}
	/**
	 * @return the columnName
	 */
	public String getColumnName() {
		return columnName;
	}
	/**
	 * @param columnBame the columnBame to set
	 */
	public IForeignKey setColumnName(String columnName) {
		this.columnName = columnName;
		return this;
	}
	/**
	 * @return the tableForeign
	 */
	public String getTableForeign() {
		return tableForeign;
	}
	/**
	 * @param tableForeign the tableForeign to set
	 */
	public IForeignKey setTableForeign(String tableForeign) {
		this.tableForeign = tableForeign;
		return this;
	}
	
	@Override
	public IForeignKey getAtribute() {
		return this;
	}
	
	@Override
	public CreateAtributes getType() {
		return CreateAtributes.FOREIGN_KEY;
	}
	
}
