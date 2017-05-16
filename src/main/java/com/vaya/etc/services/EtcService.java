package com.vaya.etc.services;

import java.util.List;

import com.vaya.etc.domain.Etc;

public interface EtcService {
	public List<Etc> etcList();
	public void save(Etc etc);
}
