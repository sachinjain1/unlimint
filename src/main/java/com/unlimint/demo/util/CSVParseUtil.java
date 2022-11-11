package com.unlimint.demo.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unlimint.demo.dto.ParsedData;

public class CSVParseUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(CSVParseUtil.class);

	public static void parseCSV(List<ParsedData> parsedDataList, String filePath, String fileName, String currentPath)
			throws FileNotFoundException, IOException {
		File f = new File(filePath);
		if (f.exists() && !f.isDirectory()) {
			CSVParser csvParser = new CSVParser(new FileReader(filePath), CSVFormat.DEFAULT);
			int i = 0;
			for (CSVRecord record : csvParser) {
				ParsedData parsedData = null;
				Double amount = null;
				Long orderId = null;
				String currency;
				String comment = null;
				try {
					orderId = Long.parseLong(record.get(0));
					amount = Double.parseDouble(record.get(1));
					currency = record.get(1);
					comment = record.get(3);
					parsedData = new ParsedData(orderId, amount, comment, fileName, i + 1, "OK");
					parsedDataList.add(parsedData);
					i++;
					LOGGER.info("List inside parseCSV fucntion>>>>>> " + parsedDataList.toString());
				} catch (Exception e) {
					LOGGER.error("The Exception inside parseCSV is.... " + e.getMessage());
					parsedData = new ParsedData(orderId, amount, comment, fileName, i + 1, e.getMessage());
					parsedDataList.add(parsedData);
					LOGGER.info("List inside parseCSV fucntion>>>>> " + parsedDataList.toString());
					i++;
				}

			}
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(new File(currentPath + "output.json"), parsedDataList);
		}

	}
}
