package api.interfaces;

import api.models.statements.Result;

public interface ISelect  extends ISQLParameters<ISelect>{

	/*
	 * Retrieve the results
	 * */
	public Result queryResult();
	
	public Result queryResult(boolean useLogTransaction);
	public Exception getException();
	/*
	 * Select the column in the query Result
	 * */
	public ISelect addColumn(String column);
	/*
	 * Add CLause inner join
	 * @param table define this table,
	 * */
	public ISelect addInnerjoin(String table,String onClause);
	
	public ISelect filter(String filter);
}
