package api.interfaces.actions;

import java.util.List;
import java.util.function.Consumer;

import api.connection.ConnectionManager;
import api.interfaces.ISQLParameters;
import api.models.statements.Result;

public interface ISelect  extends ISQLParameters<ISelect>{

	/*
	 * Retrieve the results
	 * */
	public Result queryResult();
	
	
	/*
	 * Retrieve the List the object T
	 */
    public <T> List<T> queryList(Class<T> clazz);
    
    public <T> void queryListAsync(Class<T> clazz,Consumer<List<T>>function);
    
    public <T> T queryResult(Class<T> clazz);
    
    public <T> void queryResultAsync(Class<T>classz,Consumer<T>function);
    
    public void queryResultAsync(Consumer<Result>function);
    
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
	
	public ISelect setConnectionManager(ConnectionManager connection);
}
