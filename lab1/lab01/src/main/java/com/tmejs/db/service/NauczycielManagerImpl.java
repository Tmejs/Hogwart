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
package com.tmejs.db.service;

import com.tmejs.db.domain.Nauczyciel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Tmejs
 */
public class NauczycielManagerImpl implements NauczycielManager {

    private Connection connection;
    public String dbName;

    private PreparedStatement addNauczycielStmt;
    private PreparedStatement getNauczycielStmt;
    private PreparedStatement getAllNauczycielsStmt;
    private PreparedStatement updateNauczycielsStmt;
    private PreparedStatement deleteNauczycielsStmt;

    public NauczycielManagerImpl(String database, String login, String password) throws Exception {
        dbName = database;
        setConnection(getConnection(database, login, password));
        if (!isDatabaseReady()) {
//            createDatabase();
            createTables();
            
        }

    }

    
    public void createDatabase() throws SQLException{
        PreparedStatement ps = connection.prepareStatement("CREATE DATABASE databasename");
        int result = ps.executeUpdate();
    }
    
    
    private static Connection getConnection(String database, String login, String password) throws Exception {
        // This will load the MySQL driver, each DB has its own driver
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//
//        } catch (Exception e) {
//            throw e;
//        }
        // Setup the connection with the DB
        return DriverManager
                .getConnection("jdbc:hsqldb:hsql://localhost/workdb");
    }

    public NauczycielManagerImpl() throws Exception {
        throw new Exception("Zdefiniuj połączenie z bazą");
    }

    public void createTables() throws SQLException {
        connection.createStatement().executeUpdate(
                "CREATE TABLE "
                + dbName + "." + "Nauczyciel(id INTEGER PRIMARY KEY NOT NULL AUTO_INCREMENT, "
                + "imie char(20) NOT NULL, " + "nazwisko char(20) NOT NULL)");
    }

    public boolean isDatabaseReady() {
        try {
            ResultSet rs = connection.getMetaData().getTables(null, null, null, null);
            boolean tableExists = false;
            while (rs.next()) {
                if ("Nauczyciel".equalsIgnoreCase(rs.getString("TABLE_NAME"))) {
                    tableExists = true;
                    break;
                }
            }
            return tableExists;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

    @Override
    public void setConnection(Connection connection) throws SQLException {
        this.connection = connection;

        addNauczycielStmt = connection.
                prepareStatement("INSERT INTO "
                        + dbName + "." + "Nauczyciel (imie, nazwisko) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);

        getAllNauczycielsStmt = connection.
                prepareStatement("select * from "
                        + dbName + "." + "Nauczyciel");
        updateNauczycielsStmt = connection.
                prepareStatement("update "
                        + dbName + "." + "Nauczyciel set imie = ?, nazwisko = ? where id = ?");
        deleteNauczycielsStmt = connection.
                prepareStatement("delete from "
                        + dbName + "." + "Nauczyciel where id = ?");

        getNauczycielStmt = connection.
                prepareStatement("select id,imie,nazwisko from "
                        + dbName + "." + "Nauczyciel where id = ?");

    }

    @Override
    public Integer addNauczyciel(Nauczyciel person) {
        int count = 0;
        try {
            addNauczycielStmt.setString(1, person.Imie);
            addNauczycielStmt.setString(2, person.Nazwisko);
            count = addNauczycielStmt.executeUpdate();
            int affectedRows = addNauczycielStmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            try (ResultSet rs = addNauczycielStmt.getGeneratedKeys()) {
                if (rs.next()) {
                    count = rs.getInt(1);
                }
                rs.close();
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e.getMessage() + "\n" + e.getStackTrace().toString());
        }
        return count;
    }

    @Override
    public List<Nauczyciel> getAllNauczyciels() {
        List<Nauczyciel> nauczyciels = new LinkedList<>();
        try {
            ResultSet rs = getAllNauczycielsStmt.executeQuery();

            while (rs.next()) {
                Nauczyciel p = new Nauczyciel();
                p.id = rs.getInt("id");
                p.Imie = rs.getString("imie");
                p.Nazwisko = rs.getString("nazwisko");
                nauczyciels.add(p);
            }

        } catch (SQLException e) {
            throw new IllegalStateException(e.getMessage() + "\n" + e.getStackTrace().toString());
        }
        return nauczyciels;
    }

    @Override
    public Boolean updateNauczyciel(Nauczyciel nauczyciel) {
        try {
            updateNauczycielsStmt.setString(1, nauczyciel.Imie);
            updateNauczycielsStmt.setString(2, nauczyciel.Nazwisko);
            updateNauczycielsStmt.setLong(3, nauczyciel.id);
            updateNauczycielsStmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new IllegalStateException(e.getMessage() + "\n" + e.getStackTrace().toString());
        }
    }

    @Override
    public Boolean deleteNauczyciel(Nauczyciel nauczyciel) {
        try {
            deleteNauczycielsStmt.setLong(1, nauczyciel.id);
            deleteNauczycielsStmt.execute();
            return true;
        } catch (SQLException e) {
            throw new IllegalStateException(e.getMessage() + "\n" + e.getStackTrace().toString());
        }
    }

    @Override
    public Nauczyciel getNauczyciel(Long id) {
        Nauczyciel p = new Nauczyciel();
        try {
            getNauczycielStmt.setInt(1, id.intValue());
            ResultSet rs = getNauczycielStmt.executeQuery();

            while (rs.next()) {
                p.id = rs.getLong("id");
                p.Imie = rs.getString("imie");
                p.Nazwisko = rs.getString("nazwisko");
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e.getMessage() + "\n" + e.getStackTrace().toString());

        }
        return p;
    }
}
