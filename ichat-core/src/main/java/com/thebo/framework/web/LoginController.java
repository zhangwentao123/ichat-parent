package com.thebo.framework.web;

import com.thebo.framework.constants.Constants;
import com.thebo.framework.constants.SysConstants;
import com.thebo.framework.dto.LoginResponse;
import com.thebo.framework.dto.ResponseEntity;
import com.thebo.framework.exception.DataCommitException;
import com.thebo.framework.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    protected final Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private LoginService loginService;

//	@RequestMapping(value = "home", method=RequestMethod.GET)
//	public String index() {
//		return "home";
//	}

    @ResponseBody
    @RequestMapping(value = "login")
    public LoginResponse login(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               @RequestParam("loginIp") String loginIp,
                               HttpServletRequest request, HttpServletResponse httpResponse) {
        LoginResponse response = null;
        try {
            response = loginService.login(username, password ,loginIp,request,httpResponse);
        } catch (DataCommitException e) {
            response = new LoginResponse();
            response.setStatus(Constants.System.FAIL);
            response.setError(Constants.System.PERMISSION_DENIED);
            response.setMsg(e.getMessage());
            logger.error(e.getMessage(), e);
        }
        return response;
    }

    @RequestMapping(value = "/logout")
    public ResponseEntity logout(@RequestParam("userId") String userId,
                                 @RequestParam("loginIp") String loginIp,
                                 HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.setAttribute(SysConstants.USER_SESSION_KEY, null);
        session.setAttribute(SysConstants.USER_ROLES_SESSION_KEY, null);
        session.setAttribute(SysConstants.USER_RESOURCES_SESSION_KEY, null);

        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(SysConstants.USER_SESSION_KEY)) {
                cookie.setMaxAge(0);
                cookie.setPath("/");
                response.addCookie(cookie);
                break;
            }
        }
        try {
            loginService.logout(userId,loginIp);
        } catch (DataCommitException e) {
            logger.error(e.getMessage(), e);
        }
        ResponseEntity resp = new ResponseEntity();
        resp.setStatus(Constants.System.OK);
        return resp;
    }
}
