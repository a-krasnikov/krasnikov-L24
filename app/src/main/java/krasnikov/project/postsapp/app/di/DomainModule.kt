package krasnikov.project.postsapp.app.di

import android.content.Context
import android.content.res.Resources
import krasnikov.project.postsapp.post.create.domain.validate.PostValidator
import org.koin.dsl.module

val domainModule = module {
    factory { PostValidator(provideResources(get())) }
}

fun provideResources(context: Context): Resources {
    return context.resources
}