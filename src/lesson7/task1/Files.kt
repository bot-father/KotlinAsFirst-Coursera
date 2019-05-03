@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson7.task1

import java.io.File

/**
 * Пример
 *
 * Во входном файле с именем inputName содержится некоторый текст.
 * Вывести его в выходной файл с именем outputName, выровняв по левому краю,
 * чтобы длина каждой строки не превосходила lineLength.
 * Слова в слишком длинных строках следует переносить на следующую строку.
 * Слишком короткие строки следует дополнять словами из следующей строки.
 * Пустые строки во входном файле обозначают конец абзаца,
 * их следует сохранить и в выходном файле
 */
fun alignFile(inputName: String, lineLength: Int, outputName: String) {
    val outputStream = File(outputName).bufferedWriter()
    var currentLineLength = 0
    for (line in File(inputName).readLines()) {
        if (line.isEmpty()) {
            outputStream.newLine()
            if (currentLineLength > 0) {
                outputStream.newLine()
                currentLineLength = 0
            }
            continue
        }
        for (word in line.split(" ")) {
            if (currentLineLength > 0) {
                if (word.length + currentLineLength >= lineLength) {
                    outputStream.newLine()
                    currentLineLength = 0
                }
                else {
                    outputStream.write(" ")
                    currentLineLength++
                }
            }
            outputStream.write(word)
            currentLineLength += word.length
        }
    }
    outputStream.close()
}

/**
 * Средняя
 *
 * Во входном файле с именем inputName содержится некоторый текст.
 * На вход подаётся список строк substrings.
 * Вернуть ассоциативный массив с числом вхождений каждой из строк в текст.
 * Регистр букв игнорировать, то есть буквы е и Е считать одинаковыми.
 *
 */
fun countSubstrings(inputName: String, substrings: List<String>): Map<String, Int> {
    val result = mutableMapOf<String,Int>()
    val textToSearch = File(inputName).readText()
    substrings.forEach {
        var xy = textToSearch
        result[it] = 0
        do{
            if(xy.startsWith(it, true)) result[it] = (result[it] ?: 0) + 1
            xy = xy.drop(1)
            }while(xy.length >= it.length) }
return result}


/**
 * Средняя
 *
 * В русском языке, как правило, после букв Ж, Ч, Ш, Щ пишется И, А, У, а не Ы, Я, Ю.
 * Во входном файле с именем inputName содержится некоторый текст на русском языке.
 * Проверить текст во входном файле на соблюдение данного правила и вывести в выходной
 * файл outputName текст с исправленными ошибками.
 *
 * Регистр заменённых букв следует сохранять.
 *
 * Исключения (жюри, брошюра, парашют) в рамках данного задания обрабатывать не нужно
 *
 */
fun sibilants(inputName: String, outputName: String) {
    val outputStream = File(outputName).bufferedWriter()
    var txtIn = File(inputName).readText()
    do {
        when{
            txtIn.startsWith("жы", true) -> {outputStream.write("%c%c".format(txtIn[0], (txtIn[1].toInt() - 19).toChar()))
                txtIn = txtIn.drop(2)}
            txtIn.startsWith("шы", true) -> {outputStream.write("%c%c".format(txtIn[0], (txtIn[1].toInt() - 19).toChar()))
                txtIn = txtIn.drop(2)}
            txtIn.startsWith("жя", true) -> {outputStream.write("%c%c".format(txtIn[0], (txtIn[1].toInt() - 31).toChar()))
                txtIn = txtIn.drop(2)}
            txtIn.startsWith("щя", true) -> {outputStream.write("%c%c".format(txtIn[0], (txtIn[1].toInt() - 31).toChar()))
                txtIn = txtIn.drop(2)}
            txtIn.startsWith("шя", true) -> {outputStream.write("%c%c".format(txtIn[0], (txtIn[1].toInt() - 31).toChar()))
                txtIn = txtIn.drop(2)}
            txtIn.startsWith("жю", true) -> {outputStream.write("%c%c".format(txtIn[0], (txtIn[1].toInt() - 11).toChar()))
                txtIn = txtIn.drop(2)}
            txtIn.startsWith("щю", true) -> {outputStream.write("%c%c".format(txtIn[0], (txtIn[1].toInt() - 11).toChar()))
                txtIn = txtIn.drop(2)}
            txtIn.startsWith("шю", true) -> {outputStream.write("%c%c".format(txtIn[0], (txtIn[1].toInt() - 11).toChar()))
                txtIn = txtIn.drop(2)}
            txtIn.startsWith("чю", true) -> {outputStream.write("%c%c".format(txtIn[0], (txtIn[1].toInt() - 11).toChar()))
                txtIn = txtIn.drop(2)}
            txtIn.startsWith("чя", true) -> {outputStream.write("%c%c".format(txtIn[0], (txtIn[1].toInt() - 31).toChar()))
                txtIn = txtIn.drop(2)}
            else -> {outputStream.write(txtIn[0].toString())
                txtIn = txtIn.drop(1)}}
    }while (txtIn.isNotEmpty())
    outputStream.close()
}

