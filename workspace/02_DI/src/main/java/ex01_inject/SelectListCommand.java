package ex01_inject;

import javax.inject.Inject;

public class SelectListCommand {

	// 1. 필드를 이용해서 주입하기
	/*
	@Inject
	private Dao dao;
	*/
	
	
	// 2. 생성자를 이용해서 주입하기
	/*
	private Dao dao;
	
	@Inject
	public SelectListCommand(Dao dao) {  // 매개변수 Dao dao에 주입된다.
		this.dao = dao;
	}
	*/
	
	// 3. setter를 이용해서 주입하기
	/*
	private Dao dao;
	
	@Inject
	public void setDao(Dao dao) {
		this.dao = dao;
	}
	*/
	
	// field
	private Dao dao;
	
	// setter
	@Inject
	public void setDao(Dao dao) {
		this.dao = dao;
	}
	
	public void execute() {
		dao.selectList();  // dao가 자동으로 주입되지 않았다면 NullPointerException 발생
	}
	
	
}
