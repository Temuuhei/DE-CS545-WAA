package homeworks

class Test {

}

fun main(args : Array<String>) {
    val book1 = Book("Harry Potter", "J.K Rowling", 14.5)
    println(book1.read())
    val ebook1 = EBook("LOTR", "Temka", 15.5, "Kinder")
    println(ebook1.read())
}