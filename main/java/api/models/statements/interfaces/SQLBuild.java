package api.models.statements.interfaces;

import api.models.enums.ConnectionType;
import api.models.enums.TransactionType;

public interface SQLBuild<T> {

	public boolean checkType(ConnectionType connectionType,TransactionType transactionType,Object objectType);
	public String buildSQL(T objectType);
	public String buildSQL(String... data);
}
