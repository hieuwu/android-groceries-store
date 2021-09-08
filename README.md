# android-groceries-store
[![Android CI](https://github.com/hieuwu/android-groceries-store/actions/workflows/app-build.yml/badge.svg?branch=main)](https://github.com/hieuwu/android-groceries-store/actions/workflows/app-build.yml)

[![Kotlin](https://img.shields.io/badge/kotlin-%23FF5722.svg?&style=for-the-badge&logo=kotlin&logoColor=white)](https://github.com/hieuwu/kotlin-android-practice)
[![Android](https://img.shields.io/badge/android-teal.svg?&style=for-the-badge&logo=android&logoColor=white")](https://github.com/hieuwu/kotlin-android-practice)

The user interface follow this design:
- [Groceries Store Design](https://www.figma.com/file/exhlJtkLIcHvfxd8SDja3T/Online-Groceries-App-UI-(Community)?node-id=1%3A2)

Application Architecture folows Clean Architecture:
- [Use Case](https://proandroiddev.com/why-you-need-use-cases-interactors-142e8a6fe576)
- [Clean Architecture](https://medium.com/@dmilicic/a-detailed-guide-on-developing-android-apps-using-the-clean-architecture-pattern-d38d71e94029)

# Libraries
- [Glide](https://github.com/bumptech/glide), [GlidePalette](https://github.com/florent37/GlidePalette) - loading images.
- [Timber](https://github.com/JakeWharton/timber) - Logging.
- [Retrofit2](https://github.com/square/retrofit) - Construct the REST APIs and paging network data.
- [Moshi](https://github.com/square/moshi/) - A modern JSON library for Kotlin and Java.
- [Hilt](https://dagger.dev/hilt/) for dependency injection.
- JetPack
  - Lifecycle - dispose of observing data when lifecycle state changes.
  - ViewModel - UI related data holder, lifecycle aware.
  - Room Persistence - construct a database using the abstract layer.
- Architecture
  - MVVM Architecture (View - DataBinding - ViewModel - Model)
