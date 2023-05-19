package com.example.jobfindercompanies

import java.io.Serializable

data class Company( var name : String = ""
                    ,var email : String = ""
                    ,var field : String = ""
                    ,var location : String = ""
                    ,var bio : String = ""
                    , var id : String = ""
                    ,var numberOfEmployees: Int = 0
): Serializable

