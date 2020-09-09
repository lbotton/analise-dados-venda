package com.ntconsult.analise.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

public class DirectoryWatcher {
	
	private final String HOMEPATH = System.getProperty("user.home") + "//data//in//";
	
	//NIO watch service
	public void watcher() throws IOException, InterruptedException, URISyntaxException {
        WatchService watchService = FileSystems.getDefault().newWatchService();
        Path path = Paths.get(HOMEPATH);
        path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
        
        watchEvents(watchService);
        watchService.close();
    }

	private void watchEvents(WatchService watchService)
			throws InterruptedException, UnsupportedEncodingException, FileNotFoundException, IOException {
		WatchKey key;
		Parser parser = new Parser();
		while ((key = watchService.take()) != null) {
            for (WatchEvent<?> event : key.pollEvents()) {
                	Thread.sleep(1000);
                	parser.extractLine(event);
            }
            key.reset();
        }
	}

	
	
}
