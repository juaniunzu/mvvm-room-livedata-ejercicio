package com.example.mvvm_livedata_room;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = Note.class, version = 1)
public abstract class NoteDatabase extends RoomDatabase {

    //se usa para el singleton
    private static NoteDatabase instance;

    //metodo que se usa a cambio del constructor (esta clase no se instancia con new)
    public static synchronized NoteDatabase getInstance(Context context){

        if (instance == null){

            //codigo equivalente al constructor privado (room tiene un builder)
            instance = Room.databaseBuilder(context.getApplicationContext(), NoteDatabase.class, "note_database")
                    //algo de la version, ver
                    .fallbackToDestructiveMigration()
                    //callback creada abajo, este metodo puede no agregarse en caso de no querer poner nada de antemano
                    .addCallback(roomCallback)
                    .build();
        }

        //si estaba creada, la retorno. si no estaba creada, la creo primero y dps la retorno (singleton)
        return instance;
    }

    //room se encarga de crear lo que devuelve este metodo
    public abstract NoteDao noteDao();

    //callback donde vamos a agregar resultados iniciales a la db. se tienen que agregar
    //de manera asincrona asique creamos clase abajo que extiende de AsyncTask donde van a estar
    //los insert. esa clase se llama en el onCreate de esta callback
    public static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }

    };

    //async task donde vamos a agregar resultados iniciales a la db
    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void>{

        private NoteDao noteDao;

        public PopulateDbAsyncTask(NoteDatabase db) {
            this.noteDao = db.noteDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.insert(new Note("Title 1", "Description 1", 1));
            noteDao.insert(new Note("Title 2", "Description 2", 5));
            noteDao.insert(new Note("Title 3", "Description 3", 7));

            return null;
        }
    }



}
