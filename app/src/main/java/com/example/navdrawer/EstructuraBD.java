package com.example.navdrawer;

public class EstructuraBD {
    private EstructuraBD() {}

    /* Inner class that defines the table contents */
    //public static class FeedEntry implements BaseColumns {
    public static final String NOMBRE_TABLA = "datosPersonas";
    public static final String COL1 = "Id";
    public static final String COL2 = "Nombre";
    public static final String COL3 = "Apellido";

    protected static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + EstructuraBD.NOMBRE_TABLA + " (" +
                    EstructuraBD.COL1 + " INTEGER PRIMARY KEY," +
                    EstructuraBD.COL2 + " TEXT," +
                    EstructuraBD.COL3 + " TEXT)";

    protected static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + EstructuraBD.NOMBRE_TABLA;
    // }

}