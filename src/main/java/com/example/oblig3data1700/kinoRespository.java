package com.example.oblig3data1700;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
    public boolean lagreKunde(biletter innKunde){
        String sql = "INSERT INTO biletter(film,antall,fornavn,etternavn,telefonnr,epost) Values(?,?,?,?,?,?)";
        try {
            db.update(sql, innKunde.getFilm(),innKunde.getAntall(),innKunde.getFornavn(),innKunde.getEtternavn(),
                    innKunde.getTelefonnr(),innKunde.getEpost());
            return true;
        }catch (Exception e){
            logger.error("Feil i lagreKunde(): "+ e);
            return false;
        }


    }


    // metode som henter ut billetter som ligger i tabellen
    public List<biletter> hentAllebilett(){
        String sql="SELECT * FROM biletter";
        List<biletter>alleKunder=db.query(sql, new BeanPropertyRowMapper(biletter.class));
        return alleKunder;
    }
    //metode som sletter billetter

    public void slettAllebilett(){
        String sql="DELETE FROM biletter";
        db.update(sql);
    }



}

