package min.dept.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import min.dept.control.Controller;
import min.dept.dao.DeptDAO;
import min.dept.dto.DeptDTO;
import min.dept.hander.DeptHandlerAdapter;

public class DeptUpdateController implements Controller{
	private static Log log = LogFactory.getLog(DeptSelectController.class);

	
	@Override
	public DeptHandlerAdapter execute(HttpServletRequest request, HttpServletResponse response) {
//		클라이언트로부터 받은 요청에서 부서 번호를 추출
		int deptno = Integer.parseInt(request.getParameter("deptno"));
		log.info(deptno);
		System.out.println(deptno);
		
//		부서 정보를 처리하기 위한 DeptDAO 클래스의 인스턴스를 생성
		DeptDAO deptDAO = new DeptDAO();
		
//		부서 정보를 담을 DeptDTO 클래스의 인스턴스를 생성
		DeptDTO deptDTO = new DeptDTO();
		
		/*
		 * DAO를 사용하여 부서 번호에 해당하는 부서 정보를 데이터베이스에서 선택 
		 * 주어진 부서 번호를 사용하여 데이터베이스에서 해당 부서 정보를 검색한 다음에, 
		 * 그 정보를 DeptDTO 객체에 저장하여 반환
		 */
		deptDTO = deptDAO.deptSelect(deptno);
		
		/*
		 * 검색된 부서 정보를 deptDTO라는 이름으로 request에 저장
		 * ->JSP 페이지에서 이 정보를 사용할 수 있음
		*/
		request.setAttribute("deptDTO", deptDTO);
		
//		요청 처리 결과를 나타내는 DeptHandlerAdapter 객체를 생성
		DeptHandlerAdapter deptHandlerAdapter = new DeptHandlerAdapter();
		log.info("특정 부서 조회");
		
		// 포워드로 파라미터를 전송한다. 
		deptHandlerAdapter.setPath("/WEB-INF/view/dept/dept_update.jsp");
		
//		요청 처리 결과인 deptHandlerAdapter 객체를 반환
		return deptHandlerAdapter;
	}

}
