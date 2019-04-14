@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import lesson3.task1.isPrime
import kotlin.math.pow
import kotlin.math.sqrt

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
        when {
            y < 0 -> listOf()
            y == 0.0 -> listOf(0.0)
            else -> {
                val root = sqrt(y)
                // Результат!
                listOf(-root, root)
            }
        }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.toLowerCase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double { //1
    var result = 0.0
    if(v.isEmpty()) return result
    for(i in 0 until v.size) result += v[i] * v[i]
    return sqrt(result)
}

/**
 * Простая
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double { //2
    var result = 0.0
    if(list.isEmpty()) return 0.0
    for(i in 0 until list.size) result += list[i]
    return result / list.size
}

/**
 * Средняя
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> { //3
    var sum: Double
    if(list.size == 0) return list
    sum = list.reduce { total, next -> total + next }
    sum /= list.size
    for(i in 0 until list.size) list[i] -= sum
return list}

/**
 * Средняя
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.0.
 */
fun times(a: List<Double>, b: List<Double>): Double { //4
    var result = 0.0
    for (i in 0 until a.size) result += a[i] * b[i]
    return result
}

/**
 * Средняя
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0.0 при любом x.
 */
fun polynom(p: List<Double>, x: Double): Double{ //5
    var result = 0.0
    if(p.isEmpty()) return result
    for (i in 0 until p.size){
        result += p[i] * x.pow(i)
    }
return result}

/**
 * Средняя
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Double>): MutableList<Double> { //6
    if(list.size < 2) return list
    for (i in (list.size - 1) downTo 1){
        for (j in (i - 1) downTo 0) list[i] += list[j] }
    return list}

/**
 * Средняя
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> { //7
    var ishod = n
    val result = mutableListOf<Int>()
    if(isPrime(n)){
        result.add(n)
        return result
    }
    do{
        for( i in 2..ishod)
        if(isPrime(i)){
            if (ishod % i == 0){
               result.add(i)
               ishod /= i
               break}}}while (!isPrime(ishod))
    result.add(ishod)
return result}

/**
 * Сложная
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int): String { //8
    var ishod = n
    val result = mutableListOf<Int>()
    if(isPrime(n)){
        result.add(n)
        return result.joinToString(separator = "*")
    }
    do{
        for( i in 2..ishod)
            if(isPrime(i)){
                if (ishod % i == 0){
                    result.add(i)
                    ishod /= i
                    break}}}while (!isPrime(ishod))
    result.add(ishod)
    return result.joinToString(separator = "*")}

/**
 * Средняя
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> { //9
    var numb: Int
    var rest = n
    val result = mutableListOf<Int>()
    do{
        numb = rest % base
        result.add(numb)
        rest /= base
    }while (rest > base)
    if(rest != 0) result.add(rest)
    return result.reversed()}

/**
 * Сложная
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 */
fun convertToString(n: Int, base: Int): String { //10
    var numb: Int
    var rest = n
    val result = mutableListOf<Int>()
    val endResult = mutableListOf<Char>()
    do{
        numb = rest % base
        result.add(numb)
        rest /= base
    }while (rest > base)
    if(rest != 0) result.add(rest)
    for (i in result.size - 1 downTo 0) endResult.add(when{
        result[i] < 10 -> (48 + result[i]).toChar()
        else -> (87 + result[i]).toChar()})
    return endResult.joinToString(separator = "")}

/**
 * Средняя
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int { //11
    var i = digits.size
    var multiplicator = 1
    var result = 0
    do{
        i--
        result += digits[i] * multiplicator
        multiplicator *= base
    }while (i != 0)
return result}

/**
 * Сложная
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 */
fun decimalFromString(str: String, base: Int): Int { //12
    var i = str.length
    var multiplicator = 1
    var result = 0
    do{
        i--
        result += when{
            str[i].toInt() < 58 -> str[i].toInt() - 48
            else -> str[i].toInt() - 87
        } * multiplicator
        multiplicator *= base
    }while (i != 0)
    return result}


