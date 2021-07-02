package com.koreait.mypage.dao;

import com.koreait.mypage.dto.MemberDTO;

public interface MemberDAO {
	
	public int insertMember(MemberDTO memberDTO);
	public MemberDTO selectOneMember(MemberDTO memberDTO);
	public int deleteMember(String id);
}
