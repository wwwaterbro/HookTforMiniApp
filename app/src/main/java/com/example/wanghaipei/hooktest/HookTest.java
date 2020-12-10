package com.example.wanghaipei.hooktest;



import de.robv.android.xposed.IXposedHookLoadPackage;

import de.robv.android.xposed.XC_MethodHook;

import de.robv.android.xposed.XposedBridge;

import de.robv.android.xposed.XposedHelpers;

import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class HookTest implements IXposedHookLoadPackage {

    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        //XposedBridge.log("11111111111" + loadPackageParam.processName+":"+loadPackageParam.packageName);

        if (loadPackageParam.packageName.equals("com.example.wanghaipei.hooktest")) {

            XposedBridge.log(" has Hooked!");

            Class clazz = loadPackageParam.classLoader.loadClass(

                    "com.example.wanghaipei.hooktest.MainActivity");

            XposedHelpers.findAndHookMethod(clazz, "toastMessage", new XC_MethodHook() {

                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {

                    super.beforeHookedMethod(param);

                    //XposedBridge.log(" has Hooked!");

                }

                protected void afterHookedMethod(MethodHookParam param) throws Throwable {

                    param.setResult("你已被劫持");

                }

            });

        }

    }

}