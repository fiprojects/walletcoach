declare variable $description as xs:string external;
declare variable $price as xs:decimal external;
declare variable $datetime as xs:string external;
declare variable $categoryId as xs:long external;
declare variable $subjectId as xs:long external;

let $max := fn:max(//item/@id)
let $id := if($max) then $max + 1 else 1

return (
  insert node (
    <item id="{$id}">
      <description>{$description}</description>
      <price>{$price}</price>
      <datetime>{$datetime}</datetime>
      <category-id>{$categoryId}</category-id>
      <company-id>{$subjectId}</company-id>
    </item>
  ) into /items
)