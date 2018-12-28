package com.henangengyun.gxrl.service;

import org.springframework.stereotype.Service;

import com.henangengyun.gxrl.po.Client;

@Service
public interface ClientService {

	public abstract Client getClientByNo(String No);
	
}
