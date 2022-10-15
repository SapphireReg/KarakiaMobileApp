package com.example.karakiamobileapp.data

//extension property to use for any type, returns the same object, turns statement into an expression
val <T> T.exhaustive: T
    get() = this