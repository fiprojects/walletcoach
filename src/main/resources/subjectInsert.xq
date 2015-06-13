declare variable $ic as xs:string external;
declare variable $name as xs:string external;
declare variable $street as xs:string external;
declare variable $number as xs:string external;
declare variable $city as xs:string external;
declare variable $country as xs:string external;
declare variable $description as xs:string external;
let $id := fn:max(//subject/@id) + 1
return (
  insert node (
    <subject id="{$id}">
      <ic>{$ic}</ic>
      <name>{$name}</name>
      <address>
        <street>{$street}</street>
        <number>{$number}</number>
        <city>{$city}</city>
        <country>{$country}</country>
      </address>
      <description>{$description}</description>
    </subject>
  ) into /subjects
)