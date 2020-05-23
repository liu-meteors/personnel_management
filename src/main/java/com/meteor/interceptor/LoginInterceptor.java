package com.meteor.interceptor;

import com.meteor.pojo.Employee;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author ：liujingyu
 * @date ：Created in 2020/4/1 0:44
 * @description：登录拦截器
 * @modified By：
 * @version: 0.0.1$
 */
public class LoginInterceptor implements HandlerInterceptor {
    /**
     * Intercept the execution of a handler. Called after HandlerMapping determined
     * an appropriate handler object, but before HandlerAdapter invokes the handler.
     * <p>DispatcherServlet processes a handler in an execution chain, consisting
     * of any number of interceptors, with the handler itself at the end.
     * With this method, each interceptor can decide to abort the execution chain,
     * typically sending an HTTP error or writing a custom response.
     * <p><strong>Note:</strong> special considerations apply for asynchronous
     * request processing. For more details see
     * {@link AsyncHandlerInterceptor}.
     * <p>The default implementation returns {@code true}.
     *
     * @param request  current HTTP request
     * @param response current HTTP response
     * @param handler  chosen handler to execute, for type and/or instance evaluation
     * @return {@code true} if the execution chain should proceed with the
     * next interceptor or the handler itself. Else, DispatcherServlet assumes
     * that this interceptor has already dealt with the response itself.
     * @throws Exception in case of errors
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println(request.getHeader("Origin"));
        boolean isTrue=!"http://localhost:8081".equals(request.getHeader("Origin"));
        if (!"http://192.168.1.88:8081".equals(request.getHeader("Origin"))&&isTrue){
            System.out.println(request.getRequestURI());
            if (request.getRequestURI().contains("download")){

            }else {

                return false;
            }
        }
        System.out.println("token::::"+request.getHeader("token"));
        HttpSession session=request.getSession();

        String contextPath=session.getServletContext().getContextPath();
        String[] requireAuthPages = new String[]{
                "departmentInformation"
        };
        String uri = request.getRequestURI();
        uri=StringUtils.remove(uri,contextPath+"/");

        String page =uri;
        System.err.println("session:::::::"+session.getAttribute("user"));
        System.err.println(uri);
        if (begingWith(page,requireAuthPages)){
            Employee employee= (Employee) session.getAttribute("user");
            if (employee==null){
                response.sendRedirect("login");
                return false;
            }
        }
        return true;
    }

    private boolean begingWith(String page,String[] requireAuthPages){
        boolean result =false;
        for (String requireAuthPage : requireAuthPages){
            if(StringUtils.startsWith(page, requireAuthPage)) {
                result = true;
                break;
            }
        }
        return result;
    }
}
