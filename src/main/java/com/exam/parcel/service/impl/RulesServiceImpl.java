package com.exam.parcel.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.exam.parcel.request.RulesRequest;
import com.exam.parcel.response.RulesResponse;
import com.exam.parcel.rule.RuleType;
import com.exam.parcel.service.RulesService;
import com.exam.parcel.util.ParcelRuleProperties;
import com.exam.parcel.util.PropertiesUtil;

@Service
public class RulesServiceImpl implements RulesService {

	@Override
	public RulesResponse updateRule(String ruleName, RulesRequest request) throws Exception {
		RulesResponse response = new RulesResponse();
		String message = "";
		List<String> messageList = new ArrayList<String>();
		RuleType rule = ParcelRuleProperties.getRuleTypeByRuleName(ruleName);

		if (request.getCondition() != null) {
			rule.setCondition(request.getCondition());
			updateRuleCondition(ruleName, rule);

			response.setCondition(rule.getCondition());
			messageList
					.add(ruleName + PropertiesUtil.getPropertyValue(ParcelRuleProperties.RULE_CONDITION_VALUE_MESSAGE));
		}
		if (request.getCost() != null) {
			rule.setCost(request.getCost());
			updateRuleCost(ruleName, rule);

			response.setCost(rule.getCost());
			messageList.add(ruleName + PropertiesUtil.getPropertyValue(ParcelRuleProperties.RULE_COST_VALUE_MESSAGE));
		}

		ParcelRuleProperties.initializeRuleValues();

		message = String.join(",", messageList);
		response.setMessage(message);
		response.setIsSuccess(true);
		response.setRuleName(ruleName);

		return response;
	}

	private void updateRuleCondition(String ruleName, RuleType ruleType) throws Exception {
		List<RuleType> ruleTypeList = ParcelRuleProperties.getRuleList();

		ruleTypeList.stream().filter(rule -> rule.getRuleName().equals(ruleName)).findAny().orElse(new RuleType())
				.setCondition(ruleType.getCondition());

		ParcelRuleProperties.updateRuleList(ruleTypeList);
	}

	private void updateRuleCost(String ruleName, RuleType ruleType) throws Exception {
		List<RuleType> ruleTypeList = ParcelRuleProperties.getRuleList();

		ruleTypeList.stream().filter(rule -> rule.getRuleName().equals(ruleName)).findAny().orElse(new RuleType())
				.setCost(ruleType.getCost());

		ParcelRuleProperties.updateRuleList(ruleTypeList);
	}

}
