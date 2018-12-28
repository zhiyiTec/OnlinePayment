package com.henangengyun.gxrl.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.henangengyun.gxrl.Commons.Constant;
import com.henangengyun.gxrl.mapper.ChargeDetailMapper;
import com.henangengyun.gxrl.mapper.SeasonFeeMapper;
import com.henangengyun.gxrl.po.ChargeDetail;
import com.henangengyun.gxrl.po.SeasonFee;
import com.henangengyun.gxrl.service.SeasonFeeService;

@Service
public class SeasonFeeServiceImpl implements SeasonFeeService {
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

	@Autowired
	private SeasonFeeMapper seasonFeeMapper;

	@Autowired
	private ChargeDetailMapper chargeDetailMapper;

	@Override
	public List<SeasonFee> getSeasonFeeByNo(String No) {
		return seasonFeeMapper.getSeasonFeeByNo(No);
	}

	@Override
	public List<SeasonFee> getSeasonFeeByNOAndSeason(String No, String Season) {
		return seasonFeeMapper.getSeasonFeeByNoAndSeason(No, Season);
	}

	@Override
	public List<ChargeDetail> getChargeDetailByFeeId(long FeeId) {
		return chargeDetailMapper.getChargeDetailByFeeId(FeeId);
	}

	@Override
	public void countDelayFee(SeasonFee seasonFee) throws ParseException {
		switch (seasonFee.getState()) {
		case "0":
			judgeState(seasonFee);
			break;
		case "1":
			judgeState(seasonFee);
			break;
		case "2":
			seasonFee.setDelayFee(0f);
			break;
		case "3":
			judgeState(seasonFee);
			break;
		case "4":
			judgeState(seasonFee);
			break;
		case "5":
			seasonFee.setDelayFee(0f);
			break;
		}

	}

	@Override
	public void countOwedFee(SeasonFee seasonFee) {
		seasonFee.setOwedFee(seasonFee.getDelayFee() + seasonFee.getSeasonOwedFee());

	}

	@Override
	public void changeState(SeasonFee seasonFee) {
		switch (seasonFee.getState()) {
		case "0":
			seasonFee.setState("预收欠费");
			break;
		case "1":
			seasonFee.setState("预收欠其它费用");
			break;
		case "2":
			seasonFee.setState("预收已交清");
			break;
		case "3":
			seasonFee.setState("结算欠费");
			break;
		case "4":
			seasonFee.setState("结算欠其它费用");
			break;
		case "5":
			seasonFee.setState("结清");
			break;
		default:
			seasonFee.setState("未知状态");
			break;
		}

	}

	public void judgeState(SeasonFee seasonFee) throws ParseException {
		// 滞纳金公式比率
		float ratio = Constant.LATE_FEE_RATIO;
		Date today = new Date();
		Date date = formatter.parse(seasonFee.getLateFeeStartTime());
		int day = (int) Math.ceil(((today.getTime() - date.getTime()) / (1000 * 3600 * 24)));
		if (day <= 0) {
			seasonFee.setDelayFee(0f);
			return;
		}
		float count = day * ratio * seasonFee.getSeasonOwedFee();
		count = (int) (count * 10) / 10.0f;
		seasonFee.setDelayFee(count);

	}

	@Override
	public float returnDelayFee(SeasonFee seasonFee) throws ParseException {
		switch (seasonFee.getState()) {
		case "0":
			return returnJudgeState(seasonFee);
		case "1":
			return 0f;
		case "2":
			return returnJudgeState(seasonFee);
		case "3":
			return returnJudgeState(seasonFee);
		case "4":
			return returnJudgeState(seasonFee);
		case "5":
			return 0f;
		}
		return 0;
	}

	public float returnJudgeState(SeasonFee seasonFee) throws ParseException {
		// 滞纳金公式比率
		float ratio = Constant.LATE_FEE_RATIO;
		Date today = new Date();
		Date date = formatter.parse(seasonFee.getLateFeeStartTime());
		int day = (int) Math.ceil(((today.getTime() - date.getTime()) / (1000 * 3600 * 24)));
		if (day <= 0) {
			seasonFee.setDelayFee(0f);
			return 0f;
		}
		float count = day * ratio * seasonFee.getSeasonOwedFee();
		count = (int) (count * 10) / 10.0f;
		return count;

	}

}
