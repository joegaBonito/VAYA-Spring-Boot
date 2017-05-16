package com.vaya.general.domain;

public enum Role {
	/*
	 * GUEST - Can only see. 
	 * User - Can CRUD his/her own post. 
	 * ADMIN -Can CRUD all posts and members.
	 */
	GUEST, USER, ADMIN;
}
