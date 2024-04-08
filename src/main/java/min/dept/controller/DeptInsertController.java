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

public class DeptInsertController implements Controller{
	private static Log log = LogFactory.getLog(DeptSelectController.class);


	@Override
	public DeptHandlerAdapter execute(HttpServletRequest request, HttpServletResponse response) {
//		클라이언트로부터 전달된 부서 정보(부서 번호, 부서 이름, 부서 위치)를 받음
		int deptno = Integer.parseInt(request.getParameter("deptno"));
		log.info(deptno);

		String dname = request.getParameter("dname");
		log.info(dname);
		
		String loc = request.getParameter("loc");
		log.info(loc);

//		DeptDAO 객체를 생성하여 데이터베이스와의 상호작용을 담당
		DeptDAO deptDAO = new DeptDAO();
		
//		새로운 DeptDTO 객체를 생성하여 부서 정보를 저장
		DeptDTO deptDTO = new DeptDTO();
		
		ArrayList<DeptDTO> arrayList = new ArrayList<DeptDTO>();
		
//		DeptDAO를 사용하여 이미 등록된 모든 부서 정보를 조회
		arrayList = deptDAO.deptSelectAll();
		log.info(arrayList);
		request.setAttribute("arrayList", arrayList);
		
		deptDTO.setDeptno(deptno);
		deptDTO.setDname(dname);
		deptDTO.setLoc(loc);
		
//		부서 정보를 데이터베이스에 등록하고 등록된 정보를 DeptDTO 객체에 저장
		deptDTO = deptDAO.deptInsert(deptDTO);
		log.info(deptDTO);
		
//		부서 정보가 등록된 후에 JSP 파일에서 해당 정보를 사용할 수 있도록 deptDTO 객체를 request의 속성으로 설정
		request.setAttribute("deptDTO", deptDTO);
		log.info("부서 정보 등록");
		
//		DeptHandlerAdapter 객체 생성, 이 객체는 JSP 파일의 경로를 설정하기 위해 사용
		DeptHandlerAdapter deptHandlerAdapter = new DeptHandlerAdapter( );

//		JSP 파일의 경로를 설정한 후 DeptHandlerAdapter 객체 생성하여 반환
		deptHandlerAdapter.setPath("/WEB-INF/view/dept/dept_insert_view.jsp");
		return deptHandlerAdapter;
	}

}
