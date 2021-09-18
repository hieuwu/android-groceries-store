# android-groceries-store
[![Android CI](https://github.com/hieuwu/android-groceries-store/actions/workflows/app-build.yml/badge.svg?branch=main)](https://github.com/hieuwu/android-groceries-store/actions/workflows/app-build.yml)

[![Kotlin](https://img.shields.io/badge/kotlin-%23FF5722.svg?&style=for-the-badge&logo=kotlin&logoColor=white)](https://github.com/hieuwu/android-groceries-store)
[![Android](https://img.shields.io/badge/android-teal.svg?&style=for-the-badge&logo=android&logoColor=white")](https://github.com/hieuwu/android-groceries-store)
![Gradle](https://img.shields.io/badge/Gradle-545454.svg?&style=for-the-badge&logo=gradle&logoColor=white)
![SQLite](https://img.shields.io/badge/SQLite-003B57.svg?&style=for-the-badge&logo=sqlite&logoColor=0772de)
![Firebase](https://img.shields.io/badge/Firebase-b0b0b0.svg?&style=for-the-badge&logo=firebase&logoColor=FFCA28)
![Github Action](https://img.shields.io/badge/Github%20Actions-424a53.svg?&style=for-the-badge&logo=githubactions&logoColor=white)

# UI Design & Architecture
The user interface follow this design:
- [Groceries Store Design](https://www.figma.com/file/exhlJtkLIcHvfxd8SDja3T/Online-Groceries-App-UI-(Community)?node-id=1%3A2)

Next Step is to follow Clearn Architecture:
- [Use Case](https://proandroiddev.com/why-you-need-use-cases-interactors-142e8a6fe576)
- [Clean Architecture](https://medium.com/@dmilicic/a-detailed-guide-on-developing-android-apps-using-the-clean-architecture-pattern-d38d71e94029)
- MVVM Architecture (View - DataBinding - ViewModel - Model)

# Libraries
- [Glide](https://github.com/bumptech/glide) - loading images.
- [Timber](https://github.com/JakeWharton/timber) - Logging.
- [Retrofit2](https://github.com/square/retrofit) - Construct the REST APIs and paging network data.
- [Moshi](https://github.com/square/moshi/) - A modern JSON library for Kotlin and Java.
- [Hilt](https://dagger.dev/hilt/) for dependency injection.
- JetPack
  - Lifecycle - dispose of observing data when lifecycle state changes.
  - ViewModel - UI related data holder, lifecycle aware.
  - Room Persistence - construct a database using the abstract layer.

