declare variable $name as xs:string external;
declare variable $color as xs:string external;

let $max := fn:max(//category/@id)
let $id := if($max) then $max + 1 else 1
return (
  insert node (
    <category id="{$id}">
      <name>{$name}</name>
      <color>#{$color}</color>
    </category>
  ) into /categories
)