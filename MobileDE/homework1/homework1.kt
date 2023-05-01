package homework1

fun homework1a(num:Int): Int {
    println("here" + num);
    val firstDigit = num / 10
    val lastDigit = num % 10
    var result = firstDigit * 10 + lastDigit
    println
    return firstDigit * 10 + lastDigit
}

fun main(args: Array<String>) {
    println(args);
    println("Result : " + homework1a(12345))
}