package com.henangengyun.gxrl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.henangengyun.gxrl.po.Client;
import com.henangengyun.gxrl.service.ClientService;

@Controller
@RequestMapping("/server")
public class ClientController {

	@Autowired
	private ClientService clientService;

	@ResponseBody
	@RequestMapping(value = "/client", method = RequestMethod.GET)
	public Client getClientByNo(@RequestParam(value = "No", required = true) String No) {
		Client c = clientService.getClientByNo(No);
		return c;
	}

}
