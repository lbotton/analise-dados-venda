package com.ntconsult.analise;

import java.io.IOException;
import java.net.URISyntaxException;

import com.ntconsult.analise.service.DirectoryWatcher;

public class Application {

	public static void main(String[] args) throws IOException, InterruptedException, URISyntaxException {
		// TODO Auto-generated method stub
		DirectoryWatcher dw = new DirectoryWatcher();
		dw.watcher();
	}

}
