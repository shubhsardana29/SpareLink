![ Xiaomi Banner ](https://github.com/shubhsardana29/SpareLink/assets/52607235/87ae1c4c-7908-48bc-aee8-5d6fdb52c1c5)

## Table of Contents

- [Introduction](#introduction)
- [Tech Stack](#tech-stack)
- [Database ER Diagram](#database-er-diagram)
- [Prerequisites](#prerequisites)
- [Backend Setup](#backend-setup)
  - [1. Go to the Backend Directory](#1-go-to-the-backend-directory)
  - [2. Configure the Backend](#2-configure-the-backend)
  - [3. Build and Run](#3-run)
- [Frontend Setup](#frontend-setup)
  - [1. Go to the Frontend Directory](#1-go-to-the-frontend-directory)
  - [2. Build and Run](#2-build-and-run)

## Introduction

<div align="center">
  <img src="https://github.com/shubhsardana29/SpareLink/assets/52607235/f3f5bfc8-e4ac-42d2-8f45-afd9a64c8502" alt="SpareLink Logo" width=500 height = 500/>
</div>
SpareLink is a comprehensive software solution designed to streamline spare part planning, visibility, and assignment for aftersales stakeholders. It enables efficient spare part management for service centers, planning teams, warehouse teams, and customer support, ultimately improving customer experience and reducing manual material allocation.

[![Intro Video](link-to-your-intro-video-thumbnail)](https://github.com/shubhsardana29/SpareLink/assets/52607235/c3e9ab7f-74da-4235-986a-fc08c810583c)

Watch our [Introductory Video](https://github.com/shubhsardana29/SpareLink/assets/52607235/c3e9ab7f-74da-4235-986a-fc08c810583c) to see how SpareLink can transform your aftersales process.

## Tech Stack

### Backend (Java Spring Boot)

- **Java** 
- **Spring Boot** 
- **Maven** 
- **MySQL** 

### Frontend (React)

- **React:** 
- **Tailwind CSS** 
- **Yarn**
  
## Database ER Diagram
![erdiagram](https://github.com/shubhsardana29/SpareLink/assets/52607235/e67a7e92-5c36-4ada-b712-96354cfc66a5)

## Prerequisites

Before you begin, ensure you have met the following requirements:

- [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/javase-downloads.html)
- [Apache Maven](https://maven.apache.org/download.cgi)
- [Node.js](https://nodejs.org/en/download/)
- [Yarn](https://classic.yarnpkg.com/en/docs/install/)

Clone the Repository:

```bash
git clone https://github.com/shubhsardana29/SpareLink.git
cd SpareLink
```

## Backend Setup

### 1. Go to the Backend Directory

```bash
cd SpareLink_backend
```

### 2. Configure the Backend

In the `SpareLink_backend/src/main/resources` directory, you'll find the application.properties file. Open this file and configure the database connection settings according to your setup. Replace the following placeholders:

- `${DATABASE_HOST}`: Replace with your database host.
- `${DATABASE_PORT}`: Replace with your database port.
- `${DATABASE_NAME}`: Replace with your database name.
- `${DATABASE_USERNAME}`: Replace with your database username.
- `${DATABASE_PASSWORD}`: Replace with your database password.

### 3. Run

```bash
./mvnw spring-boot:run
```

The backend will run at `http://localhost:8080`.

## Frontend Setup

### 1. Go to the Frontend Directory

```bash
cd SpareLink_frontend
```

### 2. Build and Run

```bash
yarn install
yarn start
```

The frontend will run at `http://localhost:5173`.

## Backend API Endpoints

- `http://localhost:8080/api/users`: Create new user.
- `http://localhost:8080/login`: Login User.
- `http://localhost:8080/api/get-role`: Get user team/role.
- `http://localhost:8080/getpartrequests`: Get part requests for a particular service center.
- `http://localhost:8080/getallpartrequests`: Get All part requests for planning team.
- `http://localhost:8080/getalljobsheets`: Get all jobsheets for a particular service center.
- `http://localhost:8080/createjobsheet`: Create new jobsheet for a service center user.
- `http://localhost:8080/createpartrequest`: Create new part request from service center.

