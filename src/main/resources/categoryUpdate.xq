declare variable $id as xs:long external;
declare variable $name as xs:string external;
declare variable $color as xs:string external;
replace node //category[@id = $id] with (
    <category id="{$id}">
        <name>{$name}</name>
        <color>#{$color}</color>
    </category>
)