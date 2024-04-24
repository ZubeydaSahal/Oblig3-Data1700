package com.example.oblig3data1700;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class kinoController {

    @Autowired
    private kinoRespository rep;


    @GetMapping("/hentFilmer")
    public List<Film> hentFilmer(HttpServletResponse response)throws IOException{
        List<Film> enFilm=rep.hentAlleFilmer();
        if (enFilm==null){
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Feil i DB-prøv igjen senere");
        }
        return enFilm;
    }

    @PostMapping("/lagre")
    public void LagreKunde(Billett innKunde, HttpServletResponse response) throws IOException {

       if (!rep.lagreKunde(innKunde)){
           response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                   "feil i databsen-prøv igjen senere");
       }

    }
    @GetMapping("/hentEnBillett")
    public Billett henteEnBillett(int id,HttpServletResponse response ) throws IOException{
        Billett enBillett=rep.henteEnBillett(id);
        if (enBillett==null){
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Feil i DB-prøb igjen senere");
        }
        return enBillett;
    }

    @PostMapping("/endre")
    public void endre(Billett enBillett,HttpServletResponse response)throws  IOException{
        if (!rep.endreEnBillett(enBillett)){
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Feil i DB prøv igjen senere");
        }
    }

    @GetMapping("/slettEnBillett")
    public void slettEnBillett(int id,HttpServletResponse response)throws  IOException{
        if (!rep.slettEnBillett(id)){
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Feil i DB prøv igjen senere");

        }
    }

    @GetMapping("/hentalle")
    public List<Billett> hentAlle(HttpServletResponse response)throws IOException{
        List<Billett> enBillett= rep.hentAllebilett();
        if (enBillett==null){
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(),"FEil i Db prøv igjen senere");
        }
        return enBillett;
    }

    @GetMapping("/slettAlle")
    public void slettAlle(HttpServletResponse response)throws IOException{
        if (!   rep.slettAllebilett()){
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Feil i DB prøv igjen senere");

        }

    }


}
