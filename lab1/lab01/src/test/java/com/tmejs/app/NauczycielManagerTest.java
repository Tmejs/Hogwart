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
    Nauczyciel nauczyciel;
    
    public NauczycielManagerTest() throws SQLException {
//        String url = "jdbc:hsqldb:hsql://localhost/workdb";

    }
    
    @Before
    public void initRepository() throws Exception {
        nauczycielManager = new NauczycielManagerImpl("workdb", "root", "root");
        
        nauczyciel = new Nauczyciel();
        Nauczyciel nauczyciel2 = new Nauczyciel();
        Nauczyciel nauczyciel3 = new Nauczyciel();
        Nauczyciel nauczyciel4 = new Nauczyciel();
        
        nauczyciel.Id = 1;
        nauczyciel.Imie = "Jeden";
        nauczyciel.Nazwisko = "1Nazwisko";
        
        nauczyciel2.Id = 2;
        nauczyciel2.Imie = "Dwa";
        nauczyciel2.Nazwisko = "2Nazwisko";
        
        nauczyciel3.Id = 3;
        nauczyciel3.Imie = "trzzy";
        nauczyciel3.Nazwisko = "3Nazwisko";
        
        nauczyciel4.Id = 4;
        nauczyciel4.Imie = "cztery";
        nauczyciel4.Nazwisko = "4Nazwisko";
        
        nauczycielManager.addNauczyciel(nauczyciel);
        nauczycielManager.addNauczyciel(nauczyciel2);
        nauczycielManager.addNauczyciel(nauczyciel3);
        nauczycielManager.addNauczyciel(nauczyciel4);
    }
    
    @Test
    public void checkTable() throws Exception {
        assertTrue(nauczycielManager.isDatabaseReady());
    }
    
    @Test
    public void checkAdding() {
        Nauczyciel nauczyciel = new Nauczyciel();
        nauczyciel.Id = 10;
        nauczyciel.Imie = "addImie";
        nauczyciel.Nazwisko = "addNaziwso";
        Integer id = nauczycielManager.addNauczyciel(nauczyciel);

        //Sprawdzenie czy poprawnie dodany
        Nauczyciel newNauczyciel = nauczycielManager.getNauczyciel(nauczyciel.Id);
        assertNotNull(newNauczyciel);
        
    }
    
    @Test
    public void checkUpdating() {

        //Kontrolny do sprawdzenia czy nie za duzo update
        Nauczyciel newNauczycielTest = new Nauczyciel();
        newNauczycielTest.Id = 1;
        newNauczycielTest.Imie = "testUpdate";
        newNauczycielTest.Nazwisko = "testUpate";

        //Update
        assertTrue(nauczycielManager.updateNauczyciel(newNauczycielTest));

        //Sprawdzenie update
        Nauczyciel downloaded = nauczycielManager.getNauczyciel(newNauczycielTest.Id);
        assertEquals(downloaded.Imie, newNauczycielTest.Imie);
        assertEquals(downloaded.Nazwisko, newNauczycielTest.Nazwisko);
        
        nauczycielManager.updateNauczyciel(this.nauczyciel);
        
    }
    
    @Test
    public void checkDelete() {
        Nauczyciel nauczyciel = new Nauczyciel();
        nauczyciel.Id = 500;
        nauczyciel.Imie = "addImie";
        nauczyciel.Nazwisko = "addNaziwso";
        nauczycielManager.addNauczyciel(nauczyciel);
        assertNotNull(nauczycielManager.getNauczyciel(nauczyciel.Id));
        assertTrue(nauczycielManager.deleteNauczyciel(nauczyciel));

        //Sprawdzenie czy nie usuneliśmy wszystkich
        assertFalse(nauczycielManager.getAllNauczyciels().isEmpty());
    }
    
    @Test
    public void checkGet() {
        //Sprawdzenie czy go zwraca
        Nauczyciel newNauczyciel = nauczycielManager.getNauczyciel(nauczyciel.Id);
        assertNotNull(newNauczyciel);
        
        assertEquals(nauczyciel.Imie, newNauczyciel.Imie);
        assertEquals(nauczyciel.Nazwisko, newNauczyciel.Nazwisko);
    }
    
}