/**
 * Сложная
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String = TODO()

/**
 * Очень сложная
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun spaceRemover(inString: String): String{ //14
    var result = ""
    for (i in 1 until inString.length){
        if(inString[i - 1] != ' ') result += inString[i -1]
        else if(inString[i] != ' ') result += inString[i -1]}
    if(inString[inString.length - 1] != ' ') result += inString[inString.length - 1]
return result}

fun russian(n: Int): String{
    var number = n
    val result  = mutableListOf<String>()
// единицы
    result.add(when(number % 100){
        1 -> "один"
        2 -> "два"
        3 -> "три"
        4 -> "четыре"
        5 -> "пять"
        6 -> "шесть"
        7 -> "семь"
        8 -> "восемь"
        9 -> "девять"
        10 -> "десять"
        11 -> "одинадцать"
        12 -> "двенадцать"
        13 -> "тринадцать"
        14 -> "четырнадцать"
        15 -> "пятнадцать"
        16 -> "шстнадцать"
        17 -> "семнадцать"
        18 -> "восемьнадцать"
        19 -> "девятнадцать"
        else -> when(number % 10){
            1 -> "один"
            2 -> "два"
            3 -> "три"
            4 -> "четыре"
            5 -> "пять"
            6 -> "шесть"
            7 -> "семь"
            8 -> "восемь"
            9 -> "девять"
            else -> ""
        }
    })
    //десятки
    number /= 10
    if(number == 0) return result.joinToString(separator = " ").trim()
    result.add(when(number % 10){
        2 -> "двадцать"
        3 -> "тридцать"
        4 -> "сорок"
        5 -> "пятьдесят"
        6 -> "шестьдесят"
        7 -> "семьдесят"
        8 -> "восемьдесят"
        9 -> "девяносто"
        else -> ""
    })
    //сотни
    number /= 10
    if(number == 0)     return spaceRemover(result.reversed().joinToString(separator = " ")).trim()
    result.add(when(number % 10){
        1 -> "сто"
        2 -> "двести"
        3 -> "триста"
        4 -> "четыреста"
        5 -> "пятьсот"
        6 -> "шестьсот"
        7 -> "семьсот"
        8 -> "восемьсот"
        9 -> "девятьсот"
        else -> ""
    })
    //тысячи
    number /= 10
    if(number == 0)     return spaceRemover(result.reversed().joinToString(separator = " "))
    result.add(when(number % 100){
        1 -> "тысяча"
        2 -> "две тысячи"
        3 -> "три тысячи"
        4 -> "четыре тысячи"
        5 -> "пять тысяч"
        6 -> "шесть тысяч"
        7 -> "семь тысяч"
        8 -> "восемь тысяч"
        9 -> "девять тысяч"
        10 -> "десять тысяч"
        11 -> "одинадцать тысяч"
        12 -> "двенадцать тысяч"
        13 -> "тринадцать тысяч"
        14 -> "четырнадцать тысяч"
        15 -> "пятнадцать тысяч"
        16 -> "шстнадцать тысяч"
        17 -> "семнадцать тысяч"
        18 -> "восемьнадцать тысяч"
        19 -> "девятнадцать тысяч"
        else -> when(number % 10){
            1 -> "тысяча"
            2 -> "две тысячи"
            3 -> "три тысячи"
            4 -> "четыре тысячи"
            5 -> "пять тысяч"
            6 -> "шесть тысяч"
            7 -> "семь тысяч"
            8 -> "восемь тысяч"
            9 -> "девять тысяч"
            else -> "тысяч"}
    })
    //десятки тысяч
            number /= 10
        if(number == 0)     return spaceRemover(result.reversed().joinToString(separator = " "))
                result.add(when(number % 10){
            2 -> "двадцать"
            3 -> "тридцать"
            4 -> "сорок"
            5 -> "пятьдесят"
            6 -> "шестьдесят"
            7 -> "семьдесят"
            8 -> "восемьдесят"
            9 -> "девяносто"
            else -> ""
        })
    //сотни тысяч
    number /= 10
    if(number == 0)     return spaceRemover(result.reversed().joinToString(separator = " "))
    result.add(when(number % 10){
        1 -> "сто"
        2 -> "двести"
        3 -> "триста"
        4 -> "четыреста"
        5 -> "пятьсот"
        6 -> "шестьсот"
        7 -> "семьсот"
        8 -> "восемьсот"
        9 -> "девятьсот"
        else -> ""
    })
    println(result)
    return spaceRemover(result.reversed().joinToString(separator = " "))
}