/**
 * Средняя
 *
 * Во входном файле с именем inputName содержится некоторый текст (в том числе, и на русском языке).
 * Вывести его в выходной файл с именем outputName, выровняв по центру
 * относительно самой длинной строки.
 *
 * Выравнивание следует производить путём добавления пробелов в начало строки.
 *
 *
 * Следующие правила должны быть выполнены:outputStream.close()
 * 1) Пробелы в начале и в конце всех строк не следует сохранять.
 * 2) В случае невозможности выравнивания строго по центру, строка должна быть сдвинута в ЛЕВУЮ сторону
 * 3) Пустые строки не являются особым случаем, их тоже следует выравнивать
 * 4) Число строк в выходном файле должно быть равно числу строк во входном (в т. ч. пустых)
 *
 */
fun centerFile(inputName: String, outputName: String) {
    val inFile = File(inputName)
    var maxStrLength = 0
    val outputStream = File(outputName).bufferedWriter()
    val workMap = mutableListOf<List<String>>()
    for (line in inFile.readLines()) { //разобьём текст на строки и слова, выпилив пробелы в начале строк
        workMap.add(line.dropWhile{it == ' '}.split(" "))
    }
    workMap.forEach { //найдём строку максимальной длины
        val leng = it.fold(0){ total, next -> total + next.length + 1}
        if(leng > maxStrLength) maxStrLength = leng}
    workMap.forEach {
        val spaces = (maxStrLength - it.fold(0){ total, next -> total + next.length + 1}) / 2
        for (i in 1..spaces) outputStream.write(" ")
        outputStream.write(it.joinToString(separator = " "))
        outputStream.newLine()
    }
    outputStream.close()
}

/**
 * Сложная
 *
 * Во входном файле с именем inputName содержится некоторый текст (в том числе, и на русском языке).
 * Вывести его в выходной файл с именем outputName, выровняв по левому и правому краю относительно
 * самой длинной строки.
 * Выравнивание производить, вставляя дополнительные пробелы между словами: равномерно по всей строке
 *
 * Слова внутри строки отделяются друг от друга одним или более пробелом.
 *
 * Следующие правила должны быть выполнены:
 * 1) Каждая строка входного и выходного файла не должна начинаться или заканчиваться пробелом.
 * 2) Пустые строки или строки из пробелов трансформируются в пустые строки без пробелов.
 * 3) Строки из одного слова выводятся без пробелов.
 * 4) Число строк в выходном файле должно быть равно числу строк во входном (в т. ч. пустых).
 *
 * Равномерность определяется следующими формальными правилами:
 * 5) Число пробелов между каждыми двумя парами соседних слов не должно отличаться более, чем на 1.
 * 6) Число пробелов между более левой парой соседних слов должно быть больше или равно числу пробелов
 *    между более правой парой соседних слов.
 *
 * Следует учесть, что входной файл может содержать последовательности из нескольких пробелов  между слвоами. Такие
 * последовательности следует учитывать при выравнивании и при необходимости избавляться от лишних пробелов.
 * Из этого следуют следующие правила:
 * 7) В самой длинной строке каждая пара соседних слов должна быть отделена В ТОЧНОСТИ одним пробелом
 * 8) Если входной файл удовлетворяет требованиям 1-7, то он должен быть в точности идентичен выходному файлу
 */
