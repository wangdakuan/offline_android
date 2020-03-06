package cm.offline.tv.model;

import java.io.Serializable;

/**
 * Copyright (C), 2015-2020, 湖南靠谱科技股份有限公司
 * FileName: BaseResponse
 * Author: wangdakuan
 * Date: 2020-03-06 14:38
 * Description: 数据格式基类
 * History:
 * version：1.5
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
public class BaseResponse <T> implements Serializable {

    private static final long serialVersionUID = 5213230387175987834L;

    public int code; //code码
    public String msg; //提示
    public T data; //数据

    @Override
    public String toString() {
        return "LzyResponse{\n" +//
                "\tcode=" + code + "\n" +//
                "\tmsg='" + msg + "\'\n" +//
                "\tdata=" + data + "\n" +//
                '}';
    }
}
