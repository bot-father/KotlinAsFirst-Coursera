@file:Suppress("UNUSED_PARAMETER")
package lesson2.task1

import lesson1.task1.discriminant
import kotlin.math.max
import kotlin.math.sqrt

/**
 * Пример
 *
 * Найти число корней квадратного уравнения ax^2 + bx + c = 0
 */
fun quadraticRootNumber(a: Double, b: Double, c: Double): Int {
    val discriminant = discriminant(a, b, c)
    return when {
        discriminant > 0.0 -> 2
        discriminant == 0.0 -> 1
        else -> 0
    }
}

/**
 * Пример
 *
 * Получить строковую нотацию для оценки по пятибалльной системе
 */
fun gradeNotation(grade: Int): String = when (grade) {
    5 -> "отлично"
    4 -> "хорошо"
    3 -> "удовлетворительно"
    2 -> "неудовлетворительно"
    else -> "несуществующая оценка $grade"
}

/**
 * Пример
 *
 * Найти наименьший корень биквадратного уравнения ax^4 + bx^2 + c = 0
 */
fun minBiRoot(a: Double, b: Double, c: Double): Double {
    // 1: в главной ветке if выполняется НЕСКОЛЬКО операторов
    if (a == 0.0) {
        if (b == 0.0) return Double.NaN // ... и ничего больше не делать
        val bc = -c / b
        if (bc < 0.0) return Double.NaN // ... и ничего больше не делать
        return -sqrt(bc)
        // Дальше функция при a == 0.0 не идёт
    }
    val d = discriminant(a, b, c)   // 2
    if (d < 0.0) return Double.NaN  // 3
    // 4
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    val y3 = max(y1, y2)       // 5
    if (y3 < 0.0) return Double.NaN // 6
    return -sqrt(y3)           // 7
}

/**
 * Простая
 *
 * Мой возраст. Для заданного 0 < n < 200, рассматриваемого как возраст человека,
 * вернуть строку вида: «21 год», «32 года», «12 лет».
 */
fun ageDescription(age: Int): String =
    when{
        age > 4 && age < 21 -> "$age лет"
        age % 10 > 1 && age % 10 < 5 -> "$age года"
        age % 10 > 4 -> "$age лет"
        else -> "$age год"
    }

/**
 * Простая
 *
 * Путник двигался t1 часов со скоростью v1 км/час, затем t2 часов — со скоростью v2 км/час
 * и t3 часов — со скоростью v3 км/час.
 * Определить, за какое время он одолел первую половину пути?
 */
fun timeForHalfWay(t1: Double, v1: Double,
                   t2: Double, v2: Double,
                   t3: Double, v3: Double): Double {
    val halfpath : Double = (t1 * v1 + t2 * v2 + t3 * v3) / 2
    if(t1 * v1 > halfpath) return halfpath / v1
    if(t1 * v1 + t2 * v2 > halfpath) return t1 + (halfpath - t1 * v1) / v2
    else return t1 + t2 + (halfpath - t1 * v1 - t2 * v2) / v3
}

/**
 * Простая
 *
 * Нa шахматной доске стоят черный король и две белые ладьи (ладья бьет по горизонтали и вертикали).
 * Определить, не находится ли король под боем, а если есть угроза, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от первой ладьи, 2, если только от второй ладьи,
 * и 3, если угроза от обеих ладей.
 * Считать, что ладьи не могут загораживать друг друга
 */
fun whichRookThreatens(kingX: Int, kingY: Int,
                       rookX1: Int, rookY1: Int,
                       rookX2: Int, rookY2: Int): Int{
    var bang : Int = 0
    if(kingX == rookX1 || kingY == rookY1) bang = 1 // угроза от ладьи 1
    if(kingX == rookX2 || kingY == rookY2) bang += 2 // угроза от ладьи 2
    return bang}
/**
 * Простая
 *
 * На шахматной доске стоят черный король и белые ладья и слон
 * (ладья бьет по горизонтали и вертикали, слон — по диагоналям).
 * Проверить, есть ли угроза королю и если есть, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от ладьи, 2, если только от слона,
 * и 3, если угроза есть и от ладьи и от слона.
 * Считать, что ладья и слон не могут загораживать друг друга.
 */
fun rookOrBishopThreatens(kingX: Int, kingY: Int,
                          rookX: Int, rookY: Int,
                          bishopX: Int, bishopY: Int): Int {
    var bang : Int = 0
    if(kingX == rookX || kingY == rookY) bang = 1 // угроза от ладьи
    if(kingX - kingY == bishopX - bishopY || kingX + kingY == bishopX + bishopY) bang += 2 // угроза от слона
    return bang}

/**
 * Простая
 *
 * Треугольник задан длинами своих сторон a, b, c.
 * Проверить, является ли данный треугольник остроугольным (вернуть 0),
 * прямоугольным (вернуть 1) или тупоугольным (вернуть 2).
 * Если такой треугольник не существует, вернуть -1.
 */
fun pifagor(gipo: Double, katet1: Double,katet2: Double): Int { //Пифагор
    val katets = katet1 * katet1 + katet2 * katet2
    val gipoquadrat = gipo * gipo
    if(katets == gipoquadrat) return 1
    if(katets > gipoquadrat) return 0
    else return 2
}

fun triangleKind(a: Double, b: Double, c: Double): Int {
    val sum : Double = (a + b + c) / 2
    if(sum < a || sum < b || sum < c) return -1
    if(a > b && a > c) return pifagor(a, b, c) //поиск длиннейшей стороны
    if(b > a && b > c) return pifagor(b, a, c)
    else return pifagor(c, b, a)
}

/**
 * Средняя
 *
 * Даны четыре точки на одной прямой: A, B, C и D.
 * Координаты точек a, b, c, d соответственно, b >= a, d >= c.
 * Найти длину пересечения отрезков AB и CD.
 * Если пересечения нет, вернуть -1.
 */
fun segmentLength(a: Int, b: Int, c: Int, d: Int): Int = when{
    b < c -> -1
    b >= c && c >= a && d >= b -> b - c
    d >= a && c <= a && b >= d -> d - a
    a <= c && d <= b -> d - c
    a >= c && d >= b -> b - a
    else -> -1
}
