package com.th7.msyktool;

import android.content.Context;

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
                            Context context = (Context) param.args[0];
                            ClassLoader classLoader = context.getClassLoader();
                            XposedHelpers.findAndHookConstructor("com.zdsoft.newsquirrel.android.entity.LoginUser", classLoader, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, int.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, int.class, int.class, java.lang.String.class, int.class, int.class, int.class, new XC_MethodHook() {
                                @Override
                                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                    super.beforeHookedMethod(param);
                                    loginUserId = (String) param.args[0];
                                    //XposedBridge.log("th7's msyktool:"+loginUserId);
                                }
                            });

                            XposedHelpers.findAndHookMethod("com.zdsoft.newsquirrel.android.util.PublicKeyUtil", classLoader, "signatureVerify", java.lang.String.class, new XC_MethodHook() {
                                @Override
                                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                    super.afterHookedMethod(param);
                                    String sign = (String) param.getResult();
                                    XposedBridge.log("th7's msyktool:SignDec:"+sign);
                                    String[] split = sign.split(":");
                                    String full_sign = split[1]+loginUserId;
                                    XposedBridge.log("th7's msyktool:FullSign:"+full_sign);
                                    //String result = loginUserId+":402881"+loginUserId+":"+System.currentTimeMillis();
                                    //param.setResult(result);
                                }
                            });

                            XposedHelpers.findAndHookMethod("com.zdsoft.newsquirrel.android.activity.student.homework.detail.dtk.StudentDtkHwDetailPresenter", classLoader, "setLongTime", long.class, new XC_MethodHook() {
                                @Override
                                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                    super.beforeHookedMethod(param);
                                    param.args[0]=0;
                                }
                            });

                            XposedHelpers.findAndHookMethod("com.zdsoft.newsquirrel.android.activity.student.homework.detail.StudentHomeworkDetailPresenter", classLoader, "setLongTime", long.class, new XC_MethodHook() {
                                @Override
                                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                    super.beforeHookedMethod(param);
                                    param.args[0]=0;
                                }
                            });

                            XposedHelpers.findAndHookMethod("com.zdsoft.newsquirrel.android.entity.Homework", classLoader, "setLongTime", long.class, new XC_MethodHook() {
                                @Override
                                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                    super.beforeHookedMethod(param);
                                    param.args[0]=0;
                                }
                            });

                            XposedHelpers.findAndHookMethod("com.zdsoft.newsquirrel.android.activity.student.errornotenew.systemexercise.SystemBrushExerciseDoPresenter", classLoader, "setLongTime", long.class, new XC_MethodHook() {
                                @Override
                                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                    super.beforeHookedMethod(param);
                                    param.args[0]=0;
                                }
                            });

                            XposedHelpers.findAndHookMethod("com.zdsoft.newsquirrel.android.activity.student.errornotenew.StudentErrorNoteDoPresenter", classLoader, "setLongTime", long.class, new XC_MethodHook() {
                                @Override
                                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                    super.beforeHookedMethod(param);
                                    param.args[0]=0;
                                }
                            });

                            XposedHelpers.findAndHookMethod("com.zdsoft.newsquirrel.android.entity.StudentHomeWork", classLoader, "setLongTime", long.class, new XC_MethodHook() {
                                @Override
                                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                    super.beforeHookedMethod(param);
                                    param.args[0]=0;
                                }
                            });

                        }
                    });


        }
    }
}
