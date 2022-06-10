package com.androidsolutions.fragmentnavigation

import java.io.Serializable

data class CustomerModel(var name : String,
                         var desc : String,
                         var product : String,
                         var date : String) : Serializable
