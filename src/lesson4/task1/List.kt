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
/*fun abs(v: List<Double>) = when{
    v.isEmpty() -> 0.0
    else -> sqrt(v.fold(0.0, { total, next -> total + next * next }))
}*/
fun abs(v: List<Double>) = sqrt(v.fold(0.0, { total, next -> total + next * next }))

/**
 * Простая
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>) = when{
    list.isEmpty() ->  0.0
    else -> list.sum() / list.size}

/**
 * Средняя
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> { //3
    if(list.isEmpty()) return list
    val sum  = list.sum() / list.size
    for (i in 0 until list.size) list[i] -= sum
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
    return result}

/**
 * Средняя
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0.0 при любом x.

*/

fun polynom(p: List<Double>, x: Double): Double = p.foldIndexed(0.0, {i, sum, next -> sum + next * x.pow(i)})
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
    for (i in (list.size - 1) downTo 1) list[i] = list.take(i + 1).sum()
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
    if(isPrime(n)) return n.toString()
    do{
        for( i in 2..ishod)
            if(isPrime(i)){
                if (ishod % i == 0){
                    result.add(i)
                    ishod /= i
                    break }
            }}while (!isPrime(ishod))
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
    var rest = n
    val result = mutableListOf<Int>()
    do{
        result.add(rest % base)
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
    var rest = n
    val result = mutableListOf<Int>()
    val endResult = mutableListOf<Char>()
    do{
        result.add(rest % base)
        rest /= base
    }while (rest > base)
    if(rest != 0) result.add(rest)
    result.reversed().forEach { endResult.add(when{
        it < 10 -> (48 + it).toChar()
        else -> (87 + it).toChar()}) }
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
fun roman(n: Int): String { //13
    var number = n
    val result = mutableListOf<String>()
    if(n == 0) return ""
    when(n % 10){ //единицы
        1 -> result.add("I")
        2 -> result.add("II")
        3 -> result.add("III")
        4 -> result.add("IV")
        5 -> result.add("V")
        6 -> result.add("VI")
        7 -> result.add("VII")
        8 -> result.add("VIII")
        9 -> result.add("IX")
        else -> {}
    }
    number /= 10
    if(number == 0) return result.joinToString(separator = "")
    when(number % 10){ //десятки
        1 -> result.add("X")
        2 -> result.add("XX")
        3 -> result.add("XXX")
        4 -> result.add("XL")
        5 -> result.add("L")
        6 -> result.add("LX")
        7 -> result.add("LXX")
        8 -> result.add("LXXX")
        9 -> result.add("XC")
        else -> {}
    }
    number /= 10
    if(number == 0) return result.reversed().joinToString(separator = "")
    when(number % 10){ //сотни
        1 -> result.add("C")
        2 -> result.add("CC")
        3 -> result.add("CCC")
        4 -> result.add("CD")
        5 -> result.add("D")
        6 -> result.add("DC")
        7 -> result.add("DCC")
        8 -> result.add("DCCC")
        9 -> result.add("CM")
        else -> {}
    }
    number /= 10
    if(number == 0) return result.reversed().joinToString(separator = "")
    when(number % 10){
        1 -> result.add("M")
        2 -> result.add("MM")
        3 -> result.add("MMM")
        else -> {}
    }
return result.reversed().joinToString(separator = "")}

/**
 * Очень сложная
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */

fun russian(n: Int): String{ //14
    var number = n
    val result  = mutableListOf<String>()
// единицы
    when(number % 100){
        1 -> result.add("один")
        2 -> result.add("два")
        3 -> result.add("три")
        4 -> result.add("четыре")
        5 -> result.add("пять")
        6 -> result.add("шесть")
        7 -> result.add("семь")
        8 -> result.add("восемь")
        9 -> result.add("девять")
        10 -> result.add("десять")
        11 -> result.add("одинадцать")
        12 -> result.add("двенадцать")
        13 -> result.add("тринадцать")
        14 -> result.add("четырнадцать")
        15 -> result.add("пятнадцать")
        16 -> result.add("шстнадцать")
        17 -> result.add("семнадцать")
        18 -> result.add("восемьнадцать")
        19 -> result.add("девятнадцать")
        else -> when(number % 10){
            1 -> result.add("один")
            2 -> result.add("два")
            3 -> result.add("три")
            4 -> result.add("четыре")
            5 -> result.add("пять")
            6 -> result.add("шесть")
            7 -> result.add("семь")
            8 -> result.add("восемь")
            9 -> result.add("девять")
            else -> {}
        }}
    //десятки
    number /= 10
    if(number == 0) return result.joinToString(separator = " ")
    when(number % 10){
        2 -> result.add("двадцать")
        3 -> result.add("тридцать")
        4 -> result.add("сорок")
        5 -> result.add("пятьдесят")
        6 -> result.add("шестьдесят")
        7 -> result.add("семьдесят")
        8 -> result.add("восемьдесят")
        9 -> result.add("девяносто")
        else -> {}
    }
    //сотни
    number /= 10
    if(number == 0) return result.reversed().joinToString(separator = " ")
    when(number % 10){
        1 -> result.add("сто")
        2 -> result.add("двести")
        3 -> result.add("триста")
        4 -> result.add("четыреста")
        5 -> result.add("пятьсот")
        6 -> result.add("шестьсот")
        7 -> result.add("семьсот")
        8 -> result.add("восемьсот")
        9 -> result.add("девятьсот")
        else -> {}
    }
    //тысячи
    number /= 10
    if(number == 0) return result.reversed().joinToString(separator = " ")
when(number % 100){
        1 -> result.add("тысяча")
        2 -> result.add("две тысячи")
        3 -> result.add("три тысячи")
        4 -> result.add("четыре тысячи")
        5 -> result.add("пять тысяч")
        6 -> result.add("шесть тысяч")
        7 -> result.add("семь тысяч")
        8 -> result.add("восемь тысяч")
        9 -> result.add("девять тысяч")
        10 -> result.add("десять тысяч")
        11 -> result.add("одинадцать тысяч")
        12 -> result.add("двенадцать тысяч")
        13 -> result.add("тринадцать тысяч")
        14 -> result.add("четырнадцать тысяч")
        15 -> result.add("пятнадцать тысяч")
        16 -> result.add("шестнадцать тысяч")
        17 -> result.add("семнадцать тысяч")
        18 -> result.add("восемьнадцать тысяч")
        19 -> result.add("девятнадцать тысяч")
        else -> when(number % 10){
            1 -> result.add("тысяча")
            2 -> result.add("две тысячи")
            3 -> result.add("три тысячи")
            4 -> result.add("четыре тысячи")
            5 -> result.add("пять тысяч")
            6 -> result.add("шесть тысяч")
            7 -> result.add("семь тысяч")
            8 -> result.add("восемь тысяч")
            9 -> result.add("девять тысяч")
            else -> result.add("тысяч")}
    }
    //десятки тысяч
    number /= 10
    if(number == 0) return result.reversed().joinToString(separator = " ")
    when(number % 10){
        2 -> result.add("двадцать")
        3 -> result.add("тридцать")
        4 -> result.add("сорок")
        5 -> result.add("пятьдесят")
        6 -> result.add("шестьдесят")
        7 -> result.add("семьдесят")
        8 -> result.add("восемьдесят")
        9 -> result.add("девяносто")
        else -> {}
    }
    //сотни тысяч
    number /= 10
    if(number == 0) return result.reversed().joinToString(separator = " ")
when(number % 10){
        1 -> result.add("сто")
        2 -> result.add("двести")
        3 -> result.add("триста")
        4 -> result.add("четыреста")
        5 -> result.add("пятьсот")
        6 -> result.add("шестьсот")
        7 -> result.add("семьсот")
        8 -> result.add("восемьсот")
        9 -> result.add("девятьсот")
        else -> {}}
    return result.reversed().joinToString(separator = " ")
}