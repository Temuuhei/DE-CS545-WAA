package homeworks

fun planetWeights(input : Double, planet : String) : Number {
    val result = when (planet) {
        "Venus" -> input * 0.78
        "Mars" -> input * 0.39
        "Jupiter" -> input * 2.65
        "Saturn" -> input * 1.17
        "Uranus" -> input * 1.05
        "Neptune" -> input * 1.23
        else -> -1
    }

    return result
}

fun sumOffOdd(arrs : Array<Int>) : Int {
    var sum = 0
    for (num in arrs) {
        if ( num % 2 != 0) {
            sum += num * num
        }
    }

    return sum
}

fun sumOfFirstAndLastDigit(num : Int):Int {
    var first : Int  = num
    while(first > 10) {
        first /= 10
    }
    val last = num % 10
    println("first - $first")
    println("last - $last")
    val result= first * 10 + last
    println("result =  $result")
    return result
}

fun main() {

    // first ex
    val res = sumOfFirstAndLastDigit(12345)
    println("Result of 12345 - $res");

    // second ex
    var intArray = arrayOf(1, 2, 3, 4, 6, 5)
    val sum = sumOffOdd(intArray)
    println("Result of sum odd squares - $sum")

    // third ex
    println("Enter planet either Venus, Mars, Jupiter, Saturn, Uranus, Neptune")
    val inputPlanet = readLine()

    var yourWeight: Double?
    do {
        print("Enter your weight : ")
        yourWeight  = readLine()!!.toDoubleOrNull()
        if (yourWeight == null) println("Not a valid number, try again")
    }
    while (yourWeight == null)

    val nas = inputPlanet?.let { planetWeights(yourWeight, it) }

    println("Your weight is $nas pounds on $inputPlanet")

}