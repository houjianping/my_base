package com.androidapp.permission;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.androidapp.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

public class PermissionUtils {

    /**
     * 检测权限
     *
     * @return true：已授权； false：未授权；
     */
    public static boolean checkPermission(Context context, PermissionEnum permissionEnum) {
        return ContextCompat.checkSelfPermission(context, permissionEnum.getPermission()) == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * 检测多个权限
     *
     * @return 未授权的权限
     */
    public static List<PermissionEnum> checkMorePermissions(Context context, List<PermissionEnum> permissions) {
        List<PermissionEnum> permissionList = new ArrayList<>();
        for (int i = 0; i < permissions.size(); i++) {
            if (!checkPermission(context, permissions.get(i)))
                permissionList.add(permissions.get(i));
        }
        return permissionList;
    }

    /**
     * 请求权限
     */
    public static void requestPermission(Context context, PermissionEnum permissionEnum, int requestCode) {
        ActivityCompat.requestPermissions((Activity) context, new String[]{permissionEnum.getPermission()}, requestCode);
    }

    /**
     * 请求多个权限
     */
    public static void requestMorePermissions(Context context, List<PermissionEnum> permissionList, int requestCode) {
        String[] permissions = new String[permissionList.size()];
        for (int i = 0; i < permissionList.size(); i++) {
            permissions[i] = permissionList.get(i).getPermission();
        }
        requestMorePermissions(context, permissions, requestCode);
    }

    /**
     * 请求多个权限
     */
    public static void requestMorePermissions(Context context, String[] permissions, int requestCode) {
        ActivityCompat.requestPermissions((Activity) context, permissions, requestCode);
    }

    /**
     * 判断是否已拒绝过权限
     *
     * @return
     * @describe :如果应用之前请求过此权限但用户拒绝，此方法将返回 true;
     * -----------如果应用第一次请求权限或 用户在过去拒绝了权限请求，
     * -----------并在权限请求系统对话框中选择了 Don't ask again 选项，此方法将返回 false。
     */
    public static boolean judgePermission(Context context, String permission) {
        return ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, permission);
    }

    /**
     * 检测权限并请求权限：如果没有权限，则请求权限
     */
    public static void checkAndRequestPermission(Context context, PermissionEnum permission, int requestCode) {
        if (!checkPermission(context, permission)) {
            requestPermission(context, permission, requestCode);
        }
    }

    /**
     * 检测并请求多个权限
     */
    public static void checkAndRequestMorePermissions(Context context, List<PermissionEnum> permissions, int requestCode) {
        List<PermissionEnum> permissionList = checkMorePermissions(context, permissions);
        requestMorePermissions(context, permissionList, requestCode);
    }

    /**
     * 检测权限
     */
    public static void checkPermission(Context context, final PermissionEnum permissionEnum, PermissionCheckCallBack callBack) {
        if (checkPermission(context, permissionEnum)) { // 用户已授予权限
            callBack.onHasPermission();
        } else {
            if (judgePermission(context, permissionEnum.getPermission()))  // 用户之前已拒绝过权限申请
                callBack.onUserHasAlreadyTurnedDown(new ArrayList<PermissionEnum>() {{
                    add(permissionEnum);
                }});
            else                                       // 用户之前已拒绝并勾选了不在询问、用户第一次申请权限。
                callBack.onUserHasAlreadyTurnedDownAndDontAsk(new ArrayList<PermissionEnum>() {{
                    add(permissionEnum);
                }});
        }
    }

    /**
     * 检测多个权限
     */
    public static void checkMorePermissions(Context context, List<PermissionEnum> permissions, PermissionCheckCallBack callBack) {
        List<PermissionEnum> permissionList = checkMorePermissions(context, permissions);
        if (permissionList.size() == 0) {  // 用户已授予权限
            callBack.onHasPermission();
        } else {
            boolean isFirst = true;
            for (int i = 0; i < permissionList.size(); i++) {
                String permission = permissionList.get(i).getPermission();
                if (judgePermission(context, permission)) {
                    isFirst = false;
                    break;
                }
            }
            if (isFirst)// 用户之前已拒绝过权限申请
                callBack.onUserHasAlreadyTurnedDownAndDontAsk(permissionList);
            else       // 用户之前已拒绝并勾选了不在询问、用户第一次申请权限。
                callBack.onUserHasAlreadyTurnedDown(permissionList);

        }
    }

