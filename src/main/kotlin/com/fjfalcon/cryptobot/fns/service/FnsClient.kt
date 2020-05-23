package com.fjfalcon.cryptobot.fns.service

import io.micronaut.http.annotation.FilterMatcher

@FilterMatcher
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS, AnnotationTarget.VALUE_PARAMETER)
annotation class FnsClient
