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
                dao.insert(Karakia(R.drawable.wintec_marae_entrance, R.raw.opening_karakia_timatanga1,
                    "opening_karakia_timatanga_1_maori.txt",
                    "opening_karakia_timatanga_1_english.txt",
                    "Opening Karakia Timatanga 1", "For beginning a hui (meeting, event or gathering).",
                    "This Karakia is used when starting a hui (meeting, event or gathering).\n" +
                            "\n" +
                            "It’s a Karakia that includes all by drawing learners, staff, and everyone from around the world together under love and peace for as long as we remain.\n" +
                            "\n" +
                            "The last words, “Taiki E” (pronounced “tie key aye”) should be spoken by everyone in the group, as they are used to signal that the group is united and ready to move forward together.")
                )
                dao.insert(Karakia(R.drawable.wintec_students_marae, R.raw.opening_karakia_timatanga2,
                    "opening_karakia_timatanga_2_maori.txt",
                    "opening_karakia_timatanga_2_english.txt",
                    "Opening Karakia Timatanga 2", "For beginning a hui (meeting, event or gathering).",
                    "This Karakia is used when starting a hui (meeting, event or gathering).\n" +
                            "\n" +
                            "It’s a Karakia that means to prepare for what lies ahead, that the dawn will rise again, so come and join.\n" +
                            "\n" +
                            "The last words, “Taiki E” (pronounced “tie key aye”) should be spoken by everyone in the group, as they are used to signal that the group is united and ready to move forward together.")
                )
                dao.insert(Karakia(R.drawable.wintec_marae, R.raw.closing_karakia_whakamutunga2,
                    "closing_karakia_whakamutunga_1_maori.txt",
                    "closing_karakia_whakamutunga_1_english.txt",
                    "Closing Karakia Whakamutunga 1", "For ending a hui (meeting, event or gathering).",
                    "This Karakia is used when ending a hui (meeting, event or gathering).\n" +
                            "\n" +
                            "It’s a Karakia that prays for the love of God and the fellowship of the Holy Spirit to be with everyone. A blessing for everyone to end the hui with.\n" +
                            "\n" +
                            "The last word, “Amine” (pronounced “ah men eh”) should be spoken by everyone in the group, it is used to acknowledge the group is united and the ending of the Karakia.")
                )
                dao.insert(Karakia(R.drawable.wintec_marae_pillars, R.raw.closing_karakia_whakamutunga1,
                    "closing_karakia_whakamutunga_2_maori.txt",
                    "closing_karakia_whakamutunga_2_english.txt",
                    "Closing Karakia Whakamutunga 2", "For ending a hui (meeting, event or gathering).",
                    "This Karakia is used when ending a hui (meeting, even or gathering).\n" +
                            "\n" +
                            "It’s a Karakia that prays for honour, glory, and peace to the land. And that good thoughts come to all, forever and ever.\n" +
                            "\n" +
                            "The last word, “Amine” (pronounced “ah men eh”) should be spoken by everyone in the group, it is used to acknowledge the group is united and the ending of the Karakia.")
                )
                dao.insert(Karakia(R.drawable.maori_weaving, R.raw.karakia_ki_te_kai_blessing_for_food,
                    "karakia_ki_te_kai_maori.txt",
                    "karakia_ki_te_kai_english.txt",
                    "Karakia Ki Te Kai", "For blessing food.",
                    "This Karakia is used to bless Kai (food).\n" +
                            "\n" +
                            "It’s a Karakia that blesses the food that we’re about to receive for the goodness of our bodies, not only physically but spiritually.\n" +
                            "\n" +
                            "The last word, “Amine” (pronounced “ah men eh”) should be spoken by everyone in the group, it is used to acknowledge the ending of the Karakia and that you may begin eating.")
                )

            }

        }
    }
}

