@file:Suppress("UNUSED_PARAMETER")
package lesson2.task2
import lesson1.task1.sqr
import kotlin.math.sqrt

/**
 * Пример
 *
 * Лежит ли точка (x, y) внутри окружности с центром в (x0, y0) и радиусом r?
 */
fun pointInsideCircle(x: Double, y: Double, x0: Double, y0: Double, r: Double) =
        sqr(x - x0) + sqr(y - y0) <= sqr(r)

/**
 * Простая
 *
 * Четырехзначное число назовем счастливым, если сумма первых двух ее цифр равна сумме двух последних.
 * Определить, счастливое ли заданное число, вернуть true, если это так.
 */
fun isNumberHappy(number: Int): Boolean = number % 10 + (number / 10) % 10 == (number / 100) % 10 + number / 1000

/**
 * Простая
 *
 * На шахматной доске стоят два ферзя (ферзь бьет по вертикали, горизонтали и диагоналям).
 * Определить, угрожают ли они друг другу. Вернуть true, если угрожают.
 * Считать, что ферзи не могут загораживать друг друга.
 */
fun queenThreatens(x1: Int, y1: Int, x2: Int, y2: Int): Boolean = x1 == x2 || y1 == y2 || x1 + y1 == x2 + y2 || x1 - y1 == x2 - y2


/**
 * Простая
 *
 * Дан номер месяца (от 1 до 12 включительно) и год (положительный).
 * Вернуть число дней в этом месяце этого года по григорианскому календарю.
 */
fun daysInMonth(month: Int, year: Int): Int = when(month){
    1 -> 31
    2 -> if(year % 4 == 0 && (year % 400 == 0 || year % 100 != 0)) 29 else 28
    3 -> 31
    4 -> 30
    5 -> 31
    6 -> 30
    7 -> 31
    8 -> 31
    9 -> 30
    10 -> 31
    11 -> 30
    12 -> 31
    else -> 0
}

/**
 * Средняя
 *
 * Проверить, лежит ли окружность с центром в (x1, y1) и радиусом r1 целиком внутри
 * окружности с центром в (x2, y2) и радиусом r2.
 * Вернуть true, если утверждение верно
 */
fun circleInside(x1: Double, y1: Double, r1: Double,
                 x2: Double, y2: Double, r2: Double): Boolean{
    //проверим, что центр окружности 1 внутри окружности 2
    if(!pointInsideCircle(x1, y1, x2, y2, r2)) return false
    //расстояние между центрами по Пифагору
    val distance = sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2))
    //если r2 больше r1 + расстояние - false
    return r2 >= distance + r1
}

/**
 * Средняя
 *
 * Определить, пройдет ли кирпич со сторонами а, b, c сквозь прямоугольное отверстие в стене со сторонами r и s.
 * Стороны отверстия должны быть параллельны граням кирпича.
 * Считать, что совпадения длин сторон достаточно для прохождения кирпича, т.е., например,
 * кирпич 4 х 4 х 4 пройдёт через отверстие 4 х 4.
 * Вернуть true, если кирпич пройдёт
 */
fun minimal(a: Int, b: Int, c: Int) = when{ //возвращает минимум из трёх
    a <= b && a <= c -> a
    b <= a && b <= c -> b
    else -> c
}

fun brickPasses(a: Int, b: Int, c: Int, r: Int, s: Int): Boolean  {
    // Вторая по величине грань
    val brickSecondSide = when{
        b in a..c -> b
        b in c..a -> b
        c in b..a -> c
        c in a..b -> c
        else -> a
    }
    //Проверка впихуемости
    return if(r > s) r >= brickSecondSide && s >= minimal(a, b, c)
    else r >= minimal(a, b, c) && s >= brickSecondSide
}
