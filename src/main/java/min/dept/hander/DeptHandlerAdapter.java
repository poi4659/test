/*
 * Front Controller에서 각각의 컨트롤러가 
 * 처리한 결과를 담는 클래스인 DeptHandlerAdapter
 * 
 * redirect: 이 속성은 클라이언트에게 리다이렉트를 해야하는지 여부를 나타내는 boolean 값
 * 리다이렉트가 필요한 경우 true로 설정됨
 
 * path: 클라이언트로부터 요청을 처리한 후에 전달해야 할 뷰의 경로
 * 
 * 메서드 설명:
 * isRedirect(): 리다이렉트 여부를 반환
 * setRedirect(boolean redirect): 리다이렉트 여부를 설정
 * getPath(): 뷰의 경로를 반환
 * setPath(String path): 뷰의 경로를 설정
*/
package min.dept.hander;

public class DeptHandlerAdapter {
	private boolean redirect = false;
	
	private String path = null;
	
	public boolean isRedirect() {
		return redirect;
	}
	
	public void setRedirect() {
		this.redirect = redirect;
	}
	
	public String getPath() {
		return path;
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	
}
