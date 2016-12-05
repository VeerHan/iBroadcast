package com.leihan.ibroadcast;

/**
 * Created by Leo on 2016/12/5.
 */

public class Constants {

    public static final int FILE_TYPE_IMG = 0x01;
    public static final int FILE_TYPE_ADD_ICON = 0x02;

    // attach state type define
    public static final byte FILE_STATE_DEFAULT = 0X00;  // 默认状态
    public static final byte FILE_STATE_LOADING = 0X01;  // 上传中
    public static final byte FILE_STATE_ERROR = 0X02;  // 上传失败

}
