# NightModeTest

HyperionとAppCompatDelegate.setDefaultNightMode()を併用するとメモリリークすることのテスト

MainActivity.kt:

```Kotlin
package com.example.nightmodetest

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

internal class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(Button(this).also {
            it.text = this::class.java.simpleName
            it.setOnClickListener {

                this.startActivity(Intent(this, SubActivity::class.java))

            }
        })
    }
}

```

SubActivity.kt:

```Kotlin
package com.example.nightmodetest

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate

internal class SubActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(Button(this).also {
            it.text = this::class.java.simpleName
            it.setOnClickListener {

                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

            }
        })
    }
}

```

app/build.gradle:

```Groovy
plugins {
    id 'com.android.application'
    id 'kotlin-android'
}

android {
    compileSdkVersion 30
    buildToolsVersion "30.0.3"

    defaultConfig {
        applicationId "com.example.nightmodetest"
        minSdkVersion 23
        targetSdkVersion 30
        versionCode 1
        versionName "1.0"

        testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }
    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = '1.8'
    }
}

dependencies {

    implementation "org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version"
    implementation 'androidx.core:core-ktx:1.3.2'
    implementation 'androidx.appcompat:appcompat:1.2.0'
    implementation 'com.google.android.material:material:1.2.1'

    //  https://mvnrepository.com/artifact/com.squareup.leakcanary/leakcanary-android
    debugImplementation("com.squareup.leakcanary:leakcanary-android:2.5")

    //  https://mvnrepository.com/artifact/com.willowtreeapps.hyperion/hyperion-core
    debugImplementation("com.willowtreeapps.hyperion:hyperion-core:0.9.30")
}
```
