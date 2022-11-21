# <a target="_blank" href="https://github.com/DenZhICT/APIPartOfQAProject"><img src="images/screen/Reqres.png" alt="DNS" width="55" height="35"/>Проект: API Автоматизации тестирования Reqres</a>

## :scroll: Содержание:

- [Технологии и инструменты](#hammer_and_wrench-технологии-и-инструменты)
- [Реализованные проверки](#memo-реализованные-проверки)
- [Сборка в Jenkins](#man_technologist-jenkins-job)
- [Запуск из терминала](#desktop_computer-Запуск-тестов-из-терминала)
- [Allure отчет](#chart_with_upwards_trend-отчет-в-allure-report)
- [Отчёт в Allure TestOps](#bar_chart-отчет-в-allure-testops)
- [Отчет в Telegram](#grapes-уведомление-в-telegram-при-помощи-бота)

## :hammer_and_wrench: Технологии и инструменты

<p align="center">
<a href="https://www.jetbrains.com/idea/"><img src="images/logo/Intelij_IDEA.svg" width="50" height="50"  alt="IDEA"/></a>
<a href="https://www.java.com/"><img src="images/logo/Java.svg" width="50" height="50"  alt="Java"/></a>
<a href="https://github.com/"><img src="images/logo/GitHub.svg" width="50" height="50"  alt="Github"/></a>
<a href="https://junit.org/junit5/"><img src="images/logo/JUnit5.svg" width="50" height="50"  alt="JUnit 5"/></a>
<a href="https://gradle.org/"><img src="images/logo/Gradle.svg" width="50" height="50"  alt="Gradle"/></a>
<a href="https://rest-assured.io"><img src="images/logo/RestAssured.svg" width="50" height="50"  alt="RestAssured"/></a>
<a href="https://github.com/allure-framework/allure2"><img src="images/logo/Allure_Report.svg" width="50" height="50"  alt="Allure"/></a>
<a href="https://qameta.io"><img src="images/logo/Allure_TO.svg" width="50" height="50"  alt="Allure TestOps"/></a>
<a href="https://www.jenkins.io/"><img src="images/logo/Jenkins.svg" width="50" height="50"  alt="Jenkins"/></a>
<a href="https://telegram.org"><img src="images/logo/Telegram.svg" width="50" height="50"  alt="Telegram"/></a>
</p>

## :memo: Реализованные проверки

- :white_check_mark: Проверка фамилии пользователя по id.
- :white_check_mark: Проверка о 404 ошибке.
- :white_check_mark: Проверка создания нового пользователя.
- :white_check_mark: Проверка удаления пользователя.
- :white_check_mark: Проверка регистрации.
- :white_check_mark: Проверка существования пантон-цвета.

## :man_technologist: Jenkins job

<a target="_blank" href="https://jenkins.autotests.cloud/job/DenisZhICT_%20UIPartOfQAProject_qa.guru14/">Сборка в Jenkins</a>
<p align="center">
<img src="images/screen/jenkins_job.png" alt="Jenkins"/>
</p>

### Параметры сборки в Jenkins:

* test_type (Определят тип запускаемых тестов по Tag'ам)

### :desktop_computer: Запуск тестов из терминала

```bash
gradle clean All
```

### :globe_with_meridians: Удаленный запуск:

```bash
clean 
${TEST_TYPE}
```

## :chart_with_upwards_trend: Отчет в <a target="_blank" href="https://jenkins.autotests.cloud/job/DenisZhICT_%20UIPartOfQAProject_qa.guru14/9/allure/">Allure report</a>

### Основное окно

<p align="center">
<img title="Allure Overview Dashboard" src="images/screen/allure_main.png">
</p>

## :bar_chart: Отчет в <a target="_blank" href="https://allure.autotests.cloud/launch/16977/tree?treeId=0">Allure TestOps</a>

### Test Result Tree

<p align="center">
<img title="Allure Test Ops Overview TaskList" src="images/screen/allure_to.png">
</p>

## :grapes: Уведомление в Telegram при помощи бота

<p align="center">
<img title="Allure Overview Dashboard" src="images/screen/allure_telegram.png">
</p>