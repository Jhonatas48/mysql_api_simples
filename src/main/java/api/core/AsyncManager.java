package api.core;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.core.config.Scheduled;

import api.models.utils.Checkers;


public class AsyncManager {

    private final ScheduledExecutorService service;
    private Queue<Runnable>transactions = new LinkedList<Runnable>();
    private final CountDownLatch latch = new CountDownLatch(1);
    
    public AsyncManager(int threads){
        service = Executors.newScheduledThreadPool(threads);
        service.scheduleAtFixedRate(()->{
        	
        	Runnable runnable = transactions.poll();
        	if(Checkers.isObjectNotNull(runnable)) {
        		runnable.run();
        	}
        	
        },0,30,TimeUnit.MILLISECONDS);
    }
    
    public void addTransaction(Runnable runnable) {
    	
    	transactions.add(runnable);
    }
    public void shutdown() {
//        // Aguarda até que todas as transações sejam processadas ou até que o tempo limite seja atingido
//        try {
//            latch.await();
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt();
//        }
//        // Encerra o serviço
    	 service.shutdown();
    	try {
			service.awaitTermination(30, TimeUnit.SECONDS);
			Runnable runnable = null;
			while((runnable=transactions.poll())!= null) {
				runnable.run();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
       
    }

    public void setLatch() {
        // Define o latch quando a lista de transações estiver vazia
        if (transactions.isEmpty()) {
            latch.countDown();
        }
    }
    
//    public ExecutorService getService() {
//        return service;
//    }
//    
    
    
}
