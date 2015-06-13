declare variable $name as xs:string external;
declare variable $color as xs:string external;
let $id := fn:max(//category/@id) + 1
return (
  insert node (
    <category id="{$id}">
      <name>{$name}</name>
      <color>#{$color}</color>
    </category>
  ) into /categories
)