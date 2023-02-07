package com.exam.parcel.rule;

public enum RuleEnum {

	REJECT("1", "Reject"), 
	HEAVY_PARCEL("2", "Heavy Parcel"), 
	SMALL_PARCEL("3", "Small Parcel"),
	MEDIUM_PARCEL("4", "Medium Parcel"), 
	LARGE_PARCEL("5", "Large Parcel");

	private String priority;
	private String ruleName;

	private RuleEnum(String priority, String ruleName) {
		this.priority = priority;
		this.ruleName = ruleName;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public String getPriority() {
		return priority;
	}

	public String getRuleName() {
		return ruleName;
	}

	public static RuleEnum findByRuleName(String ruleName) {
		for (RuleEnum r : values()) {
			if (r.ruleName.equals(ruleName)) {
				return r;
			}
		}
		return null;
	}

	public static RuleEnum findByEnumName(String name) {
		return RuleEnum.valueOf(name);
	}
}
