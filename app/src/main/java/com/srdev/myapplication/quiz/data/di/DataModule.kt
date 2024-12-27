package com.srdev.myapplication.quiz.data.di

import com.srdev.myapplication.quiz.data.remote.QuizApi
import com.srdev.myapplication.quiz.data.repository.QuizRepositoryImpl
import com.srdev.myapplication.quiz.domain.repository.QuizRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * A Dagger Hilt module for providing dependencies related to data handling in the quiz application.
 * This module is installed in the SingletonComponent, making the provided dependencies available
 * application-wide.
 */
@InstallIn(SingletonComponent::class)
@Module
object DataModule {

    /**
     * Provides a singleton instance of [QuizApi] for making API calls.
     *
     * @return A [QuizApi] instance created using Retrofit.
     */
    @Provides
    @Singleton
    fun provideQuizApi(): QuizApi {
        return Retrofit.Builder()
            .baseUrl("https://opentdb.com/") // Base URL for the Open Trivia Database API
            .addConverterFactory(GsonConverterFactory.create()) // Converts JSON responses to Kotlin objects
            .build()
            .create(QuizApi::class.java)
    }

    /**
     * jb dagger is function ko call karege to wo phele quizzApi:QuizApi ko check karega ki iske liye koi function hai ya nhi
     */
    @Provides
    @Singleton
    fun provideQuizRepository(quizApi: QuizApi) : QuizRepository {
        return QuizRepositoryImpl(quizApi= quizApi)
    }
}