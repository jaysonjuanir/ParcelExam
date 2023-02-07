package com.exam.parcel.service.impl;

import org.springframework.stereotype.Service;

import com.exam.parcel.helper.VoucherAPIHelper;
import com.exam.parcel.request.ParcelRequest;
import com.exam.parcel.request.VoucherRequest;
import com.exam.parcel.response.ParcelResponse;
import com.exam.parcel.response.VoucherResponse;
import com.exam.parcel.rule.RuleEnum;
import com.exam.parcel.rule.RuleType;
import com.exam.parcel.service.ParcelService;
import com.exam.parcel.util.ParcelRuleProperties;
import com.exam.parcel.util.PropertiesUtil;

@Service
public class ParcelServiceImpl implements ParcelService {

	@Override
	public ParcelResponse calculateCostOfDelivery(ParcelRequest parcel) throws Exception {
		ParcelResponse response = new ParcelResponse();
		Double cost = getTotalCostOfDelivery(parcel);

		try {
			cost = getDiscountCostValue(cost, parcel);
			response.setMessage(
					PropertiesUtil.getPropertyValue(ParcelRuleProperties.PARCEL_VALID_VOUCHER_CODE_MESSAGE));
		} catch (Exception e) {
			response.setMessage(e.getMessage());
		}

		response.setCost(cost);
		response.setIsSuccess(true);

		return response;
	}

	private Double getTotalCostOfDelivery(ParcelRequest parcel) throws Exception {
		Double weightCost = 0d;
		Double volumeCost = 0d;
		Double volume = getParcelVolume(parcel);

		RuleType reject = ParcelRuleProperties.getRuleTypeByRuleName(RuleEnum.REJECT.getRuleName());
		RuleType heavyParcel = ParcelRuleProperties.getRuleTypeByRuleName(RuleEnum.HEAVY_PARCEL.getRuleName());
		RuleType smallParcel = ParcelRuleProperties.getRuleTypeByRuleName(RuleEnum.SMALL_PARCEL.getRuleName());
		RuleType mediumParcel = ParcelRuleProperties.getRuleTypeByRuleName(RuleEnum.MEDIUM_PARCEL.getRuleName());
		RuleType largeParcel = ParcelRuleProperties.getRuleTypeByRuleName(RuleEnum.LARGE_PARCEL.getRuleName());

		if (parcel.getWeight() > reject.getCondition()) {
			throw new Exception("Invalid Parcel: Weight exceeded limit of " + reject.getCondition());
		} else if (parcel.getWeight() > heavyParcel.getCondition()) {
			weightCost = heavyParcel.getCost() * parcel.getWeight();
		}

		if (volume < smallParcel.getCondition()) {
			volumeCost = smallParcel.getCost() * volume;
		} else if (volume < mediumParcel.getCondition()) {
			volumeCost = mediumParcel.getCost() * volume;
		} else if (volume > mediumParcel.getCondition() && parcel.getWeight() < reject.getCondition()) {
			volumeCost = largeParcel.getCost() * volume;
		}

		return weightCost + volumeCost;
	}

	private Double getParcelVolume(ParcelRequest parcel) {
		return parcel.getHeight() * parcel.getWidth() * parcel.getLength();
	}

	private Double getDiscountCostValue(Double cost, ParcelRequest parcel) throws Exception {
		VoucherRequest voucherRequest = new VoucherRequest();
		voucherRequest.setKey(PropertiesUtil.getPropertyValue(ParcelRuleProperties.VOUCHER_API_KEY));
		voucherRequest.setVoucherCode(parcel.getVoucherCode());

		String url = PropertiesUtil.getPropertyValue(ParcelRuleProperties.VOUCHER_API_BASE_URL)
				+ PropertiesUtil.getPropertyValue(ParcelRuleProperties.VOUCHER_API_DISCOUNT_ENDPOINT);
		VoucherResponse response = VoucherAPIHelper.sendGet(url, voucherRequest);

		if (response != null) {
			cost = cost - (cost * (response.getDiscount() / 100));
		}
		return cost;
	}
}
