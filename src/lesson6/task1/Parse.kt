@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson6.task1

/**
 * Пример
 *
 * Время представлено строкой вида "11:34:45", содержащей часы, минуты и секунды, разделённые двоеточием.
 * Разобрать эту строку и рассчитать количество секунд, прошедшее с начала дня.
 */
fun timeStrToSeconds(str: String): Int {
    val parts = str.split(":")
    var result = 0
    for (part in parts) {
        val number = part.toInt()
        result = result * 60 + number
    }
    return result
}

/**
 * Пример
 *
 * Дано число n от 0 до 99.
 * Вернуть его же в виде двухсимвольной строки, от "00" до "99"
 */
fun twoDigitStr(n: Int) = if (n in 0..9) "0$n" else "$n"

/**
 * Пример
 *
 * Дано seconds -- время в секундах, прошедшее с начала дня.
 * Вернуть текущее время в виде строки в формате "ЧЧ:ММ:СС".
 */
fun timeSecondsToStr(seconds: Int): String {
    val hour = seconds / 3600
    val minute = (seconds % 3600) / 60
    val second = seconds % 60
    return String.format("%02d:%02d:%02d", hour, minute, second)
}

/**
 * Пример: консольный ввод
 */
fun main(args: Array<String>) {
    println("Введите время в формате ЧЧ:ММ:СС")
    val line = readLine()
    if (line != null) {
        val seconds = timeStrToSeconds(line)
        if (seconds == -1) {
            println("Введённая строка $line не соответствует формату ЧЧ:ММ:СС")
        }
        else {
            println("Прошло секунд с начала суток: $seconds")
        }
    }
    else {
        println("Достигнут <конец файла> в процессе чтения строки. Программа прервана")
    }
}


/**
 * Средняя
 *
 * Дата представлена строкой вида "15 июля 2016".
 * Перевести её в цифровой формат "15.07.2016".
 * День и месяц всегда представлять двумя цифрами, например: 03.04.2011.
 * При неверном формате входной строки вернуть пустую строку.
 *
 * Обратите внимание: некорректная с точки зрения календаря дата (например, 30.02.2009) считается неверными
 * входными данными.
 */
fun dateStrToDigit(str: String): String {
    val parts = str.split(" ")
    if (parts.size != 3) return ""
    val day = parts[0].toInt()
    val month = parts[1]
    val year = parts[2].toInt()
    val montDigit = when(month){
        "января" -> 1
        "февраля" -> 2
        "марта" -> 3
        "апреля" -> 4
        "мая" -> 5
        "июня" -> 6
        "июля" -> 7
        "августа" -> 8
        "сентября" -> 9
        "октября" -> 10
        "ноября" -> 11
        "декабря" -> 12
        else -> 13
    }
    if (montDigit == 13) return ""
    if (montDigit == 1 && day > 31) return ""
    if (montDigit == 2){
        if(year % 4 == 0 && (year % 400 == 0 || year % 100 != 0)) {if(day > 29) return ""}
        else if (day > 28) return ""}
    if (montDigit == 3 && day > 31) return ""
    if (montDigit == 4 && day > 30) return ""
    if (montDigit == 5 && day > 31) return ""
    if (montDigit == 6 && day > 30) return ""
    if (montDigit == 7 && day > 31) return ""
    if (montDigit == 8 && day > 31) return ""
    if (montDigit == 9 && day > 30) return ""
    if (montDigit == 10 && day > 31) return ""
    if (montDigit == 11 && day > 30) return ""
    if (montDigit == 12 && day > 31) return ""
    return String.format("%02d.%02d.%d", day, montDigit, year)
}

/**
 * Средняя
 *
 * Дата представлена строкой вида "15.07.2016".
 * Перевести её в строковый формат вида "15 июля 2016".
 * При неверном формате входной строки вернуть пустую строку
 *
 * Обратите внимание: некорректная с точки зрения календаря дата (например, 30 февраля 2009) считается неверными
 * входными данными.
 */
