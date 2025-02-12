architectury {
    fabric()
}

val common: Configuration by configurations.creating {
    configurations.compileClasspath.get().extendsFrom(this)
    configurations.runtimeClasspath.get().extendsFrom(this)
    configurations["developmentFabric"].extendsFrom(this)
}

sourceSets {
    getByName("test") {
        runtimeClasspath += sourceSets["main"].runtimeClasspath
        compileClasspath += sourceSets["main"].compileClasspath
    }
    create("datagen") {
        runtimeClasspath += sourceSets["main"].runtimeClasspath
        compileClasspath += sourceSets["main"].compileClasspath
    }
}

loom {
    runs {
        create("test") {
            client()
            ideConfigGenerated(true)
            name = "Test Client"
            source(sourceSets.getByName("test"))
            property("fabric-api.gametest")
        }

        create("datagen") {
            client()
            ideConfigGenerated(true)
            name = "DataGen"

            vmArg("-Dfabric-api.datagen")
            vmArg("-Dfabric-api.datagen.output-dir=${project(":common").file("src/main/generated/resources")}")
            vmArg("-Dfabric-api.datagen.modid=vanity-datagen")

            source(sourceSets.getByName("datagen"))
        }
    }
}

dependencies {
    common(project(":common", configuration = "namedElements")) {
        isTransitive = false
    }
    shadowCommon(project(path = ":common", configuration = "transformProductionFabric")) {
        isTransitive = false
    }

    val minecraftVersion: String by project
    val fabricLoaderVersion: String by project
    val fabricApiVersion: String by project

    modImplementation(group = "net.fabricmc", name = "fabric-loader", version = fabricLoaderVersion)
    modApi(group = "net.fabricmc.fabric-api", name = "fabric-api", version = "$fabricApiVersion+$minecraftVersion")
}