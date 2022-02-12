package com.example.educational3dworld.di

import com.example.educational3dworld.app.App
import com.example.educational3dworld.data.pref.MyPref
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun getSharedPref() = MyPref(App.instance)

    @Provides
    @Singleton
    fun getFirebaseFirestore() = Firebase.firestore
}