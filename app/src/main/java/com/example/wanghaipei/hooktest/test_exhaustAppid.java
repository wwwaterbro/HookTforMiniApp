package com.example.wanghaipei.hooktest;

import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import android.support.v4.util.Pair;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;


public class test_exhaustAppid implements IXposedHookLoadPackage {



    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        // hook的APP包名
        final String currenPkg = lpparam.packageName;

        // 判断是否hook的是支付宝对应的包
        if (currenPkg.equals("com.eg.android.AlipayGphone")) {
            // 创建一个存储Object类型的HashSet集合
            final HashSet<Object> appModels = new HashSet<>();
            // 找到AppInfoManagerImpl类
            final Class<?> AppInfoManagerImpl = XposedHelpers.findClass("com.alipay.mobile.nebulax.resource.biz.appinfo.AppInfoManagerImpl", lpparam.classLoader);
            // 找到AppModel类
            final Class<?> AppModel = XposedHelpers.findClass("com.alibaba.ariver.resource.api.models.AppModel", lpparam.classLoader);
            // 找到AppInfoQuery类
            final Class<?> AppInfoQuery = XposedHelpers.findClass("com.alibaba.ariver.resource.api.models.AppInfoQuery", lpparam.classLoader);
            // 得到AppInfoManagerImpl类下的成员方法getAppModel(),该成员方法所需参数为AppInfoQuery类实例
            final Method getAppModel = AppInfoManagerImpl.getMethod("getAppModel", AppInfoQuery);

            // 找到ResourceManagerImpl类
            final Class<?> ResourceManagerImpl = XposedHelpers.findClass("com.alipay.mobile.nebulax.resource.biz.appinfo.ResourceManagerImpl", lpparam.classLoader);
            // 找到PackageDownloadCallback类
            Class<?> PackageDownloadCallback = XposedHelpers.findClass("com.alibaba.ariver.resource.api.PackageDownloadCallback", lpparam.classLoader);
            // 找到PackageInstallCallback类
            Class<?> PackageInstallCallback = XposedHelpers.findClass("com.alibaba.ariver.resource.api.PackageInstallCallback", lpparam.classLoader);

            // 得到ResourceManagerImpl类下的成员方法downloadApp(),该方法所需的参数为AppModel类，boolean，PackageDownloadCallback类
            final Method downloadApp = ResourceManagerImpl.getMethod("downloadApp", AppModel, boolean.class, PackageDownloadCallback);
            // 找到ResourceManagerImpl类下的成员方法installApp(),该方法所需的参数为AppModel类，PackageInstallCallback类
            final Method installApp = ResourceManagerImpl.getMethod("installApp", AppModel, PackageInstallCallback);
            // 找到DownloadStep类
            final Class<?> DownloadStep = XposedHelpers.findClass("com.alibaba.ariver.resource.prepare.steps.DownloadStep", lpparam.classLoader);
            // 找到BasePrepareStep类
            final Class<?> BasePrepareStep = XposedHelpers.findClass("com.alibaba.ariver.resource.prepare.steps.BasePrepareStep", lpparam.classLoader);

            // 找到RequestUtils类
            final Class<?> RequestUtils = XposedHelpers.findClass("com.alipay.mobile.nebulax.resource.biz.appinfo.RequestUtils", lpparam.classLoader);

            // 找到NebulaAppUpdater类
            final Class<?> NebulaAppUpdater = XposedHelpers.findClass("com.alipay.mobile.nebulax.resource.biz.appinfo.NebulaAppUpdater", lpparam.classLoader);
            // 找到UpdateAppParam类
            final Class<?> UpdateAppParam = XposedHelpers.findClass("com.alibaba.ariver.resource.api.appinfo.UpdateAppParam", lpparam.classLoader);
            // 找到UpdateAppCallback类
            final Class<?> UpdateAppCallback = XposedHelpers.findClass("com.alibaba.ariver.resource.api.appinfo.UpdateAppCallback", lpparam.classLoader);
            // 找到NebulaAppUpdater类下的成员方法a(private的),a方法所需参数为UpdateAppParam类、UpdateAppCallback类、Set类
            final Method updater = NebulaAppUpdater.getDeclaredMethod("a", UpdateAppParam, UpdateAppCallback, Set.class);
            // 取消对private修饰的方法的访问检查
            updater.setAccessible(true);

            // 找到AppRes类
            final Class<?> AppRes = XposedHelpers.findClass("com.alipay.mobile.nebulax.resource.api.appinfo.AppRes", lpparam.classLoader);

            for(Method method: NebulaAppUpdater.getDeclaredMethods()){
                if( method.getName().equals("a") && method.getParameterTypes().length==4){
                    XposedBridge.hookMethod(method, new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            super.beforeHookedMethod(param);
                            XposedBridge.log("小程序更新了");
                            Log.d("whatsthis","打印调用栈");
                            //打印updateAppParam的Pair Map AppInfoScene UpdateMode

                            new Exception().printStackTrace();
                        }

                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            super.afterHookedMethod(param);
                            XposedBridge.log("更新结束了");
                        }
                    });
                }
            }

            for(Method method: UpdateAppParam.getDeclaredMethods()){
                if(method.getName().equals("getRequestMainPackage")){
                    XposedBridge.hookMethod(method, new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            super.beforeHookedMethod(param);
                            Log.d("UpdateAppParam","hook到getRequestMainPackage了！");
                        }

                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            super.afterHookedMethod(param);
                            Log.d("UpdateAppParam","getRequestMainPackage结束了！");
                            Pair res =(Pair) param.getResult();
                            //todo:res的内容要怎么打印呢
                        }
                    });
                }
            }


        }
    }
}
