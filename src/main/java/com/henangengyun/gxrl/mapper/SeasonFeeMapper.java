package com.henangengyun.gxrl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.henangengyun.gxrl.po.SeasonFee;

public interface SeasonFeeMapper {
	public List<SeasonFee> getSeasonFeeByNo(String No);
	public List<SeasonFee> getSeasonFeeByNoAndSeason(@Param("No")String No,@Param("HeatingSeason")String HeatingSeason);
}
