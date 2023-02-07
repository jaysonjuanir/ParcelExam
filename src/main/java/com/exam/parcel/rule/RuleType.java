package com.exam.parcel.rule;

public class RuleType {

	private String priority;
	private String ruleName;
	private Double condition;
	private Double cost;
	private String costDescription;

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public Double getCondition() {
		return condition;
	}

	public void setCondition(Double condition) {
		this.condition = condition;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public String getCostDescription() {
		return costDescription;
	}

	public void setCostDescription(String costDescription) {
		this.costDescription = costDescription;
	}

}
