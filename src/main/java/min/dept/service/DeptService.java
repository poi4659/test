package min.dept.service;

import java.util.ArrayList;

import min.dept.dto.DeptDTO;

public interface DeptService {
//	전체 데이터 조회
	public ArrayList<DeptDTO> deptSelectAll();
	
//	특정 데이터 조회
	public DeptDTO deptSelect(int deptno);

//	데이터 입력
	public DeptDTO deptInsert(DeptDTO deptDTO);
	
//	데이터 수정
	public DeptDTO deptUpdate(DeptDTO deptDTO);
	
//	데이터 삭제
	public DeptDTO deptDelete(int deptno);
}
