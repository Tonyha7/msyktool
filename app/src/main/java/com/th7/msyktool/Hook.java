package com.th7.msyktool;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class Hook implements IXposedHookLoadPackage {
    String loginUserId;
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        if (loadPackageParam.packageName.equals("com.zdsoft.newsquirrel")) {
            XposedHelpers.findAndHookMethod("com.netease.nis.wrapper.MyApplication", loadPackageParam.classLoader,
                    "attachBaseContext", Context.class, new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            super.afterHookedMethod(param);
                            final Context context = (Context) param.args[0];
                            ClassLoader classLoader = context.getClassLoader();
                            XposedHelpers.findAndHookConstructor("com.zdsoft.newsquirrel.android.entity.LoginUser", classLoader, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, int.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, int.class, int.class, java.lang.String.class, int.class, int.class, int.class, new XC_MethodHook() {
                                @Override
                                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                    super.beforeHookedMethod(param);
                                    loginUserId = (String) param.args[0];
                                    //XposedBridge.log("th7's msyktool:"+loginUserId);
                                }
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                    super.afterHookedMethod(param);
                                }
                            });

                            XposedHelpers.findAndHookMethod("com.zdsoft.newsquirrel.android.util.PublicKeyUtil", classLoader, "signatureVerify", java.lang.String.class, new XC_MethodHook() {
                                @Override
                                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                    super.beforeHookedMethod(param);
                                }
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                    super.afterHookedMethod(param);
                                    //XposedBridge.log("th7's msyktool:"+(String) param.getResult());
                                    String result = loginUserId+":402881"+loginUserId+":"+System.currentTimeMillis();
                                    param.setResult(result);
                                }
                            });
                        }
                    });


        }
    }
}