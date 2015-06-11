<items>
{
for $item in doc('\\ad.fi.muni.cz\DFS\home\x390895\_profile\Documents\NetBeansProjects\walletcoach\etc\testdata\items.xml')/items/item
let $date := $item/datetime/text()
let $day := fn:day-from-dateTime($date)
let $month := fn:month-from-dateTime($date)
let $year := fn:year-from-dateTime($date)
order by $date
where $month = 2
return
<item>
  <name>{$item/name/text()}</name>
  {
    for $category in  doc('\\ad.fi.muni.cz\DFS\home\x390895\_profile\Documents\NetBeansProjects\walletcoach\etc\testdata\categories.xml')/categories/category where $category/@id = $item/category-id/text() return <category color="{$category/color/text()}">{$category/name/text()}</category>
  }
  <company>
     {
    for $subject in  doc('\\ad.fi.muni.cz\DFS\home\x390895\_profile\Documents\NetBeansProjects\walletcoach\etc\testdata\subjects.xml')/subjects/subject where $subject/@id = $item/company-id/text() return $subject/name/text()
     }
  </company>
  <date>{$day}. {$month}. {$year}</date>
  <price>{$item/price/text()} CZK</price>
</item>
}
</items>