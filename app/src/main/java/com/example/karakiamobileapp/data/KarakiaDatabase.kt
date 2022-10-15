package com.example.karakiamobileapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.karakiamobileapp.R
import com.example.karakiamobileapp.di.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider


@Database(entities = [Karakia::class], version = 1)
abstract class KarakiaDatabase : RoomDatabase() {

    abstract fun karakiaDao() : KarakiaDao

    class Callback @Inject constructor(
        private val database: Provider<KarakiaDatabase>,
        @ApplicationScope private val applicationScope: CoroutineScope
    ) : RoomDatabase.Callback() { //tells dagger to create instance to class and pass dependencies if defined in constructor
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            val dao = database.get().karakiaDao()

            applicationScope.launch {
                dao.insert(Karakia(R.drawable.test_image, R.raw.vid1, R.raw.audio1, R.raw.verse1, R.raw.verse1,
                        "Karakia Title1", "This is a short description of the Karakia", "This is a very very long description of the karakia")
                )
                dao.insert(Karakia(R.drawable.test_image, R.raw.vid2, R.raw.audio1, R.raw.verse1, R.raw.verse1,
                        "Karakia Title2", "This is a short description of the Karakia", "This is a very very long description of the karakia")
                )
                dao.insert(Karakia(R.drawable.test_image, R.raw.vid3, R.raw.audio1, R.raw.verse1, R.raw.verse1,
                        "Karakia Title2", "This is a short description of the Karakia", "This is a very very long description of the karakia")
                )
                dao.insert(Karakia(R.drawable.test_image, R.raw.vid4, R.raw.audio1, R.raw.verse1, R.raw.verse1,
                    "Karakia Title2", "This is a short description of the Karakia", "This is a very very long description of the karakia")
                )
                dao.insert(Karakia(R.drawable.test_image, R.raw.vid5, R.raw.audio1, R.raw.verse1, R.raw.verse1,
                    "Karakia Title2", "This is a short description of the Karakia", "This is a very very long description of the karakia")
                )
                dao.insert(Karakia(R.drawable.test_image, R.raw.vid6, R.raw.audio1, R.raw.verse1, R.raw.verse1,
                    "Karakia Title2", "This is a short description of the Karakia", "This is a very very long description of the karakia")
                )
            }
        }
    }
}

