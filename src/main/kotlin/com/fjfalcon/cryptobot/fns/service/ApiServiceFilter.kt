package com.fjfalcon.cryptobot.fns.service

import com.fjfalcon.cryptobot.fns.FNSProperties
import io.micronaut.http.HttpResponse
import io.micronaut.http.MutableHttpRequest
import io.micronaut.http.annotation.Filter
import io.micronaut.http.filter.ClientFilterChain
import io.micronaut.http.filter.HttpClientFilter
import org.reactivestreams.Publisher
import javax.inject.Inject

@FnsClient
@Filter
class ApiServiceFilter @Inject constructor(private val fnsProperties: FNSProperties) :
    HttpClientFilter {
    override fun doFilter(
        request: MutableHttpRequest<*>?,
        chain: ClientFilterChain?
    ): Publisher<out HttpResponse<*>> {
        return chain!!.proceed(
            request!!.header("device-os", fnsProperties.os)
                .header("device-id", fnsProperties.device)
                .basicAuth(fnsProperties.login, fnsProperties.password)
        )
    }
}
