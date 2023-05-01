class Homework1 {

    fun homework1a(num:Int): Int {
        val firstDigit = num / 10
        val lastDigit = num % 10
        return firstDigit * 10 + lastDigit
    }

    fun main(args: Array<String>) {
        println(args);
        println(homework1a(12345))
    }

}