fun alignFileByWidth(inputName: String, outputName: String) {
    val inFile = File(inputName)
    var maxStrLength = 0
    val outputStream = File(outputName).bufferedWriter()
    val workMap = mutableListOf<List<String>>()
    for (line in inFile.readLines()) { //разобьём текст на строки и слова, выпилив пробелы в начале строк
        workMap.add(line.dropWhile{it == ' '}.split(" "))
    }
    workMap.forEach { //найдём строку максимальной длины
        val leng = it.fold(0){ total, next -> total + next.length + 1}
        if(leng > maxStrLength) maxStrLength = leng}
    workMap.forEach {
        if (it.isNotEmpty()){
            if (it.size != 1){
                var spaces = (maxStrLength - 1 - it.fold(0){ total, next -> total + next.length}) //считаем необходимое кол-во пробелов
                val listOfSpaces = mutableListOf<String>() //формируем список пробелов
                for (j in 1 until it.size) listOfSpaces.add("")
                var i = 0
                do {
                    listOfSpaces[i % listOfSpaces.size] = listOfSpaces[i % listOfSpaces.size] + ' '
                    i++
                    spaces--
                }while (spaces != 0)
                listOfSpaces.forEachIndexed {ind,  es -> //вывод слов и пробелов по очереди
                    outputStream.write(it[ind])
                    outputStream.write(es)
                    }
                outputStream.write(it[it.size - 1]) //вывод последнего слова в строке
            }
            else outputStream.write(it[0])}
        outputStream.newLine()
    }
    outputStream.close()
}

/**
 * Средняя
 *
 * Во входном файле с именем inputName содержится некоторый текст (в том числе, и на русском языке).
 *
 * Вернуть ассоциативный массив, содержащий 20 наиболее часто встречающихся слов с их количеством.
 * Если в тексте менее 20 различных слов, вернуть все слова.
 *
 * Словом считается непрерывная последовательность из букв (кириллических,
 * либо латинских, без знаков препинания и цифр).
 * Цифры, пробелы, знаки препинания считаются разделителями слов:
 * Привет, привет42, привет!!! -привет?!
 * ^ В этой строчке слово привет встречается 4 раза.
 *
 * Регистр букв игнорировать, то есть буквы е и Е считать одинаковыми.
 * Ключи в ассоциативном массиве должны быть в нижнем регистре.
 *
 */
fun top20Words(inputName: String): Map<String, Int> {
    val result = mutableMapOf<String, Int>()
     //разобьём текст на строки и слова, выпилив всё, кроме букв
    val toworkList = File(inputName).readText().replace('.', ' ', true).replace('-', ' ', true).replace('\n', ' ', true).toLowerCase().split(" ")
    // соберём промежуточный связанный список со всеми словами и их повторениями
    toworkList.forEach{if (it.isNotEmpty()){
        val es = it.dropWhile{es -> !es.isLetter()}.dropLastWhile{es -> !es.isLetter()}
        result[es] = (result[es] ?: 0) + 1}}
    //выберем максимальные 20
    val endResult = mutableMapOf<String, Int>()
    var k = ""
    while (result.isNotEmpty() && endResult.size < 20){
        var maxCount = 0
        result.forEach { (s, i) ->
            if (i > maxCount){
            maxCount = i
            k = s
        }}
        if (k.isNotEmpty()) endResult[k] = maxCount
        result.minusAssign(k)
    }
return endResult}

