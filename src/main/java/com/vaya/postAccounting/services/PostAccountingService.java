package com.vaya.postAccounting.services;

import java.util.List;

import com.vaya.postAccounting.domain.PostAccounting;

public interface PostAccountingService {
	
	public List<PostAccounting> list();
	public List<PostAccounting> listByMember(Long id);
	public PostAccounting save(PostAccounting post);
	public PostAccounting get(Long id);
	public void delete(Long id);
}
