package com.henangengyun.gxrl.mapper;

import java.util.List;

import com.henangengyun.gxrl.po.ChargeInfo;

public interface ChargeInfoMapper {
	List<ChargeInfo> getChargeInfoByNo(String No);
}
