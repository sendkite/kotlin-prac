package com.group.libraryapp.util

import org.springframework.data.repository.CrudRepository

fun fail() : Nothing {
    throw IllegalArgumentException()
}