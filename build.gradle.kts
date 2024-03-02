// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    extra.apply {
        set("room_version", "2.5.2")
    }
}

plugins {
    id("com.android.application") version "8.2.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.22" apply false
    // Plugin for Relay
    id("com.google.relay") version "0.3.09"
    //Ksp
    id("com.google.devtools.ksp") version "1.9.22-1.0.17" apply false
    // Hilt
    id("com.google.dagger.hilt.android") version "2.50" apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
