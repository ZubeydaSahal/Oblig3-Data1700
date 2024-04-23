//kjører når den er ferdig lastet
$(function (){
    hentAlleFilmer();
    henteEnBillet();
});

function hentAlleFilmer(){
    $.get("/hentFilmer", function (filmer){
        formaterData(filmer);
    }).fail(function(jqXHR) {
        const json = $.parseJSON(jqXHR.responseText);
        $("#feil").html(json.message);
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


function henteEnBillet(){
    const id=window.location.search.substring(1);
    const url = "/hentEnBillett?id=" + id;

    $.get(url,function (enBillett){
        $("#id").val(enBillett.id);
        $("#film").val(enBillett.film);
        $("#antall").val(enBillett.antall);
        $("#fornavn").val(enBillett.fornavn);
        $("#etternavn").val(enBillett.etternavn);
        $("#telefonnr").val(enBillett.telefonnr);
        $("#epost").val(enBillett.epost);
    }).fail(function(jqXHR) {
        const json = $.parseJSON(jqXHR.responseText);
        $("#feil").html(json.message);
    });
}
function endreBilett(){
    let id=$("#id").val();
    let film=$("#valgFilm").val();
    let antall=$("#antall").val();
    let fornavn=$("#fornavn").val();
    let etternavn=$("#etternavn").val();
    let telefonnr=$("#telefonnr").val();
    let epost=$("#epost").val();


    //henter onbjectene som er lagret i arrayere fra server

    let sjekk=false;

    //sjekke om alle inputtene ikke er tomme, hvis de er sender ut feilmelding
    if (document.getElementById("film").value===""){
        document.getElementById("feilfilm").innerHTML="Velg en film";
        sjekk=true;
    }

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
    if (!regexTlf.test(telefonnr) && telefonnr !== "") {
        document.getElementById("feilvalideringtlf").innerHTML = "Telefonnummeret er ugyldig. Skriv inn et gyldig norsk telefonnummer på 8 siffer.";
        sjekk = true;
    }

    // Validering av e-post med regex
    const regexEmail = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!regexEmail.test(epost) && epost !== "") {
        document.getElementById("feilvalideringepost").innerHTML = "E-postadressen er ugyldig. Skriv inn en gyldig e-postadresse.";
        sjekk = true;
    }

    //lagrer billeten
    if (!sjekk) {
        const bilett = {
            id:id,
            film: film,
            antall: antall,
            fornavn: fornavn,
            etternavn: etternavn,
            telefonnr: telefonnr,
            epost: epost
        };
        console.log(bilett)

        $.post("/endre", bilett, function () {
            hentAlle();
        }).fail(function (jqXHR){
            const json=$.parseJSON(jqXHR.responseText);
            $("#feil").html(json.message);
        });

        //istedet for å restarte veridene ka man sende bruken tilbake til index.htm
        window.location.href="index.html";

    }

}