fun dateDigitToStr(digital: String): String {
    val parts = digital.split(".")
    if (parts.size != 3) return ""
    val day :Int
    try {day = parts[0].toInt()}catch (e: NumberFormatException){return ""}
    val month :Int
    try {month = parts[1].toInt()}catch (e: NumberFormatException){return ""}
    val year :Int
    try {year = parts[2].toInt()}catch (e: NumberFormatException){return ""}
    val montStr = when(month){
        1 -> "января"
        2 -> "февраля"
        3 -> "марта"
        4 -> "апреля"
        5 -> "мая"
        6 -> "июня"
        7 -> "июля"
        8 -> "августа"
        9 -> "сентября"
        10 -> "октября"
        11-> "ноября"
        12 -> "декабря"
        else -> "13"
    }
    if (montStr == "13") return ""
    if (month == 1 && day > 31) return ""
    if (month == 2){
        if(year % 4 == 0 && (year % 400 == 0 || year % 100 != 0)) {if(day > 29) return ""}
        else if (day > 28) return ""}
    if (month == 3 && day > 31) return ""
    if (month == 4 && day > 30) return ""
    if (month == 5 && day > 31) return ""
    if (month == 6 && day > 30) return ""
    if (month == 7 && day > 31) return ""
    if (month == 8 && day > 31) return ""
    if (month == 9 && day > 30) return ""
    if (month == 10 && day > 31) return ""
    if (month == 11 && day > 30) return ""
    if (month == 12 && day > 31) return ""
    return String.format("%d %s %d", day, montStr, year)
}

/**
 * Средняя
 *
 * Номер телефона задан строкой вида "+7 (921) 123-45-67".
 * Префикс (+7) может отсутствовать, код города (в скобках) также может отсутствовать.
 * Может присутствовать неограниченное количество пробелов и чёрточек,
 * например, номер 12 --  34- 5 -- 67 -89 тоже следует считать легальным.
 * Перевести номер в формат без скобок, пробелов и чёрточек (но с +), например,
 * "+79211234567" или "123456789" для приведённых примеров.
 * Все символы в номере, кроме цифр, пробелов и +-(), считать недопустимыми.
 * При неверном формате вернуть пустую строку
 */
fun flattenPhoneNumber(phone: String): String{
    var result = ""
    if (phone.startsWith('+')) result = "+"
    for (char in phone){
        if (char in ':'..'z') return ""
        if (char in '0'..'9') result += char
    }
return result}

/**
 * Средняя
 *
 * Результаты спортсмена на соревнованиях в прыжках в длину представлены строкой вида
 * "706 - % 717 % 703".
 * В строке могут присутствовать числа, черточки - и знаки процента %, разделённые пробелами;
 * число соответствует удачному прыжку, - пропущенной попытке, % заступу.
 * Прочитать строку и вернуть максимальное присутствующее в ней число (717 в примере).
 * При нарушении формата входной строки или при отсутствии в ней чисел, вернуть -1.
 */

fun bestLongJump(jumps: String): Int{
    val parts = jumps.split(" ")
    val partsInt = mutableListOf<Int>()
    parts.forEach {
        when{
            Regex("""\d+""").containsMatchIn(it) -> try {partsInt.add(it.toInt()) }catch (e: NumberFormatException){return -1}
            it == "-" -> {}
            it == "%" -> {}
            else -> return -1}}
    return (partsInt.max() ?: -1)
}

/**
 * Сложная
 *
 * Результаты спортсмена на соревнованиях в прыжках в высоту представлены строкой вида
 * "220 + 224 %+ 228 %- 230 + 232 %%- 234 %".
 * Здесь + соответствует удачной попытке, % неудачной, - пропущенной.
 * Высота и соответствующие ей попытки разделяются пробелом.
 * Прочитать строку и вернуть максимальную взятую высоту (230 в примере).
 * При нарушении формата входной строки вернуть -1.
 */
fun bestHighJump(jumps: String): Int{
val parts = jumps.split(" ")
val resultsInt = mutableListOf<Int>()
var jump = 0
parts.forEach {
    if (it[0] in ':'..'z' || it[0] in '&'..'*') return -1
    if (it[0] == '+' && jump != 0) resultsInt.add(jump)
    jump = 0
    try {jump = it.toInt()}catch (e: NumberFormatException){}
}
return (resultsInt.max() ?: -1)
}

/**
 * Сложная
 *
 * В строке представлено выражение вида "2 + 31 - 40 + 13",
 * использующее целые положительные числа, плюсы и минусы, разделённые пробелами.
 * Наличие двух знаков подряд "13 + + 10" или двух чисел подряд "1 2" не допускается.
 * Вернуть значение выражения (6 для примера).
 * Про нарушении формата входной строки бросить исключение IllegalArgumentException
 */
fun plusMinus(expression: String): Int {
    var operation = 0
    val parts = expression.split(" ")
    var result = 0
    var i: Int
    var step = true
loo@    for(it in parts) {
        if(step) {try {i = it.toInt()}catch (e: java.lang.NumberFormatException){throw IllegalArgumentException("IllegalArgumentException")}
            step = false}
        else {
            operation = when (it) {
                "+" -> 1
                "-" -> 2
                else -> throw IllegalArgumentException("IllegalArgumentException")
            }
        step = true
        continue@loo}
        when(operation){
            1 -> result += i
            2 -> result -= i
            else -> result = i
        }
    }
return result}

