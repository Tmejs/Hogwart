package com.tmejs.db.domain;

/**
 * Hello world!
 *
 */
public class Nauczyciel {

    public long id;
    public String Imie;
    public String Nazwisko;

    public Boolean equals(Nauczyciel obj) {
        return id == ((Nauczyciel) obj).id && Imie.equalsIgnoreCase(((Nauczyciel) obj).Imie) && Nazwisko.equalsIgnoreCase(((Nauczyciel) obj).Nazwisko);
    }

}
