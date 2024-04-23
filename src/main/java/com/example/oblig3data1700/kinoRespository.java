package com.example.oblig3data1700;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class kinoRespository {

    //knytte til databasen
    @Autowired
    private JdbcTemplate db; //db objektet blir mulig å accecere mot databasen


    private Logger logger= LoggerFactory.getLogger(kinoRespository.class);


    //skal sjekke ogsåå om det skjer feill
    //metode som skal hente filmene
    public  List<Film> hentAlleFilmer() {
        String sql = "SELECT * FROM Film";
        try {
            return db.query(sql, new BeanPropertyRowMapper<>(Film.class));
        }
        catch (Exception e){
            logger.error("Feil i lagreFilm():"+ e);
            return null;
        }
    }

    //metode som skal lagre billetter
    public boolean lagreKunde(Billett innKunde){
        String sql = "INSERT INTO Billett(film,antall,fornavn,etternavn,telefonnr,epost) Values(?,?,?,?,?,?)";
        try {
            db.update(sql, innKunde.getFilm(),innKunde.getAntall(),innKunde.getFornavn(),innKunde.getEtternavn(),
                    innKunde.getTelefonnr(),innKunde.getEpost());
            return true;
        }catch (Exception e){
            logger.error("Feil i lagreKunde(): "+ e);
            return false;
        }

    }

    public Billett henteEnBillett(int id){
        String sql="SELECT * FROM Billett WHERE id=?";
        try {
            List<Billett> enBillett=db.query(sql,new BeanPropertyRowMapper<>(Billett.class),id);
            return enBillett.get(0);
        }catch (Exception e){
            logger.error("Feil i ehtn en Billett"+ e);
            return null;
        }

    }

    public boolean endreEnBillett(Billett enBillett) {
        String sql = "UPDATE Billett SET film=?,antall=?,fornavn=?,etternavn=?,telefonnr=?,epost=? WHERE id=?";
        try {
            db.update(sql, enBillett.getFilm(), enBillett.getAntall(), enBillett.getFornavn(), enBillett.getEtternavn(),
                    enBillett.getTelefonnr(), enBillett.getEpost(), enBillett.getId());
            return true;
        }catch (Exception e){
            logger.error("Feil i endre en Billett"+ e);
            return false;

        }

    }

    public boolean slettEnBillett(int id){
        String sql="DELETE FROM Billett WHERE id=?";
        try {
            db.update(sql,id);
            return true;
        }catch (Exception e){
            logger.error("Feil i slett i slett en og en Billett"+ e);
            return false;
        }

    }


    // metode som henter ut billetter som ligger i tabellen
    public List<Billett> hentAllebilett(){
        String sql="SELECT * FROM Billett";
        try {
            List<Billett>alleKunder=db.query(sql, new BeanPropertyRowMapper(Billett.class));
            Collections.sort(alleKunder, new Sorter());
            return alleKunder;
        }catch (Exception e){
            return null;
        }
    }
    //metode som sletter billetter

    public boolean slettAllebilett(){
        String sql="DELETE FROM Billett";
        try {
            db.update(sql);
            return true;
        }catch (Exception e){
            logger.error("Feil i seltt alle Billetter");
            return false;
        }

    }





}

