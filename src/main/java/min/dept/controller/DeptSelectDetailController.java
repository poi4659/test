package min.dept.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import min.dept.control.Controller;
import min.dept.dao.DeptDAO;
import min.dept.dto.DeptDTO;
import min.dept.hander.DeptHandlerAdapter;

public class DeptSelectDetailController implements Controller{
	private static Log log = LogFactory.getLog(DeptSelectController.class);

	@Override
	public DeptHandlerAdapter execute(HttpServletRequest request, HttpServletResponse response) {
//		클라이언트가 전달한 요청에서 부서 번호 가져옴
		int deptno = Integer.parseInt(request.getParameter("deptno"));
		log.info(deptno);
		
//		부서 정보를 조회하는데 사용
		DeptDAO deptDAO = new DeptDAO();
		
//		부서 정보를 담는데 사용
		DeptDTO deptDTO = new DeptDTO();
		
//		전달받은 부서 번호를 사용하여 데이터베이스에서 부서 정보를 검색하고, 
//		해당 정보를 DeptDTO 객체로 반환
		deptDTO = deptDAO.deptSelect(deptno);
		log.info(deptDTO);
		
		/*
		 * request 객체에 deptDTO 객체를 속성으로 설정 
		 * ->부서 정보를 담고 있는 DeptDTO 객체를 뷰에서 사용할 수 있도록 함
		 */		
		request.setAttribute("deptDTO", deptDTO);
		
//		DeptHandlerAdapter 객체를 생성-컨트롤러가 실행된 후에 뷰로 이동할 때 사용될 객체
		DeptHandlerAdapter deptHandlerAdapter = new DeptHandlerAdapter();
		log.info("특정 부서 조회");
		
		/*
		 * DeptHandlerAdapter 객체의 경로를 설정
		 * 이 경로는 컨트롤러가 실행된 후에 이동할 뷰의 경로임
		 */
		deptHandlerAdapter.setPath("/WEB-INF/view/dept/dept_select_detail_view.jsp");
		
		/*
		 * DeptHandlerAdapter 객체를 반환 
		 * 이 객체는 컨트롤러가 실행된 후에 어떤 뷰로 이동할지를 나타내는 역할
		 */
		return deptHandlerAdapter;
	}

}
