package cm.offline.tv.model;

import java.io.Serializable;

/**
 * Copyright (C), 2015-2020, 湖南靠谱科技股份有限公司
 * FileName: SimpleResponse
 * Author: wangdakuan
 * Date: 2020-03-06 14:40
 * Description: 示例
 * History:
 * version：1.5
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
public class SimpleResponse implements Serializable {

    private static final long serialVersionUID = -1477609349345966116L;

    public int code;
    public String msg;

    public BaseResponse toResponse() {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.code = code;
        baseResponse.msg = msg;
        return baseResponse;
    }
}
