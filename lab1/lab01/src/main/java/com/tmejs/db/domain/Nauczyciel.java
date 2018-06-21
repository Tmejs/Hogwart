package com.tmejs.db.domain;

/**
 * Hello world!
 *
 */
public class Nauczyciel {

    public Integer Id;
    public String Imie;
    public String Nazwisko;

    public Nauczyciel(Integer id, String imie, String nazwisko) {
        this.Id = id;
        Imie = imie;
        Nazwisko = nazwisko;
    }

    public Nauczyciel() {

    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Nauczyciel) {
            return this.equals((Nauczyciel) obj);
        } else {
            return super.equals(obj);
        }
    }

    public Boolean equals(Nauczyciel obj) {
        return Id == (obj).Id && Imie.equalsIgnoreCase((obj).Imie) && Nazwisko.equalsIgnoreCase(( obj).Nazwisko);
    }

    public Integer getId() {
        return Id;
    }

    public String getImie() {
        return Imie;
    }

    public String getNazwisko() {
        return Nazwisko;
    }
}
