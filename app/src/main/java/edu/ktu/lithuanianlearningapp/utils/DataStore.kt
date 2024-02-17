package edu.ktu.lithuanianlearningapp.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val currentUser = FirebaseAuth.getInstance().currentUser

val user = Firebase.auth.currentUser!!

val PREFERENCE_NAME = user.uid
//Instance of DataStore
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCE_NAME)

/**
* Add Boolean to the data store
*/
suspend fun Context.writeBool(key: String, value: Boolean) {
    dataStore.edit { pref -> pref[booleanPreferencesKey(key)] = value }
}

/**
 * Reading the Boolean from the data store
 */
fun Context.readBool(key: String): Flow<Boolean> {
    return dataStore.data.map { pref ->
        pref[booleanPreferencesKey(key)] ?: false
    }
}
/**
 * Add Integer to the data store
 */
suspend fun Context.writeInt(key: String, value: Int) {
    dataStore.edit { pref -> pref[intPreferencesKey(key)] = value }
}

/**
 * Reading the Int value from the data store
 */
fun Context.readInt(key: String): Flow<Int> {
    return dataStore.data.map { pref ->
        pref[intPreferencesKey(key)] ?: 0
    }
}