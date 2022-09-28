# CarbonTest
This repository contains my solution to the Android take home challenge for an Android Developer role at Carbon.
Multi module Android app which showcases Kotlin, MVVM, Navigation, Hilt, Coroutines, Retrofit, Room Database, Unit test and Kotlin Gradle DSL.

## Modules

#### App module
The `:app` module is an [com.android.application](https://developer.android.com/studio/build/), which is the main and default module needed to create the app bundle.

#### Core module
The `:core` module is an [com.android.library](https://developer.android.com/studio/projects/android-library) for making network requests. Providing the data source for the features that need it.

#### Feature module
The `:features` module is an [com.android.library](https://developer.android.com/studio/projects/android-library) which is a module containing a the different features of the app, isolated from the rest in accordance with business logic.

## Testing
Local unit testing is done for ViewModels.

## Setup
This application requires a minimum SDK version of 23. Clone the repository. You will need an API key
from [The Movie Database](https://developers.themoviedb.org/3/) to request data. You'll need to create an account one in order to request
an API Key. After you've gotten your API key, in your project's root directory, open the ```local.properties``` file
and include the following line: ``` apiKey = "YOUR API KEY" ```. You can now build the project.

## Libraries used and things done:
- MVVM and clean architecture
- Multi Module 
- Hilt for dependency injection
- Retrofit and OkHttp for network call
- Room database for offline caching
- Android Navigation Component and safe args for smooth navigation
- Coroutines to handle network calls
- JUnit and Mockito for unit testing
- Glide for loading images
- Error handling
- Configuration changes