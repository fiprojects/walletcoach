declare variable $description as xs:string external;
declare variable $price as xs:double external;
declare variable $datetime as xs:datetime external;
declare variable $categoryId as xs:string external;
declare variable $subjectId as xs:string external;

let $id := fn:max(//item/@id) + 1

return (
  insert node (
    <item id="{$id}">
      <description>{$description}</description>
      <price>{$price}</price>
      <datetime>{$datetime}</datetime>
      <category-id>{$categoryId}</category-id>
      <company-id>{$subjectId}</company-id>
    </category>
  ) into /categories
)