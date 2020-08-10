package uk.ac.ucl.util;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class FileChangeMonitor implements Runnable {
    boolean stop = false;
    Path path;

    public FileChangeMonitor(Path path) throws IOException {
        this.path = path;
    }

    public synchronized void start() throws IOException {
        WatchService monitor = FileSystems.getDefault().newWatchService();
        registerAll(monitor);

        while (true) {
            WatchKey key = null;
            try {
                key = monitor.take();
                for (WatchEvent<?> event : key.pollEvents()){
                    String fileName = event.context().toString();
                    if (fileName.endsWith(".jar") || fileName.endsWith(".class") || fileName.endsWith(".xml")){
                        System.out.println(fileName + " has been modified!");
                    }
                }
                key.reset();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public void stop(){

    }

    private void registerAll(WatchService monitor) throws IOException {
        Files.walkFileTree(this.path, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs)
                    throws IOException {
                 dir.register(monitor, StandardWatchEventKinds.ENTRY_CREATE,
                         StandardWatchEventKinds.ENTRY_DELETE,
                         StandardWatchEventKinds.ENTRY_MODIFY);
                 return FileVisitResult.CONTINUE;
            }
        });
    }

    @Override
    public void run() {
        try {
            start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
