package cn.com.conversant.weizi.crm.commons.log;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

/**
 * Function:APILogUtil用于面向Restful的返回类型json的API，
 *          info:打印API请求的正常逻辑,
 *               在API请求即将返回model的时候,打印请求参数和返回值
 *          error:打印API请求的异常逻辑,
 *               在请求request过程中有异常情况不能按照预期的返回response,
 *               交给springmvc拦截器[GlobalExceptionResolver]处理异常时打印错误信息日志
 */
public class APILogUtil {

    private static Logger apiLogger = LoggerFactory.getLogger("API_LOG");
    private static final ObjectMapper mapper = new ObjectMapper();
    private static Logger logger = LoggerFactory.getLogger(APILogUtil.class);

    /**
     *
     */
    public static void info(String apiUri,  Object input, Object output){
        try{
            Map customerMap = Maps.newHashMap();

            if(input == null){
                input = Maps.newHashMap();
            }
            if(output == null){
                output = Maps.newHashMap();
            }

            if(apiUri == null){
                apiUri = "";
            }

            apiLogger.info("{},{},{},{}",  apiUri, customerMap, toJson(input), toJson(output));
        }catch (Exception ex){
            logger.error("APILogUtil.log error", ex);
        }
    }

    public static void error(String module, String apiUri,  Object input, Object output){
        try{
            Map customerMap = Maps.newHashMap();

            if(input == null){
                input = Maps.newHashMap();
            }
            if(output == null){
                output = Maps.newHashMap();
            }
            if(module == null){
                module = "";
            }

            apiLogger.error("{},{},{},{},{}", module, apiUri, customerMap, toJson(input), toJson(output));
        }catch (Exception ex){
            logger.error("APILogUtil.log error", ex);
        }
    }

    private static String toJson(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (IOException e) {
            logger.warn("write to json string error:" + object, e);
            return null;
        }
    }


}
