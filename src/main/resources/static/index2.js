$(function (){
    hentAlle();
})
function hentAlle(){
    $.get("/hentalle", function (biletter){
        let ut="<table class='table table-striped'><tr><th>Film</th><th>Antall</th><th>Fornavn</th>"+
            "<th> Etternavn </th><th>Telefonnr</th><th>Epost</th><th></th><th></th></tr>";
        for (let bilett of biletter){
            ut+="<tr><td>"+bilett.film+"</td><td>"+bilett.antall+"</td><td>"+bilett.fornavn+"</td><td>"+bilett.etternavn+
                "</td><td>"+bilett.telefonnr+"</td><td>"+bilett.epost+"</td>" +
               " <td><button class='btn btn-primary' onclick='idTilEndring("+bilett.id+")'>Endring</td>"+
                " <td><button class='btn btn-primary' onclick='slettEnBillett("+bilett.id+")'>Slett</td>"+
                "</tr>";

            console.log(bilett)
        }
        ut+="</table>";
        $("#registrer").html(ut);
    })
}


function idTilEndring(id){
    window.location.href="/endring.html?"+id;
}
function slettEnBillett(id){
    const url=("/slettEnBillett?id="+ id);
    $.get(url,function (){
        //window.location.href="/";
        window.location.href="/";

    });


}

function slettAlle(){
    $.get("/slettAlle",function (){
      hentAlle();
    })

}
