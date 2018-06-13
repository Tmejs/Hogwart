/*
 * Copyright (C) 2018 Tmejs
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.tmejs.app;

import com.tmejs.db.domain.Nauczyciel;

import com.tmejs.db.service.NauczycielManager;
import com.tmejs.db.service.NauczycielManagerImpl;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.sql.SQLException;
import org.junit.Before;

/**
 *
 * @author Tmejs
 */
@RunWith(JUnit4.class)
public class NauczycielManagerTest {

    NauczycielManager nauczycielManager;

    public NauczycielManagerTest() throws SQLException {
//        String url = "jdbc:hsqldb:hsql://localhost/workdb";

    }

    @Before
    public void initRepository() throws Exception {
        nauczycielManager = new NauczycielManagerImpl("workdb", "root", "root");
    }

    @Test
    public void checkAdding() {
        Nauczyciel nauczyciel = new Nauczyciel();
        nauczyciel.Imie = "addImie";
        nauczyciel.Nazwisko = "addNaziwso";
        Integer id = nauczycielManager.addNauczyciel(nauczyciel);
        System.out.println("ID:  " + id);
        assertNotNull(id);

        //Sprawdzenie czy poprawnie dodany
        Nauczyciel newNauczyciel = nauczycielManager.getNauczyciel(Long.decode("44"));
        assertNotNull(newNauczyciel);

    }

    @Test
    public void checkUpdating() {
        Nauczyciel nauczyciel = new Nauczyciel();
        nauczyciel.Imie = "addImieUpdate";
        nauczyciel.Nazwisko = "addNaziwsoUpdate";
        Integer id = nauczycielManager.addNauczyciel(nauczyciel);
        
        
        //Pobranie iinego nauczyciel an potrzeby testu
        Nauczyciel testNauczyciel = nauczycielManager.getNauczyciel(id.longValue());
        testNauczyciel.Imie = testNauczyciel.Imie + "pUpdate";

        //Kontrolny do sprawdzenia czy nie za duzo update
        Nauczyciel newNauczycielTest = new Nauczyciel();
        newNauczycielTest.Imie = "testUpdate";
        newNauczycielTest.Nazwisko = "testUpate";
        Integer newId = nauczycielManager.addNauczyciel(newNauczycielTest);

        //Update
        assertTrue(nauczycielManager.updateNauczyciel(testNauczyciel));

        //Pobranie nauczyciela w celu sprawdzenia czy poszedł update
        Nauczyciel newNauczyciel = nauczycielManager.getNauczyciel(id.longValue());
        Nauczyciel newNauczycielTestToTest = nauczycielManager.getNauczyciel(newId.longValue());

        //Sprawdzenie update
        assertEquals(testNauczyciel.Imie, newNauczyciel.Imie);

        //Sprawdzenie czy nie update innychpozycji
        assertEquals(newNauczycielTest.Imie, newNauczycielTestToTest.Imie);

    }

    @Test
    public void checkDelete() {
        Nauczyciel nauczyciel = new Nauczyciel();
        nauczyciel.id = 1;
        nauczyciel.Imie = "Imie";
        nauczyciel.Nazwisko = "Nazwisko";
        assertTrue(nauczycielManager.deleteNauczyciel(nauczyciel));

        //Sprawdzenie czy nie usuneliśmy wszystkich
        assertFalse(nauczycielManager.getAllNauczyciels().isEmpty());
    }

    @Test
    public void checkGet() {

        //Dodajemu nowego nauczyciela
        Nauczyciel nauczyciel = new Nauczyciel();

        nauczyciel.Imie = "ImieGet";
        nauczyciel.Nazwisko = "NazwiskoGet";
        Integer id = nauczycielManager.addNauczyciel(nauczyciel);
        assertNotNull(id);
        //Sprawdzenie czy go zwraca
        Nauczyciel newNauczyciel = nauczycielManager.getNauczyciel(id.longValue());
        assertNotNull(newNauczyciel);

        assertEquals(nauczyciel.Imie, newNauczyciel.Imie);
        assertEquals(nauczyciel.Nazwisko, newNauczyciel.Nazwisko);
        //Sprawdzeonie czy jest taki sam jak wcześniej dodany
    }

}
