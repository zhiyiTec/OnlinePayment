package com.henangengyun.gxrl.service;

import java.util.List;

import com.henangengyun.gxrl.po.ChargeRecord;

public interface ChargeRecordService {
	public List<ChargeRecord> getChargeRecord(String No, String start, String end);


	void changePayMethod(ChargeRecord chargeRecord);
}
