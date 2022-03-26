<h1 align="center"> 🚀 Groceries Store</h1>

 [![Android CI](https://github.com/hieuwu/android-groceries-store/actions/workflows/app-build.yml/badge.svg?branch=main)](https://github.com/hieuwu/android-groceries-store/actions/workflows/app-build.yml)


![Groceries Store](https://i.imgur.com/Wn6ZcZl.jpeg)
<img src="https://github.com/hieuwu/hieuwu.github.io/blob/master/assets/img/rocket.gif" align="right" height="330px">

<!-- [![Kotlin](https://img.shields.io/badge/kotlin-%23FF5722.svg?&style=for-the-badge&logo=kotlin&logoColor=white)](https://github.com/hieuwu/android-groceries-store)
[![Android](https://img.shields.io/badge/android-teal.svg?&style=for-the-badge&logo=android&logoColor=white")](https://github.com/hieuwu/android-groceries-store)
![Gradle](https://img.shields.io/badge/Gradle-545454.svg?&style=for-the-badge&logo=gradle&logoColor=white)
![SQLite](https://img.shields.io/badge/SQLite-003B57.svg?&style=for-the-badge&logo=sqlite&logoColor=0772de)
![Firebase](https://img.shields.io/badge/Firebase-b0b0b0.svg?&style=for-the-badge&logo=firebase&logoColor=FFCA28)
![Github Action](https://img.shields.io/badge/Github%20Actions-424a53.svg?&style=for-the-badge&logo=githubactions&logoColor=white) -->

[Download on Google Play Store](https://play.google.com/store/apps/details?id=com.hieuwu.groceriesstore)



## About
Groceries Store is a project to help people order grocery online. The main purpose of this project is to demonstrate modern Android development skills in real production environment, reduce the learning curve when jump into Android development world.

## UI Design & Architecture
<img src="https://github.com/hieuwu/hieuwu.github.io/blob/master/assets/img/real-estate.gif" align="left" height="280px">


**Design**
- [Groceries Store Design](https://www.figma.com/file/exhlJtkLIcHvfxd8SDja3T/Online-Groceries-App-UI-(Community)?node-id=1%3A2)
- <a href='https://www.freepik.com/vectors/background'>Background vector created by freepik - www.freepik.com</a>
- <a href='https://www.freepik.com/vectors/food'>Food vector created by terdpongvector - www.freepik.com</a>

**Architecture**
- [Use Case](https://proandroiddev.com/why-you-need-use-cases-interactors-142e8a6fe576)
- [Clean Architecture](https://medium.com/@dmilicic/a-detailed-guide-on-developing-android-apps-using-the-clean-architecture-pattern-d38d71e94029)
- [MVVM Architecture](https://medium.com/swlh/understanding-mvvm-architecture-in-android-aa66f7e1a70b)


## MAD Scorecard:

![Caption](https://i.imgur.com/omCNl7x.png)

## Libraries
- [Glide](https://github.com/bumptech/glide) - Loading images.
- [Timber](https://github.com/JakeWharton/timber) - Logging.
- [Retrofit2](https://github.com/square/retrofit) - Construct the REST APIs and paging network data.
- [Moshi](https://github.com/square/moshi/) - A modern JSON library for Kotlin and Java.
- [Hilt](https://dagger.dev/hilt/) for dependency injection.
- JetPack
  - Lifecycle - dispose of observing data when lifecycle state changes.
  - ViewModel - UI related data holder, lifecycle aware.
  - Room Persistence - construct a database using the abstract layer.

## Setup

#### Android Studio
Android Studio Bumblebee or later
SDK 23 or later

#### Firestore
1. Create your Firebase account and setup your own Google service following instruction from Firebase
2. Update the properties in each collection as below
- `products`
   -    `category`: reference (to specific category)
   -    `description`: string
   -    `id`: string
   -    `image`: string
   -    `name`: string
   -    `nutrition`: string
   -    `price`: number

- `orders`
   -    `address`: string
   -    `lineItems`: array
   -    `total`: number

- `categories`
   -    `description`: string
   -    `image`: string
   -    `name`: string

- `users`
   -    `address`: string
   -    `email`: string
   -    `name`: string
   -    `phone`: string

3. If you have any changes in the Firestore collections, feel free to update the `FirebaseStoreConstant.kt` file

## Find this repository useful? :heart:
Support it by joining __[stargazers](https://github.com/hieuwu/android-groceries-store/stargazers)__ for this repository. :star: <br>
Also, __[follow me](https://github.com/hieuwu)__ on GitHub for my next creations! 🤩
