package com.thebo.framework.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.thebo.framework.constants.SysConstants;
import com.thebo.framework.util.SessionUtils;

@Controller
@RequestMapping("/{moduleName}/{submoduleName}")
public class BaseController {
	
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	@RequestMapping(produces="text/html")
    public String index(@PathVariable String moduleName, @PathVariable String submoduleName, Model model) {
	    model.addAttribute("moduleName", moduleName);
        model.addAttribute("submoduleName", submoduleName);
        model.addAttribute(SysConstants.USER_SESSION_KEY, SessionUtils.getUser());
        return moduleName + "." + submoduleName; 
    }
}
