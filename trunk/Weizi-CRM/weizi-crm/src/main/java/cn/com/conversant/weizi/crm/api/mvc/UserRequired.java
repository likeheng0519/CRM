/**
 * Copyright (c) 2012 Conversant Solutions. All rights reserved.
 *
 * Created on 14-3-3.
 */
package cn.com.conversant.weizi.crm.api.mvc;

import java.lang.annotation.*;

/**
 * Created with Swift CMS
 * User: huangli@conversant.com.cn
 * Date: 14-3-3
 * Time: 下午2:17
 * To change this template use File | Settings | File Templates.
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UserRequired {
}
