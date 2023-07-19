package com.github.jhonatas48.api.interfaces;

import java.util.function.Consumer;


public interface ICommitAction {

	public boolean commit();
	public boolean commit(Consumer<? super Throwable> failure);
	
}
