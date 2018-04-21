
BroCast
===============================

BroCast is the new instant messaging app that all the cool kids use! It simplifies instant messaging to the bare essentials by removing text, pictures and videos and leaving only emoticons!

Team members
-------------------------------

* Sander Kools (@Grabot)
* Yoeri Houtzager (@yhoutzager)
* Jasper Visser (@jasperav)
* Mark Verleg (@mverleg)

Context
-------------------------------

This is an entry for the 2018 Keylane Hackathon in Utrecht!

Install
-------------------------------

* Install nodejs and npm
* Go to directory /firebase/functions
* ``npm i``
* ``npm i -g firebase-tools``
* ``firebase login``

When changing any Firebase rules, to deploy:

    ``firebase deploy --only firestore:rules``

To launch the app from Android Studio:

* Install an emulator or use a phone of API level 22 or higher
* Run configuration
* Module: app
* Run
