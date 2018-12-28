package com.henangengyun.gxrl.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.henangengyun.gxrl.Commons.Constant;
import com.henangengyun.gxrl.mapper.SeasonFeeInfoMapper;
import com.henangengyun.gxrl.po.SeasonFeeInfo;
import com.henangengyun.gxrl.service.SeasonFeeInfoService;

@Service
public class SeasonFeeInfoServiceImpl implements SeasonFeeInfoService {
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

	@Autowired
	private SeasonFeeInfoMapper seasonFeeInfoMapper;
	
	@Override
	public List<SeasonFeeInfo> getSeasonFeeInfoByNo(String No) {
		return seasonFeeInfoMapper.getSeasonFeeInfoByNo(No);
	}

	@Override
	public void changeState(SeasonFeeInfo SeasonFeeInfo) {
		switch (SeasonFeeInfo.getState()) {
		case "0":
			SeasonFeeInfo.setState("预收欠费");
			break;
		case "1":
			SeasonFeeInfo.setState("预收欠其它费用");
			break;
		case "2":
			SeasonFeeInfo.setState("预收已交清");
			break;
		case "3":
			SeasonFeeInfo.setState("结算欠费");
			break;
		case "4":
			SeasonFeeInfo.setState("结算欠其它费用");
			break;
		case "5":
			SeasonFeeInfo.setState("结清");
			break;
		default:
			SeasonFeeInfo.setState("未知状态");
			break;
		}

	}

	@Override
	public void countDelayFee(SeasonFeeInfo seasonFeeInfo) throws ParseException {
		switch (seasonFeeInfo.getState()) {
		case "0":
			judgeState(seasonFeeInfo);
			break;
		case "1":
			judgeState(seasonFeeInfo);
			break;
		case "2":
			seasonFeeInfo.setDelayFee(0f);
			break;
		case "3":
			judgeState(seasonFeeInfo);
			break;
		case "4":
			judgeState(seasonFeeInfo);
			break;
		case "5":
			seasonFeeInfo.setDelayFee(0f);
			break;
		}

	}

	@Override
	public void countOwedFee(SeasonFeeInfo seasonFeeInfo) {
		seasonFeeInfo.setOwedFee(seasonFeeInfo.getDelayFee() + seasonFeeInfo.getSeasonOwedFee());

	}
	
	public void judgeState(SeasonFeeInfo seasonFeeInfo) throws ParseException {
		// 滞纳金公式比率
		float ratio = Constant.LATE_FEE_RATIO;
		Date today = new Date();
		Date date = formatter.parse(seasonFeeInfo.getLateFeeStartTime());
		int day = (int) Math.ceil(((today.getTime() - date.getTime()) / (1000 * 3600 * 24)));
		if (day <= 0) {
			seasonFeeInfo.setDelayFee(0f);
			return;
		}
		float count = day * ratio * seasonFeeInfo.getSeasonOwedFee();
		count = (int) (count * 10) / 10.0f;
		seasonFeeInfo.setDelayFee(count);

	}

}
