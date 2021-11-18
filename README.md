# BookFinder-App
# Overview
Designed and created an android Kotlin app that allows the user to save books in database and retrieve them in the main screen. The user can also filter the books based on the reading status
or seach for a specific book name/ author.
# Features
- The app consists of 2 screens: Main Screen that displays all saved books, DetailScreen to save a new book. The user can also click on a book in the Main Screen to navigate to the Detail Screen and update the book.

![1](https://user-images.githubusercontent.com/35550711/142439135-4306617c-dd95-4278-bbc8-c67784a5b7e6.jpg)
     ![2](https://user-images.githubusercontent.com/35550711/142439290-aee851c9-eb29-406c-b2bd-a068c03fd27c.jpg)

- A Custom navigation drawer with Motion Layout was implemented to display an animation of the items when opening or closing the drawer. The user can filter the saved books based on the reading status. 

![3](https://user-images.githubusercontent.com/35550711/142439653-63ea4796-3471-4682-bd7e-f888da4d2a60.jpg)
- The user can search a book by name or author and a list of corresponding books will be displayed on a recyclerview.
 
![4](https://user-images.githubusercontent.com/35550711/142440226-99453920-1cb4-4114-a866-67faca75168e.jpg)
![5](https://user-images.githubusercontent.com/35550711/142440251-2503b372-3793-4a09-8e79-06b36009196b.jpg)
- When the user swipes a book right or left, the book will be deleted from database
 
![6](https://user-images.githubusercontent.com/35550711/142440623-dbd322d6-6368-463d-b05a-1ee902c4828b.jpg)




# Built With
- AndroidX Room
- AndroidX Navigation
- KotlinX Coroutines
- AndroidX ViewModel
- AndroidX RecyclerView
- MotionLayout

# License
Copyright 2021 Mariem Mezghani

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
