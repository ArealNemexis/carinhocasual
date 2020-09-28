// package com.carinhocasual.model.entity.user

// import com.carinhocasual.model.entity.interest.Interest
// import com.carinhocasual.model.entity.gender.Gender
// import com.carinhocasual.model.entity.sexualOrientation.SexualOrientation

// open class People () {
//     private var id: String? = null
//     private var name: String? = null
//     private var email: String? = null
//     private var password: String? = null
//     private var phone: String? = null
//     private var birthday: String? = null
//     private var token: String? = null
//     private var interests: MutableList <Interest>? = mutableListOf <Interest> ()
//     private var gender: Gender? = null
//     private var sexualOrient: SexualOrientation? = null
//     //var picture: Picture? = null (not implemented yet)

//     fun getId () = id
//     fun getName () = name
//     fun getEmail () = email
//     fun getPassword () = password
//     fun getPhone () = phone
//     fun getBrithday () = birthday
//     fun getToken () = token
//     fun getInterests () = interests
//     fun getGender () = gender
//     fun getSexualOrientation () = sexualOrient

//     fun setId (id: String) {
//         this.id = id
//     }

//     fun setName (name: String) {
//         this.name = name
//     }

//     fun setEmail (email: String) {
//         this.email = email
//     }

//     fun setPassword (password: String) {
//         this.password = password
//     }

//     fun setPhone (phone: String) {
//         this.phone = phone
//     }

//     fun setBirthday (birthday: String) {
//         this.birthday = birthday
//     }

//     fun setToken (token: String) {
//         this.token = token
//     }

//     fun addInterest (interest: Interest) {
//         this.interests?.add (interest)
//     }
// }

// // open class People2 (
// //     name: String?,
// //     phone: String?,
// //     email: String?,
// //     birthday: String?,
// // ){
// //     var name: String? =  null
// //     var phone: String? = null
// //     var email: String? = null
// //     var birthday: String? = null
// //     var id: String? = null

// //     init {
// //         this.name = name
// //         this.phone= phone
// //         this.email = email
// //         this.birthday = birthday
// //         // this.id = uuidGenerate()
// //     }
// // }

// //    fun editAll(target:People){
// //        if(target.name != null && target.name != ""){
// //            this.name = target.name
// //        }
// //        if(target.phone != null && target.phone != ""){
// //            this.phone = target.phone
// //        }
// //        if(target.email != null && target.email != ""){
// //            this.email = target.email
// //        }
// //        if(target.birthday != null && target.birthday != ""){
// //            this.birthday = target.birthday
// //        }
// //    }