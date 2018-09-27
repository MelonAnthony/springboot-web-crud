package per.wq.springboot.component;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Auther: Anthony
 * @Date: 2018/9/26 19:09
 * @Description:检查用户是否登录
 */
public class LoginHandlerInterceptor implements HandlerInterceptor{
    //目标方法执行之前
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object loginUser = request.getSession().getAttribute("loginUser");
        if(loginUser==null){
            //未登录,返回登录页面,使用请求转发
            //并创建错误消息
            request.setAttribute("msg","没有权限请先登录!");
            request.getRequestDispatcher("/index.html").forward(request,response);
           return  false;
        }else {
            //已登录，放行请求
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
