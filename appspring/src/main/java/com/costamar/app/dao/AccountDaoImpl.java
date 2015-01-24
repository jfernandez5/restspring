package com.costamar.app.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.costamar.app.model.Account;

public class AccountDaoImpl implements AccountDao{
	
	private JdbcTemplate jdbcTemplate;
	
	private final AtomicLong counter = new AtomicLong();

	public AccountDaoImpl(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public Account listById(String user) {
		String query = "select Name, Addr2 from DBA.AccountId where AccountId= ? ";
        Account account = jdbcTemplate.queryForObject(query, new Object[]{user}, new RowMapper<Account>(){
        	@Override
            public Account mapRow(ResultSet rs, int rowNum)throws SQLException {
            	Account acc = new Account();
            	acc.setId(counter.incrementAndGet());
            	acc.setName(rs.getString(1));
            	acc.setAddress(rs.getString(2));
                return acc;
            }});
        return account;
	}

	@Override
	public List<Account> getTicket(String query) throws Exception {
		List<Account> accList = new ArrayList<Account>();
        List<Map<String,Object>> accRows = jdbcTemplate.queryForList(query);
        
        for(Map<String,Object> empRow : accRows){
        	Account acc = new Account();
        	acc.setId(counter.incrementAndGet());
        	acc.setName(String.valueOf(empRow.get("Traveler")));
        	acc.setAddress(String.valueOf(empRow.get("TotalCost")));
        	accList.add(acc);
        }
		return accList;
	}
	

}
