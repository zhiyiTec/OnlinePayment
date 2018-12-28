package com.henangengyun.gxrl.controller;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.henangengyun.gxrl.Commons.Constant;
import com.henangengyun.gxrl.po.ConfirmInfo;
import com.henangengyun.gxrl.service.ChargeConfirmService;

@Controller
@RequestMapping("/server")
public class ChargeConfirmController {
	@Autowired
	ChargeConfirmService chargeConfirmService;

	@RequestMapping(value = "/chargeconfirm", method = RequestMethod.GET)
	public void chargeConfirm(@RequestBody final ConfirmInfo confirmInfo, HttpServletRequest request,
			HttpServletResponse response) throws ParseException, IOException {
		if (!request.getRemoteAddr().equals(Constant.IP)) {
			response.sendError(404);
			return;
		}
		chargeConfirmService.ChargeConfirm(confirmInfo);
	}
}
