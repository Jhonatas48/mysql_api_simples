package api.interfaces;

public interface ISQLParameters<T extends ISQLParameters<?>> {

	public T setTable(String table);
}
