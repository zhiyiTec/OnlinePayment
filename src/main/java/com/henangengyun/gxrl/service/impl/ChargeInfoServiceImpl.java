package com.henangengyun.gxrl.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.henangengyun.gxrl.mapper.ChargeInfoMapper;
import com.henangengyun.gxrl.mapper.SeasonFeeMapper;
import com.henangengyun.gxrl.po.ChargeInfo;
import com.henangengyun.gxrl.po.SeasonFee;
import com.henangengyun.gxrl.service.ChargeInfoService;

@Service
public class ChargeInfoServiceImpl implements ChargeInfoService {
	@Autowired
	private ChargeInfoMapper chargeInfoMapper;

	@Autowired
	private SeasonFeeMapper seasonFeeMapper;

	@Override
	public List<ChargeInfo> getChargeInfoByNo(String No) {
		return chargeInfoMapper.getChargeInfoByNo(No);
	}

	@Override
	public List<SeasonFee> getSeasonFeeByNo(String No) {
		return seasonFeeMapper.getSeasonFeeByNo(No);
	}

	@Override
	public void countTotalOwedFee(List<SeasonFee> list, ChargeInfo chargeInfo) {
		float total = 0;
		for (SeasonFee seasonFee : list) {
			total += seasonFee.getOwedFee();
		}
		chargeInfo.setTotalOwedFee(total);
	}

	@Override
	public float returnTotalFee(List<SeasonFee> list) {
		float total = 0;
		for (SeasonFee seasonFee : list) {
			total += seasonFee.getOwedFee();
		}
		return total;
	}

}
