package com.costamar.app.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.costamar.app.dao.AccountDao;
import com.costamar.app.model.Account;

@RestController
public class AccountController {
	
	@Autowired
	private AccountDao accountDao;
	
	
	@RequestMapping(value="myrest", method = RequestMethod.GET)
	public Account retrieve(@RequestParam(value="user",defaultValue="0100154057")String user){
		Account account = new Account();;
		try {
			account = accountDao.listById(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return account;
	}
	
	@RequestMapping(value="qrest", method = RequestMethod.POST)
	public ArrayList<Account> retrieveAll(@RequestParam(value="query",
			defaultValue="select top 3 Traveler, TotalCost from DBA.Invoice where AccountId='0100154057'")String query) throws Exception{
		
		ArrayList<Account> list = new ArrayList<Account>();
		try {
			list = (ArrayList<Account>) accountDao.getTicket(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	

}
