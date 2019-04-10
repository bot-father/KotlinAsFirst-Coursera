@file:Suppress("UNUSED_PARAMETER")
package lesson3.task1

import kotlin.math.*

/**
 * Пример
 *
 * Вычисление факториала
 */
fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n) {
        result = result * i // Please do not fix in master
    }
    return result
}

/**
 * Пример
 *
 * Проверка числа на простоту -- результат true, если число простое
 */
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    if (n == 2) return true
    if (n % 2 == 0) return false
    for (m in 3..sqrt(n.toDouble()).toInt() step 2) {
        if (n % m == 0) return false
    }
    return true
}

/**
 * Пример
 *
 * Проверка числа на совершенность -- результат true, если число совершенное
 */
fun isPerfect(n: Int): Boolean {
    var sum = 1
    for (m in 2..n/2) {
        if (n % m > 0) continue
        sum += m
        if (sum > n) break
    }
    return sum == n
}

/**
 * Пример
 *
 * Найти число вхождений цифры m в число n
 */
fun digitCountInNumber(n: Int, m: Int): Int =
        when {
            n == m -> 1
            n < 10 -> 0
            else -> digitCountInNumber(n / 10, m) + digitCountInNumber(n % 10, m)
        }

/**
 * Тривиальная
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun digitNumber(n: Int): Int {
    var i = 1
    var num = n
    while(num > 9){
        i++
        num /= 10
    }
    return i
}

/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fiboRecursiv(n: Int): Int = when(n){
    1 -> 1
    2 -> 1
    else -> fib(n - 1) + fib(n - 2)
}

fun fib(n: Int): Int {
    var result = 0
    var fiboPrev = 1
    var fiboAct = 1
    when (n) {
        1 -> return 1
        2 -> return 1
        else -> for(i in 3..n){
            result = fiboAct + fiboPrev
            fiboPrev = fiboAct
            fiboAct = result
        }
    }
return result}

/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int {
    val max= m * n
    var result = 0
    for(i in 2..max){
        if(i % m == 0 && i % n == 0){
            result = i
            break}
    }
    return result}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    var result = 0
    for(i in 2..n){
        if(n % i == 0){
            result = i
            break
        }
    }
return result}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int {
    var result = n - 1
    while (n % result != 0) result--
    return result}

/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean {
    val min = when{m < n -> m
    else -> n}
    var result = true
    for (i in 2..min){
        if(n % i == 0  && m % i == 0){
            result = false
            break
        }
    }
return result}

/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean {
    var result = false
    var sum: Int
    if(m > 2147395600) return false //последний корень перед Int.MAX_VALUE
    for (i in m..n){
        sum = 0
        for (j in 1..i step 2){ //проверка является ли квадратом (суммой нечётных чисел)
            sum += j
            if(sum == i){
                result = true
                break
            }
            if(sum > i) break
        }
    if(result) break
    }
return result}

/**
 * Средняя
 *
 * Гипотеза Коллатца. Рекуррентная последовательность чисел задана следующим образом:
 *
 *   ЕСЛИ (X четное)
 *     Xслед = X /2
 *   ИНАЧЕ
 *     Xслед = 3 * X + 1
 *
 * например
 *   15 46 23 70 35 106 53 160 80 40 20 10 5 16 8 4 2 1 4 2 1 4 2 1 ...
 * Данная последовательность рано или поздно встречает X == 1.
 * Написать функцию, которая находит, сколько шагов требуется для
 * этого для какого-либо начального X > 0.
 */
fun collatzSteps(x: Int): Int {
    var xNext = x
    var count = 0
    while(xNext != 1) {
        if (xNext % 2 == 0) xNext /= 2
        else xNext = 3 * xNext + 1
        count++
    }
return count}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */

fun sin(x: Double, eps: Double): Double {
    var glied = 1.0
    var gliedNum = 3
    val xCut = x % (2 * PI)
    var xHoch = xCut * xCut * xCut
    var xFaktorial = 6.0
    var result :Double = xCut
    while (abs(glied) > eps){
        glied = xHoch / xFaktorial
        result -= glied
        xHoch *= xCut * xCut
        gliedNum++
        xFaktorial *= gliedNum
        gliedNum++
        xFaktorial *= gliedNum
        glied = xHoch / xFaktorial
        result += glied
        xHoch *= xCut * xCut
        gliedNum++
        xFaktorial *= gliedNum
        gliedNum++
        xFaktorial *= gliedNum
    }
return result}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun cos(x: Double, eps: Double): Double {
    var glied = 1.0
    var gliedNum = 2
    val xCut = x % (2 * PI)
    var xHoch = xCut * xCut
    var xFaktorial = 2.0
    var result = 1.0
    while (abs(glied) > eps){
        glied = xHoch / xFaktorial
        result -= glied
        xHoch *= xCut * xCut
        gliedNum++
        xFaktorial *= gliedNum
        gliedNum++
        xFaktorial *= gliedNum
        glied = xHoch / xFaktorial
        result += glied
        xHoch *= xCut * xCut
        gliedNum++
        xFaktorial *= gliedNum
        gliedNum++
        xFaktorial *= gliedNum
    }
    return result}

/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun revert(n: Int): Int {
    var numb = n
    var result = 0
    do{
        result *= 10
        result += numb % 10
        numb /= 10
    }while(numb > 0)
return result}

/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun isPalindrome(n: Int): Boolean {
    var numb = n
    var result = 0
    do{
        result *= 10
        result += numb % 10
        numb /= 10
    }while(numb > 0)
return n == result}

/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun hasDifferentDigits(n: Int): Boolean {
    var numb = n / 10
    var result = n % 10
    do{
        if(numb % 10 != result) return true
        result = numb % 10
        numb /= 10
    }while(numb > 0)
return false}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun squareSequenceDigit(n: Int): Int {
    var digitNumber = 0
    var count = 0
    var quadrat :Int
    do {
        count++
        quadrat = count * count // вычисляем квадраты
        do{
            quadrat /= 10
            digitNumber++
        }while (quadrat != 0) // определяем длину полученной последовательности
    }while (n > digitNumber) // сравниваем с n
    quadrat = count * count
    for(i in n..digitNumber){ // выбираем из последнего квадрата нужную цифру
        count = quadrat % 10
        quadrat /= 10
    }
return count}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun fibSequenceDigit(n: Int): Int = TODO()
