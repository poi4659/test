package min.dept.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import min.dept.control.Controller;
import min.dept.dao.DeptDAO;
import min.dept.dto.DeptDTO;
import min.dept.hander.DeptHandlerAdapter;

public class DeptSelectController implements Controller{
	private static Log log = LogFactory.getLog(DeptSelectController.class);

	/*
	 * Controller 인터페이스에서 정의된 메서드를 재정의함 
	 * HttpServletRequest와 HttpServletResponse 객체를 매개변수로 받음
	 * 이 객체들은 HTTP 요청과 응답을 처리하는 데 사용
	 */	
	@Override
	public DeptHandlerAdapter execute(HttpServletRequest request, HttpServletResponse response) {
//		DeptDAO 객체 생성-데이터베이스에서 부서 정보를 조회하는데 사용
		DeptDAO deptDAO = new DeptDAO();
		
//		DeptDTO 객체 생성-조회한 부서 정보를 담는 데 사용
		DeptDTO deptDTO = new DeptDTO();
		log.info(deptDTO);
		
//		ArrayList<DeptDTO> 객체 생성-조회된 모든 부서 정보를 담는 데 사용
		ArrayList<DeptDTO> arrayList = new ArrayList<DeptDTO>();
		
//		deptSelectAll() 메서드를 호출하여 모든 부서 정보를 조회하고 그 결과를 arrayList에 할당
		arrayList = deptDAO.deptSelectAll();
		log.info(arrayList);
		
		/*
		 * 조회된 부서 정보를 HttpServletRequest 객체의 속성으로 설정
		 * 이렇게 하면 JSP 페이지에서 해당 정보에 접근할 수 있음
		 */
		request.setAttribute("arrayList", arrayList);
		
		/*
		 * DeptHandlerAdapter 객체를 생성하고 설정한 후에 반환
		 * 이 객체는 서블릿에서 포워드로 파라미터를 전송하고 
		 * 설정된 경로로 뷰를 전달하여 클라이언트에게 응답함
		 */
		DeptHandlerAdapter deptHandlerAdapter = new DeptHandlerAdapter();
		log.info("부서 정보 조회");

//		포워드로 파라미터 전송
		deptHandlerAdapter.setPath("/WEB-INF/view/dept/dept_select_view.jsp");		
		return deptHandlerAdapter;
	}

}
