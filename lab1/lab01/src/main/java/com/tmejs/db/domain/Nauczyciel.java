package com.tmejs.db.domain;

/**
 * Hello world!
 *
 */
public class Nauczyciel {

    public Integer id;
    public String Imie;
    public String Nazwisko;

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Nauczyciel) {
            return this.equals((Nauczyciel) obj);
        } else {
            return super.equals(obj);
        }
    }

    public Boolean equals(Nauczyciel obj) {
        return id == (obj).id && Imie.equalsIgnoreCase((obj).Imie) && Nazwisko.equalsIgnoreCase(( obj).Nazwisko);
    }

}
