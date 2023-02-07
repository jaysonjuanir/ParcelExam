package com.exam.parcel.service;

import com.exam.parcel.request.ParcelRequest;
import com.exam.parcel.response.ParcelResponse;

public interface ParcelService {

	public ParcelResponse calculateCostOfDelivery(ParcelRequest parcel) throws Exception;
}
