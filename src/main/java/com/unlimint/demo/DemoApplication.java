package com.unlimint.demo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.io.FilenameUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unlimint.demo.constants.MessageConstants;
import com.unlimint.demo.dto.ParsedData;
import com.unlimint.demo.util.CSVParseUtil;
import com.unlimint.demo.util.JSONParseUtil;

@SpringBootApplication
@ComponentScan({ "com.unlimint.demo" })
public class DemoApplication {
	private static final Logger LOGGER = LoggerFactory.getLogger(DemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

		String currentPath = System.getProperty("user.dir") + "\\";
		LOGGER.info("The System current path is>>>>>  " + currentPath);
		List<ParsedData> parsedDataList = new ArrayList<>();
		ExecutorService threadPool = Executors.newFixedThreadPool(args.length);
		for (int i = 0; i < args.length; i++) {
			String inputFileName = args[i];
			String fullPath = currentPath.concat(inputFileName);
			LOGGER.info("The System full file path is>>>>  " + fullPath);
			threadPool.submit(new Runnable() {
				public void run() {
					String fileExtension = FilenameUtils.getExtension(fullPath);
					if (fileExtension.equalsIgnoreCase(MessageConstants.JSON_STRING)) {
						try {
							JSONParseUtil.parseJson(parsedDataList, fullPath, inputFileName, currentPath);
						} catch (IOException | ParseException e) {
							e.printStackTrace();
						}
					}
					if (fileExtension.equalsIgnoreCase(MessageConstants.CSV_STRING)) {
						try {
							CSVParseUtil.parseCSV(parsedDataList, fullPath, inputFileName, currentPath);
						} catch (IOException e) {
							e.printStackTrace();
						}

					}
				}

			});
		}

	}

}
