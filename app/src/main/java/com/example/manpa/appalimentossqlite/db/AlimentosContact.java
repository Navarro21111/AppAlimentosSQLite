package com.example.manpa.appalimentossqlite.db;

import android.provider.BaseColumns;

public class AlimentosContact {
    public static abstract class AlimentosEntry implements BaseColumns {
        public static final String TABLE_NAME = "Alimentos";
        public static final String COLUMN_ID = BaseColumns._ID;
        public static final String COLUMN_NAME = "NOMBRE";
        public static final String COLUMN_TIPO = "TIPO";
        public static final String COLUMN_ORIGEN = "ORIGEN";
        public static final String COLUMN_NUTRIENTES = "NUTRIENTES";
        public static final String COLUMN_FUNCION = "FUNCION";

    }
}
