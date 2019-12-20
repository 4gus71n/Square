### Square Example

Simple example app that shows a list of repositories from the Square Github account.

#### Architecture

Simple **multi-module MVVM** architecture. The **base** module contains everything related to styles. The **core** module containes the core components of the app (API, Repositories, Interactors, Model classes, etc.) and exposes the **Interactors** to the **feature modules**. The app module is the deployable module, since this is a pretty straightforward example, there's no feature modules. Only the app module.

#### Tech stack

- Retrofit as the REST client.
- Dagger for DI
- Gson to map the backend responses.

#### Quick demo


#### Apk


#### Notes

Feel free to ask any question as an issue on this repo.


