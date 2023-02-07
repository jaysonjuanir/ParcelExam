package com.exam.parcel.helper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.exam.parcel.request.VoucherRequest;
import com.exam.parcel.response.VoucherResponse;
import com.exam.parcel.util.ParcelRuleProperties;
import com.exam.parcel.util.PropertiesUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

public class VoucherAPIHelper {

	public static VoucherResponse sendGet(String url, VoucherRequest voucherRequest) throws Exception {
		CloseableHttpClient httpClient = HttpClients.createDefault();

		try {

			VoucherResponse voucherResponse = new VoucherResponse();
			url = String.format(url, voucherRequest.getVoucherCode(), voucherRequest.getKey());

			URL u = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) u.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;
			String result = "";
			while ((output = br.readLine()) != null) {
				result += output;
			}
			voucherResponse = new ObjectMapper().readValue(result, VoucherResponse.class);
			conn.disconnect();

			return voucherResponse;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new Exception(PropertiesUtil.getPropertyValue(ParcelRuleProperties.PARCEL_INVALID_VOUCHER_CODE_MESSAGE));
		} finally {
			httpClient.close();
		}
	}
}
