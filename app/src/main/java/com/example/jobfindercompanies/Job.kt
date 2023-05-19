package com.example.jobfindercompanies

data class Job( var publisher : String = "" ,
                var date :String = "",
                var id : String = "",
                var title: String ="",
                var description  : String = "",
                var requirement : String = "",
                var location : String = "",
                var forDisabledPeople : Boolean = false // true for disable people and false for normal people
)