/**
 * Средняя
 *
 * Реализовать транслитерацию текста из входного файла в выходной файл посредством динамически задаваемых правил.

 * Во входном файле с именем inputName содержится некоторый текст (в том числе, и на русском языке).
 *
 * В ассоциативном массиве dictionary содержится словарь, в котором некоторым символам
 * ставится в соответствие строчка из символов, например
 * mapOf('з' to "zz", 'р' to "r", 'д' to "d", 'й' to "y", 'М' to "m", 'и' to "yy", '!' to "!!!")
 *
 * Необходимо вывести в итоговый файл с именем outputName
 * содержимое текста с заменой всех символов из словаря на соответствующие им строки.
 *
 * При этом регистр символов в словаре должен игнорироваться,
 * но при выводе символ в верхнем регистре отображается в строку, начинающуюся с символа в верхнем регистре.
 *
 * Пример.
 * Входной текст: Здравствуй, мир!
 *
 * заменяется на
 *
 * Выходной текст: Zzdrавствуy, mир!!!
 *
 * Пример 2.
 *
 * Входной текст: Здравствуй, мир!
 * Словарь: mapOf('з' to "zZ", 'р' to "r", 'д' to "d", 'й' to "y", 'М' to "m", 'и' to "YY", '!' to "!!!")
 *
 * заменяется на
 *
 * Выходной текст: Zzdrавствуy, mир!!!
 *
 * Обратите внимание: данная функция не имеет возвращаемого значения
 */
fun transliterate(inputName: String, dictionary: Map<Char, String>, outputName: String) {
    val toworkText = File(inputName).readText()
    var resultText = ""
    var change = true
    toworkText.forEach {
        when(it){
            in 'А'..'Я' -> {
                dictionary.forEach { (c, s) ->
                    if (it.equals(c, true)){
                        resultText += s.toLowerCase().capitalize()
                        change = false
                    }
                }}
            in 'а'..'я' -> {
                dictionary.forEach { (c, s) ->
                    if (it.equals(c, true)){
                        resultText += s.toLowerCase()
                        change = false
                    }
                }}
            else -> dictionary.forEach { (c, s) ->
                if (it.equals(c, true)){
                    resultText += s
                    change = false
                }}}
        if (change) resultText += it
        change = true
        }
    val outputStream = File(outputName).bufferedWriter()
    outputStream.write(resultText)
    outputStream.close()
}

/**
 * Средняя
 *
 * Во входном файле с именем inputName имеется словарь с одним словом в каждой строчке.
 * Выбрать из данного словаря наиболее длинное слово,
 * в котором все буквы разные, например: Неряшливость, Четырёхдюймовка.
 * Вывести его в выходной файл с именем outputName.
 * Если во входном файле имеется несколько слов с одинаковой длиной, в которых все буквы разные,
 * в выходной файл следует вывести их все через запятую.
 * Регистр букв игнорировать, то есть буквы е и Е считать одинаковыми.
 *
 * Пример входного файла:
 * Карминовый
 * Боязливый
 * Некрасивый
 * Остроумный
 * БелогЛазый
 * ФиолетОвый

 * Соответствующий выходной файл:
 * Карминовый, Некрасивый
 *
 * Обратите внимание: данная функция не имеет возвращаемого значения
 */
fun chooseLongestChaoticWord(inputName: String, outputName: String) {
    val workMap = mutableMapOf<String, Int>()
    var gotIt = true
    //найдём все слова с разными буквами
    for (line in File(inputName).readLines()) {
        for (i in 0 until line.length - 2){
            for (j in i + 1 until line.length - 1) if (line[i].equals(line[j], true)) gotIt = false
        }
        if (gotIt) workMap[line] = line.length
        gotIt = true
    }
    //выберем длиннейшие
    var maxSize = 0
    val outList = mutableListOf<String>()
    workMap.forEach { (_, i) ->  if (i > maxSize) maxSize = i}
    workMap.forEach{ (s, i) -> if (i == maxSize) outList.add(s)}
    //вывод
    val outputStream = File(outputName).bufferedWriter()
    outputStream.write(outList.joinToString())
    outputStream.close()
}

