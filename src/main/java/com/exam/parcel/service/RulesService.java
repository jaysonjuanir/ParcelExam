package com.exam.parcel.service;

import com.exam.parcel.request.RulesRequest;
import com.exam.parcel.response.RulesResponse;

public interface RulesService {

	public RulesResponse updateRule(String ruleName, RulesRequest request) throws Exception;
}
