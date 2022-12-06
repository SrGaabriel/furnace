rootProject.name = "furnace"

enableFeaturePreview("VERSION_CATALOGS")

dependencyResolutionManagement {
    versionCatalogs {
        create("furnace") {
            from(files("furnace.versions.toml"))
        }
    }
}

include("api")
include("bot")