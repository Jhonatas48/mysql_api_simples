package com.github.jhonatas48.api.models.statements.interfaces;

import com.github.jhonatas48.api.models.enums.ConnectionType;
import com.github.jhonatas48.api.models.enums.TransactionType;

public interface SQLBuild<T> {

	public boolean checkType(ConnectionType connectionType,TransactionType transactionType,Object objectType);
	public String buildSQL(T objectType);
	public String buildSQL(String... data);
}
