package api.models;

import api.interfaces.ICreate;
import api.interfaces.IDelete;
import api.interfaces.IInsert;
import api.interfaces.ISelect;
import api.interfaces.ITransaction;
import api.interfaces.IUpdate;

public class Transaction implements ITransaction {

	/*
	 * Create instance from perform Create
	 * */
	@Override
	public ICreate create() {
		// TODO Auto-generated method stub
	
		return new CreateImpl();
	}
	/*
	 * Create instance from perform Insert
	 * */
	@Override
	public IInsert insert() {
		// TODO Auto-generated method stub
		
		return new InsertImpl();
	}
	
	/*
	 * Create instance from perform Select
	 * */
	@Override
	public ISelect select() {
		// TODO Auto-generated method stub
		return new SelectImpl();
	}

	/*
	 * Create instance from perform Update
	 * */
	@Override
	public IUpdate update() {
		// TODO Auto-generated method stub
		return new UpdateImpl();
	}

	@Override
	public IDelete delete() {
		// TODO Auto-generated method stub
		return new DeleteImpl();
	}


}
