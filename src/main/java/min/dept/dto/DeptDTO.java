package min.dept.dto;

public class DeptDTO {
	private int deptno;
	private String dname;
	private String loc;
	
//	부서 번호 호출
	public int getDeptno() {
		return deptno;
	}
	
//	부서 번호 수정하여 임시 저장
	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}
	
//	부서 이름 호출
	public String getDname() {
		return dname;
	}
	
//	부서 이름 수정하여 임시 저장
	public void setDname(String dname) {
		this.dname = dname;
	}

//	부서 위치 호출
	public String getLoc() {
		return loc;
	}

//	부서 위치 수정하여 임시 저장
	public void setLoc(String loc) {
		this.loc = loc;
	}

//	데이터 검증
	@Override
	public String toString() {
		return "DeptDTO [deptno=" + deptno + ", dname=" + dname + ", loc=" + loc + "]";
	}
	
}
