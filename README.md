# Swap & Style 👗✨

## Table of Contents
1. [General Info](#general-info)
2. [Technologies](#technologies)
3. [Installation](#installation)


### General Info
***
**Status: In Development** Swap&Style is a digital platform designed for swapping second-hand clothes in a simple, economical, and sustainable way. The project aims to promote conscious consumption, reduce textile waste, and build a community where circular fashion is accessible and fun.

Users can publish garments, browse items from others, and manage reservations. The application follows a social media-inspired interface where any user can create and exchange items.

**Key Features:**
* Full CRUD for clothing items (Create, Read, Update, Delete(Future implementation)).
* Advanced filtering by category and date.
* Item reservation system with concurrency protection.
* Responsive design (Desktop, Tablet, and Mobile).
* Secure authentication and form validation.
* Smtp- simple mail transfer protocol

## Technologies
***
A list of technologies used within the project:
* [Maven-SpringBoot]
* [PostgreSQL.pgAdmin]
* [Java JDK: v21]
* [Cloudinary SDK: images manage.]
* [Lombok]
* [Spring Data JPA: Persistence]
* [Mapper Structure]

** Respect the layering pattern (`Controller` -> `Service` -> `Repository`).

**Immutability:** The use of **Java Records** is mandatory for any DTO class that transports data.

**Style:** Follow Spring naming conventions (CamelCase) and keep your code free of Lombok warnings.

**Pull Requests:** Before proposing a change, ensure the project compiles correctly with `./mvnw clean comp`.



## Installation
***

To run this project locally, you will need to set up both the Frontend and the Backend.

**1. Backend Setup:**
* Locate the Frontend repository in the [FemCoderP8-Nebula organization](https://github.com/FemCoderP8-Nebula).

**Frontend Setup:**
```bash
$git clone [https://github.com/FemCoderP8-Nebula/Back_Swap-Style.git$](https://github.com/FemCoderP8-Nebula/Back_Swap-Style.git$)
cd Back_Swap-Style
$ ./mvnw spring-boot:run
