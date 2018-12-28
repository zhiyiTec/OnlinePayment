package com.henangengyun.gxrl.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.henangengyun.gxrl.po.SeasonFeeInfo;
import com.henangengyun.gxrl.service.SeasonFeeInfoService;

@Controller
@RequestMapping("/server")
public class FeeInfoController {
	@Autowired
	SeasonFeeInfoService seasonFeeInfoService;
	

	//缴费页面
	@ResponseBody
	@RequestMapping(value = "feeinfo", method = RequestMethod.GET)
	public List<SeasonFeeInfo> feeinfo(@RequestParam(value = "No") String no) throws ParseException {
		
		List<SeasonFeeInfo>seasonFeeInfos = seasonFeeInfoService.getSeasonFeeInfoByNo(no);
		
		for (SeasonFeeInfo seasonFeeInfo : seasonFeeInfos) {
			seasonFeeInfoService.countDelayFee(seasonFeeInfo);
			seasonFeeInfoService.countOwedFee(seasonFeeInfo);
			seasonFeeInfoService.changeState(seasonFeeInfo);
			
		}
		
		return seasonFeeInfos;
	}
}
