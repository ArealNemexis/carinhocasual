package user

import java.util.*

class User(
        var name:String,
        var phone:String,
        var email:String,
        var birthday: String,
        var last_longitude:Float = 0.0F,
        var last_latitude:Float = 0.0F)
{
    val id = UUID.randomUUID().toString();


}