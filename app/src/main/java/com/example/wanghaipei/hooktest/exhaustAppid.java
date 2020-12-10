package com.example.wanghaipei.hooktest;


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

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;




public class exhaustAppid implements IXposedHookLoadPackage {
    static String appIDList[] = {"2018000042646284", "2018000067834467", "2018000000346010", "2018000063206065", "2018000010533694", "2018000171721752", "2019032863713538", "2018000109037095", "2018000197063320", "2018000144417173", "2018000257395698", "2018000288865062", "2018000265852739", "2018000265885678", "2018000258854367", "2018000307338590", "2018000345508330", "2018000390773614", "2018000389052653", "2018000396952025", "2018000464728250", "2018000424957503", "2018000451733692", "2018000452267852", "2018000426863886", "2018000527689103", "2018000594062055", "2018000599791933", "2018000533013743", "2018000579744555", "2018000687586362", "2018000684854700", "2018000683361793", "2018000688400960", "2018000649602218", "2018000799561873", "2018000736000925", "2018000797124778", "2018000790477768", "2018000757554482", "2018000817676766", "2018000879784057", "2018000821200159", "2018000895032013", "2018000833733819", "2018000916983194", "2018000914853863", "2018000968421877", "2018000976642939", "2018000937084275", "2018001060251186", "2018001063537802", "2018001027174628", "2021001102638386", "2018001080786053", "2018001194766799", "2018001129261656", "2018001156379794", "2018001141871692", "2018001185497612", "2018001276644170", "2018001204824490", "2018001288504505", "2018001250104058", "2018001298397485", "2018001385721691", "2018001386075794", "2018001332073484", "2018001356934278", "2018001322679501", "2018001405005847", "2018001464309316", "2018001496538791", "2018001428182655", "2018001449890768", "2018001525710814", "2018001596019910", "2018001501723206", "2018001522669686", "2018001507079816", "2018001690246203", "2018001657171574", "2018001678454044", "2018001620815956", "2018001640805178", "2018001793904654", "2018001797604256", "2018001723089250", "2018001752760748", "2018001725057134", "2018001846028421", "2018001807190115", "2018001890607285", "2018001878279130", "2018001818625514", "2018001921175369", "2018001997804949", "2018001950874522", "2018001931468301", "2018001968161313", "2018002057627348", "2018002052430961", "2018002094595054", "2018002066741040", "2018002060845653", "2018002176775630", "2018002169610960", "2018002104641836", "2018002141081524", "2018002108863969", "2018002265433122", "2018002258053595", "2018002232432375", "2018002235175421", "2018002200598462", "2018002316627537", "2018002323634887", "2018002307007435", "2018002322060168", "2018002338616530", "2018002477248771", "2018002489596469", "2018002425778434", "2018002466175647", "2018002421894928", "2018002548675881", "2018002591556798", "2018002502423522", "2018002575399722", "2018002570769441", "2018002600453092", "2018002633629074", "2018002619500939", "2018002607363325", "2018002675933455", "2018002796704927", "2018002727581642", "2018002749202736", "2018002703316401", "2018002761760711", "2018002895186813", "2018002805244894", "2018002891057823", "2018002882683340", "2018002827235785", "2018002901655152", "2018002946976007", "2018002980752108", "2018002921974076", "2018002903928405", "2018003001556372", "2018003055700193", "2018003066515958", "2018003066546404", "2018003041128167", "2018003163617627", "2018003102843420", "2018003188014254", "2018003199854434", "2018003156702084", "2018010061347070", "2018010013385728", "2018010074680660", "2018010066844881", "2018010002335323", "2018010194775866", "2018010132115336", "2018010166928141", "2018010125310112", "2018010172301285", "2018010273668726", "2018010297650189", "2018010247233681", "2018010272124796", "2018010271220172", "2018010332464976", "2018010365082482", "2018010315319821", "2018010321542444", "2018010355994698", "2018010467357701", "2018010430504610", "2018010494669205", "2018010406444994", "2018010485257578", "2018010570850769", "2018010532991762", "2018010539261359", "2018010571858447", "2018010557013078", "2018010656088550", "2018010683392547", "2018010645791665", "2018010668388937", "2018010667106735", "2018010750056615", "2018010764010014", "2018010721289622", "2018010772290498", "2018010727133062", "2018010821407634", "2018010830839890", "2018010864898814", "2018010876789976", "2018010830292337", "2018010937346690", "2018010976063873", "2018010913805180", "2018010946905457", "2018010961454929", "2018011016893082", "2018011031235384", "2018011072552554", "2018011051747134", "2018011064731316", "2018011188380674", "2018011191837798", "2018011110953655", "2018011187937151", "2018011156676204", "2018011228115821", "2018011233960075", "2018011286381757", "2018011202056157", "2018011245841827", "2018011327533092", "2018011325347944", "2018011337089725", "2018011309265870", "2018011395453238", "2018011408772440", "2018011407558039", "2018011453465545", "2018011470963651", "2018011461362422", "2018011538876860", "2018011517240944", "2018011538712610", "2018011512145586", "2018011514611715", "2018011605618695", "2018011624489865", "2018011674122980", "2018011656371148", "2018011607965877", "2018011725763855", "2018011775243084", "2018011784063070", "2018011700408638", "2018011721248344"};


