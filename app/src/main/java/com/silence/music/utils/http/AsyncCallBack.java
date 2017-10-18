package com.silence.music.utils.http;

/**
 * @author tinlone
 * @date 2017/10/17 0017.
 */

public interface AsyncCallBack<T> {

    void onSuccess(T result);

    void onFailed(String msg, int error_code);
}
