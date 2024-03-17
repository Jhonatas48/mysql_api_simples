package api.interfaces;

import java.util.function.Consumer;


public interface ICommitAction {

	public boolean commit();
	public boolean commit(Consumer<? super Throwable> failure);
	public void commitAsync(Consumer<Boolean>action);
	public void commitAsync(Consumer<Boolean>action,Consumer<Throwable>error);
}
