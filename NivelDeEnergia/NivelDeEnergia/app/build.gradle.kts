plugins {
    // ✅ Solo necesitas este plugin
    id("com.android.application")
}

android {
    namespace = "com.example.niveldeenergia"

    // ✅ Compile SDK estable
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.niveldeenergia"

        // ✅ Adaptive icons necesitan al menos API 26
        minSdk = 26
        targetSdk = 34

        versionCode = 1
        versionName = "1.0"

        // Runner de test por defecto
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            // ❌ No minificar ni ofuscar
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    // ⚙️ Compilación Java (más moderna y compatible)
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    // ✅ No necesitamos Compose ni Data Binding
    buildFeatures {
        dataBinding = false
        compose = false
    }
}

dependencies {
    // 🔹 Librerías esenciales para proyectos en Java con XML
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.2.0")

    // 🔹 Tests (opcionales)
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}
