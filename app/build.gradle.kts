plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.mobile1project"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.mobile1project"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        // Agregamos el API KEY desde local.properties
        manifestPlaceholders["MAPS_API_KEY"] = project.findProperty("MAPS_API_KEY") ?: ""
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

    buildFeatures {
        compose = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    packagingOptions {
        resources {
            excludes += listOf(
                "kotlin/internal/internal.kotlin_builtins",
                "messages/JavaOptionBundle.properties",
                "META-INF/DEPENDENCIES",
                "META-INF/LICENSE",
                "META-INF/LICENSE.txt",
                "META-INF/NOTICE",
                "META-INF/NOTICE.txt"
            )
        }
    }
}

configurations.all {
    resolutionStrategy {
        force("com.google.auto.value:auto-value:1.6.3")
        force("com.google.auto.value:auto-value-annotations:1.6.3")
    }
}

dependencies {
    // Retrofit y red
    implementation("io.coil-kt:coil-compose:2.5.0")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.6.4")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.3")

    // Jetpack Compose
    implementation("androidx.compose.material3:material3:1.1.2")
    implementation("androidx.compose.ui:ui:1.5.1")
    implementation("androidx.compose.ui:ui-tooling-preview:1.5.1")
    implementation("androidx.compose.foundation:foundation:1.5.1")
    implementation("androidx.compose.material:material-icons-extended:1.5.0")
    implementation("com.google.accompanist:accompanist-swiperefresh:0.31.5-beta")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")
    implementation("androidx.compose.runtime:runtime-livedata:1.5.1")

    // Google Maps Compose
    implementation("com.google.maps.android:maps-compose:4.3.0")
    implementation("com.google.android.gms:play-services-maps:18.2.0")

    // Core y navegación
    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation(libs.androidx.navigation.compose)

    // AutoValue
    implementation("com.google.auto.value:auto-value:1.6.3") {
        exclude(group = "org.checkerframework", module = "checker-qual")
    }

    // BOM
    implementation(platform("androidx.compose:compose-bom:2023.06.00"))

    // Tests
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.5.1")
    debugImplementation("androidx.compose.ui:ui-tooling:1.5.1")
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.5.1")
}
