package com.mironov.newsapp.domain

enum class NewsResources(val domain: String? = null, val source : String? = null) {
    LENTA(domain = "lenta.ru"),
    VEDOMOSTI(domain = "vedomosti.ru"),
    RBC(source = "rbc")
}