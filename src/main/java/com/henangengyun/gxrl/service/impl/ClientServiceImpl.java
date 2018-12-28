package com.henangengyun.gxrl.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.henangengyun.gxrl.mapper.ClientMapper;
import com.henangengyun.gxrl.po.Client;
import com.henangengyun.gxrl.service.ClientService;

@Service
public class ClientServiceImpl implements ClientService {

	
	@Autowired
	ClientMapper clientMapper;
	
	@Override
	public Client getClientByNo(String No) {
		return clientMapper.getClientByNo(No);
	}

	
	
	

}
