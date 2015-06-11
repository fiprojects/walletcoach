declare variable $monthParameter as xs:integer external;
for $item in doc('data/items.xml')/items/item
let $date := $item/datetime/text()
let $day := fn:day-from-dateTime($date)
let $month := fn:month-from-dateTime($date)
let $year := fn:year-from-dateTime($date)
order by $date
where $month = $monthParameter
return
<item id="{$item/@id}">
  {
    for $category in  doc('data/categories.xml')/categories/category where $category/@id = $item/category-id/text() return <category color="{$category/color/text()}">{$category/name/text()}</category>
  }
  <company>
     {
    for $subject in  doc('data/subjects.xml')/subjects/subject where $subject/@id = $item/company-id/text() return $subject/name/text()
     }
  </company>
  <date>{$day}. {$month}. {$year}</date>
  <description>{$item/name/text()}</description>
  <price>{$item/price/text()} CZK</price>
</item>