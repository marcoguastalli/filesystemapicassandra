package net.marco27.api.filesystemapi.executor;

import java.util.concurrent.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.marco27.api.filesystemapi.configuration.ApplicationConfiguration;
import net.marco27.api.filesystemapi.domain.FileStructure;

@Component
public class PrintFileSystemExecutor {

    @Autowired
    private ApplicationConfiguration applicationConfiguration;

    public FileStructure execute(final Callable callable) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(applicationConfiguration.getNumberoOfThreads());
        try {
            Future<FileStructure> result = executorService.submit(callable);
            return result.get();
        } finally {
            executorService.shutdown();
        }
    }
}
