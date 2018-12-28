package com.henangengyun.gxrl.mapper;

import org.apache.ibatis.annotations.Param;

public interface ChargeConfirmMapper {

	void insertChargeRecord(@Param("Season") String Season, @Param("ClientId") long ClientId,
			@Param("ClientNo") String ClientNo, @Param("PayedFee") float PayedFee, @Param("DelayFee") float DelayFee,
			@Param("PayedTime") String PayedTime, @Param("SeasonFeeId") long SeasonFeeId,
			@Param("StreamNo") String StreamNo);

}
