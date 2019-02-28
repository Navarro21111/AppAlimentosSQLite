package com.example.manpa.appalimentossqlite.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.manpa.appalimentossqlite.model.Alimento;

import java.util.ArrayList;

public class AlimentosSQLiteHelper extends SQLiteOpenHelper {


    static final String DATABASE_NAME = "ContactosDB";
    static final int DATABASE_VERSION = 1;

    static final String CREATE_TABLE_ALIMENTOS =
            "CREATE TABLE "
                    + AlimentosContact.AlimentosEntry.TABLE_NAME +
                    "( " + AlimentosContact.AlimentosEntry.COLUMN_ID
                    + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
                    AlimentosContact.AlimentosEntry.COLUMN_NAME
                    + " TEXT NOT NULL," +
                    AlimentosContact.AlimentosEntry.COLUMN_TIPO
                    + " TEXT NOT NULL," +
                    AlimentosContact.AlimentosEntry.COLUMN_ORIGEN
                    + " TEXT NOT NULL," +
                    AlimentosContact.AlimentosEntry.COLUMN_NUTRIENTES
                    + " TEXT NOT NULL," +
                    AlimentosContact.AlimentosEntry.COLUMN_FUNCION
                    + " TEXT NOT NULL);";

    private ArrayList<Alimento> listaInicial = new ArrayList<Alimento>();

    private void cargarLista() {
        listaInicial.add(new Alimento("arroz", "Cereales", "Vegetal", "Carbohidratos", "Energética"));
        listaInicial.add(new Alimento("pasta", "Cereales", "Vegetal", "Carbohidratos", "Energética"));
        listaInicial.add(new Alimento("pan", "Cereales", "Vegetal", "Carbohidratos", "Energética"));
        listaInicial.add(new Alimento("leche", "Lácteos", "Animal", "Proteínas animales", "Plástica"));
        listaInicial.add(new Alimento("queso", "Lácteos", "Animal", "Proteínas animales", "Plástica"));
        listaInicial.add(new Alimento("yogurt", "Lácteos", "Animal", "Proteínas animales", "Plástica"));
        listaInicial.add(new Alimento("huevos", "Huevos", "Animal", "Proteínas animales", "Plástica"));
        listaInicial.add(new Alimento("azúcar", "Azúcares", "Vegetal", "Carbohidratos", "Energética"));
        listaInicial.add(new Alimento("chocolate", "Azúcares", "Vegetal", "Carbohidratos", "Energética"));
        listaInicial.add(new Alimento("aceite oliva", "Aceites", "Vegetal", "Lípidos", "Energética"));
        listaInicial.add(new Alimento("berenjena", "Verduras y Hortalizas", "Vegetal", "Vitaminas", "Reguladora"));
        listaInicial.add(new Alimento("calabacín", "Verduras y Hortalizas", "Vegetal", "Vitaminas", "Reguladora"));
        listaInicial.add(new Alimento("tomate", "Verduras y Hortalizas", "Vegetal", "Vitaminas", "Reguladora"));
        listaInicial.add(new Alimento("zanahoria", "Verduras y Hortalizas", "Vegetal", "Vitaminas", "Reguladora"));
        listaInicial.add(new Alimento("patata", "Verduras y Hortalizas", "Vegetal", "Vitaminas, Proteínas Vegetales y Lípidos", "Reguladora, Plástica y Energética"));
        listaInicial.add(new Alimento("garbanzos", "Legumbres", "Vegetal", "Vitaminas, Proteínas Vegetales y Lípidos", "Reguladora, Plástica y Energética"));
        listaInicial.add(new Alimento("lentejas", "Legumbres", "Vegetal", "Vitaminas, Proteínas Vegetales y Lípidos", "Reguladora, Plástica y Energética"));
        listaInicial.add(new Alimento("naranja", "Frutas", "Vegetal", "Vitaminas", "Reguladora"));
        listaInicial.add(new Alimento("plátano", "Frutas", "Vegetal", "Vitaminas", "Reguladora"));
        listaInicial.add(new Alimento("manzana", "Frutas", "Vegetal", "Vitaminas", "Reguladora"));
        listaInicial.add(new Alimento("almendra", "Frutos secos", "Vegetal", "Vitaminas, Proteínas Vegetales y Lípidos", "Reguladora, Plástica y Energética"));
        listaInicial.add(new Alimento("nuez", "Frutos secos", "Vegetal", "Vitaminas, Proteínas Vegetales y Lípidos", "Reguladora, Plástica y Energética"));
        listaInicial.add(new Alimento("jamón", "Carne", "Animal", "Proteínas animales", "Plástica"));
        listaInicial.add(new Alimento("conejo", "Carne", "Animal", "Proteínas animales", "Plástica"));
        listaInicial.add(new Alimento("pollo", "Carne", "Animal", "Proteínas animales", "Plástica"));
        listaInicial.add(new Alimento("bacalao", "Pescado", "Animal", "Proteínas animales", "Plástica"));
        listaInicial.add(new Alimento("merluza", "Pescado", "Animal", "Proteínas animales", "Plástica"));
        listaInicial.add(new Alimento("salmón", "Pescado", "Animal", "Proteínas animales", "Plástica"));

    }


    public AlimentosSQLiteHelper(Context contexto) {
        super(contexto, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_ALIMENTOS);
        cargarLista();
        cargaInicial(sqLiteDatabase);

    }

    private void cargaInicial(SQLiteDatabase database) {

        database.beginTransaction();

        ContentValues cv;
        long id = 0;
        for (Alimento al: listaInicial) {
            cv = new ContentValues();
            cv.put(AlimentosContact.AlimentosEntry.COLUMN_NAME,
                    al.getNombre());
            cv.put(AlimentosContact.AlimentosEntry.COLUMN_TIPO,
                    al.getTipo());
            cv.put(AlimentosContact.AlimentosEntry.COLUMN_ORIGEN,
                    al.getOrigen());
            cv.put(AlimentosContact.AlimentosEntry.COLUMN_NUTRIENTES,
                    al.getNutrientes());
            cv.put(AlimentosContact.AlimentosEntry.COLUMN_FUNCION,
                    al.getFuncion());

            id = database.insert(AlimentosContact.AlimentosEntry.TABLE_NAME,
                    null, cv);


        }
        if (id != -1) {
            database.setTransactionSuccessful();
        }

        database.endTransaction();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " +
                AlimentosContact.AlimentosEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
