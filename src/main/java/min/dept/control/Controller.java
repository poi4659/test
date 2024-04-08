package min.dept.control;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import min.dept.hander.DeptHandlerAdapter;

public interface Controller {
//	비즈니스 요청을 수행하고 결괏값을 반환하는 공통 기능인 execute 메서드 설정
	public DeptHandlerAdapter execute(HttpServletRequest request, HttpServletResponse response);
}
