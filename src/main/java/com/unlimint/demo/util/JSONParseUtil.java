package com.unlimint.demo.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unlimint.demo.dto.ParsedData;

public class JSONParseUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(JSONParseUtil.class);

	public static void parseJson(List<ParsedData> parsedDataList, String filePath, String fileName, String currentPath)
			throws FileNotFoundException, IOException, ParseException {
		File f = new File(filePath);
		if (f.exists() && !f.isDirectory()) {
			JSONParser jsonParser = new JSONParser();
			JSONArray orderArray = (JSONArray) jsonParser.parse(new FileReader(filePath));

			ParsedData parsedData = null;
			for (int i = 0; i < orderArray.size(); i++) {
				Double amount = null;
				Long orderId = null;
				String currency;
				String comment = null;
				try {
					JSONObject orderJsonData = (JSONObject) orderArray.get(i);
					amount = (Double) orderJsonData.get("amount");
					orderId = (Long) orderJsonData.get("orderId");
					currency = (String) orderJsonData.get("currency");
					comment = (String) orderJsonData.get("comment");
					parsedData = new ParsedData(orderId, amount, comment, fileName, i + 1, "OK");
					parsedDataList.add(parsedData);
					LOGGER.info("List inside parseJson fucntion>>>>> " + parsedDataList.toString());

				} catch (Exception e) {
					LOGGER.error("The Exception inside parseJson is>>>> " + e.getMessage());
					parsedData = new ParsedData(orderId, amount, comment, fileName, i + 1, e.getMessage());
					parsedDataList.add(parsedData);
					LOGGER.info("List inside parseJson fucntion>>>> " + parsedDataList.toString());
				}
			}
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(new File(currentPath + "output.json"), parsedDataList);
		}
	}

}
