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
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.sql.SQLException;

/**
 *
 * @author Tmejs
 */
@Ignore
@RunWith(JUnit4.class)
public class NauczycielManagerTest {

    NauczycielManager nauczycielManager;

    public NauczycielManagerTest() throws SQLException {
        String url = "jdbc:hsqldb:hsql://localhost/workdb";
        nauczycielManager = null;
    }

    @Test
    public void checkAdding() {
        Nauczyciel nauczyciel = new Nauczyciel();
        nauczyciel.id = 1;
        nauczyciel.Imie = "Imie";
        nauczyciel.Nazwisko = "Nazwisko";
        assertNotNull(nauczycielManager.addNauczyciel(nauczyciel));

        //Sprawdzenie czy poprawnie dodany
        Nauczyciel newNauczyciel = nauczycielManager.getNauczyciel(Long.getLong("1"));
        assertNotNull(newNauczyciel);

    }

    @Test
    public void checkUpdating() {
        Nauczyciel nauczyciel = new Nauczyciel();
        nauczyciel.id = 1;
        nauczyciel.Imie = "Imie";
        nauczyciel.Nazwisko = "Nazwisko";

        //Pobranie iinego nauczyciel an potrzeby testu
        Nauczyciel testNauczyciel = nauczycielManager.getNauczyciel(Long.getLong("2"));

        //Update
        assertTrue(nauczycielManager.updateNauczyciel(nauczyciel));

        //Pobranie nauczyciela w celu sprawdzenia czy poszedł update
        Nauczyciel newNauczyciel = nauczycielManager.getNauczyciel(nauczyciel.id);

        //Sprawdzenie update
        assertEquals(nauczyciel, newNauczyciel);

        //Sprawdzenie innego losowego czy nie zosatły zmienione dane
        Nauczyciel newTestNauczyciel = nauczycielManager.getNauczyciel(Long.getLong("2"));
        assertEquals(testNauczyciel, newTestNauczyciel);

    }

    @Test
    public void checkDelete() {
        Nauczyciel nauczyciel = new Nauczyciel();
        nauczyciel.id = 1;
        nauczyciel.Imie = "Imie";
        nauczyciel.Nazwisko = "Nazwisko";
        assertTrue(nauczycielManager.deleteNauczyciel(nauczyciel));

        //Sprawdzenie czy nie usuneliśmy wszystkich
        assertNotNull(nauczycielManager.getAllPersons());
    }

    @Test
    public void checkGet() {

        //Dodajemu nowego nauczyciela
        Nauczyciel nauczyciel = new Nauczyciel();
        nauczyciel.id = 1;
        nauczyciel.Imie = "Imie";
        nauczyciel.Nazwisko = "Nazwisko";
        assertNotNull(nauczycielManager.addNauczyciel(nauczyciel));

        //Sprawdzenie czy go zwraca
        assertNotNull(nauczycielManager.getNauczyciel(nauczyciel.id));

        assertEquals(nauczycielManager.getNauczyciel(nauczyciel.id), nauczyciel);
        //Sprawdzeonie czy jest taki sam jak wcześniej dodany
    }

}
