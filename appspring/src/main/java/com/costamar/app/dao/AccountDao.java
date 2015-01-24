package com.costamar.app.dao;

import java.util.List;

import com.costamar.app.model.Account;

public interface AccountDao {
	public Account listById(String user)throws Exception;
	public List<Account> getTicket(String query)throws Exception;
}
