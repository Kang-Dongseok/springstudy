package com.koreait.contact03.dao;

import java.util.List;

import com.koreait.contact03.dto.Contact;

public interface ContactDAO {

	// 1.list
	public List<Contact> selectContactList();
	
	// 2. view
	public Contact selectContactByNo(long no);
		
	// 3. insert
	public int insertContact(Contact contact);
	
	// 4. update
	public int updateContact(Contact contact);

	// 5. delete
	public int deleteContact(long no);
	
}