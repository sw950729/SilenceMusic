package com.silence.music.utils.http;

/**
 * @author tinlone
 * @date 2017/10/17 0017.
 */

public interface AsyncCallBack<T> {

    /**
     * 成功数据回调
     *
     * @param result 解析后的实体或数据
     */
    void onSuccess(T result);

    /**
     * 失败后的错误回调
     *
     * @param msg       错误信息
     * @param errorCode 错误码
     */
    void onFailed(String msg, int errorCode);
}
