package com.catching.pucks.database.DataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): MainDatabase {
        return Room.databaseBuilder(
            context,
            MainDatabase::class.java,
            "test.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideFavoriteDao(database: MainDatabase): CoinDao {
        return database.daoFavorite
    }

    @Provides
    fun providePortfolioDao(database: MainDatabase): PortfolioDao {
        return database.daoPortfolio
    }

    @Provides
    fun provideRegistrationDao(database: MainDatabase): UserDao {
        return database.daoUser
    }
}

@Database(entities = [CoinDB::class, CoinPortfolioDB::class, UserDB::class], version = 1)
abstract class MainDatabase : RoomDatabase() {
    abstract val daoFavorite: CoinDao
    abstract val daoPortfolio: PortfolioDao
    abstract val daoUser: UserDao

}