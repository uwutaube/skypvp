plugins {
    java
}

group = "com.zoo"
version = "1.0.0"

java {
    toolchain {
        // The server jar advertises Java 21 in its manifest.
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

repositories {
    mavenCentral()
}

dependencies {
    // Compile against the server API classes by using the server jar directly.
    // For real projects, you may want to publish an API artifact or use a deobfuscated SDK instead.
    compileOnly(files("libs/HytaleServer.jar"))
}

tasks.jar {
    archiveBaseName.set("skypvp")
    // Ensure manifest.json ends up at jar root (it lives in src/main/resources).
}

