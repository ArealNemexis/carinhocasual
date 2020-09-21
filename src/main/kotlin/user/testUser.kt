package user

fun main(){
    var users = arrayListOf<User>()
    var new = User("Lucas Ferreira de Araujo", "+5561992614682", "cobrinha@carinhocasual.com");

    while(true){
        println("Type the User name: ")
        var name:String = readLine()!!
        println("Type the User phone number: ")
        var phone:String = readLine()!!
        println("Type the User Email")
        var email:String = readLine()!!

        users.add(User(name,phone, email))

        print("Want add a new User? Yes/No:")
        var op:String = readLine()!!
        println(op.toUpperCase())
        if(op.toUpperCase() == "N" || op.toUpperCase() == "NO"){
            break
        }
    }
    users.forEach { user ->
        println("----------------------------")
        println(user.id)
        println(user.name)
        println(user.phone)
        println(user.email)
        println(user.last_latitude)
        println(user.last_longitude)
        println("----------------------------")
    }
//    println(new.id)
//    println(new.name)
//    println(new.phone)
//    println(new.email)
//    println(new.last_latitude)
//    println(new.last_longitude)
}