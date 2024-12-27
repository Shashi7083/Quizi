package com.srdev.myapplication.quiz.domain.di

import com.srdev.myapplication.quiz.data.remote.QuizApi
import com.srdev.myapplication.quiz.data.repository.QuizRepositoryImpl
import com.srdev.myapplication.quiz.domain.repository.QuizRepository
import com.srdev.myapplication.quiz.domain.usecases.GetQuizzesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object DomainModule {

    @Provides
    @Singleton
    fun provideGetQuizzesUseCases(quizRepository: QuizRepository) : GetQuizzesUseCase{
        return GetQuizzesUseCase(quizRepository = quizRepository)
    }

}