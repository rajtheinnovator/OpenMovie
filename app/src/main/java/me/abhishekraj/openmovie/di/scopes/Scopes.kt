package me.abhishekraj.openmovie.di.scopes

import javax.inject.Scope

/**
 * Created by Abhishek Raj on 8/15/2019.
 */

@Scope
@Retention(AnnotationRetention.SOURCE)
annotation class ActivityScope

@Scope
@Retention(AnnotationRetention.SOURCE)
annotation class ApplicationScope