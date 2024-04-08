package min.dept.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import min.dept.control.Controller;
import min.dept.dao.DeptDAO;
import min.dept.dto.DeptDTO;
import min.dept.hander.DeptHandlerAdapter;

public class DeptUpdateViewController implements Controller{
	private static Log log = LogFactory.getLog(DeptSelectController.class);
	
	@Override
	public DeptHandlerAdapter execute(HttpServletRequest request, HttpServletResponse response) {
		int deptno = Integer.parseInt(request.getParameter("deptno"));
		log.info(deptno);
		String dname = request.getParameter("dname");
		log.info(dname);
		String loc = request.getParameter("loc");
		log.info(loc);
		
//		부서 정보를 수정하기 위해 데이터베이스에 접근하기 위한 DAO 객체를 생성
		DeptDAO deptDAO = new DeptDAO();
		
//		수정된 부서 정보를 담을 DeptDTO 객체를 생성
		DeptDTO deptDTO = new DeptDTO();
		
//		받아온 부서 번호, 부서 이름, 부서 지역을 deptDTO 객체에 설정
		deptDTO.setDeptno(deptno);
		deptDTO.setDname(dname);
		deptDTO.setLoc(loc);
		
//		 DAO 객체를 사용하여 부서 정보를 수정하고, 수정된 정보를 deptDTO 객체에 저장
		deptDTO = deptDAO.deptUpdate(deptDTO);
		
//		수정된 부서 정보를 포함한 DeptHandlerAdapter 객체를 생성
		DeptHandlerAdapter deptHandlerAdapter = new DeptHandlerAdapter( );
		
//		포워드로 파라미터를 전송
//		수정된 부서 정보를 담은 deptHandlerAdapter 객체의 경로를 설정
		deptHandlerAdapter.setPath("/WEB-INF/view/dept/dept_update_view.jsp");
		
//		수정된 부서 정보를 담은 deptHandlerAdapter 객체를 반환
		return deptHandlerAdapter;
	}

}
