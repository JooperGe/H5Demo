package com.mzwy.pinhu.utils;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.mzwy.pinhu.interfaces.PermissionInterface;

/**
 * Created by 张亚楠 on 2017/5/2.
 */

public class PermissionUtils {

    private static PermissionUtils permissionUtils;

    public static synchronized PermissionUtils getIncetance() {
        if (permissionUtils == null) {
            permissionUtils = new PermissionUtils();
        }
        return permissionUtils;
    }

    private boolean permissionBlean = Build.VERSION.SDK_INT >= 23;
    private PermissionInterface permissionInterface;

    public void RequstPermissionUtils(final Context context, String manifest, final PermissionInterface permissionInterface) {
        if (!permissionBlean) {
            Toast.makeText(context, "权限小于23", Toast.LENGTH_LONG).show();
            return;
        }
        if (Dexter.isRequestOngoing()) {
            return;
        }
        this.permissionInterface = permissionInterface;
        Dexter.checkPermission(new PermissionListener() {
            @Override
            public void onPermissionGranted(PermissionGrantedResponse response) {
                if (null != permissionInterface) {
                    permissionInterface.onSuccess();
                }
                new AlertDialog.Builder(context).setTitle("请允许获取存储空间")
                        .setMessage("无法获取此权限，不能正常工作")
                        .setNegativeButton("拒绝", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setPositiveButton("去设置", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent();
                                intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                                intent.setData(Uri.parse("package:" + context.getPackageName()));
                                intent.putExtra("cmp", "com.android.settings/.applications.InstalledAppDetails");
                                intent.addCategory("android.intent.category.DEFAULT");
                                context.startActivity(intent);
                                dialog.dismiss();
                            }
                        })
                        .show();
            }

            @Override
            public void onPermissionDenied(PermissionDeniedResponse response) {
                new AlertDialog.Builder(context).setTitle("请允许获取存储空间")
                        .setMessage("无法获取此权限，不能正常工作")
                        .setNegativeButton("拒绝", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setPositiveButton("去设置", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent();
                                intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                                intent.setData(Uri.parse("package:" + context.getPackageName()));
                                intent.putExtra("cmp", "com.android.settings/.applications.InstalledAppDetails");
                                intent.addCategory("android.intent.category.DEFAULT");
                                context.startActivity(intent);
                                dialog.dismiss();
                            }
                        })
                        .show();
            }

            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permission, final PermissionToken token) {
                new AlertDialog.Builder(context).setTitle("提示")
                        .setMessage("获取相册需要存储空间的权限，不开启将无法正常工作！")
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                token.cancelPermissionRequest();
                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                token.continuePermissionRequest();
                            }
                        })
                        .setOnDismissListener(new DialogInterface.OnDismissListener() {
                            @Override
                            public void onDismiss(DialogInterface dialog) {
                                token.cancelPermissionRequest();
                            }
                        })
                        .show();
            }
        }, manifest);
    }


}
