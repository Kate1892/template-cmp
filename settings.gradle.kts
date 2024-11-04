//rootProject.name = "template-cmp"
//enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
//
//pluginManagement {
//    repositories {
//        google {
//            mavenContent {
//                includeGroupAndSubgroups("androidx")
//                includeGroupAndSubgroups("com.android")
//                includeGroupAndSubgroups("com.google")
//            }
//        }
//        mavenCentral()
//        gradlePluginPortal()
//    }
//}
//
//dependencyResolutionManagement {
//    repositories {
//        google {
//            mavenContent {
//                includeGroupAndSubgroups("androidx")
//                includeGroupAndSubgroups("com.android")
//                includeGroupAndSubgroups("com.google")
//            }
//        }
//        mavenCentral()
//    }
//}

include(":composeApp")
//include(":core:network")

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {

        google()
        mavenCentral()
    }
}

rootProject.name = "template-cmp"
include(":androidApp")
//include(":core:network")
