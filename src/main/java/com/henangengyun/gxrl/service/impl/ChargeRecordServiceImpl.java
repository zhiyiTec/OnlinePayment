package com.henangengyun.gxrl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.henangengyun.gxrl.mapper.ChargeRecordMapper;
import com.henangengyun.gxrl.po.ChargeRecord;
import com.henangengyun.gxrl.service.ChargeRecordService;

@Service
public class ChargeRecordServiceImpl implements ChargeRecordService {
	@Autowired
	ChargeRecordMapper chargeRecordMapper;

	@Override
	public List<ChargeRecord> getChargeRecord(String No, String start, String end) {
		if (start == null) {
			start = "1970";
		}
		if (end == null) {
			end = "3000";
		}
		return chargeRecordMapper.getChargeRecord(No, start, end);
	}

	@Override
	public void changePayMethod(ChargeRecord chargeRecord) {
		switch (chargeRecord.getPayedMethod()) {
		case "1":
			chargeRecord.setPayedMethod("洛阳银行");
			break;
		case "2":
			chargeRecord.setPayedMethod("交通银行");
			break;
		case "3":
			chargeRecord.setPayedMethod("微信");
			break;
		case "4":
			chargeRecord.setPayedMethod("支付宝");
			break;
		default:
			chargeRecord.setPayedMethod("未知支付方式");
			break;
		}
	}

}
