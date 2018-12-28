package com.henangengyun.gxrl.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.henangengyun.gxrl.po.ChargeDetail;
import com.henangengyun.gxrl.po.ChargeInfo;
import com.henangengyun.gxrl.po.SeasonFee;
import com.henangengyun.gxrl.service.ChargeInfoService;
import com.henangengyun.gxrl.service.SeasonFeeInfoService;
import com.henangengyun.gxrl.service.SeasonFeeService;

@Controller
@RequestMapping("/server")
public class ChargeInfoController {

	@Autowired
	SeasonFeeService seasonFeeService;

	@Autowired
	ChargeInfoService chargeInfoService;

	@Autowired
	SeasonFeeInfoService seasonFeeInfoService;

	// 费用查询 
	// 缴费页面
	@ResponseBody
	@RequestMapping(value = "/chargeinfo", method = RequestMethod.GET)
	public List<ChargeInfo> chargeInfos(@RequestParam(value = "No") String No) throws ParseException {
		List<SeasonFee> seasonFees = seasonFeeService.getSeasonFeeByNo(No);
		List<ChargeInfo> chargeInfos = chargeInfoService.getChargeInfoByNo(No);
		for (SeasonFee seasonFee : seasonFees) {
			seasonFeeService.countDelayFee(seasonFee);
			seasonFeeService.countOwedFee(seasonFee);
		}
		for (ChargeInfo chargeInfo : chargeInfos) {
			chargeInfoService.countTotalOwedFee(seasonFees, chargeInfo);

		}

		return chargeInfos;
	}

	// 费用查询
	@ResponseBody
	@RequestMapping(value = "/seasonfeeinfo", method = RequestMethod.GET)
	public List<SeasonFee> seasonFeeInfos(@RequestParam(value = "No") String No,
			@RequestParam(value = "Season", required = false) String Season) throws ParseException {

		if (Season == null || Season.equals("")) {
			List<SeasonFee> seasonFees = seasonFeeService.getSeasonFeeByNo(No);
			for (SeasonFee seasonFee : seasonFees) {
				seasonFeeService.countDelayFee(seasonFee);
				seasonFeeService.countOwedFee(seasonFee);
				seasonFeeService.changeState(seasonFee);
			}
			return seasonFees;

		} else {
			List<SeasonFee> seasonFees = seasonFeeService.getSeasonFeeByNOAndSeason(No, Season);
			for (SeasonFee seasonFee : seasonFees) {
				seasonFeeService.countDelayFee(seasonFee);
				seasonFeeService.countOwedFee(seasonFee);
				seasonFeeService.changeState(seasonFee);
			}
			return seasonFees;
		}

	}

	// 费用查询
	@ResponseBody
	@RequestMapping(value = "/chargedetail", method = RequestMethod.GET)
	public List<ChargeDetail> chargeDetail(@RequestParam(value = "FeeId") long FeeId) {
		return seasonFeeService.getChargeDetailByFeeId(FeeId);
	}

}
