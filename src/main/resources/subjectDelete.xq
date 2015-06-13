declare variable $id as xs:integer external;
delete node doc("subjects")//subject[@id = $id],
delete node doc("items")//item[./company-id = $id]