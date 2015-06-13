declare variable $id as xs:integer external;
for $subject in //subject
where $subject/@id = $id
return $subject