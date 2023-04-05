package api.interfaces;

import java.util.function.Consumer;

public interface IInsert  extends ISQLParameters<IInsert>{

	public IInsert addColumn(String column,String value);
	public boolean commit();
	public boolean commit(Consumer<? super Throwable> failure);
}
