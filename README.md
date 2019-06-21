# Flickr Findr

App that gets and displays images using the Flickr api.


## How it works

After doing a search, the ViewModel calls a Retrofit service which returns a "Photos" object in a Flowable.
The ViewModel then stores the "Photos" object as LiveData.
Only 25 photos are returned at this time.
The ImageSearchFragment is observing the LiveData, and when it changes it updates the recyclerview with thumbnails and titles.
An additional 25 images are loaded when you arrive at 15 items away from then end of the recycler view.
Clicking on a photo loads a higher resolution version of the images and displays it as large as it can on your phone.

### Libraries

* Dagger: dependency injection
* Picasso: loading the images from Flickr
* Retrofit: performing api calls
* LiveData: storing the search results in a viewmodel
* Epoxy: building views inside of a recyclerview
* Mockito: light testing

### Java Files

The project is done mostly in Kotlin, but per the instructions two files are written in Java.
The ImageDetailsFragment and the Photos entity.
