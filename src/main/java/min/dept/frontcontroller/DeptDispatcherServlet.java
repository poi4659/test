package min.dept.frontcontroller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import min.dept.control.Controller;
import min.dept.controller.DeptDeleteController;
import min.dept.controller.DeptInsertController;
import min.dept.controller.DeptSelectController;
import min.dept.controller.DeptSelectDetailController;
import min.dept.controller.DeptUpdateController;
import min.dept.controller.DeptUpdateViewController;
import min.dept.hander.DeptHandlerAdapter;

/**
 * Servlet implementation class DeptDispatcherServlet
 */
//web.xml에서 서블릿을 설정했으므로 @WebServlet 어노테이션 설정 안함
public class DeptDispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static Log log = LogFactory.getLog(DeptDispatcherServlet.class);
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{		
//		서블릿이 클라이언트로부터 받은 HTTP 요청 URL 처리
		
//		클라이언트가 보낸 요청의 URI를 가져옴
		// URL에서 포트 번호 다음부터 마지막 문자열인 http://localhost/컨텍스트명/매핑명으로 반환
		String requestURI = request.getRequestURI();

		/*
		 * 현재 웹 애플리케이션의 컨텍스트 경로 가져옴 
		 * 컨텍스트 경로는 웹 애플리케이션의 루트 경로를 의미함
		 * URL에서 /(슬래시)를 첨부한 컨텍스트 이름인 /컨텍스트명/매핑명을 반환
		 */
		String contextPath = request.getContextPath();
		
		/*
		 * 요청 URI에서 컨텍스트 경로를 제외한 부분을 추출함
		 * ->실제 서블릿 매핑명을 얻을 수 있음
		 */
		String pathURL = requestURI.substring(contextPath.length());
		
//		서블릿 매핑명을 로그에 출력
		log.info("매핑명 조회 - " + pathURL);
		
//		서블릿에서 사용할 DeptHandlerAdapter 객체를 초기화
		DeptHandlerAdapter deptHandlerAdapter = null;
		
//		서블릿에서 사용할 Controller 객체를 초기화
		Controller controller = null;
		
//		전체 부서 조회의 매핑명 설정
//		클라이언트로 받은 HTTP 요청 URL인 pathURL가 /DeptSelect.do와 일치한다면
		if (pathURL.equals("/DeptSelect.do")) {
//			DeptSelectController를 생성하여 controller 변수에 할당
			controller = new DeptSelectController();
			
			/*
			 * controller의 execute 메서드를 호출함 
			 * 해당 메서드는 클라이언트 요청을 처리하고 결과를 나타내는 객체인 
			 * deptHandlerAdapter를 반환함
			 */
			deptHandlerAdapter = controller.execute(request, response);
			
//			부서 조회 성공 확인-서버의 로그 파일에 기록됨
			log.info("부서 조회 확인 - " + deptHandlerAdapter);
			
//		상세 부서 조회의 매핑명 설정
//		클라이언트로부터 받은 요청 URL을 확인-"/DeptSelectDetail.do"와 일치하는지 확인
		} else if (pathURL.equals("/DeptSelectDetail.do")) {
			/*
			 * "/DeptSelectDetail.do"와 일치하는 요청이 있으면 
			 * DeptSelectDetailController 객체를 생성
			 */
			controller = new DeptSelectDetailController();
			
			/*
			 * 해당 컨트롤러의 execute 메서드를 호출하여 요청을 처리
			 * 클라이언트로부터 받은 요청과 응답 객체를 매개변수로 전달
			 */
			deptHandlerAdapter = controller.execute(request, response);
			
//			상세 부서 조회 성공 확인-서버의 로그 파일에 기록됨
			log.info("상세 부서 조회 확인 - " + deptHandlerAdapter);
			
//		부서 등록 뷰의 매핑명 설정
		} else if (pathURL.equals("/DeptInsertView.do")) {
			/*
			 * 새로운 DeptHandlerAdapter 객체를 생성
			 * DeptHandlerAdapter는 요청에 대한 처리 결과를 나타내는 객체
			 */
			deptHandlerAdapter = new DeptHandlerAdapter();
			
			/*
			 * 생성된 deptHandlerAdapter 객체의 경로를 
			 * "/WEB-INF/view/dept/dept_insert.jsp"로 설정
			 * 클라이언트에게 직접 노출되지 않아 보안성에 이점 있음
			 */
			deptHandlerAdapter.setPath("/WEB-INF/view/dept/dept_insert.jsp");
			log.info("부서 등록 화면 뷰 확인 - " + deptHandlerAdapter);
			
//		부서 등록의 매핑명 설정
//		클라이언트가 요청한 URL(pathURL)이 /DeptInsert.do와 일치한다면
		} else if (pathURL.equals("/DeptInsert.do")) {
//			DeptInsertController라는 컨트롤러 객체를 생성
			controller = new DeptInsertController();
			
//			컨트롤러의 execute() 메서드를 호출하여 부서 등록 작업을 수행
//			부서 등록 작업이 완료되면, 해당 결과를 나타내는 DeptHandlerAdapter 객체가 반환됨
			deptHandlerAdapter = controller.execute(request, response);
			
//			부서 등록 확인-서버의 로그 파일에 기록됨
			log.info("부서 등록 확인 - " + deptHandlerAdapter);

//		부서 수정 뷰의 매핑명 설정
//		클라이언트가 요청한 URL이 "/DeptUpdateView.do"인지 확인
		} else if (pathURL.equals("/DeptUpdateView.do")) {
			/*
			 * 만약 일치한다면, 부서 정보를 수정하는 데 사용될 컨트롤러인 
			 * DeptUpdateViewController의 인스턴스를 생성
			 */
			controller = new DeptUpdateViewController();
			
			/*
			 *  생성된 컨트롤러의 execute 메서드를 호출하여 요청을 처리하고, 
			 *  결과를 나타내는 DeptHandlerAdapter 객체를 받음
			 */
			deptHandlerAdapter = controller.execute(request, response);
			
//			부서 수정 화면 뷰 확인
			log.info("부서 수정 화면 뷰 확인 - " + deptHandlerAdapter);
			
//		부서 수정의 매핑명 설정
//		클라이언트가 요청한 URL이 "/DeptUpdate.do"인지 확인
		} else if (pathURL.equals("/DeptUpdate.do")) {
			/*
			 * 일치한다면, 부서 정보를 수정하는 데 사용될 컨트롤러인 
			 * DeptUpdateController의 인스턴스를 생성
			 */
			controller = new DeptUpdateController();
			
			
			/*
			 * 생성된 컨트롤러의 execute 메서드를 호출하여 요청을 처리하고,
			 * 결과를 나타내는 DeptHandlerAdapter 객체를 받음
			*/
			deptHandlerAdapter = controller.execute(request, response);
			
//			DeptHandlerAdapter 객체에 대한 정보가 기록됨
			log.info("부서 수정 확인 - " + deptHandlerAdapter);
			
//		부서 삭제 뷰의 매핑명 설정
//		클라이언트가 보낸 요청 URL이 "/DeptDeleteView.do"인지 확인
		} else if (pathURL.equals("/DeptDeleteView.do")) {
//			부서 삭제 화면의 경로를 설정하기 위해 DeptHandlerAdapter 객체를 생성
			deptHandlerAdapter = new DeptHandlerAdapter();
			
			/*
			 * 생성된 deptHandlerAdapter 객체의 경로를
			 * "/WEB-INF/view/dept/dept_delete.jsp"로 설정
			 */
			deptHandlerAdapter.setPath("/WEB-INF/view/dept/dept_delete.jsp");
			
//			서버의 로그에 부서 삭제 화면의 경로를 기록
			log.info("부서 삭제 화면 뷰 확인 - " + deptHandlerAdapter);
			
//		부서 삭제의 매핑명 설정
//		클라이언트가 보낸 요청 URL이 "/DeptDelete.do"인지 확인
		} else if (pathURL.equals("/DeptDelete.do")) {
			/*
			 * 클라이언트의 요청을 처리할 컨트롤러 객체를 생성
			 * 여기서는 부서를 삭제하는 기능을 처리하는 DeptDeleteController를 생성
			 */
			controller = new DeptDeleteController();
			
			/*
			 * 생성된 컨트롤러의 execute 메서드를 호출하여 클라이언트 요청을 처리 
			 * 부서 삭제 작업이 수행되고, 결과를 나타내는 DeptHandlerAdapter 객체를 반환
			 */
			deptHandlerAdapter = controller.execute(request, response);
			
//			부서 삭제 작업이 성공적으로 수행되었는지를 로그에 기록
			log.info("부서 삭제 확인 - " + deptHandlerAdapter);
		}
		
//		isRedirect 메서드 값이 false이면 포워드 방식으로 처리하고 true면 리다이렉트로 처리
		if (deptHandlerAdapter != null) {
			if (deptHandlerAdapter.isRedirect()) {
				response.sendRedirect(deptHandlerAdapter.getPath());
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(deptHandlerAdapter.getPath());
				dispatcher.forward(request, response);
			}
		}
	}
	
	/*
	 * 클라이언트로부터 GET 또는 POST 요청이 들어왔을 때 모두 service 메서드를 호출하도록 함 
	 * protected 쓴 이유: doGet 및 doPost 메서드를 재정의한 서브 클래스에서 
	 * service 메서드에 접근할 수 있어야 함
	 * 이를 가능하게 하기 위해 service 메서드를 protected로 선언하여 
	 * 재정의한 서브 클래스에서 접근할 수 있도록 한 것
	 */	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request, response);
	}
	
}
