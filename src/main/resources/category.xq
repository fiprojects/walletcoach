declare variable $id as xs:integer external;
for $category in //category
where $category/@id = $id
return $category