package com.exam.parcel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.exam.parcel.request.RulesRequest;
import com.exam.parcel.response.RulesResponse;
import com.exam.parcel.rule.RuleEnum;
import com.exam.parcel.service.RulesService;
import com.exam.parcel.util.ParcelRuleProperties;

@RestController
public class RulesController {

	@Autowired
	RulesService rulesService;

	@RequestMapping(value = "/rules/list", method = RequestMethod.GET)
	public Object getRulesList() {
		return ParcelRuleProperties.getRuleList();
	}

	@RequestMapping(value = "/rules/update/{ruleName}", method = RequestMethod.PUT)
	public Object updateRule(@PathVariable(required = true) RuleEnum ruleName, RulesRequest ruleRequest) {
		try {
			return rulesService.updateRule(ruleName.getRuleName(), ruleRequest);
		} catch (Exception e) {
			RulesResponse error = new RulesResponse();
			error.setErrorMessage(e.getMessage());
			error.setIsSuccess(false);
			return error;
		}

	}
}