/**
 * Сложная
 *
 * Реализовать транслитерацию текста в заданном формате разметки в формат разметки HTML.
 *
 * Во входном файле с именем inputName содержится текст, содержащий в себе элементы текстовой разметки следующих типов:
 * - *текст в курсивном начертании* -- курсив
 * - **текст в полужирном начертании** -- полужирный
 * - ~~зачёркнутый текст~~ -- зачёркивание
 *
 * Следует вывести в выходной файл этот же текст в формате HTML:
 * - <i>текст в курсивном начертании</i>
 * - <b>текст в полужирном начертании</b>
 * - <s>зачёркнутый текст</s>
 *
 * Кроме того, все абзацы исходного текста, отделённые друг от друга пустыми строками, следует обернуть в теги <p>...</p>,
 * а весь текст целиком в теги <html><body>...</body></html>.
 *
 * Все остальные части исходного текста должны остаться неизменными с точностью до наборов пробелов и переносов строк.
 * Отдельно следует заметить, что открывающая последовательность из трёх звёздочек (***) должна трактоваться как "<b><i>"
 * и никак иначе.
 *
 * Пример входного файла:
Lorem ipsum *dolor sit amet*, consectetur **adipiscing** elit.
Vestibulum lobortis, ~~Est vehicula rutrum *suscipit*~~, ipsum ~~lib~~ero *placerat **tortor***,

Suspendisse ~~et elit in enim tempus iaculis~~.
 *
 * Соответствующий выходной файл:
<html>
    <body>
        <p>
            Lorem ipsum <i>dolor sit amet</i>, consectetur <b>adipiscing</b> elit.
            Vestibulum lobortis. <s>Est vehicula rutrum <i>suscipit</i></s>, ipsum <s>lib</s>ero <i>placerat <b>tortor</b></i>.
        </p>
        <p>
            Suspendisse <s>et elit in enim tempus iaculis</s>.
        </p>
    </body>
</html>
 *
 * (Отступы и переносы строк в примере добавлены для наглядности, при решении задачи их реализовывать не обязательно)
 */
fun markdownToHtmlSimple(inputName: String, outputName: String) {
    val toworkText = File(inputName).readText()
    var outText = "<html>\n    <body>\n        <p>\n            "
    var i = false
    var b = false
    var s = false
    var j = 0
    while (j < toworkText.length - 1){
        when{
            toworkText[j] == '*' && toworkText[j + 1] == '*' -> {
                if (b) {outText += "</b>"
                    b = false}
                else {outText += "<b>"
                    b = true}
                j += 2}
            toworkText[j] == '*' -> {
                if (i) {outText += "</i>"
                    i = false}
                else {outText += "<i>"
                    i = true}
                j++}
            toworkText[j] == '~' && toworkText[j + 1] == '~' -> {
                if (s) {outText += "</s>"
                    s = false}
                else {outText += "<s>"
                    s = true}
                j += 2}
            toworkText[j] == '\n' && toworkText[j + 1] == '\n' -> {
                outText += "</p>\n        <p>"
                j += 2}
            else -> {outText += toworkText[j]
                j++}
        }
    }
    outText += "        </p>\n    </body>\n</html>"
    //вывод
    val outputStream = File(outputName).bufferedWriter()
    outputStream.write(outText)
    outputStream.close()
}

