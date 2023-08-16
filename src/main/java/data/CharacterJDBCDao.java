package data;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import properties.PlayerCharacter;
import properties.Fighter;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class CharacterJDBCDao implements CharacterDAO{

    private JdbcTemplate jdbcTemplate;

    public CharacterJDBCDao(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public int createCharacter(PlayerCharacter playerCharacter) {
        String sqlNew = "INSERT INTO player_characters(name, str, dex, con, intel, wis, cha, max_hp, current_hp, hit_die, damage, level) " +
                "VALUES(?,?,?,?,?,?,?,?,?,?,?,?) RETURNING id;";
//        String sqlUpdate = "UPDATE player_characters SET str = ?, dex = ?, con = ?, intel = ?, wis = ?, " +
//                "cha = ?, max_hp = ?, current_hp = ?, hit_die = ?, damage = ?, level = ? WHERE name = ?;";
//        String sqlFind = "SELECT Count(name) AS exists FROM player_characters WHERE name = ?;";
        int newId = -1;
        try{
//            SqlRowSet row = jdbcTemplate.queryForRowSet(sqlFind, playerCharacter.getCharName());
//            int doesExist = row.getInt("exists");
//            if(doesExist > 0){
//                jdbcTemplate.update(sqlUpdate, int.class, playerCharacter.getStr(),
//                        playerCharacter.getDex(), playerCharacter.getCon(), playerCharacter.getIntel(), playerCharacter.getWis(), playerCharacter.getCha(),
//                        playerCharacter.getMaxHitPoints(), playerCharacter.getCurrentHitPoints(), playerCharacter.getHitDie(),
//                        playerCharacter.getDamage(), playerCharacter.getLevel(), playerCharacter.getCharName());
//
//            } else {

                newId = jdbcTemplate.queryForObject(sqlNew, int.class, playerCharacter.getCharName(), playerCharacter.getStr(),
                        playerCharacter.getDex(), playerCharacter.getCon(), playerCharacter.getIntel(), playerCharacter.getWis(), playerCharacter.getCha(),
                        playerCharacter.getMaxHitPoints(), playerCharacter.getCurrentHitPoints(), playerCharacter.getHitDie(),
                        playerCharacter.getDamage(), playerCharacter.getLevel());
//            }

        } catch (CannotGetJdbcConnectionException e){
            System.out.println("Cannot connect");
        } catch (DataIntegrityViolationException e){
            System.out.println("This character already exists.");

        }
        return newId;
    }


    @Override
    public int saveCharacter(PlayerCharacter playerCharacter) {

        String sql = "UPDATE player_characters SET str = ?, dex = ?, con = ?, intel = ?, wis = ?, " +
                "cha = ?, max_hp = ?, current_hp = ?, hit_die = ?, damage = ?, level = ? WHERE name = ?;";
        int numberOfRows = 0;
        try{
            numberOfRows = jdbcTemplate.update(sql, playerCharacter.getStr(),
                    playerCharacter.getDex(), playerCharacter.getCon(), playerCharacter.getIntel(), playerCharacter.getWis(), playerCharacter.getCha(),
                    playerCharacter.getMaxHitPoints(), playerCharacter.getCurrentHitPoints(), playerCharacter.getHitDie(),
                    playerCharacter.getDamage(), playerCharacter.getLevel(), playerCharacter.getCharName());
//            }

        } catch (CannotGetJdbcConnectionException e){
            System.out.println("Cannot connect");
        } catch (DataIntegrityViolationException e){
            System.out.println("Data Violation");
            System.out.println(e);
        }
        return numberOfRows;
    }

    @Override
    public PlayerCharacter loadCharacter(String name) {
        String sql = "SELECT * from player_characters WHERE name = ?;";
        PlayerCharacter loadCharacter = null;
        try{
            SqlRowSet row = jdbcTemplate.queryForRowSet(sql, name);
            if(row.next()){
                loadCharacter = mapRowToCharacter(row);
            }
        } catch(CannotGetJdbcConnectionException e){
            System.out.println("Cannot Connect");
        } catch (DataIntegrityViolationException e){
            System.out.println("Data Violation");
        }

        return loadCharacter;
    }

    @Override
    public int deleteCharacter(String name) {
        String sql = "DELETE FROM player_characters WHERE name = ?;";
        int numberOfRows = 0;
        try{
            numberOfRows = jdbcTemplate.update(sql, name);
        } catch (CannotGetJdbcConnectionException e){
            System.out.println("Cannot connect");
        } catch (DataIntegrityViolationException e){
            System.out.println("Data Violation");
        }
        return numberOfRows;
    }

    public String displayCharacters(){
        String characterList = "\n";
        String sql = "SELECT name FROM player_characters;";
        try{
            SqlRowSet row = jdbcTemplate.queryForRowSet(sql);
            while (row.next()){
                characterList += row.getString("name") + "\n";
            }
        } catch (CannotGetJdbcConnectionException e){
            System.out.println("Cannot connect");
        }
        return characterList;
    }

    public PlayerCharacter mapRowToCharacter(SqlRowSet row){
        PlayerCharacter playerCharacter = new Fighter();
        playerCharacter.setCharName(row.getString("name"));
        playerCharacter.setStr(row.getInt("str"));
        playerCharacter.setDex(row.getInt("dex"));
        playerCharacter.setCon(row.getInt("con"));
        playerCharacter.setIntel(row.getInt("intel"));
        playerCharacter.setWis(row.getInt("wis"));
        playerCharacter.setCha(row.getInt("cha"));
        playerCharacter.setCurrentHitPoints(row.getInt("current_hp"));
        playerCharacter.setDamage(row.getInt("damage"));
        playerCharacter.setMaxHitPoints(row.getInt("max_hp"));
        playerCharacter.setHitDie(row.getInt("hit_die"));
        playerCharacter.setLevel(row.getInt("level"));

        return playerCharacter;
    }
}
