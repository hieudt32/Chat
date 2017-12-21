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
-dontwarn com.google.common.**

-keepattributes *Annotation*,EnclosingMethod,Signature

########### For Android Support libs ###########
# The support library contains references to newer platform versions.
# Don't warn about those in case this app is linking against an older
# platform version.  We know about them, and they are safe.
-dontwarn android.support.**

########### For Android Support Design ###########
-dontwarn android.support.design.**
-keep class android.support.design.** { *; }
-keep interface android.support.design.** { *; }
-keep public class android.support.design.R$* { *; }

########### For Android Support CardView ###########
-keep class android.support.v7.widget.RoundRectDrawable { *; }

########### For Android appcombat ###########
-dontwarn android.support.v7.**
-keep class android.support.v7.** { *; }
-keep interface android.support.v7.** { *; }

########## For RecyclerView$LayoutManager ###########
-keep public class * extends android.support.v7.widget.RecyclerView$LayoutManager {
    public <init>(...);
}

########### For v7 ###########
-keep class android.support.v7.widget.** { *; }
-dontwarn android.support.v7.widget.**

########### For butter knife ###########
-dontwarn butterknife.internal.**
-dontwarn butterknife.Views$InjectViewProcessor
-keep class butterknife.** { *; }
-keep class **$$ViewBinder { *; }
-keep class **$$ViewInjector { *; }
-keepnames class * { @butterknife.InjectView *;}

-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}

-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}

########### For retrofit2 ###########
-dontwarn retrofit2.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions
-dontwarn okio.**

########### For Glide ###########
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}

