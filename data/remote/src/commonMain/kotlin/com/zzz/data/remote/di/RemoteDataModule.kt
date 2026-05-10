package com.zzz.data.remote.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import com.zzz.data.remote.HttpClientFactory
import com.zzz.data.remote.data.auth.RemoteAuthSource
import com.zzz.data.remote.data.job.RemoteJobApplicationSource
import com.zzz.data.remote.data.job.RemoteJobSource
import com.zzz.data.remote.data.prefs.RemoteDatastoreSource
import com.zzz.data.remote.data.prefs.datastoreName
import com.zzz.data.remote.data.student.announcements.RemoteAnnouncementSource
import com.zzz.data.remote.data.student.profile.RemoteProfileSource
import com.zzz.data.remote.domain.auth.AuthSource
import com.zzz.data.remote.domain.job.JobApplicationSource
import com.zzz.data.remote.domain.job.JobSource
import com.zzz.data.remote.domain.student.announcements.AnnouncementSource
import com.zzz.data.remote.domain.student.profile.ProfileSource
import io.ktor.client.HttpClient
import org.koin.core.module.Module
import org.koin.dsl.module
import kotlin.math.sin

val remoteDataModule = module {
    single<HttpClient> {
        HttpClientFactory.create(
            engine = get(),
            tokenSource = get()
        )
    }
    single<RemoteDatastoreSource> {
        RemoteDatastoreSource(
            datastore = get()
        )
    }
    //--------AUTH--------
    single<AuthSource> {
        RemoteAuthSource(
            client = get()
        )
    }
    //--------PROFILW--------
    single<ProfileSource> {
        RemoteProfileSource(
            client = get()
        )
    }
    //--------JOB--------
    single<JobSource> {
        RemoteJobSource(
            client = get()
        )
    }
    single<JobApplicationSource> {
        RemoteJobApplicationSource(
            client = get()
        )
    }
    single<AnnouncementSource> {
        RemoteAnnouncementSource(
            client = get()
        )
    }

}

expect val engineModule : Module
