package moe.bitt.filebrowser.network.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import moe.bitt.filebrowser.common.model.api.qualifier.Auth
import moe.bitt.filebrowser.network.BuildConfig
import moe.bitt.filebrowser.network.interceptor.HandleHostnameInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val EMPTY_BASE_URL = "https://somesite.com/"

    @Auth
    @Provides
    fun provideAuthRetrofit(@Auth client: OkHttpClient): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(EMPTY_BASE_URL)
            .client(client)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(EMPTY_BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Auth
    @Provides
    fun provideAuthClient(@ApplicationContext context: Context): OkHttpClient {
        // TODO Добавить интерсептор с авторизацией
        val clientBuilder = OkHttpClient
            .Builder()
            .addInterceptor(HandleHostnameInterceptor())
        return when (BuildConfig.BUILD_TYPE) {
            "debug" -> {
                // Create the Collector
                val chuckerCollector = ChuckerCollector(
                    context = context,
                    // Toggles visibility of the notification
                    showNotification = true,
                    // Allows to customize the retention period of collected data
                    retentionPeriod = RetentionManager.Period.ONE_HOUR
                )
                // Create the Interceptor
                val chuckerInterceptor = ChuckerInterceptor.Builder(context)
                    // The previously created Collector
                    .collector(chuckerCollector)
                    // The max body content length in bytes, after this responses will be truncated.
                    .maxContentLength(250_000L)
                    // Read the whole response body even when the client does not consume the response completely.
                    // This is useful in case of parsing errors or when the response body
                    // is closed before being read like in Retrofit with Void and Unit types.
                    .alwaysReadResponseBody(true)
                    .build()
                val logging = HttpLoggingInterceptor()
                logging.level = (HttpLoggingInterceptor.Level.BASIC)
                clientBuilder.addInterceptor(logging)
                clientBuilder.addInterceptor(chuckerInterceptor)
                clientBuilder.build()
            }
            else -> clientBuilder.build()
        }
    }

    @Provides
    fun provideClient(@ApplicationContext context: Context): OkHttpClient {
        val clientBuilder = OkHttpClient
            .Builder()
            .addInterceptor(HandleHostnameInterceptor())
        return when (BuildConfig.BUILD_TYPE) {
            "debug" -> {
                // Create the Collector
                val chuckerCollector = ChuckerCollector(
                    context = context,
                    // Toggles visibility of the notification
                    showNotification = true,
                    // Allows to customize the retention period of collected data
                    retentionPeriod = RetentionManager.Period.ONE_HOUR
                )
                // Create the Interceptor
                val chuckerInterceptor = ChuckerInterceptor.Builder(context)
                    // The previously created Collector
                    .collector(chuckerCollector)
                    // The max body content length in bytes, after this responses will be truncated.
                    .maxContentLength(250_000L)
                    // Read the whole response body even when the client does not consume the response completely.
                    // This is useful in case of parsing errors or when the response body
                    // is closed before being read like in Retrofit with Void and Unit types.
                    .alwaysReadResponseBody(true)
                    .build()
                val logging = HttpLoggingInterceptor()
                logging.level = (HttpLoggingInterceptor.Level.BASIC)
                clientBuilder.addInterceptor(logging)
                clientBuilder.addInterceptor(chuckerInterceptor)
                clientBuilder.build()
            }
            else -> clientBuilder.build()
        }
    }

}