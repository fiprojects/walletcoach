declare namespace D = "http://wwwinfo.mfcr.cz/ares/xml_doc/schemas/ares/ares_datatypes/v_1.0.3";
declare variable $ic as xs:string external;
let $ares := doc($ic)
return (
  <ares>
    <name>{$ares//D:OF/text()}</name>
    <street>{$ares//D:AA/D:NU/text()}{" "}{$ares//D:AA/D:CD/text()}</street>
    <city>{$ares//D:AA/D:N/text()}</city>
    <number>{$ares//D:AA/D:PSC/text()}</number>
  </ares>
)