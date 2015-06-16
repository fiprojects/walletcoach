<map>
{
for $subject in doc("subjects")//subject 
where $subject//street and $subject//city
return (
  let $address := fn:encode-for-uri(fn:concat($subject//street, ", ", " ", $subject/number, " ", $subject/city, " ", $subject//country))
  let $api := doc(fn:concat("https://maps.googleapis.com/maps/api/geocode/xml?address=", $address))//result[1]
  let $lat := $api//location/lat
  let $lng := $api//location/lng
  let $name := $subject/name/text()
  where $lat and $lng
  return (
    <subject name="{$name}" lat="{$lat}" lng="{$lng}" />
  )
)
}
</map>