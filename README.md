## Задание 3 (прасер +  анализ неиспользуемых присваиваний)

Запустить анализатор можно так (принимает путь до файла):
```
./gradlew :dataflow:run --args="src/test/data/test4.input"
```
Ожидаемый вывод:
```
> Task :dataflow:run
Assignment y = x not used in line 3
Assignment x = 2 not used in line 7
```

Так же можно запустить тесты (тестовые данные находятся вот [здесь](https://github.com/simpletonDL/InternshipJB/tree/cpp_data_flow/dataflow/src/test/data)):
```
./gradlew :dataflow:test
```

## Задание 2 (DSL для задания кривых)
Сделал небольшое расширение для библиотеки [plotly](https://github.com/mipt-npm/plotly.kt). Пример находится вот [тут](https://github.com/simpletonDL/InternshipJB/blob/cpp_data_flow/curves/src/main/kotlin/main.kt).
Его можно запустить:
```
./gradlew :curves:run
```
