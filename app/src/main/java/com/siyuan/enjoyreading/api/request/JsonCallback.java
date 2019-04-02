package com.siyuan.enjoyreading.api.request;

import android.net.Uri;

import com.androidapp.utils.DeviceUtil;
import com.androidapp.utils.ManifestUtil;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.request.base.Request;
import com.siyuan.enjoyreading.App;
import com.siyuan.enjoyreading.R;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Response;
import okhttp3.ResponseBody;

public abstract class JsonCallback<T> extends AbsCallback<T> {

    private Type type;
    private Class<T> clazz;

    protected JsonCallback() {
    }

    public JsonCallback(Type type) {
        this.type = type;
    }

    public JsonCallback(Class<T> clazz) {
        this.clazz = clazz;
    }

    /**
     * 主要用于在所有请求之前添加公共的请求头或请求参数
     * 例如登录授权的 token
     * 使用的设备信息
     * 可以随意添加,也可以什么都不传
     * 还可以在这里对所有的参数进行加密，均在这里实现
     *
     * @param request
     */
    @Override
    public void onStart(Request<T, ? extends Request> request) {
        super.onStart(request);
        request.params("lat", "000", true);
        request.params("lng", "000", true);
        request.params("time", System.currentTimeMillis(), true);
        request.params("os-version", DeviceUtil.getOSVersion(), true);
        request.params("version", ManifestUtil.getVersionCode(App.getInstance()), true);
        request.params("device-name", DeviceUtil.getMobileName(), true);
        request.params("client", "android", true);
        request.params("app-name", App.getInstance().getString(R.string.appname), true);
        request.params("market", Uri.encode(ManifestUtil.getApplicationMetaData(App.getInstance(), "CHANNEL")), true);
        request.params("token", "AAAA", true);
    }

    @Override
    public T convertResponse(Response response) throws Throwable {
        if (type == null) {
            if (clazz == null) {
                Type genType = getClass().getGenericSuperclass();
                type = ((ParameterizedType) genType).getActualTypeArguments()[0];
            }
        }
        ResponseBody body = response.body();
        if (body == null) return null;
        T data = null;
        Gson gson = new Gson();
        JsonReader jsonReader = new JsonReader(body.charStream());
        if (type != null) data = gson.fromJson(jsonReader, type);
        if (clazz != null) data = gson.fromJson(jsonReader, clazz);
        return data;
    }
}
