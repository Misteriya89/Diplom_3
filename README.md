# Diplom_3

Учебный проект по автотестированию UI

## Описание

Версия Java 11.

В проекте тестируется функциональность в Google Chrome и Яндекс.Браузере.

### Проект использует следующие библиотеки:

* JUnit 4
* Selenium
* Allure


### Документация

[Ссылка](https://stellarburgers.nomoreparties.site/)  на учебное приложение

### Запуск автотестов

Для запуска автотестов необходимо:

### Скачать код

**git clone https://github.com/Misteriya89/Diplom_3.git**

### Запустить команду в проекте

**mvn clean test**

### Для создания отчета в Allure ввести команду

**mvn allure:report**

### Структура проекта

```
src
|-- main
|   |-- java
|   |   |-- pageobject
|   |   |   |-- ForgotPasswordPage.java
|   |   |   |-- LoginPage.java
|   |   |   |-- MainPage.java
|   |   |   |-- ProfilePage.java
|   |   |   |-- RegisterPage.java
|   |   |-- user
|   |   |   |-- User.java
|   |   |   |-- UserDataGenerator.java
|   |   |   |-- UserLogin.java
|   |   |   |-- UserSteps.java
|   |-- resources
|   |   |-- drivers
|   |   |   |-- chromedriver.exe
|   |   |   |-- yandexdriver.exe
|-- test
|   |-- java
|   |   |-- test
|   |   |   |-- ConstructorEnterTest.java
|   |   |   |-- ConstructorTest.java
|   |   |   |-- LoginTest.java
|   |   |   |-- LogoutTest.java
|   |   |   |-- ProfilePageEnterTest.java
|   |   |   |-- UserRegisterTest.java
.gitignore
pom.xml
README.md
```