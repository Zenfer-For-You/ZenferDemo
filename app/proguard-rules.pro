# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
#指定代码的压缩级别
-optimizationpasses 5
#包明不混合大小写
-dontusemixedcaseclassnames
#不去忽略非公共的库类
-dontskipnonpubliclibraryclasses
#优化  不优化输入的类文件
-dontoptimize
#预校验
-dontpreverify
#混淆时是否记录日志
-verbose
# 混淆时所采用的算法
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*
#保护注解
-keepattributes *Annotation*
# 避免混淆泛型
-keepattributes Exceptions,InnerClasses,Signature
# 抛出异常时保留代码行号
-keepattributes SourceFile,LineNumberTable


#如果引用了v4或者v7包
-keep class android.support.**{*;}
# 保留继承的
-keep public class * extends android.support.**
#不混淆资源类
-keepclassmembers class **.R$* {
     public static <fields>;
}
#保持 native 方法不被混淆
-keepclasseswithmembernames class * {native <methods>;}
 #保持自定义控件类不被混淆
 -keepclasseswithmembers class * {
     public <init>(android.content.Context);
 }
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}
-keepclasseswithmembernames class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
# 保留在Activity中的方法参数是view的方法，
# 这样以来我们在layout中写的onClick就不会被影响
-keepclassmembers class * extends android.app.Activity {
    public void *(android.view.View);
}
# 保留我们使用的四大组件，自定义的Application等等这些类不被混淆
# 因为这些子类都有可能被外部调用
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Fragment
-keep public class * extends android.app.Appliction
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class * extends android.view.View
-keep public class * extends android.support.annotation.**
-keep public class com.android.vending.licensing.ILicensingService
#保持 Parcelable 不被混淆
-keep class * implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator *;
}
#保持 Serializable 不被混淆
-keepnames class * implements java.io.Serializable
#保持 Serializable 不被混淆并且enum 类也不被混淆
-keepclassmembers class * implements java.io.Serializable {
        static final long serialVersionUID;
        private static final java.io.ObjectStreamField[] serialPersistentFields;
        !static !transient <fields>;
        !private <fields>;
        !private <methods>;
        private void writeObject(java.io.ObjectOutputStream);
        private void readObject(java.io.ObjectInputStream);
        java.lang.Object writeReplace();
        java.lang.Object readResolve();
}
# 保留枚举类不被混淆
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
## 保留我们自定义控件（继承自View）不被混淆
-keep public class * extends android.view.View{
    *** get*();
    void set*(***);
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

# 对于带有回调函数的onXXEvent、**On*Listener的，不能被混淆
-keepclassmembers class * {
    void *(**On*Event);
    void *(**On*Listener);
}

-keepclassmembers class * {
     public void *ButtonClicked(android.view.View);
}

# webView处理，项目中没有使用到webView忽略即可
-keepclassmembers class fqcn.of.javascript.interface.for.webview {
    public *;
}
-keepclassmembers class * extends android.webkit.webViewClient {
    public void *(android.webkit.WebView, java.lang.String, android.graphics.Bitmap);
    public boolean *(android.webkit.WebView, java.lang.String);
}
-keepclassmembers class * extends android.webkit.webViewClient {
    public void *(android.webkit.webView, jav.lang.String);
}
-keep public class * extends android.content.ContentProvider
-keepnames class * extends android.view.View

-keep class * extends android.app.Fragment {
 public void setUserVisibleHint(boolean);
 public void onHiddenChanged(boolean);
 public void onResume();
 public void onPause();
}
-keep class android.support.v4.app.Fragment {
 public void setUserVisibleHint(boolean);
 public void onHiddenChanged(boolean);
 public void onResume();
 public void onPause();
}
-keep class * extends android.support.v4.app.Fragment {
 public void setUserVisibleHint(boolean);
 public void onHiddenChanged(boolean);
 public void onResume();
 public void onPause();
}

-assumenosideeffects class android.util.Log {
    public static *** e(...);
    public static *** w(...);
    public static *** wtf(...);
    public static *** d(...);
    public static *** v(...);
}

-keepclassmembers class * {
   public <init>(org.json.JSONObject);
}

##########################################################工具包------------start


#################### rx
-dontwarn sun.misc.**
-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
 long producerIndex;
 long consumerIndex;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
 rx.internal.util.atomic.LinkedQueueNode producerNode;
}
-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
 rx.internal.util.atomic.LinkedQueueNode consumerNode;
}
#################### rx end

