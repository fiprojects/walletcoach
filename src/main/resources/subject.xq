declare variable $id as xs:integer external;
for $subject in doc('data/subjects.xml')//subject
where $subject/@id = $id
return $subject