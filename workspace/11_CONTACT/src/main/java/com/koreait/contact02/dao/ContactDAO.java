package com.koreait.contact02.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.koreait.contact02.dto.Contact;

public class ContactDAO {

	@Autowired
	private JdbcTemplate template;
	private String sql;
	
	// 1.list
	public List<Contact> selectContactList(){
		sql = "SELECT NO, NAME, TEL, ADDR, EMAIL, NOTE FROM CONTACT";
		return template.query(sql, new BeanPropertyRowMapper<Contact>(Contact.class));
	}
	
	// 2. view
	public Contact selectContactByNo(long no) {
		sql = "SELECT NO, NAME, TEL, ADDR, EMAIL, NOTE FROM CONTACT WHERE NO = ?";
		return template.queryForObject(sql, new BeanPropertyRowMapper<Contact>(Contact.class), no);
	}
	
	// 3. insert
	public int insertContact(Contact contact) {
		sql = "INSERT INTO CONTACT VALUES (CONTACT_SEQ.NEXTVAL, ?, ?, ?, ?, ?)";
		return template.update(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, contact.getName());
				ps.setString(2, contact.getTel());
				ps.setString(3, contact.getAddr());
				ps.setString(4, contact.getEmail());
				ps.setString(5, contact.getNote());
			}
		});
	}
	
	// 4. update
	public int updateContact(Contact contact) {
		return template.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				sql = "UPDATE CONTACT SET NAME = ?, TEL = ?, ADDR = ?, EMAIL = ?, NOTE = ? WHERE NO = ?";
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, contact.getName());
				ps.setString(2, contact.getTel());
				ps.setString(3, contact.getAddr());
				ps.setString(4, contact.getEmail());
				ps.setString(5, contact.getNote());
				ps.setLong(6, contact.getNo());
				return ps;
			}
		});
	}

	// 5. delete
	public int deleteContact(long no) {
		sql = "DELETE FROM CONTACT WHERE NO = ?";
		return template.update(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setLong(1, no);
			}
		});
	}
	
}