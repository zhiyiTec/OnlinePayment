package com.henangengyun.gxrl.mapper;

import java.util.List;

import com.henangengyun.gxrl.po.ChargeDetail;

public interface ChargeDetailMapper {
	List<ChargeDetail> getChargeDetailByFeeId(long FeeId);

}