/**
 * Сложная
 *
 * Строка состоит из набора слов, отделённых друг от друга одним пробелом.
 * Определить, имеются ли в строке повторяющиеся слова, идущие друг за другом.
 * Слова, отличающиеся только регистром, считать совпадающими.
 * Вернуть индекс начала первого повторяющегося слова, или -1, если повторов нет.
 * Пример: "Он пошёл в в школу" => результат 9 (индекс первого 'в')
 */

fun firstDuplicateIndex(str: String): Int{
    var result = 0
    val parts = str.split(" ")
    var helVar = " "
    parts.forEach {
        if(it.equals(helVar, true)) return result - it.length - 1
        result += it.length + 1
        helVar = it
    }
    return -1}

/**
 * Сложная
 *
 * Строка содержит названия товаров и цены на них в формате вида
 * "Хлеб 39.9; Молоко 62; Курица 184.0; Конфеты 89.9".
 * То есть, название товара отделено от цены пробелом,
 * а цена отделена от названия следующего товара точкой с запятой и пробелом.
 * Вернуть название самого дорогого товара в списке (в примере это Курица),
 * или пустую строку при нарушении формата строки.
 * Все цены должны быть больше либо равны нуля.
 */
fun mostExpensive(description: String): String{
    var maxPreis = 0.0
    var currentPreis: Double
    var result = ""
    val parts = description.split("; ")
    if(parts[0].isEmpty()) return ""
    parts.forEach {
        try {currentPreis = ("""\d.*\d$""".toRegex().find(it)?.value ?: "0").toDouble()}catch (e: java.lang.NumberFormatException){return ""}
        if (currentPreis > maxPreis){
            maxPreis = currentPreis
            result = it.dropLastWhile { es -> !es.isLetter() }
        }
    }
return result}

/**
 * Сложная
 *
 * Перевести число roman, заданное в римской системе счисления,
 * в десятичную систему и вернуть как результат.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: XXIII = 23, XLIV = 44, C = 100
 *
 * Вернуть -1, если roman не является корректным римским числом
 */
fun fromRoman(roman: String): Int{
    var result = 0
    var zw = roman
    //тысячи
    when{
        zw.startsWith("MMM") -> {
            result = 3000
            zw = zw.removePrefix("MMM")}
        zw.startsWith("MM") -> {
            result = 2000
            zw = zw.removePrefix("MM")}
        zw.startsWith("M") -> {
            result = 1000
            zw = zw.removePrefix("M")}
        else -> {}
    }
    //сотни
    when{
        zw.startsWith("CCC") -> {
            result += 300
            zw = zw.removePrefix("CCC")}
        zw.startsWith("CC") -> {
            result += 200
            zw = zw.removePrefix("CC")}
        zw.startsWith("CD") -> {
            result += 400
            zw = zw.removePrefix("CD")}
        zw.startsWith("CM") -> {
            result += 900
            zw = zw.removePrefix("CM")}
        zw.startsWith("C") -> {
            result += 100
            zw = zw.removePrefix("C")}
        zw.startsWith("DCCC") -> {
            result += 800
            zw = zw.removePrefix("DCCC")}
        zw.startsWith("DCC") -> {
            result += 700
            zw = zw.removePrefix("DCC")}
        zw.startsWith("DC") -> {
            result += 600
            zw = zw.removePrefix("DC")}
        zw.startsWith("D") -> {
            result += 500
            zw = zw.removePrefix("D")}
        else -> {}
    }
    //десятки
    when{
        zw.startsWith("XXX") -> {
            result += 30
            zw = zw.removePrefix("XXX")}
        zw.startsWith("XX") -> {
            result += 20
            zw = zw.removePrefix("XX")}
        zw.startsWith("XL") -> {
            result += 40
            zw = zw.removePrefix("XL")}
        zw.startsWith("LXXX") -> {
            result += 80
            zw = zw.removePrefix("LXXX")}
        zw.startsWith("LXX") -> {
            result += 70
            zw = zw.removePrefix("LXX")}
        zw.startsWith("LX") -> {
            result += 60
            zw = zw.removePrefix("LX")}
        zw.startsWith("L") -> {
            result += 50
            zw = zw.removePrefix("L")}
        zw.startsWith("XC") -> {
            result += 90
            zw = zw.removePrefix("XC")}
        zw.startsWith("X") -> {
            result += 10
            zw = zw.removePrefix("X")}
        else -> {}
    }
    //единицы
    when{
        zw.startsWith("III") -> {
            result += 3 }
        zw.startsWith("II") -> {
            result += 2}
        zw.startsWith("IV") -> {
            result += 4}
        zw.startsWith("VIII") -> {
            result += 8}
        zw.startsWith("VII") -> {
            result += 7}
        zw.startsWith("VI") -> {
            result += 6}
        zw.startsWith("V") -> {
            result += 5}
        zw.startsWith("IX") -> {
            result += 9}
        zw.startsWith("I") -> {
            result += 1}
        else -> {}
    }
    if (result == 0) return -1
return result}

