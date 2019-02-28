package com.example.manpa.appalimentossqlite.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.manpa.appalimentossqlite.model.Alimento;

public class AlimentosDatabase {
    AlimentosSQLiteHelper ash;
    Context context;

    public AlimentosDatabase(Context contexto) {
        this.context = contexto;
        ash = new AlimentosSQLiteHelper(contexto);
    }

    public SQLiteDatabase openReadable() {
        return ash.getReadableDatabase();
    }
    public SQLiteDatabase openWriteable() {
        return ash.getWritableDatabase();
    }
    public void close(SQLiteDatabase database) {
        database.close();
    }

    public long insertarAlimento(Alimento alimento){
        SQLiteDatabase sdb= openWriteable();
        sdb.beginTransaction();

        ContentValues cv=new ContentValues();
        cv.put(AlimentosContact.AlimentosEntry.COLUMN_NAME,alimento.getNombre());
        cv.put(AlimentosContact.AlimentosEntry.COLUMN_TIPO,alimento.getTipo());
        cv.put(AlimentosContact.AlimentosEntry.COLUMN_ORIGEN,alimento.getOrigen());
        cv.put(AlimentosContact.AlimentosEntry.COLUMN_NUTRIENTES,alimento.getNutrientes());
        cv.put(AlimentosContact.AlimentosEntry.COLUMN_FUNCION,alimento.getFuncion());

        long id= sdb.insert(AlimentosContact.AlimentosEntry.TABLE_NAME, null, cv);

        if(id!=1){
            sdb.setTransactionSuccessful();
        }
        sdb.endTransaction();
        close(sdb);

        return id;
    }

    public Alimento consultarAlimento(String nombreAlimento){
        SQLiteDatabase sdb=openReadable();

        String select = "SELECT " + AlimentosContact.AlimentosEntry.COLUMN_ID +
                ", " + AlimentosContact.AlimentosEntry.COLUMN_NAME +
                ", " + AlimentosContact.AlimentosEntry.COLUMN_TIPO +
                ", " + AlimentosContact.AlimentosEntry.COLUMN_ORIGEN +
                ", " + AlimentosContact.AlimentosEntry.COLUMN_NUTRIENTES +
                ", " + AlimentosContact.AlimentosEntry.COLUMN_FUNCION +
                " FROM " + AlimentosContact.AlimentosEntry.TABLE_NAME +
                " WHERE " + AlimentosContact.AlimentosEntry.COLUMN_NAME + " =?";

        String [] args={String.valueOf(nombreAlimento)};

        Cursor cursor=sdb.rawQuery(select, args);

        Alimento alimento=null;
        int id;
        String nombre;
        String tipo;
        String origen;
        String nutrientes;
        String funcion;

        if(cursor.moveToFirst()){
            id=cursor.getInt(cursor.getColumnIndex(AlimentosContact.AlimentosEntry.COLUMN_ID));
            nombre=cursor.getString(cursor.getColumnIndex(AlimentosContact.AlimentosEntry.COLUMN_NAME));
            tipo=cursor.getString(cursor.getColumnIndex(AlimentosContact.AlimentosEntry.COLUMN_TIPO));
            origen=cursor.getString(cursor.getColumnIndex(AlimentosContact.AlimentosEntry.COLUMN_ORIGEN));
            nutrientes=cursor.getString(cursor.getColumnIndex(AlimentosContact.AlimentosEntry.COLUMN_NUTRIENTES));
            funcion=cursor.getString(cursor.getColumnIndex(AlimentosContact.AlimentosEntry.COLUMN_FUNCION));

            alimento=new Alimento(id, nombre,tipo,origen,nutrientes,funcion);
        }

        cursor.close();
        sdb.close();
        return alimento;
    }

    public void borrarAlimento(int idAlimento){
        SQLiteDatabase sdb=openWriteable();
        sdb.beginTransaction();

        String clausulaWhere=AlimentosContact.AlimentosEntry.COLUMN_NAME + " = "
                + idAlimento;
        sdb.delete(AlimentosContact.AlimentosEntry.TABLE_NAME,
              clausulaWhere,null);

        sdb.setTransactionSuccessful();
        sdb.endTransaction();
        close(sdb);
    }

}


