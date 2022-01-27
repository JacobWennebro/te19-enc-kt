import java.io.*
import java.util.*

fun numberGenerator(iterations: Int): IntArray {
    val r = Random()
    val numbers = IntArray(iterations)
    for(i in 0 until iterations) numbers.set(i, r.nextInt())
    return numbers
}

fun main(args: Array<String>) {
    val numbers = numberGenerator(1000);

    println(numbers.get(999))
    println(numbers.get(929))
    println(numbers.get(99))

}