    Map<String, String> validAppID = new HashMap<>();
    // 跑完了2021001161xxxxxx
    // 跑完了20210011636xxxxx,63[0-5]/[7-9]都没跑
    // 跑2021001165xxxxxx
    // 跑2019011863xxxxxx,跑了20190118630xxxxx、20190118631xxxxx
    // 跑2019071065xxxxxx
    // 跑2019042864xxxxxx
    // 跑2021001144xxxxxx
    static int scale = 50000;
    static int count_1 = 44000000;
    static int count_2 = count_1 + 2*scale;
    static int count1_end = count_2;
    static int count_3 = count_1 + 4*scale;
    static int count2_end = count_3;
    static int count_4 = count_1 + 6*scale;
    static int count3_end = count_4;
    static int count_5 = count_1 + 8*scale;
    static int count4_end = count_5;
    static int count_6 = count_1 + 10*scale;
    static int count5_end = count_6;
    static int count_7 = count_1 + 12*scale;
    static int count6_end = count_7;
    static int count_8 = count_1 + 14*scale;
    static int count7_end = count_8;
    static int count_9 = count_1 + 16*scale;
    static int count8_end = count_9;
    static int count_10 = count_1 + 18*scale;
    static int count9_end = count_10;
    static int count10_end = count_1 + 20*scale;


