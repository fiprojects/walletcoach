declare variable $id as xs:integer external;
delete node doc("categories")//category[@id = $id],
delete node doc("items")//item[./category-id = $id]