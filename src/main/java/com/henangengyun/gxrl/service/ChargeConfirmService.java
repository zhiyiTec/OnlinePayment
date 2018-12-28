package com.henangengyun.gxrl.service;

import java.io.IOException;
import java.text.ParseException;

import com.henangengyun.gxrl.po.ConfirmInfo;

public interface ChargeConfirmService {
	void ChargeConfirm(ConfirmInfo confirmInfo) throws ParseException, IOException;

}