    /**
     * 检测并申请权限
     */
    public static void checkAndRequestPermission(Context context, PermissionEnum permission, int requestCode, PermissionRequestSuccessCallBack callBack) {
        if (checkPermission(context, permission)) {// 用户已授予权限
            callBack.onHasPermission();
        } else {
            requestPermission(context, permission, requestCode);
        }
    }

    /**
     * 检测并申请多个权限
     */
    public static void checkAndRequestPermissions(Context context, List<PermissionEnum> permissions, int requestCode, PermissionRequestSuccessCallBack callBack) {
        List<PermissionEnum> permissionList = checkMorePermissions(context, permissions);
        if (permissionList.size() == 0) {  // 用户已授予权限
            callBack.onHasPermission();
        } else {
            requestMorePermissions(context, permissionList, requestCode);
        }
    }

    /**
     * 判断权限是否申请成功
     */
    public static boolean isPermissionRequestSuccess(int[] grantResults) {
        return grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * 用户申请权限返回
     */
    public static void onRequestPermissionResult(Context context, final PermissionEnum permissionEnum, int[] grantResults, PermissionCheckCallBack callback) {
        if (PermissionUtils.isPermissionRequestSuccess(grantResults)) {
            callback.onHasPermission();
        } else {
            if (PermissionUtils.judgePermission(context, permissionEnum.getPermission())) {
                callback.onUserHasAlreadyTurnedDown(new ArrayList<PermissionEnum>() {{
                    add(permissionEnum);
                }});
            } else {
                callback.onUserHasAlreadyTurnedDownAndDontAsk(new ArrayList<PermissionEnum>() {{
                    add(permissionEnum);
                }});
            }
        }
    }

    /**
     * 用户申请多个权限返回
     */
    public static void onRequestMorePermissionsResult(Context context, List<PermissionEnum> permissions, PermissionCheckCallBack callback) {
        boolean isBannedPermission = false;
        List<PermissionEnum> permissionList = checkMorePermissions(context, permissions);
        if (permissionList.size() == 0)
            callback.onHasPermission();
        else {
            for (int i = 0; i < permissionList.size(); i++) {
                if (!judgePermission(context, permissionList.get(i).getPermission())) {
                    isBannedPermission = true;
                    break;
                }
            }
            //已禁止再次询问权限
            if (isBannedPermission)
                callback.onUserHasAlreadyTurnedDownAndDontAsk(permissions);
            else // 拒绝权限
                callback.onUserHasAlreadyTurnedDown(permissions);
        }
    }

    /**
     * 跳转到权限设置界面
     */
    public static void toAppSetting(Context context) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= 9) {
            intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent.setData(Uri.fromParts("package", context.getPackageName(), null));
        } else if (Build.VERSION.SDK_INT <= 8) {
            intent.setAction(Intent.ACTION_VIEW);
            intent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
            intent.putExtra("com.android.settings.ApplicationPkgName", context.getPackageName());
        }
        try {
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            ToastUtils.show("权限设置页面跳转失败!");
        }
    }

    public interface PermissionRequestSuccessCallBack {
        /**
         * 用户已授予权限
         */
        void onHasPermission();
    }

    public interface PermissionCheckCallBack {
        /**
         * 用户已授予权限
         */
        void onHasPermission();

        /**
         * 用户已拒绝过权限
         * @param permission:被拒绝的权限
         */
        void onUserHasAlreadyTurnedDown(List<PermissionEnum> permission);

        /**
         * 用户已拒绝过并且已勾选不再询问选项、用户第一次申请权限;
         * @param permission:被拒绝的权限
         */
        void onUserHasAlreadyTurnedDownAndDontAsk(List<PermissionEnum> permission);
    }

    public static String convertDesc(List<PermissionEnum> permissionEnums) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < permissionEnums.size(); i ++) {
            stringBuilder.append(permissionEnums.get(i).getPermissionName());
            if (i != (permissionEnums.size() - 1)) {
                stringBuilder.append(",");
            }
        }
        return stringBuilder.toString();
    }
}
