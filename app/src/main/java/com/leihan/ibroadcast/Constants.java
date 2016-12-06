package com.leihan.ibroadcast;

/**
 * Created by Leo on 2016/12/5.
 */

public class Constants {

    public static final int FILE_TYPE_IMG = 0x01;
    public static final int FILE_TYPE_ADD_ICON = 0x02;

    // attach state type define
    public static final byte FILE_STATE_NORMAL = 0X00;  // 默认状态，不显示角标
    public static final byte FILE_STATE_SUCCESS = 0X02;  // 上传成功，显示删除角标
    public static final byte FILE_STATE_LOADING = 0X01;  // 上传中，不显示角标
    public static final byte FILE_STATE_ERROR = 0X03;  // 上传失败，显示惊叹号角标

}
