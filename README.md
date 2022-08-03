# Patchouli Provider #

PatchouliProvider is a library mod that expands on DataProviders to allow you to generate Patchouli jsons in code.

## Setting up your Gradle build script ##
You can add PatchouliProvider to your mod project using Gradle.  
Just add the following to your build script (`build.gradle`):  

### Repositories ###
```gradle
repositories {
  maven {
    // location of the maven that hosts PatchouliProvider files
    name = "BlameJared's maven"
    url = 'https://maven.blamejared.com'
  }
}
```
### Dependencies ###
```gradle
dependencies {
  compileOnly fg.deobf("xyz.brassgoggledcoders:PatchouliProvider:1.18.2-1.0.6-Snapshot.3")
}
```
