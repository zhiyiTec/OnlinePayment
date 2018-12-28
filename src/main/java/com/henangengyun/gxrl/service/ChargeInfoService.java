package com.henangengyun.gxrl.service;

import java.util.List;

import com.henangengyun.gxrl.po.ChargeInfo;
import com.henangengyun.gxrl.po.SeasonFee;

public interface ChargeInfoService {
	List<ChargeInfo> getChargeInfoByNo(String No);

	List<SeasonFee> getSeasonFeeByNo(String No);

	void countTotalOwedFee(List<SeasonFee> list, ChargeInfo chargeInfo);

	float returnTotalFee(List<SeasonFee> list);

}
