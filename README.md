<!-- PROJECT LOGO -->
<br />
<div align="center">
  <a href="https://github.com/GryaznovAS/Splay">
    <img src="https://media.discordapp.net/attachments/760917929126133834/1146401820374204446/Splay-512.png" alt="Logo" width="128" height="128">
  </a>

  <h3 align="center">Splay</h3>

  <p align="center">
    Implementation of the Splay tree.
    <br />
    <br />
    <a href="https://t.me/ASpectreTG">Report Bug</a>
    ·
    <a href="https://t.me/ASpectreTG">Request Feature</a>
  </p>
</div>



<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#immediate-tasks">Immediate Tasks</a></li>
        <li><a href="#used-technologies">Used Technologies</a></li>
      </ul>
    </li>
    <li><a href="#getting-started">Getting Started</a></li>
    <li><a href="#app-usage">App Usage</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#acknowledgments">Acknowledgments</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project

Many people use search engines to categorize various information as well as quickly get the data they need.
Such algorithms are based on binary search trees, one of which is the Splay tree, which is implemented in this repository.

Of course, my task is not only to develop the algorithm of the application itself, but also to implement a user interface for working with it, create test coverage and decent documentation.

<p align="right">(<a href="#splay">Back to top</a>)</p>



### Immediate Tasks

* Setting up a GitHub repository.
* Development of the project structure.
* Work on architecture.
* Setting up a CI project.
* Development of a tree model.
* Work on saving data.
* Writing tests for the project.
* Rendering of the graphic part.
* Application development.
* Writing documentation.

<p align="right">(<a href="#splay">Back to top</a>)</p>



### Used Technologies

Technologies used to develop the project:

* [![gradle](https://img.shields.io/badge/gradle-FFFFFF?style=for-the-badge&logo=gradle&logoColor=black&)](https://gradle.org/) - To build the project.
* [![gradle](https://img.shields.io/badge/kotlin-FFFFFF?style=for-the-badge&logo=kotlin&logoColor=black&)](https://kotlinlang.org/) - The language of the project code.
* [![gradle](https://img.shields.io/badge/junit-FFFFFF?style=for-the-badge&logo=junit&logoColor=black&)](https://junit.org/) - To test the project.
* [![gradle](https://img.shields.io/badge/sqlite-FFFFFF?style=for-the-badge&logo=sqlite&logoColor=black&)](https://www.sqlite.org/index.html) - To save data.
* [![gradle](https://img.shields.io/badge/compose-FFFFFF?style=for-the-badge&logo=compose&logoColor=black&)](https://www.jetbrains.com/ru-ru/lp/compose-multiplatform/) - Graphical application platform.

<p align="right">(<a href="#splay">Back to top</a>)</p>



<!-- GETTING STARTED -->
## Getting Started

To start working with my development, you need to clone repository:

* git

  ```sh
  git clone https://github.com/GryaznovAS/Splay.git
  ```

To initialize the tree and start working with it, you need to know the following line:

* Initializing simple BinarySearchTree:

  ```kotlin
  val tree = Splay<KeyType, ValueType>()
  ```

To work with tree, you also need to know the management commands:

* Inserting a value by key:

  ```kotlin
  tree[key] = value
  ```

* Getting a value by key:

  ```kotlin
  val value = tree[key]
  ```

* Deleting a value by key:

  ```kotlin
  tree.remove(key)
  ```

<!-- APP USAGE -->
## App Usage

Before launching the application, you need to build it:

* This is done by the command:

```sh
./gradlew run
```

A little bit about the user interface:

* In the upper left corner there are three fields with buttons: "add vertex", "remove vertex" and "find vertex".
* In the upper right corner there are three buttons: "save" (tree), "delete" (tree) and "change interface color".
* In the lower left corner there are buttons for zooming in and out of the tree.
* In the lower right corner there is a button that will take you to the portal with information about the project, to this repository.

* You can also move the vertices by holding them and move the tree around the window.

<img src="https://media.discordapp.net/attachments/760917929126133834/1146507861132853258/image.png" alt="Main" width="768" height="387" align="center">

<p align="right">(<a href="#splay">Back to top</a>)</p>



<!-- LICENSE -->
## License

Distributed under the Apache License V2.0. See `LICENSE` for more information.

<p align="right">(<a href="#splay">Back to top</a>)</p>



<!-- ACKNOWLEDGMENTS -->
## Acknowledgments

Resources that I used to get information about the project being developed:

* [Splay Tree](https://en.wikipedia.org/wiki/AVL_tree)
* [JUnit Docs](https://junit.org/junit5/docs/current/user-guide/)
* [Gradle Docs](https://docs.gradle.org/current/userguide/userguide.html)
* [Search Trees](https://en.wikipedia.org/wiki/Search_tree)
* [Compose Docs](https://developer.android.com/jetpack/compose/documentation)
* [Apache License](https://www.apache.org/licenses/LICENSE-2.0)
* [Mergeable Docs](https://mergeable.readthedocs.io/en/latest/configuration.html#basics)
* [Conventional Commits](https://www.conventionalcommits.org/en/v1.0.0/)

<p align="right">(<a href="#splay">Back to top</a>)</p>