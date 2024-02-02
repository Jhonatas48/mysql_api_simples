package api.core;

import java.util.HashSet;
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

        }, 0, 30, TimeUnit.MILLISECONDS);
    }

    public void addTransaction(Runnable runnable) {
        transactions.add(runnable);
    }

    public void shutdown() {

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

    }

}