    class MyThread extends Thread{  // 继承Thread类，作为线程的实现类
        private String name ;       // 表示线程的名称
        public Method updater;
        public XC_MethodHook.MethodHookParam param;
        public MyThread(String name, Method updater, XC_MethodHook.MethodHookParam param){
            super(name); // 通过构造方法配置name属性
            this.updater = updater;
            this.param = param;
        }
        public void run(){  // 覆写run()方法，作为线程 的操作主体
            Log.d("Mini MyThread类中", "threadName:" + Thread.currentThread().getName());
            switch (Thread.currentThread().getName()){
                case "thread_1":
                    Log.d("Mini MyThread类中thread_1", "threadName:" + Thread.currentThread().getName() + " count_1:" + count_1);
                    try {
                        Log.d("Mini thread_1界限", "From " + count_1 + " to" + count1_end);
                        for(;count_1<count1_end; count_1++)
                            this.updater.invoke(param.args[0], param.args[1], param.args[2], param.args[3]);
                            // Thread.sleep(getRandomTime(0,500));
                    }catch (Exception e){
                        Log.d("Mini ", Thread.currentThread().getName() + " updater failed!");
                        e.printStackTrace();
                    }
                    break;
                case "thread_2":
                    Log.d("Mini MyThread类中thread_2", "threadName:" + Thread.currentThread().getName() + " count_2:" + count_2);
                    try {
                        Log.d("Mini thread_2界限", "From " + count_2 + " to" + count2_end);
                        for(;count_2<count2_end;count_2++)
                            this.updater.invoke(param.args[0], param.args[1], param.args[2], param.args[3]);
                    }catch (Exception e){
                        Log.d("Mini ", Thread.currentThread().getName() + " updater failed!");
                        e.printStackTrace();
                    }
                    break;
                case "thread_3":
                    Log.d("Mini MyThread类中thread_3", "threadName:" + Thread.currentThread().getName() + " count_3:" + count_3);
                    try {
                        Log.d("Mini thread_3界限", "From " + count_3 + " to" + count3_end);
                        for(;count_3<count3_end; count_3++)
                            this.updater.invoke(param.args[0], param.args[1], param.args[2], param.args[3]);
                    }catch (Exception e){
                        Log.d("Mini ", Thread.currentThread().getName() + " updater failed!");
                        e.printStackTrace();
                    }
                    break;
                case "thread_4":
                    Log.d("Mini MyThread类中thread_4", "threadName:" + Thread.currentThread().getName() + " count_4:" + count_4);
                    try {
                        Log.d("Mini thread_4界限", "From " + count_4 + " to" + count4_end);
                        for(;count_4<count4_end;count_4++)
                            this.updater.invoke(param.args[0], param.args[1], param.args[2], param.args[3]);
                    }catch (Exception e){
                        Log.d("Mini ", Thread.currentThread().getName() + " updater failed!");
                        e.printStackTrace();
                    }
                    break;
                case "thread_5":
                    Log.d("Mini MyThread类中thread_5", "threadName:" + Thread.currentThread().getName() + " count_5:" + count_5);
                    try {
                        Log.d("Mini thread_5界限", "From " + count_5 + " to" + count5_end);
                        for(;count_5<count5_end;count_5++)
                            this.updater.invoke(param.args[0], param.args[1], param.args[2], param.args[3]);
                    }catch (Exception e){
                        Log.d("Mini ", Thread.currentThread().getName() + " updater failed!");
                        e.printStackTrace();
                    }
                    break;
                case "thread_6":
                    Log.d("Mini MyThread类中thread_6", "threadName:" + Thread.currentThread().getName() + " count_6:" + count_6);
                    try {
                        Log.d("Mini thread_6界限", "From " + count_6 + " to" + count6_end);
                        for(;count_6<count6_end;count_6++)
                            this.updater.invoke(param.args[0], param.args[1], param.args[2], param.args[3]);
                    }catch (Exception e){
                        Log.d("Mini ", Thread.currentThread().getName() + " updater failed!");
                        e.printStackTrace();
                    }
                    break;
                case "thread_7":
                    Log.d("Mini MyThread类中thread_7", "threadName:" + Thread.currentThread().getName() + " count_7:" + count_7);
                    try {
                        Log.d("Mini thread_7界限", "From " + count_7 + " to" + count7_end);
                        for(;count_7<count7_end;count_7++)
                            this.updater.invoke(param.args[0], param.args[1], param.args[2], param.args[3]);
                    }catch (Exception e){
                        Log.d("Mini ", Thread.currentThread().getName() + " updater failed!");
                        e.printStackTrace();
                    }
                    break;
                case "thread_8":
                    Log.d("Mini MyThread类中thread_8", "threadName:" + Thread.currentThread().getName() + " count_8:" + count_8);
                    try {
                        Log.d("Mini thread_8界限", "From " + count_8 + " to" + count8_end);
                        for(;count_8<count8_end;count_8++)
                            this.updater.invoke(param.args[0], param.args[1], param.args[2], param.args[3]);
                    }catch (Exception e){
                        Log.d("Mini ", Thread.currentThread().getName() + " updater failed!");
                        e.printStackTrace();
                    }
                    break;
                case "thread_9":
                    Log.d("Mini MyThread类中thread_9", "threadName:" + Thread.currentThread().getName() + " count_9:" + count_9);
                    try {
                        Log.d("Mini thread_9界限", "From " + count_9 + " to" + count9_end);
                        for(; count_9<count9_end; count_9++)
                            this.updater.invoke(param.args[0], param.args[1], param.args[2], param.args[3]);
                    }catch (Exception e){
                        Log.d("Mini ", Thread.currentThread().getName() + " updater failed!");
                        e.printStackTrace();
                    }
                    break;
                case "thread_10":
                    Log.d("Mini MyThread类thread_10", "threadName:" + Thread.currentThread().getName() + " count_10:" + count_10);
                    try {
                        Log.d("Mini thread_10界限", "From " + count_10 + " to" + count10_end);
                        for(; count_10<count10_end; count_10++)
                            this.updater.invoke(param.args[0], param.args[1], param.args[2], param.args[3]);
                    }catch (Exception e){
                        Log.d("Mini ", Thread.currentThread().getName() + " updater failed!");
                        e.printStackTrace();
                    }
            }
        }
    }


    public int getRandomTime(int min, int max){
        Random random = new Random();
        return random.nextInt(max) % (max-min+1) + min;
    }

    public String getAppID(int cnt){
        String appId;
        String yearMonthDay = "20210011";
        String last_8_bit = String.valueOf(cnt);
        StringBuilder str = new StringBuilder();
        for(int i=0; i<8-last_8_bit.length(); i++){
            str.append('0');
        }
        last_8_bit = str + last_8_bit;
        appId = yearMonthDay + last_8_bit;
        return appId;
    }

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

