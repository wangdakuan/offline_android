package cm.offline.tv.model;

/**
 * Copyright (C), 2015-2020, 湖南靠谱科技股份有限公司
 * FileName: SizeReferenceModel
 * Author: wangdakuan
 * Date: 2020-03-13 16:05
 * Description: 参考尺寸
 * History:
 * version：1.0
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
public class SizeReferenceModel {

    public String name; //名称
    public String describe; //描述

    public SizeReferenceModel() {
    }

    public SizeReferenceModel(String name, String describe) {
        this.name = name;
        this.describe = describe;
    }
}
