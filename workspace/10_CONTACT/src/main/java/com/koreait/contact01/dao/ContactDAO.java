package com.koreait.contact01.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.koreait.contact01.dto.Contact;

public class ContactDAO {

	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private String sql;
	
	private DataSource dataSource;
	private static ContactDAO instance;
	
	private ContactDAO() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/oracle");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static ContactDAO getInstance() {
		if (instance == null) {
			instance = new ContactDAO();
		}
		return instance;
	}
	
	public void close(Connection con, PreparedStatement ps, ResultSet rs) {
		try {
			if (con != null) con.close();
			if (ps != null) ps.close();
			if (rs != null) rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Contact> selectContactList(){
		List<Contact> list = new ArrayList<>();
		try {
			con = dataSource.getConnection();
			sql = "SELECT NO, NAME, TEL, ADDR, EMAIL, NOTE FROM CONTACT";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Contact contact = new Contact();
				contact.setNo(rs.getLong(1));
				contact.setName(rs.getString(2));
				contact.setTel(rs.getString(3));
				contact.setAddr(rs.getString(4));
				contact.setEmail(rs.getString(5));
				contact.setNote(rs.getString(6));
				list.add(contact);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		return list;
	}
	
	public Contact selectContactByNo(long no) {
		Contact contact = null;
		try {
			con = dataSource.getConnection();
			sql = "SELECT NO, NAME, TEL, ADDR, EMAIL, NOTE FROM CONTACT WHERE NO = ?";
			ps = con.prepareStatement(sql);
			ps.setLong(1, no);
			rs = ps.executeQuery();
			if (rs.next()) {
				contact = new Contact();
				contact.setNo(rs.getLong(1));
				contact.setName(rs.getString(2));
				contact.setTel(rs.getString(3));
				contact.setAddr(rs.getString(4));
				contact.setEmail(rs.getString(5));
				contact.setNote(rs.getString(6));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		return contact;
	}
	
	public int insertContact(Contact contact) {
		int result = 0;
		try {
			con = dataSource.getConnection();
			sql = "INSERT INTO CONTACT VALUES (CONTACT_SEQ.NEXTVAL, ?, ?, ?, ?, ?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, contact.getName());
			ps.setString(2, contact.getTel());
			ps.setString(3, contact.getAddr());
			ps.setString(4, contact.getEmail());
			ps.setString(5, contact.getNote());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, null);
		}
		return result;
	}
	
	public int updateContact(Contact contact) {
		int result = 0;
		try {
			con = dataSource.getConnection();
			sql = "UPDATE CONTACT SET NAME = ?, TEL = ?, ADDR = ?, EMAIL = ?, NOTE = ? WHERE NO = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, contact.getName());
			ps.setString(2, contact.getTel());
			ps.setString(3, contact.getAddr());
			ps.setString(4, contact.getEmail());
			ps.setString(5, contact.getNote());
			ps.setLong(6, contact.getNo());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, null);
		}
		return result;
	}
	
	public int deleteContact(long no) {
		int result = 0;
		try {
			con = dataSource.getConnection();
			sql = "DELETE FROM CONTACT WHERE NO = ?";
			ps = con.prepareStatement(sql);
			ps.setLong(1, no);
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, null);
		}
		return result;
	}
	
}
