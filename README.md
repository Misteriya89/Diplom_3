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
|   |-- resources
|   |   |-- drivers
|   |   |   |-- chromedriver.exe
|   |   |   |-- yandexdriver.exe
|-- test
|   |-- java
|   |   |-- tests
|   |   |   |-- LoginTest.java
|   |   |   |-- RegistrationTest.java
|   |   |   |-- TransitionInConstructorTest.java
|   |   |   |-- TransitionInProfilePageTest.java
|   |   |-- pages
|   |   |   |-- LoginPage.java
|   |   |   |-- MainPage.java
|   |   |   |-- ProfilePage.java
|   |   |   |-- RecoverPasswordPage.java
|   |   |   |-- RegisterPage.java
.gitignore
pom.xml
README.md
```