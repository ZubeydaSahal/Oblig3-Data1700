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
    public void LagreKunde(biletter innKunde, HttpServletResponse response) throws IOException {

       if (!rep.lagreKunde(innKunde)){
           response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                   "feil i databsen-prøv igjen senere");
       }

    }
    @GetMapping("/hentalle")
    public List<biletter> hentAlle(){
        return rep.hentAllebilett();
    }

    @GetMapping("/slettAlle")
    public void selttAlle(){
        rep.slettAllebilett();
    }


}

//List<film> listMedFilmer=new ArrayList<>();
//        listMedFilmer.add(new film("Barbie"));
//        listMedFilmer.add(new film("Black panther"));
//        listMedFilmer.add(new film("the rookie"));
//        return listMedFilmer;
