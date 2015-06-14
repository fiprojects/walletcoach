%declarations%
for $item in /items/item
let $date := $item/datetime/text()
let $day := fn:day-from-dateTime($date)
let $month := fn:month-from-dateTime($date)
let $year := fn:year-from-dateTime($date)
order by $date descending
%where%
return (
    <item>
        <data id="{$item/@id}">
            <description>{$item/description/text()}</description>
            <price>{$item/price/text()}</price>
            <datetime>{$item/datetime/text()}</datetime>
        </data>
        {
            for $category in doc('categories')//category
            where $category/@id = $item/category-id/text()
            return $category
        }
        {
            for $subject in doc('subjects')//subject
            where $subject/@id = $item/company-id/text()
            return $subject
        }
    </item>
)