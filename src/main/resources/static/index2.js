$(function (){
    hentAlleFilmer();
})

function hentAlleFilmer(){
    $.get("/hentFilmer", function (filmer){
        formaterData(filmer);
    });
}

function formaterData(filmer){
    let utt = "<select id='valgFilm'>" +  "<option value=''>Velg en film</option>";
    for(const film of filmer){
        utt += "<option>" + film.velger+ "</option>";
    }
    console.log(filmer)
    utt += "</select>";
    $("#film").html(utt);
}
function kjop(){
    let film=$("#valgFilm").val();
    let antall=$("#antall").val();
    let fornavn=$("#fornavn").val();
    let etternavn=$("#etternavn").val();
    let telefonnr=$("#telefonnr").val();
    let epost=$("#epost").val();


    //henter onbjectene som er lagret i arrayere fra server

    let sjekk=false;

    //sjekke om alle inputtene ikke er tomme, hvis de er sender ut feilmelding

    if (document.getElementById("antall").value===""){
        document.getElementById("feileantall").innerHTML="Skriv inn Antall biletter";
        sjekk=true;
    }
    if (document.getElementById("fornavn").value===""){
        document.getElementById("feilfornavn").innerHTML="Må skrive inn fornavn";
        sjekk=true;
    }
    if(document.getElementById("etternavn").value === "") {
        document.getElementById("feiletternavn").innerHTML = "Må skrive noe inn i Etternavn";
        sjekk = true;

    }

    if (document.getElementById("telefonnr").value===""){
        document.getElementById("feiltelefonnr").innerHTML="Må skrive in riktig telefonnr";
        sjekk=true;
    }

    if(document.getElementById("epost").value === "") {
        document.getElementById("feilepost").innerHTML = "Må skrive noe inn i Epost ";
        sjekk = true;

    }

    // Validering av telefonnummer med regex
    const regexTlf = /^[0-9]{8}$/;
    if (!regexTlf.test(telefonnr)) {
        document.getElementById("feilvalideringtlf").innerHTML = "Telefonnummeret er ugyldig. Skriv inn et gyldig norsk telefonnummer på 8 siffer.";
        sjekk = true;
    }

    // Validering av e-post med regex
    const regexEmail = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!regexEmail.test(epost)) {
        document.getElementById("feilvalideringepost").innerHTML = "E-postadressen er ugyldig. Skriv inn en gyldig e-postadresse.";
        sjekk = true;
    }

    //lagrer billeten
    if (!sjekk) {
        const bilett = {
            film: film,
            antall: antall,
            fornavn: fornavn,
            etternavn: etternavn,
            telefonnr: telefonnr,
            epost: epost
        };
        console.log(bilett)

        $.post("/lagre", bilett, function () {
            //henter alle objekter som er lagret i server
            hentAlle();
            ///
        }).fail(function (jqXHR){
            const json=$.parseJSON(jqXHR.responseText);
            $("#feil").html(json.message);
        })


        //tømmer boksene
        $("#ValgFilm").val("");
        $("#antall").val("");
        $("#fornavn").val("");
        $("#etternavn").val("");
        $("#telefonnr").val("");
        $("#epost").val("");

    }

}


function hentAlle(){
    $.get("/hentalle", function (biletter){
        let ut="<table class='table table-striped'><tr><th>Film</th><th>Antall</th><th>Fornavn</th>"+
            "<th> Etternavn </th><th>Telefonnr</th><th>Epost</th></tr>";
        for (let bilett of biletter){
            ut+="<tr><td>"+bilett.film+"</td><td>"+bilett.antall+"</td><td>"+bilett.fornavn+"</td><td>"+bilett.etternavn+
                "</td><td>"+bilett.telefonnr+"</td><td>"+bilett.epost+"</td></tr>";

            console.log(bilett)
        }
        ut+="</table>";
        $("#registrer").html(ut);
    })
}


function seltt(){
    $.get("/slettAlle",function (){
      hentAlle();
    })

}
