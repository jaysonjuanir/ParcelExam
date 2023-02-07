package com.exam.parcel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.exam.parcel.request.ParcelRequest;
import com.exam.parcel.response.ParcelResponse;
import com.exam.parcel.service.ParcelService;

@RestController
public class ParcelController {

	@Autowired
	private ParcelService parcelService;

	@RequestMapping(value = "/parcel/calculateCost", method = RequestMethod.GET)
	public Object calculateCost(ParcelRequest parcel) {
		try {
			return parcelService.calculateCostOfDelivery(parcel);
		} catch (Exception e) {
			e.printStackTrace();
			ParcelResponse errorResponse = new ParcelResponse(e.getMessage(), false);
			return errorResponse;
		}
	}
}
