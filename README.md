### Square Example

Simple example app that shows a list of repositories from the Square Github account.

#### Architecture

Simple **multi-module MVVM** architecture. The **base** module contains everything related to styles. The **core** module containes the core components of the app (API, Repositories, Interactors, Model classes, etc.) and exposes the **Interactors** to the **feature modules**. The app module is the deployable module, since this is a pretty straightforward example, there's no feature modules. Only the app module.

#### Tech stack

- Retrofit as the REST client.
- Dagger for DI
- Gson to map the backend responses.
- RxJava to expose the repositories.

#### Quick demo

![example](https://github.com/4gus71n/Square/blob/master/SquareExampleVid.gif?raw=true)

##### Bookmarking 

![example](https://github.com/4gus71n/Square/blob/master/Demo2.gif?raw=true)

#### Apk

[Download the apk!](https://github.com/4gus71n/Square/blob/master/app-debug.apk?raw=true "Download the apk!")

#### Notes

- Feel free to ask any question as an issue on this repo.
- This architecture it's a copy of a set of examples that I'm using on a Medium article that I'm writing. This is the original [repo](https://github.com/4gus71n/Examples). The article goes about the does and donts when developing a large-scale app.

##### Update #1

- Added the bookmarking feature. I implemented the cache just using the SharedPreferences. I'm probably going to start a new PR with a different, more scalable solution.

##### Update #2

- Merged into master a new implementation where we use Room instead of the SharedPreferences to keep track of the bookmarked repositories. I'm keeping both branches so we can see both implementations; The SharedPreferences implementation on features/bookmarks and the Room implementation on features/bookmarks_room. I'm also updating the demo apk.