            //通过更新app来Trigger
            // 遍历NebulaAppUpdater类下所有声明的成员方法
            for (Method method : NebulaAppUpdater.getDeclaredMethods()) {
                // 判断寻找NebulaAppUpdater类下的成员方法a(),并且a方法的参数个数必须为4(可能有同名的重载函数，用参数个数进行区别一下)
                if (method.getName().equals("a") && method.getParameterTypes().length == 4) {
                    // hook 参数个数为4的a()方法
                    XposedBridge.hookMethod(method, new XC_MethodHook() {
                        @Override
                        // param是被hook方法的相关信息
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            super.beforeHookedMethod(param);
                            Log.d("MiniCrawler", "updateApp start!");
                            // 将被hook的方法a()的所需的第一个参数赋值给caller
//                            Object caller = param.args[0];
//                            try{
//                                Log.d("Mini 调用updater", "调用updater1次");
//                                Log.d("Mini 执行updater", "threadName" + Thread.currentThread().getName());
//                                updater.invoke(caller, param.args[1], param.args[2], (Set) param.args[3]);
//                            }catch (Exception e)
//                            {
//                                Log.d("Mini", "updater failed");
//                            }
//                            try {
//                                for(int j=0; j<36000; j++){
//                                    if(count > 36000)
//                                        break;
//                                    // 传参调用updater()方法，其实也就是a方法；a方法是static的
//                                    Log.d("Mini 调用updater", String.valueOf(j) + "次");
//                                    updater.invoke(caller, param.args[1], param.args[2], (Set) param.args[3]);
//                                    Thread.sleep(getRandomTime(0,500));
//                                }
//                            } catch (Exception e) {
//                                Log.d("MiniCrawler", "update failed");
//                                e.printStackTrace();
//                            }


                            // 实例化Thread类，开启多线程
                            Log.d("Mini 实例化MyThread前", "threadName:" + Thread.currentThread().getName());
                            MyThread thread_1 = new MyThread("thread_1", updater, param);
                            MyThread thread_2 = new MyThread("thread_2", updater, param);
                            MyThread thread_3 = new MyThread("thread_3", updater, param);
                            MyThread thread_4 = new MyThread("thread_4", updater, param);
                            MyThread thread_5 = new MyThread("thread_5", updater, param);
                            MyThread thread_6 = new MyThread("thread_6", updater, param);
                            MyThread thread_7 = new MyThread("thread_7", updater, param);
                            MyThread thread_8 = new MyThread("thread_8", updater, param);
                            MyThread thread_9 = new MyThread("thread_9", updater, param);
                            MyThread thread_10 = new MyThread("thread_10", updater, param);
                            thread_1.start();
                            thread_2.start();
                            thread_3.start();
                            thread_4.start();
                            thread_5.start();
                            thread_6.start();
                            thread_7.start();
                            thread_8.start();
                            thread_9.start();
                            thread_10.start();

                        }

                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            super.afterHookedMethod(param);
                            Log.d("MiniCrawler", "updateApp end!");
                        }
                    });
                }
            }

            //Requester
            // 遍历RequestUtils类下的所有声明的方法
            for (Method method : RequestUtils.getDeclaredMethods()) {
                // 判断寻找RequestUtils类下名为a且参数个数为1的方法
                if (method.getName().equals("a") && method.getParameterTypes().length == 1) {
                    XposedBridge.hookMethod(method, new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            super.afterHookedMethod(param);
                            // param.getResult()返回函数的result，是AppRes类型的实例
                            Object appRes = param.getResult();
//                            for(Field field: AppRes.getDeclaredFields()){
//                                Log.d("MiniCrawler Field:", field.getName());
//                            }
                            // AppRes是一个类，其中data是类中的List类型的成员变量
                            // 一个不存在的小程序，应该是没有data成员变量的；所以会出问题，打印一下返回的是什么
                            Field results = AppRes.getDeclaredField("data");
                            // 设置data成员变量可以通过反射访问，但是data其实是public类型的，不设置这个也可以
                            results.setAccessible(true);
                            // 获取AppRes类中的public类型的List：data
                            if(appRes!=null) {
                                List<Object> targets = (List<Object>) results.get(appRes);
                                // 如果成员变量data不为空
                                if (targets != null) {
                                    // Log.d("Mini Info", "data变量不为空");
                                    for (Object target : targets) {
                                        boolean isDuplicated = false;
                                        // 遍历appModels HashSet,去重将appModel放入appModels HashSet中
                                        for (Object tmp : appModels) {
                                            // 比较targets列表和appModels HashSet中的值，如果targets列表中的值不存在于appModels中，则添加到appModels中，否则不添加
                                            if (tmp.toString().equals(target.toString())) {
                                                isDuplicated = true;
                                                break;
                                            }
                                        }
                                        if (isDuplicated)
                                            continue;

                                        String APPMODEL = target.toString();
                                        String subStr = new String("appId='");
                                        int index = APPMODEL.indexOf(subStr);
                                        int index1 = APPMODEL.indexOf("'", index + 7);
                                        String APPID = APPMODEL.substring(index + 7, index1);
                                        if (!APPID.equals("2018072560844004") && !APPID.equals("20000202")) {
                                            Log.d("MiniCrawlerModels", target.toString());
                                            appModels.add(target);
                                            // validAppID.put(APPID, APPMODEL);
                                        }
                                    }
                                }
                            }
//                            for(String id: validAppID.keySet()){
//                                Log.d("Mini *****", "appId:" + id + " value: " + validAppID.get(id));
//                            }
                        }
                    });
                }
            }

            //利用Updater添加需要更新的appID，并存储请求结果
            //Infecter
            // 找到AppUpdater类
            final Class<?> AppUpdater = XposedHelpers.findClass("com.alipay.mobile.nebulax.resource.biz.appinfo.AppUpdater", lpparam.classLoader);
            // 遍历AppUpdater中的所有声明的方法
            for (Method method : AppUpdater.getDeclaredMethods()) {
                // 找到AppUpdater类中的名为a且参数个数为3的方法
                if (method.getName().equals("a") && method.getParameterTypes().length == 3) {
                    XposedBridge.hookMethod(method, new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            super.beforeHookedMethod(param);
                            // 打印该a方法中的第三个参数Sting类型
                            // Log.d("MiniCrawler", "主appID为:" + param.args[2].toString());
                            // a方法的第二个参数为Map类型，所以创建target变量来接收这个参数值
                            Map<String, String> target = (Map<String, String>) param.args[1];
                            // 遍历Map中的内容
//                            for(String key: target.keySet()){
//                                Log.d("MiniMap 原始参数", "key:"+ key + " value:" + target.get(key));
//                            }

//
//                            // 将appIDList中的appID放入target Map中，Java中的引用机制会使a方法的第二个参数也被改变，通过在a方法中第二个参数中增加新的
//                            // appID，使支付宝去下载我们放入的新增的appID对应的小程序源码包
                            String threadName = Thread.currentThread().getName();
                            String id = "";
                            switch (threadName){
                                case "thread_1":
                                    id = getAppID(count_1);
//                                    if(count_1 % 100 == 0)
//                                        Log.d("Mini PutAppid", id + " thread Name:" + threadName + " count_1:" + count_1);
                                    break;
                                case "thread_2":
                                    id = getAppID(count_2);
//                                    if(count_2 % 100 == 0)
//                                        Log.d("Mini PutAppid", id + " thread Name:" + threadName + " count_2:" + count_2);
                                    break;
                                case "thread_3":
                                    id = getAppID(count_3);
//                                    if(count_3 % 100 == 0)
//                                        Log.d("Mini PutAppid", id + " thread Name:" + threadName + " count_3:" + count_3);
                                    break;
                                case "thread_4":
                                    id = getAppID(count_4);
//                                    if(count_4 % 100 == 0)
//                                        Log.d("Mini PutAppid", id + " thread Name:" + threadName + " count_4:" + count_4);
                                    break;
                                case "thread_5":
                                    id = getAppID(count_5);
//                                    if(count_5 % 100 == 0)
//                                        Log.d("Mini PutAppid", id + " thread Name:" + threadName + " count_5:" + count_5);
                                    break;
                                case "thread_6":
                                    id = getAppID(count_6);
//                                    if(count_6 % 100 == 0)
//                                        Log.d("Mini PutAppid", id + " thread Name:" + threadName + " count_6:" + count_6);
                                    break;
                                case "thread_7":
                                    id = getAppID(count_7);
//                                    if(count_7 % 100 == 0)
//                                        Log.d("Mini PutAppid", id + " thread Name:" + threadName + " count_7:" + count_7);
                                    break;
                                case "thread_8":
                                    id = getAppID(count_8);
//                                    if(count_8 % 100 == 0)
//                                        Log.d("Mini PutAppid", id + " thread Name:" + threadName + " count_8:" + count_8);
                                    break;
                                case "thread_9":
                                    id = getAppID(count_9);
//                                    if(count_9 % 1000 == 0)
//                                        Log.d("Mini PutAppid", id + " thread Name:" + threadName + " count_9:" + count_9);
                                    if(count_9  % 10000 == 0)
                                    {
                                        int index = getRandomTime(0, appIDList.length-1);
                                        id = appIDList[index];
                                        //id = "2019032863713538";
                                        Log.d("Mini PutAppidValid", id + " thread Name:" + threadName + " count_9:" + count_9);
                                    }
                                    if(count_9 == 100)
                                        id = "2019032863713538";
                                    break;
                                case "thread_10":
                                    id = getAppID(count_10);
//                                    if(count_10 % 100 == 0)
//                                        Log.d("Mini PutAppid", id + " thread Name:" + threadName + " count_10:" + count_10);
                                    break;
                                default:
                                    // Log.d("Mini 不在实例化指定线程", "threadName:" + threadName);
                            }
                            target.put(id, "*");
//                            if(threadName.equals("thread_9") && count_9 == 1700)
//                            {
//                                // 测试有效性appID
//                                target.put("2019032863713538", "*");
//                                Log.d("Mini test","放入指定有效id：2021001102638386");
//                            }


//                            String id = "";
//                            if(Thread.currentThread().getName().equals("thread_1")){
//                                id = getAppID(count_1);
//                                Log.d("Mini PutAppid", id + " thread Name:" + threadName + " count_1:" + count_1);
//                            }else if(Thread.currentThread().getName().equals("thread_2")){
//                                id = getAppID(count_2);
//                                Log.d("Mini PutAppid", id + " thread Name:" + threadName + " count_1:" + count_2);
//                            }else {
//                                Log.d("Mini 不在线程1/2中", id + " thread Name:" + threadName);
//                            }
//                            target.put(id, "*");


//                            if(count < 36000) {
//                                String id = getAppID(count);
//                                if(count % 5000 == 0)
//                                    target.put("2019032863713538", "*");
//                                target.put(id, "*");
//                                Log.d("Mini PutAppid", id);
//                                count++;
//                                // Log.d("MiniCrawler", "Request start: " + System.currentTimeMillis());
//                            }
//                            else{
//                                Log.d("Mini ", "end of the appIDList");
//                            }
                        }
                    });
                }
            }

            //Todo: 批量下载小程序源码
            //获取请求结果中的appModel，调用downloadApp进行下载
            //Downloader
            // 遍历DownloadStep类中的所有声明方法
