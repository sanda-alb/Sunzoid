package com.raywenderlich.android.data.network.client;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J!\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0007J\u001b\u0010\b\u001a\u00020\t2\b\b\u0001\u0010\n\u001a\u00020\u000bH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\f\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\r"}, d2 = {"Lcom/raywenderlich/android/data/network/client/WeatherApiClient;", "", "findLocation", "", "Lcom/raywenderlich/android/data/network/model/ApiLocation;", "location", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getLocationDetails", "Lcom/raywenderlich/android/data/network/model/ApiLocationDetails;", "woeid", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public abstract interface WeatherApiClient {
    
    @org.jetbrains.annotations.Nullable()
    @retrofit2.http.GET(value = "location/search/")
    public abstract java.lang.Object findLocation(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Query(value = "query")
    java.lang.String location, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.raywenderlich.android.data.network.model.ApiLocation>> p1);
    
    @org.jetbrains.annotations.Nullable()
    @retrofit2.http.GET(value = "location/{woeid}/")
    public abstract java.lang.Object getLocationDetails(@retrofit2.http.Path(value = "woeid")
    int woeid, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.raywenderlich.android.data.network.model.ApiLocationDetails> p1);
}