package com.henangengyun.gxrl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.henangengyun.gxrl.po.ChargeRecord;
import com.henangengyun.gxrl.service.ChargeRecordService;

@Controller
@RequestMapping("/server")
public class ChargeRecordController {
	// 控制器依赖于service
	@Autowired
	ChargeRecordService chargeRecordService;

	@ResponseBody
	@RequestMapping(value = "/record", method = RequestMethod.GET)
	public List<ChargeRecord> chargeRecords(@RequestParam(value = "No", required = true) String No,
			@RequestParam(value = "start", required = false) String start,
			@RequestParam(value = "end", required = false) String end) {
		List<ChargeRecord> chargeRecords = chargeRecordService.getChargeRecord(No, start, end);
		for (ChargeRecord chargeRecord : chargeRecords) {
			chargeRecordService.changePayMethod(chargeRecord);
		}
		return chargeRecords;

	}
}
