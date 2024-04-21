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
    public List<Film> hentFilmer(){
        return rep.hentAlleFilmer();
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
    public void endre(Billett enBillett){
        rep.endreEnBillett(enBillett);
    }

    @GetMapping("/slettEnBillett")
    public void slettEnBillett(int id){
        rep.slettEnBillett(id);
    }

    @GetMapping("/hentalle")
    public List<Billett> hentAlle(){
        return rep.hentAllebilett();
    }

    @GetMapping("/slettAlle")
    public void slettAlle(){
        rep.slettAllebilett();
    }


}

//List<film> listMedFilmer=new ArrayList<>();
//        listMedFilmer.add(new film("Barbie"));
//        listMedFilmer.add(new film("Black panther"));
//        listMedFilmer.add(new film("the rookie"));
//        return listMedFilmer;
