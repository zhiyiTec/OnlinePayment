package com.henangengyun.gxrl.service;

import java.text.ParseException;
import java.util.List;

import com.henangengyun.gxrl.po.SeasonFeeInfo;

public interface SeasonFeeInfoService {
	List<SeasonFeeInfo> getSeasonFeeInfoByNo(String No);
	
	void changeState(SeasonFeeInfo seasonFeeInfo);
	
	void countDelayFee(SeasonFeeInfo seasonFeeInfo) throws ParseException;

	void countOwedFee(SeasonFeeInfo seasonFeeInfo);
	
	
	

	
}
