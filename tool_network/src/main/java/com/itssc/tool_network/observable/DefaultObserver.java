package com.itssc.tool_network.observable;

import android.net.ParseException;
import android.widget.Toast;

import com.google.gson.JsonParseException;
import com.itssc.tool_network.R;
import com.itssc.tool_network.config.NetWorkErrorCode;
import com.itssc.tool_network.config.ServerResponseException;
import com.itssc.tool_network.utils.CommonUtil;

import org.json.JSONException;

import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.UnknownHostException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

/**
 * author：dachao on 2019-05-05 10:19
 */
public abstract class DefaultObserver<T> implements Observer<T> {
    @Override
    public void onSubscribe(Disposable d) {
    }

    @Override
    public void onNext(T response) {
        onSuccess(response);
        onFinish();
    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof HttpException) {
            onException(NetWorkErrorCode.BAD_NETWORK_ERROR);
        } else if (e instanceof ConnectException || e instanceof UnknownHostException) {
            onException(NetWorkErrorCode.CONNECT_FAILED_ERROR);
        } else if (e instanceof InterruptedIOException) {
            onException(NetWorkErrorCode.CONNECT_TIMEOUT_ERROR);
        } else if (e instanceof JsonParseException || e instanceof JSONException || e instanceof ParseException) {
            onException(NetWorkErrorCode.PARSE_DATA_ERROR);
        } else if (e instanceof ServerResponseException) {
            onFail(e.getMessage());
        } else {
            onException(NetWorkErrorCode.UNKNOWN_ERROR);
        }
        onFinish();
    }

    @Override
    public void onComplete() {

    }

    /**
     * 请求成功（errorCode == 200）
     *
     * @param response 服务器返回的数据
     */
    abstract public void onSuccess(T response);

    /**
     * 请求失败（errorCode != 200）
     * 服务器返回数据，但响应码不为200
     */
    public void onFail(String message) {
        Toast.makeText(CommonUtil.getApp(), message, Toast.LENGTH_SHORT).show();
    }

    public void onFinish() {

    }

    /**
     * 请求异常Toast提示
     *
     * @param reason
     */
    public void onException(int reason) {
        switch (reason) {
            case NetWorkErrorCode.CONNECT_FAILED_ERROR:
                Toast.makeText(CommonUtil.getApp(), R.string.connect_error, Toast.LENGTH_SHORT).show();
                break;
            case NetWorkErrorCode.CONNECT_TIMEOUT_ERROR:
                Toast.makeText(CommonUtil.getApp(), R.string.connect_timeout, Toast.LENGTH_SHORT).show();
                break;
            case NetWorkErrorCode.BAD_NETWORK_ERROR:
                Toast.makeText(CommonUtil.getApp(), R.string.bad_network, Toast.LENGTH_SHORT).show();
                break;
            case NetWorkErrorCode.PARSE_DATA_ERROR:
                Toast.makeText(CommonUtil.getApp(), R.string.parse_error, Toast.LENGTH_SHORT).show();
                break;
            case NetWorkErrorCode.UNKNOWN_ERROR:
            default:
                Toast.makeText(CommonUtil.getApp(), R.string.unknown_error, Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
