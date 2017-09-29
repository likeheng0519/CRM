/**
 * Copyright (c) 2012 Conversant Solutions. All rights reserved.
 * <p/>
 * Created on 14-2-19.
 */
package cn.com.conversant.weizi.crm.api.exception;

import cn.com.conversant.commons.exception.BaseRuntimeException;
import cn.com.conversant.commons.http.ErrorResponse;
import cn.com.conversant.commons.rpc.RpcRuntimeException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.util.WebUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Locale;

/**
 * @author robin.ye
 */
public class HttpExceptionResolver implements HandlerExceptionResolver {
    private final static Logger logger = LoggerFactory.getLogger(HttpExceptionResolver.class);
    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
                                         Exception ex) {
        try {
            return handleException(ex, request, response, handler);
        } catch (Exception handlerException) {
            logger.error("handleBaseRuntimeException error", handlerException);
        }

        return null;
    }

    private ModelAndView handleException(Exception ex, HttpServletRequest request, HttpServletResponse response,
                                         Object handler) throws IOException {

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

        ErrorResponse returnValue = new ErrorResponse();
        String errorMessage = "";
        if (ex instanceof BaseRuntimeException) {
            returnValue.setCode(((BaseRuntimeException) ex).getStatusCode());
            errorMessage = getErrorMessage(request, ex);
        } else if(ex instanceof AuthenticationException){
            errorMessage = ex.getMessage();
        } else if (ex instanceof UnauthenticatedException) {
            returnValue.setCode(90001);
        }else {
            logger.error("server error", ex);
            returnValue.setCode(10001);
        }

        returnValue.setMessage(errorMessage);
        handleResponseBody(returnValue, request, response);

        return new ModelAndView();

    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private void handleResponseBody(ErrorResponse returnValue, HttpServletRequest request,
                                    HttpServletResponse response) throws HttpMessageNotWritableException, IOException {
        try {
            response.getWriter().write(jsonObject(returnValue));
            response.getWriter().flush();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    private static String jsonObject(Object o) {
        try {
            if (o == null) return "";
            StringWriter sw = new StringWriter();
            mapper.writeValue(sw, o);
            return sw.getBuffer().toString();
        } catch (IOException e) {
            logger.error("gson error", e);
        }
        return null;

    }

    private String getErrorMessage(HttpServletRequest request, Exception ex) {
        Locale locale = (Locale) WebUtils.getSessionAttribute(request, SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME);
        if (locale == null)

        {
            LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
            locale = localeResolver.resolveLocale(request);
        }
        String message = null;
        try {
            message = ex.getMessage();
        } catch (Exception e) {

        }

        if (StringUtils.isBlank(message)) {
            message = ex.getMessage();
        }

        return message;
    }

}