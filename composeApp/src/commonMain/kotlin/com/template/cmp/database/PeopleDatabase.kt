package com.template.cmp.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor

//@Suppress("NO_ACTUAL_FOR_EXPECT")
//internal expect object AppDatabaseCtor : RoomDatabaseConstructor<PeopleDatabase>

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object AppDatabaseCtor : RoomDatabaseConstructor<PeopleDatabase> {
    override fun initialize(): PeopleDatabase
}

@Database(
    entities = [Person::class],
    version = 1
)
@ConstructedBy(AppDatabaseCtor::class)
abstract class PeopleDatabase : RoomDatabase() {

    abstract fun peopleDao(): PeopleDao
}