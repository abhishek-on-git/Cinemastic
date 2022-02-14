# Cinemastic

A TMDB client app that displays the currently popular Movies, TV Shows and Artists. 
<p align="center">
<img src="https://github.com/abhishek-on-git/Cinemastic/blob/master/Cinemastic_gif.gif" width = "250">
</p>

This is a good example of how to use clean architecture for your android apps.
* The app follows the clean architectural style.
  Architectural components:
  - Presentation: Includes all UI elements and the ViewModel (MVVM architecture).
  - Domain: Includes all your app's usecases and the repository interfaces (Not the implementations).
  - Data: Includes repository, models, remote, local and cache datasources, Room DB class and Data Access Objects and the remote api service for Retrofit.
* After fetching the data from TMDB endpoints, the app saves it in a local database using Room Library.
* Uses Dagger2 framework for dependency injection.
