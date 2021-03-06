## Описание задачи
Создать программу обработки текста учебника по программированию с использованием классов Слово (последовательность символов разделенная пробелами и знаками препинания), Предложение (последовательность символов разделенная символами “.”, “!”, “?” или переносом строки) и Текст (полный текст учебника). Заменить табуляции и последовательности пробелов одним пробелом.
## Требования
1. Текст учебника должен считываться с txt-файла.
    1. Сделать копию (backup) оригинального файла копированием его в отдельную директорию (использование байтовых потоков ввода/вывода и методов класса java.io.File).
    Структура проекта:
    ```	
	    App/
	        |-src/
	            |-...
	                |-resources/
	                    |-original/
	                        |-book.txt
	                    |-backup/
	                        |-book.bak
    ```
    2. Считать текст из оригинального файла (использование символьных потоков ввода/вывода) и выполнить его разбор.
        1. Разбор необходимо осуществлять с помощью регулярных выражений и механизма работы с ними в Java.
        2. Регулярные выражения необходимо хранить отдельно от кода в properties-файле.
        3. Регулярные выражения получать с помощью класса ResourceBundle.
    3. Построчно вывести результат разбора текста в оригинальный файл, дописав его (использование символьных потоков ввода/вывода). Формат текста описан в разделе [формат](#Формат)
2.	Программа должна корректно реагировать на возникающие исключения (не существует файла, не закрыт поток и т.д.).
3.	Задание должно быть выполнено со всеми требованиями к приложениям, написанным в ООП стиле, соблюдайте правила конструирования классов и приницпы SOLID.
## Формат
Вывести все слова предложений заданного текста в порядке возрастания количества слов в каждом из них. Выводиться должны только слова предложения (без знаков препинания, разделитель - пробел), суммарная длина LENGTH символов (LENGTH считать из аргументов командной строки). Если длина слов превышает LENGTH символов, то вывести только первые слова, не превышающие эту длину. То есть если LENGTH = 15, а предложение - “Word1 word2 word3”, то должно быть выведено “Word1 word2”, а не “Word1 word2 wor”. 
Пример вывода:
```
|=============================================================================|
|    №     |                     Sentence                     | Word's Count  |
|=============================================================================|
|    1     |                   That's all                     |       2       |
|-----------------------------------------------------------------------------|
|    2     |           Do you have more questions             |       5       |
|-----------------------------------------------------------------------------|
|    3     |    Just ask your trainers they can help you      |       8       |
|-----------------------------------------------------------------------------|
|    4     |       Click "Build Path" and then choose         |       9       |
|-----------------------------------------------------------------------------|
|    5     |        Are you curious about making you          |      11       |
|-----------------------------------------------------------------------------|
|    6     |      This number in next word requi4red to       |      15       |
|-----------------------------------------------------------------------------|
|    7     |      In Eclipse use the Java Build Path to       |      16       |
|-----------------------------------------------------------------------------|
|    8     |    Eclipse has several options for building      |      16       |
|-----------------------------------------------------------------------------|
|    9     |      You can also edit your classpaths in        |      16       |
|-----------------------------------------------------------------------------|
|    10    |     Click Add Folder to add the source that      |      19       |
|-----------------------------------------------------------------------------|
|    11    |     Right click on the name of the project       |      21       |
|-----------------------------------------------------------------------------|
```
## Дополнительное задание
Реализовать возможность сохранения результатов работы парсера с помощью механизма сериализации.