################### Gson
-keepattributes Signature-keepattributes
-keep class sun.misc.Unsafe { *;}
-keep class com.google.gson.stream.** {*;}
-keepattributes EnclosingMethod
-keep class sun.misc.Unsafe { *;} #解决JSON解析问题
-dontwarn sun.misc.Unsafe
-keep class com.google.gson.examples.android.model.** { *;}
-keep class com.google.gson.** { *;}
-dontwarn com.google.**
#################### Gson end

################### okhttp
-dontwarn com.squareup.okhttp3.**
-keep class com.squareup.okhttp3.** { *;}
-dontwarn okio.**
#################### okhttp end

################### butterknife
-keep class butterknife.** { *;}
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *;}
-keepclasseswithmembernames class * { @butterknife.* <fields>;}
-keepclasseswithmembernames class * { @butterknife.* <methods>;}
#################### butterknife end

#################### dontwarn
-dontwarn java.lang.**
-dontwarn org.codehaus.**
-dontwarn org.apache.commons.**
-dontwarn android.webkit.WebView
#################### dontwarn end

#################### eventBus
-keepclassmembers class ** {
@org.greenrobot.eventbus.Subscribe <methods>;
}
-keep enum org.greenrobot.eventbus.ThreadMode { *;}
-keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent { <init>(Java.lang.Throwable);}
-keepclassmembers class ** {
    public void onEvent*(**);
    void onEvent*(**);
}

##########################################################业务混淆----------start

######主工程
-keep class com.ps.recycling2b.beans.** {*;}
-keep class com.ps.recycling2b.beans.local.** {*;}
-keep class com.ps.recycling2b.beans.net.** {*;}
-keep class com.ps.recycling2b.view.** {*;}
-keep class com.ps.recycling2b.manager.** {*;}

######framework
-keep class com.ps.recycling2b.frameworkmodule.bean.** {*;}
-keep class com.ps.recycling2b.frameworkmodule.widget.** {*;}
-keep class com.ps.recycling2b.frameworkmodule.widget.captcha.** {*;}
-keep class com.ps.recycling2b.frameworkmodule.widget.dialog.** {*;}
-keep class com.ps.recycling2b.frameworkmodule.widget.input.** {*;}
-keep class com.ps.recycling2b.frameworkmodule.widget.lockview.** {*;}
-keep class com.ps.recycling2b.frameworkmodule.update.bean.** {*;}
-keep class com.ps.recycling2b.frameworkmodule.update.bean.req.** {*;}
-keep class com.ps.recycling2b.frameworkmodule.update.view.** {*;}
##保持反射不混淆
-keep class * extends com.ps.recycling2b.frameworkmodule.base.presenter**{*;}

######interfaces
-keep class com.ps.recycling2b.interfaces.core**{*;}

####
-keep class com.ps.recycling2b.thirdparty.** {*;}
-dontwarn com.ps.recycling2b.thirdparty.**

-keep class com.ps.recycling2b.widgets.** {*;}
-dontwarn com.ps.recycling2b.widgets.**

######二维码
-keep class com.code.tool.qrcmodule.scanner.** {*;}

##########################################################第三方平台---------start
-keepclassmembers class ** {
    @com.yanzhenjie.permission.PermissionYes <methods>;
}
-keepclassmembers class ** {
    @com.yanzhenjie.permission.PermissionNo <methods>;
}
#map
-keep class com.ps.recycling2b.util.LocationResult


-keep class **.R$* {
    <fields>;
}


## ----------------------------------
##      OkHttp
## ----------------------------------
-keepattributes Signature
-keepattributes *Annotation*
-keep class com.squareup.okhttp3.** { *; }
-keep interface com.squareup.okhttp3.** { *; }
-dontwarn com.squareup.okhttp3.**

## ----------------------------------
##      Okio
## ----------------------------------
-keep class sun.misc.Unsafe { *; }
-dontwarn java.nio.file.*
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement
-dontwarn okio.**

## ----------------------------------
##      Glide
## ----------------------------------
-keep class com.bumptech.glide.Glide { *; }
-keep class com.bumptech.glide.request.RequestOptions {*;}
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
-dontwarn com.bumptech.glide.**

## ----------------------------------
##      BGA
## ----------------------------------
-keep class cn.bingoogolapple.** { *; }
-keep class uk.co.senab.photoview.** { *; }
-dontwarn  cn.bingoogolapple.photopicker.**
-dontwarn  uk.co.senab.photoview.**
-dontwarn  cn.bingoogolapple.baseadapter.**

## ----------------------------------
##      Arouter
## ----------------------------------
-keep public class com.alibaba.android.arouter.routes.**{*;}
-keep public class com.alibaba.android.arouter.facade.**{*;}
-keep class * implements com.alibaba.android.arouter.facade.template.ISyringe{*;}


