package com.exam.parcel.util;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.exam.parcel.rule.RuleType;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ParcelRuleProperties {

	public static String PARCEL_VALID_VOUCHER_CODE_MESSAGE = "PARCEL_VALID_VOUCHER_CODE_MESSAGE";
	public static String PARCEL_INVALID_VOUCHER_CODE_MESSAGE = "PARCEL_INVALID_VOUCHER_CODE_MESSAGE";
	public static String RULE_CONDITION_VALUE_MESSAGE = "RULE_CONDITION_VALUE_MESSAGE";
	public static String RULE_COST_VALUE_MESSAGE = "RULE_COST_VALUE_MESSAGE";
	public static String VOUCHER_API_BASE_URL = "VOUCHER_API_BASE_URL";
	public static String VOUCHER_API_DISCOUNT_ENDPOINT = "VOUCHER_API_DISCOUNT_ENDPOINT";
	public static String VOUCHER_API_KEY = "VOUCHER_API_KEY";
	public static String RULE_LIST_FILE = "rules.json";
	public static String RULE_DIRECTORY = "rules/";

	public static List<RuleType> ruleList = new ArrayList<RuleType>();

	public static final int DEFAULT_BUFFER_SIZE = 8192;

	public static void initializeRuleValues() {
		System.out.println("Setting up values for rules");

		File file = new File(RULE_DIRECTORY + RULE_LIST_FILE);
		try (InputStream resourceStream = new FileInputStream(file)) {
			String jsonListString = convertInputStreamToString(resourceStream);
			ObjectMapper objectMapper = new ObjectMapper();
			ruleList = objectMapper.readValue(jsonListString, new TypeReference<List<RuleType>>() {
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static List<RuleType> getRuleList() {
		return ruleList;
	}

	public static RuleType getRuleTypeByRuleName(String ruleName) {
		return getRuleList().stream().filter(rl -> rl.getRuleName().equals(ruleName)).findFirst()
				.orElse(new RuleType());
	}

	private static String convertInputStreamToString(InputStream is) throws IOException {
		ByteArrayOutputStream result = new ByteArrayOutputStream();
		byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
		int length;
		while ((length = is.read(buffer)) != -1) {
			result.write(buffer, 0, length);
		}
		return result.toString("UTF-8");
	}

	public static void updateRuleList(List<RuleType> rules) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();

		String jsonInString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(rules);

		File directory = new File(RULE_DIRECTORY);

		if (!directory.exists()) {
			directory.mkdir();
		}

		File file = new File(RULE_DIRECTORY + RULE_LIST_FILE);
		try {
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(jsonInString);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}