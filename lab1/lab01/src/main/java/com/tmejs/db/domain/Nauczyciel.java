package com.tmejs.db.domain;

/**
 * Hello world!
 *
 */
public class Nauczyciel {

    public long id;
    public String Imie;
    public String Nazwisko;

    @Override
    public boolean equals(Object obj) {
        if(obj.getClass().equals(Nauczyciel.class)){
            return id==((Nauczyciel) obj).id && Imie.equalsIgnoreCase(((Nauczyciel) obj).Imie) && Nazwisko.equalsIgnoreCase(((Nauczyciel) obj).Nazwisko);
        }else{
            return super.equals(obj);
        }
    }
    
    

}
