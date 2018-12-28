package com.henangengyun.gxrl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.henangengyun.gxrl.po.SeasonFeeInfo;

public interface SeasonFeeInfoMapper {
	List<SeasonFeeInfo> getSeasonFeeInfoByNo(String No);

	/*SeasonFeeInfo getSeasonFeeByNoAndSeason(@Param("No") String No, @Param("season") String season);*/
}
