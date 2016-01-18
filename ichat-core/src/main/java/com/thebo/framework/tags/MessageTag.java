package com.thebo.framework.tags;

import com.thebo.framework.context.ContextUtils;
import com.thebo.framework.spring.property.ConstantsPlaceholderConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * Created by hebo on 2016-1-18.
 */
public class MessageTag extends TagSupport {

    @Override
    public int doStartTag() throws JspException {
        //内置一个pageContext对象，我们之前说到pageContext对象，它里面是封装了9个隐式对象
        HttpServletRequest request = (HttpServletRequest)this.pageContext.getRequest();
        JspWriter out = this.pageContext.getOut();
        ContextUtils.getBean(ConstantsPlaceholderConfigurer.class;
        String ip = request.getRemoteAddr();
        try {
            out.print(ip);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return super.doStartTag();
    }
}
