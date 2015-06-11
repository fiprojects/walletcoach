declare variable $id as xs:integer external;
for $category in doc('data/categories.xml')//category
where $category/@id = $id
return $category