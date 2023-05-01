package homeworks

open class Book {
//    title author price
    var title:String
    var author:String
    var price: Double = 0.0

    constructor(title:String, author:String, price:Double) {
        this.title = title
        this.author = author
        this.price = price
    }

    open fun read() {
        println("Reading Paper book")
    }

}

