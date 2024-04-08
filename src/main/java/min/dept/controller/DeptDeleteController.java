package min.dept.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import min.dept.control.Controller;
import min.dept.dao.DeptDAO;
import min.dept.dto.DeptDTO;
import min.dept.hander.DeptHandlerAdapter;

public class DeptDeleteController implements Controller{
	private static Log log = LogFactory.getLog(DeptSelectController.class);


	@Override
	public DeptHandlerAdapter execute(HttpServletRequest request, HttpServletResponse response) {
		int deptno = Integer.parseInt(request.getParameter("deptno"));
		log.info(deptno);
		
//		부서 정보를 데이터베이스에서 조작하기 위해 DeptDAO 클래스의 인스턴스를 생성
		DeptDAO deptDAO = new DeptDAO();

//		부서 정보를 담을 DeptDTO 클래스의 인스턴스를 생성
		DeptDTO deptDTO = new DeptDTO();

//		DTO에 부서 번호를 설정
		deptDTO.setDeptno(deptno);
		
//		 JSP 페이지에서 사용할 부서 정보를 request 객체의 속성으로 설정
		request.setAttribute("deptDTO", deptDTO);
		
		/*
		 * DAO를 사용하여 해당 부서를 데이터베이스에서 삭제함
		 * 이때, 삭제된 부서의 정보를 다시 DTO에 저장함
		 */
		deptDTO = deptDAO.deptDelete(deptno);
		
//		삭제된 부서의 정보를 로그에 기록
		log.info(deptDTO);
		
//		부서 삭제 작업 결과를 나타내는 DeptHandlerAdapter 객체를 생성
		DeptHandlerAdapter deptHandlerAdapter = new DeptHandlerAdapter( );

		// 포워드로 파라미터를 전송한다.
//		부서 삭제 결과를 표시할 JSP 페이지의 경로를 설정
		deptHandlerAdapter.setPath("/WEB-INF/view/dept/dept_delete_view.jsp");
		
//		부서 삭제 작업 결과를 담은 DeptHandlerAdapter 객체를 반환
		return deptHandlerAdapter;
	}

}
