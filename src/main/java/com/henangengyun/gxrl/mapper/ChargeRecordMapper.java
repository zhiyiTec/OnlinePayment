package com.henangengyun.gxrl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.henangengyun.gxrl.po.ChargeRecord;

public interface ChargeRecordMapper {
	List<ChargeRecord> getChargeRecord(@Param("No") String No, @Param("start") String start, @Param("end") String end);
}
