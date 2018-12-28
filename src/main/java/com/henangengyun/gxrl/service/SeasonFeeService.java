package com.henangengyun.gxrl.service;

import java.text.ParseException;
import java.util.List;

import com.henangengyun.gxrl.po.ChargeDetail;
import com.henangengyun.gxrl.po.SeasonFee;
import com.henangengyun.gxrl.po.SeasonFeeInfo;

public interface SeasonFeeService {

	List<SeasonFee> getSeasonFeeByNo(String No);
	List<SeasonFee> getSeasonFeeByNOAndSeason(String No,String Season);
	
	List<ChargeDetail> getChargeDetailByFeeId(long FeeId);

	void countDelayFee(SeasonFee seasonFee) throws ParseException;

	void countOwedFee(SeasonFee seasonFee);

	void changeState(SeasonFee seasonFee);
	
	float returnDelayFee(SeasonFee seasonFee) throws ParseException;
	
}