/**
 * Сложная
 *
 * Реализовать транслитерацию текста в заданном формате разметки в формат разметки HTML.
 *
 * Во входном файле с именем inputName содержится текст, содержащий в себе набор вложенных друг в друга списков.
 * Списки бывают двух типов: нумерованные и ненумерованные.
 *
 * Каждый элемент ненумерованного списка начинается с новой строки и символа '*', каждый элемент нумерованного списка --
 * с новой строки, числа и точки. Каждый элемент вложенного списка начинается с отступа из пробелов, на 4 пробела большего,
 * чем список-родитель. Максимально глубина вложенности списков может достигать 6. "Верхние" списки файла начинются
 * прямо с начала строки.
 *
 * Следует вывести этот же текст в выходной файл в формате HTML:
 * Нумерованный список:
 * <ol>
 *     <li>Раз</li>
 *     <li>Два</li>
 *     <li>Три</li>
 * </ol>
 *
 * Ненумерованный список:
 * <ul>
 *     <li>Раз</li>
 *     <li>Два</li>
 *     <li>Три</li>
 * </ul>
 *
 * Кроме того, весь текст целиком следует обернуть в теги <html><body>...</body></html>
 *
 * Все остальные части исходного текста должны остаться неизменными с точностью до наборов пробелов и переносов строк.
 *
 * Пример входного файла:
///////////////////////////////начало файла/////////////////////////////////////////////////////////////////////////////
* Утка по-пекински
    * Утка
    * Соус
* Салат Оливье
    1. Мясо
        * Или колбаса
    2. Майонез
    3. Картофель
    4. Что-то там ещё
* Помидоры
* Фрукты
    1. Бананы
    23. Яблоки
        1. Красные
        2. Зелёные
///////////////////////////////конец файла//////////////////////////////////////////////////////////////////////////////
 *
 *
 * Соответствующий выходной файл:
///////////////////////////////начало файла/////////////////////////////////////////////////////////////////////////////
<html>
  <body>
    <ul>
      <li>
        Утка по-пекински
        <ul>
          <li>Утка</li>
          <li>Соус</li>
        </ul>
      </li>
      <li>
        Салат Оливье
        <ol>
          <li>Мясо
            <ul>
              <li>
                  Или колбаса
              </li>
            </ul>
          </li>
          <li>Майонез</li>
          <li>Картофель</li>
          <li>Что-то там ещё</li>
        </ol>
      </li>
      <li>Помидоры</li>
      <li>
        Фрукты
        <ol>
          <li>Бананы</li>
          <li>
            Яблоки
            <ol>
              <li>Красные</li>
              <li>Зелёные</li>
            </ol>
          </li>
        </ol>
      </li>
    </ul>
  </body>
</html>
///////////////////////////////конец файла//////////////////////////////////////////////////////////////////////////////
 * (Отступы и переносы строк в примере добавлены для наглядности, при решении задачи их реализовывать не обязательно)
 */
fun markdownToHtmlLists(inputName: String, outputName: String) {
    TODO()
}

/**
 * Очень сложная
 *
 * Реализовать преобразования из двух предыдущих задач одновременно над одним и тем же файлом.
 * Следует помнить, что:
 * - Списки, отделённые друг от друга пустой строкой, являются разными и должны оказаться в разных параграфах выходного файла.
 *
 */
fun markdownToHtml(inputName: String, outputName: String) {
    TODO()
}

/**
 * Средняя
 *
 * Вывести в выходной файл процесс умножения столбиком числа lhv (> 0) на число rhv (> 0).
 *
 * Пример (для lhv == 19935, rhv == 111):
   19935
*    111
--------
   19935
+ 19935
+19935
--------
 2212785
 * Используемые пробелы, отступы и дефисы должны в точности соответствовать примеру.
 * Нули в множителе обрабатывать так же, как и остальные цифры:
  235
*  10
-----
    0
+235
-----
 2350
 *
 */
fun printMultiplicationProcess(lhv: Int, rhv: Int, outputName: String) {
    TODO()
}


/**
 * Сложная
 *
 * Вывести в выходной файл процесс деления столбиком числа lhv (> 0) на число rhv (> 0).
 *
 * Пример (для lhv == 19935, rhv == 22):
  19935 | 22
 -198     906
 ----
    13
    -0
    --
    135
   -132
   ----
      3

 * Используемые пробелы, отступы и дефисы должны в точности соответствовать примеру.
 *
 */
fun printDivisionProcess(lhv: Int, rhv: Int, outputName: String) {
    TODO()
}

