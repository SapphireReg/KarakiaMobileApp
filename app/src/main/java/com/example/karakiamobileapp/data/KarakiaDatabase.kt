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
                dao.insert(Karakia(R.drawable.test_image, R.raw.opening_karakia_timatanga1,
                    "opening_karakia_timatanga_1_maori.txt",
                    "opening_karakia_timatanga_1_english.txt",
                    "Opening Karakia Timanga 1 (Opening)", "This is a short description of the Karakia",
                    "This is a very very long description of the karakia")
                )
                dao.insert(Karakia(R.drawable.test_image, R.raw.opening_karakia_timatanga2,
                    "opening_karakia_timatanga_1_maori.txt",
                    "opening_karakia_timatanga_1_english.txt",
                    "Opening Karakia Timanga 2 (Opening)", "This is a short description of the Karakia",
                    "This is a very very long description of the karakia")
                )
                dao.insert(Karakia(R.drawable.test_image, R.raw.closing_karakia_whakamutunga1,
                    "opening_karakia_timatanga_1_maori.txt",
                    "opening_karakia_timatanga_1_english.txt",
                    "Closing Karakia Whatamutunga 1 (Closing)", "This is a short description of the Karakia",
                    "This is a very very long description of the karakia")
                )
                dao.insert(Karakia(R.drawable.test_image, R.raw.closing_karakia_whakamutunga2,
                    "opening_karakia_timatanga_1_maori.txt",
                    "opening_karakia_timatanga_1_english.txt",
                    "Karakia Whatamutunga 2 (Closing)", "This is a short description of the Karakia",
                    "This is a very very long description of the karakia")
                )
                dao.insert(Karakia(R.drawable.test_image, R.raw.karakia_ki_te_kai_blessing_for_food,
                    "opening_karakia_timatanga_1_maori.txt",
                    "opening_karakia_timatanga_1_english.txt",
                    "Karakia Ki Te Kai (Blessing for Food)", "This is a short description of the Karakia",
                    "This is a very very long description of the karakia")
                )
                dao.insert(Karakia(R.drawable.test_image, R.raw.karakia_ki_te_kai_blessing_for_food,
                    "opening_karakia_timatanga_1_maori.txt",
                    "opening_karakia_timatanga_1_english.txt",
                    "How to use the App", "This video guides users on how to use the app",
                    "This video guides users on how to use the app")
                )
            }

        }
    }
}

