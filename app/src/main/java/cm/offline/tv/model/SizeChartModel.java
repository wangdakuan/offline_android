package cm.offline.tv.model;

/**
 * Copyright (C), 2015-2020, 湖南靠谱科技股份有限公司
 * FileName: SizeChartModel
 * Author: wangdakuan
 * Date: 2020-03-13 16:03
 * Description: 尺码表
 * History:
 * version：
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
public class SizeChartModel {

    public String name; //名称
    public String key_s;
    public String key_m;
    public String key_l;
    public String key_xl;
    public String key_xxl;
    public String key_xxxl;

    public SizeChartModel() {
    }

    public SizeChartModel(String name, String key_s, String key_m, String key_l, String key_xl, String key_xxl, String key_xxxl) {
        this.name = name;
        this.key_s = key_s;
        this.key_m = key_m;
        this.key_l = key_l;
        this.key_xl = key_xl;
        this.key_xxl = key_xxl;
        this.key_xxxl = key_xxxl;
    }
}
