package api.models;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import api.models.utils.Checkers;

public class AsyncManager implements Cloneable {

	List<CommitActionImpl>transactions = new LinkedList<>();
	
	public void addTransaction(CommitActionImpl action) {
		transactions.add(action);
	}
	
//	public void performTransactions() {
//		
//		for(CommitActionImpl action:transactions) {
//			if(Checkers.isObjectNotNull(action.getConsumer())) {
//				Boolean result = action.commit();
//				action.getConsumer().accept(result);
//			}
//			
//		}
//	}
//	
	
}
