<h1 align="center"> 🚀 Groceries Store</h1>


 [![Android CI](https://github.com/hieuwu/android-groceries-store/actions/workflows/app-build.yml/badge.svg?branch=main)](https://github.com/hieuwu/android-groceries-store/actions/workflows/app-build.yml)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=hieuwu_android-groceries-store&metric=sqale_rating)](https://sonarcloud.io/summary/new_code?id=hieuwu_android-groceries-store)

![Groceries Store](https://user-images.githubusercontent.com/43868345/233767546-ba25566b-7926-4140-82fe-12346a1f357e.png)

<img src="https://github.com/hieuwu/hieuwu.github.io/blob/master/assets/img/rocket.gif" align="right" height="330px">

<!-- [![Kotlin](https://img.shields.io/badge/kotlin-%23FF5722.svg?&style=for-the-badge&logo=kotlin&logoColor=white)](https://github.com/hieuwu/android-groceries-store)
[![Android](https://img.shields.io/badge/android-teal.svg?&style=for-the-badge&logo=android&logoColor=white")](https://github.com/hieuwu/android-groceries-store)
![Gradle](https://img.shields.io/badge/Gradle-545454.svg?&style=for-the-badge&logo=gradle&logoColor=white)
![SQLite](https://img.shields.io/badge/SQLite-003B57.svg?&style=for-the-badge&logo=sqlite&logoColor=0772de)
![Firebase](https://img.shields.io/badge/Firebase-b0b0b0.svg?&style=for-the-badge&logo=firebase&logoColor=FFCA28)
![Github Action](https://img.shields.io/badge/Github%20Actions-424a53.svg?&style=for-the-badge&logo=githubactions&logoColor=white) -->
[![SonarCloud](https://sonarcloud.io/images/project_badges/sonarcloud-black.svg)](https://sonarcloud.io/summary/new_code?id=ARK-Builders_arklib-android)

[Download on Google Play Store](https://play.google.com/store/apps/details?id=com.hieuwu.groceriesstore)


## 👋 About
Groceries Store is a project to help people order grocery online. The main purpose of this project is to demonstrate modern Android development skills in real production environment, reduce the learning curve when jump into Android development world.

## 🎨 UI Design & Architecture
<img src="https://github.com/hieuwu/hieuwu.github.io/blob/master/assets/img/real-estate.gif" align="left" height="280px">


**Design**
- [Groceries Store Design](https://www.figma.com/file/exhlJtkLIcHvfxd8SDja3T/Online-Groceries-App-UI-(Community)?node-id=1%3A2)
- <a href='https://www.freepik.com/vectors/background'>Background vector created by freepik - www.freepik.com</a>
- <a href='https://www.freepik.com/vectors/food'>Food vector created by terdpongvector - www.freepik.com</a>

**Architecture**
- [Use Case](https://proandroiddev.com/why-you-need-use-cases-interactors-142e8a6fe576)
- [Clean Architecture](https://medium.com/@dmilicic/a-detailed-guide-on-developing-android-apps-using-the-clean-architecture-pattern-d38d71e94029)
- [MVVM Architecture](https://medium.com/swlh/understanding-mvvm-architecture-in-android-aa66f7e1a70b)


## 🛠️ Libraries
- [Glide](https://github.com/bumptech/glide) - Loading images.
- [Timber](https://github.com/JakeWharton/timber) - Logging.
- [Retrofit2](https://github.com/square/retrofit) - Construct the REST APIs and paging network data.
- [Moshi](https://github.com/square/moshi/) - A modern JSON library for Kotlin and Java.
- [Hilt](https://dagger.dev/hilt/) for dependency injection.
- JetPack
  - Lifecycle - dispose of observing data when lifecycle state changes.
  - ViewModel - UI related data holder, lifecycle aware.
  - Room Persistence - construct a database using the abstract layer.

## ⚙️ Setup

#### Android Studio
Android Studio Bumblebee or later
SDK 23 or later

#### Supabase
See [Supabase Wiki](https://github.com/hieuwu/android-groceries-store/wiki) to keep track the process
Database Schema:

**categories**
id | image | name | _id |
--- | --- | --- | --- | 


**line_items**
id | lineItemId | productid | quantity | subtotal|orderId|
--- | --- | --- | --- |  --- |  --- | 

**orders**
orderId | status |
--- | --- | 

**products**
_id | name | description | price | image|category|nutrition|productid
--- | --- | --- | --- |  --- |  --- | --- | --- | 


**users**
id | name | email | phone | isOrderCreatedNotiEnabled|isPromotionNotiEnabled|isDataRefreshedEnabled|address
--- | --- | --- | --- |  --- |  --- | --- | --- | 

## 🎯 Find this repository useful? :heart:
Support it by joining __[stargazers](https://github.com/hieuwu/android-groceries-store/stargazers)__ for this repository. :star: <br>
Also, __[follow me](https://github.com/hieuwu)__ on GitHub for my next creations! 🤩

## 👨‍💻 Contributors ✨
<!-- ALL-CONTRIBUTORS-BADGE:START - Do not remove or modify this section -->
[![All Contributors](https://img.shields.io/badge/all_contributors-7-orange.svg?style=flat-square)](#contributors-)
<!-- ALL-CONTRIBUTORS-BADGE:END -->
Thanks goes to these wonderful people ([emoji key](https://allcontributors.org/docs/en/emoji-key)):

<!-- ALL-CONTRIBUTORS-LIST:START - Do not remove or modify this section -->
<!-- prettier-ignore-start -->
<!-- markdownlint-disable -->
<table>
  <tbody>
    <tr>
      <td align="center"><a href="https://github.com/saurabhkpatel"><img src="https://avatars.githubusercontent.com/u/1188367?v=4?s=100" width="100px;" alt="Saurabh Patel"/><br /><sub><b>Saurabh Patel</b></sub></a><br /><a href="https://github.com/hieuwu/android-groceries-store/commits?author=saurabhkpatel" title="Code">💻</a></td>
      <td align="center"><a href="https://aditya-gupta99.github.io/"><img src="https://avatars.githubusercontent.com/u/94394661?v=4?s=100" width="100px;" alt="Aditya Gupta"/><br /><sub><b>Aditya Gupta</b></sub></a><br /><a href="#design-Aditya-gupta99" title="Design">🎨</a> <a href="https://github.com/hieuwu/android-groceries-store/commits?author=Aditya-gupta99" title="Code">💻</a> <a href="#example-Aditya-gupta99" title="Examples">💡</a></td>
      <td align="center"><a href="https://github.com/DeKaN"><img src="https://avatars.githubusercontent.com/u/1156370?v=4?s=100" width="100px;" alt="Dmitriy"/><br /><sub><b>Dmitriy</b></sub></a><br /><a href="https://github.com/hieuwu/android-groceries-store/commits?author=DeKaN" title="Code">💻</a></td>
      <td align="center"><a href="https://github.com/Devendra34"><img src="https://avatars.githubusercontent.com/u/51832211?v=4?s=100" width="100px;" alt="Devendra Varma"/><br /><sub><b>Devendra Varma</b></sub></a><br /><a href="https://github.com/hieuwu/android-groceries-store/commits?author=Devendra34" title="Code">💻</a></td>
      <td align="center"><a href="https://developers.google.com/profile/u/115663579126625722254"><img src="https://avatars.githubusercontent.com/u/85061899?v=4?s=100" width="100px;" alt="Amartya"/><br /><sub><b>Amartya</b></sub></a><br /><a href="#design-AmartyaSingh97" title="Design">🎨</a></td>
      <td align="center"><a href="https://github.com/NiranjanNlc"><img src="https://avatars.githubusercontent.com/u/25600880?v=4?s=100" width="100px;" alt="NLC"/><br /><sub><b>NLC</b></sub></a><br /><a href="#design-NiranjanNlc" title="Design">🎨</a> <a href="https://github.com/hieuwu/android-groceries-store/commits?author=NiranjanNlc" title="Code">💻</a></td>
      <td align="center"><a href="https://github.com/fejd"><img src="https://avatars.githubusercontent.com/u/4249809?v=4?s=100" width="100px;" alt="Fredrik Henricsson"/><br /><sub><b>Fredrik Henricsson</b></sub></a><br /><a href="https://github.com/hieuwu/android-groceries-store/commits?author=fejd" title="Code">💻</a></td>
    </tr>
  </tbody>
</table>

<!-- markdownlint-restore -->
<!-- prettier-ignore-end -->

<!-- ALL-CONTRIBUTORS-LIST:END -->

This project follows the [all-contributors](https://github.com/all-contributors/all-contributors) specification. Contributions of any kind welcome!
