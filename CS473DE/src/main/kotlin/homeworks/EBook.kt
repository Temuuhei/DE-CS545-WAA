package homeworks

class EBook : Book{
    var fileType: String = "Kinder"



    constructor(title: String, author: String, price: Double, fileType: String) : super(title, author, price) {
        this.fileType = fileType
    }

    override fun read() {
        println("Read from Electronic Device")
    }

}