/**
 * Очень сложная
 *
 * Имеется специальное устройство, представляющее собой
 * конвейер из cells ячеек (нумеруются от 0 до cells - 1 слева направо) и датчик, двигающийся над этим конвейером.
 * Строка commands содержит последовательность команд, выполняемых данным устройством, например +>+>+>+>+
 * Каждая команда кодируется одним специальным символом:
 *	> - сдвиг датчика вправо на 1 ячейку;
 *  < - сдвиг датчика влево на 1 ячейку;
 *	+ - увеличение значения в ячейке под датчиком на 1 ед.;
 *	- - уменьшение значения в ячейке под датчиком на 1 ед.;
 *	[ - если значение под датчиком равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей следующей командой ']' (с учётом вложенности);
 *	] - если значение под датчиком не равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей предыдущей командой '[' (с учётом вложенности);
 *      (комбинация [] имитирует цикл)
 *  пробел - пустая команда
 *
 * Изначально все ячейки заполнены значением 0 и датчик стоит на ячейке с номером N/2 (округлять вниз)
 *
 * После выполнения limit команд или всех команд из commands следует прекратить выполнение последовательности команд.
 * Учитываются все команды, в том числе несостоявшиеся переходы ("[" при значении под датчиком не равном 0 и "]" при
 * значении под датчиком равном 0) и пробелы.
 *
 * Вернуть список размера cells, содержащий элементы ячеек устройства после завершения выполнения последовательности.
 * Например, для 10 ячеек и командной строки +>+>+>+>+ результат должен быть 0,0,0,0,0,1,1,1,1,1
 *
 * Все прочие символы следует считать ошибочными и формировать исключение IllegalArgumentException.
 * То же исключение формируется, если у символов [ ] не оказывается пары.
 * Выход за границу конвейера также следует считать ошибкой и формировать исключение IllegalStateException.
 * Считать, что ошибочные символы и непарные скобки являются более приоритетной ошибкой чем выход за границу ленты,
 * то есть если в программе присутствует некорректный символ или непарная скобка, то должно быть выброшено
 * IllegalArgumentException.
 * IllegalArgumentException должен бросаться даже если ошибочная команда не была достигнута в ходе выполнения.
 *
 */
fun computeDeviceCells(cells: Int, commands: String, limit: Int): List<Int>{
    //check for errors
    var i = 0
    var inArray = cells / 2
    for (char in commands){
        when(char) {
            '[' -> i++
            ']' -> i--
            '>' -> inArray++
            '<' -> inArray--
            '+' -> {}
            '-' -> {}
            ' ' -> {}
            else -> throw IllegalArgumentException("Illegal symbol")
        }
        if(i < 0) throw IllegalArgumentException("IllegalArgumentException")
    }
    if(i != 0) throw IllegalArgumentException("[ count is not ] count")
    //lets fuck the brain!
    val result = mutableListOf<Int>()
    val returnAddres = mutableListOf<Int>()
    for (j in 1..cells) result.add(0)
    var commandsC0unt = 0
    inArray = cells / 2
    do{
        when(commands[i]){
            '+' -> result[inArray]++
            '-' -> result[inArray]--
            '>' -> inArray++
            '<' -> inArray--
            '[' -> {
                if(result[inArray] == 0) do {
                    i++
                    }while (commands[i] != ']')
                else returnAddres.add(i)
                }
            ']' -> {
                if(result[inArray] != 0) i = returnAddres[returnAddres.lastIndex]
                else returnAddres.removeAt(returnAddres.lastIndex)
                }
            else -> {}
        }
        i++
        commandsC0unt++
        if (inArray < 0 || inArray >= cells) throw IllegalStateException("Out of cells")
        }while (commandsC0unt < limit && i < commands.length)
return result}