//            for(Method method : DownloadStep.getDeclaredMethods()){
//                // 找到DownloadStep类下的execute方法
//                if(!method.getName().equals("execute"))
//                    continue;
//                XposedBridge.hookMethod(method, new XC_MethodHook() {
//
//                    @Override
//                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                        super.beforeHookedMethod(param);
//                        Log.d("MiniCrawler", "execute start!");
//                    }
//
//                    @Override
//                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                        super.afterHookedMethod(param);
//                        Log.d("MiniCrawler","execute end");
//
//                        // 返回的是调用该方法的DownloadStep对象实例
//                        Object updateStep = param.thisObject;
//
//                        // 找到BasePrepareStep类下的appInfoManager成员变量
//                        Field appInfoManager = BasePrepareStep.getDeclaredField("appInfoManager");
//                        // 将appInfoManager成员变量设置为可访问
//                        appInfoManager.setAccessible(true);
//
//                        // 传递该成员变量所在的类的对象实例updateStep，返回appInfoManager成员变量的值？这里不是应该传BasePrepareStep的对象实例吗？
//                        Object ob = appInfoManager.get(updateStep);
//
//                        Field  resourceManager = BasePrepareStep.getDeclaredField("resourceManager");
//                        resourceManager.setAccessible(true);
//                        Object rM = resourceManager.get(updateStep);
//
//                        Log.d("MiniCrawler", "Download start: " + appModels.size() + " apps left; "+System.currentTimeMillis());
//                        // 根据appModel的值下载小程序
//                        for(Object target: appModels){
//                            downloadApp.invoke(rM, target, true, null);
//                            // Thread.sleep(1000);
//                            installApp.invoke(rM,target,null);
//                        }
//                    }
//                });
//            }
        }
    }
}






