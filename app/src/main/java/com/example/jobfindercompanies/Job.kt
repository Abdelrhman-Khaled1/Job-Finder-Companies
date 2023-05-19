package com.example.jobfindercompanies

data class Job( var publisher : String = "" ,
                var date :String = "",
                var title: String ="",
                var jobDescription  : String = "",
                var jobRequirement : String = "",
                var location : String = "",
                var forDisabledPeople : Boolean = false // true for disable people and false for normal people
)
