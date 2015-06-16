declare variable $globalMonth as xs:integer external;
declare variable $globalYear as xs:integer external;

let $cnb := doc("http://www.cnb.cz/cs/financni_trhy/devizovy_trh/kurzy_devizoveho_trhu/denni_kurz.xml")
let $eur := xs:float(fn:replace($cnb//radek[@kod = 'EUR']/@kurz, ',', '.'))
return (
<report year="{$globalYear}" month="{$globalMonth}"  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:noNamespaceSchemaLocation="xsd/monthReport.xsd">
<summary>
<income>
{for $item in doc("items")/items/item
let $date := $item/datetime/text()
let $day := fn:day-from-dateTime($date)
let $month := fn:month-from-dateTime($date)
let $year := fn:year-from-dateTime($date)
where $month = $globalMonth
where $year = $globalYear
where $item/price/text() = fn:abs($item/price/text())
group by $month
return fn:sum($item/price/text()) div $eur}
</income>

<expense>
{for $item in doc("items")/items/item
let $date := $item/datetime/text()
let $day := fn:day-from-dateTime($date)
let $month := fn:month-from-dateTime($date)
let $year := fn:year-from-dateTime($date)
where $month = $globalMonth
where $year = $globalYear
where $item/price/text() != fn:abs($item/price/text())
group by $month
return fn:sum($item/price/text()) div $eur}
</expense>

<total>
{for $item in doc("items")/items/item
let $date := $item/datetime/text()
let $day := fn:day-from-dateTime($date)
let $month := fn:month-from-dateTime($date)
let $year := fn:year-from-dateTime($date)
where $month = $globalMonth
where $year = $globalYear
group by $month
return fn:sum($item/price/text()) div $eur}
</total>
</summary>
<categories>


{
for $category in  doc("categories")/categories/category
let $id := $category/@id/data()
let $name := $category/name/text()
let $color := $category/color/text()
return (
  <category name="{$name}"
  income="{fn:sum(for $item in doc("items")/items/item 
  where $item/category-id/text() = $id 
  where fn:month-from-dateTime($item/datetime/text()) = $globalMonth
  where fn:year-from-dateTime($item/datetime/text()) = $globalYear
  where $item/price/text() = fn:abs($item/price/text()) 
  return $item/price/text()) div $eur}" 
  
  expense="{fn:sum(for $item in doc("items")/items/item 
  where $item/category-id/text() = $id 
  where fn:month-from-dateTime($item/datetime/text()) = $globalMonth
  where fn:year-from-dateTime($item/datetime/text()) = $globalYear
  where $item/price/text() != fn:abs($item/price/text()) 
  return $item/price/text()) div $eur}" 
  
  total="{fn:sum(for $item in doc("items")/items/item 
  where $item/category-id/text() = $id 
  where fn:month-from-dateTime($item/datetime/text()) = $globalMonth
  where fn:year-from-dateTime($item/datetime/text()) = $globalYear
  return $item/price/text()) div $eur}"
  >

  
  </category> 
  
)

}
</categories>
<subjects>
{
for $subject in  doc("subjects")/subjects/subject
let $id := $subject/@id/data()
let $name := $subject/name/text()
return (
  <subject name="{$name}"
  income="{fn:sum(for $item in doc("items")/items/item 
  where $item/company-id/text() = $id 
  where fn:month-from-dateTime($item/datetime/text()) = $globalMonth
  where fn:year-from-dateTime($item/datetime/text()) = $globalYear
  where $item/price/text() = fn:abs($item/price/text()) 
  return $item/price/text()) div $eur}" 
  
  expense="{fn:sum(for $item in doc("items")/items/item 
  where $item/company-id/text() = $id 
  where fn:month-from-dateTime($item/datetime/text()) = $globalMonth
  where fn:year-from-dateTime($item/datetime/text()) = $globalYear
  where $item/price/text() != fn:abs($item/price/text()) 
  return $item/price/text()) div $eur}" 
  
  total="{fn:sum(for $item in doc("items")/items/item 
  where $item/company-id/text() = $id 
  where fn:month-from-dateTime($item/datetime/text()) = $globalMonth
  where fn:year-from-dateTime($item/datetime/text()) = $globalYear
  return $item/price/text()) div $eur}"
  >
  </subject>

  

)

}



</subjects>
</report>

)