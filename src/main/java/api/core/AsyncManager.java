package api.core;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class AsyncManager {

    private final ExecutorService service;

    public AsyncManager(int threads){
        service = Executors.newFixedThreadPool(threads);
    }

    public ExecutorService getService() {
        return service;
    }
}
