package cn.tedu.note.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet implements HandlerInterceptor {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HandlerInterceptor#postHandle(HttpServletRequest, HttpServletResponse, Object, ModelAndView)
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
        System.out.println("preHandle...");  
        String requestUri = request.getRequestURI();  
        String contextPath = request.getContextPath();  
        String url = requestUri.substring(contextPath.length());  
        System.out.println("requestUri" + requestUri);  
        System.out.println("contextPath" + contextPath);  
        System.out.println("url" + url); 
        if(url.indexOf("login")>=0 || url.indexOf("user/save")>=0){
        	return true;
        }
        Cookie cookies[] = request.getCookies();
        //初始化会话id
        String talkid = null;
        int i=0;
        //从cookie中取值，与session中的值对比，如果存在，就可以访问
        while(talkid == null && i<cookies.length)
        {  
            Cookie cookie = cookies[i];  
            talkid = (String) request.getSession().getAttribute(cookie.getName());
            i++;
        }
        if(null == talkid){  
            // 跳转到登录页面  
        	response.sendRedirect(contextPath+"/log_in.html"); 
        	return false;
        }  
        else{  
            return true;  
        }
         
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		 System.out.println("postHandle...");  
	        if(modelAndView != null){  
	            Map<String, String> map = new HashMap<String, String>();  
	            modelAndView.addAllObjects(map);  
	        }  
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("afterCompletion...");  
    }  


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
