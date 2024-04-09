plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
    id("org.jetbrains.kotlin.android")
}


android {
    namespace = "com.navjot.tradeeazy"
    compileSdk = 34

    buildFeatures {
        dataBinding=true
    }


    defaultConfig {
        applicationId = "com.navjot.tradeeazy"
        minSdk = 24
        targetSdk = 34
        versionCode = 16
        versionName = "2.5"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.activity:activity:1.8.2")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation("com.facebook.android:facebook-android-sdk:latest.release")
    implementation(platform("com.google.firebase:firebase-bom:32.8.0"))
    implementation("com.google.firebase:firebase-analytics")
    implementation ("com.google.firebase:firebase-auth:22.3.1")
    implementation("com.google.firebase:firebase-auth")
    implementation("com.google.android.gms:play-services-auth:21.0.0")
    implementation ("com.google.android.gms:play-services-identity:18.0.1")
    implementation ("com.github.barteksc:android-pdf-viewer:2.8.2")
    implementation("com.pierfrancescosoffritti.androidyoutubeplayer:chromecast-sender:0.28")
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("io.finnhub:kotlin-client:2.0.20")
    implementation ("com.github.KwabenBerko:News-API-Java:1.0.2")
    implementation ("com.squareup.picasso:picasso:2.5.2")

}