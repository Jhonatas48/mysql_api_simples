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

<<<<<<< Updated upstream
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
=======
	private final ScheduledExecutorService service;
    private final Queue<Runnable> transactions = new LinkedList<Runnable>();

    public AsyncManager(int threads) {

        service = Executors.newScheduledThreadPool(threads, (runnable) -> {
            Thread thread = new Thread(runnable);
            thread.setDaemon(false);
            return thread;
        });

        service.scheduleAtFixedRate(() -> {

            Runnable runnable = transactions.poll();

            if (Checkers.isObjectNotNull(runnable)) {
                runnable.run();
            }

        }, 0, 16, TimeUnit.MILLISECONDS);
>>>>>>> Stashed changes
    }
    
    public void addTransaction(Runnable runnable) {
    	
    	transactions.add(runnable);
    }
    public void shutdown() {
<<<<<<< Updated upstream
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
    	
       
=======

        service.shutdown();
        HashSet<Runnable> pedingTaks = new HashSet<>();
        try {

            boolean finish = service.awaitTermination(30, TimeUnit.SECONDS);

            if (!finish) {

                System.out.println("Serviço não finalizou a tempo.");
                pedingTaks.addAll(service.shutdownNow());

                finish = service.awaitTermination(30, TimeUnit.SECONDS);
                if(!finish)
                    System.out.println("Serviço não foi finalizado.");

            }

        } catch (InterruptedException e) {
            pedingTaks.addAll(service.shutdownNow());
        }

        // executa alguma task pendente
        if(!pedingTaks.isEmpty()) {

            System.out.println("Existem " + pedingTaks.size() + " tasks pendentes no serviço. Executando...");

            for(Runnable runnable : pedingTaks){
                runnable.run();
            }

            pedingTaks.clear();
        }
        // executa alguma task pendente

        // processa a fila de transações
        if (!transactions.isEmpty()) {

            System.out.println("Existem " + transactions.size() + " transações pendentes no serviço. Executando...");

            Runnable runnable;
            while ((runnable = transactions.poll()) != null) {
                runnable.run();
            }

        }
        // processa a fila de transações

>>>>>>> Stashed changes
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
