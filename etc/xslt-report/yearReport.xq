declare variable $globalYear as xs:integer := 2015;
declare variable $itemsPath as xs:string := "C:\Users\Maros\Documents\NetBeansProjects\walletcoach\etc\testdata\items.xml";
declare variable $categoriesPath as xs:string := "C:\Users\Maros\Documents\NetBeansProjects\walletcoach\etc\testdata\categories.xml";
declare variable $subjectsPath as xs:string := "C:\Users\Maros\Documents\NetBeansProjects\walletcoach\etc\testdata\subjects.xml";

<report year="{$globalYear}">
<summary>
<income>
{for $item in doc($itemsPath)/items/item
let $date := $item/datetime/text()
let $year := fn:year-from-dateTime($date)
where $year = $globalYear
where $item/price/text() = fn:abs($item/price/text())
group by $year
return fn:sum($item/price/text())}
</income>

<expense>
{for $item in doc($itemsPath)/items/item
let $date := $item/datetime/text()
let $year := fn:year-from-dateTime($date)
where $year = $globalYear
where $item/price/text() != fn:abs($item/price/text())
group by $year
return fn:sum($item/price/text())}
</expense>

<total>
{for $item in doc($itemsPath)/items/item
let $date := $item/datetime/text()
let $year := fn:year-from-dateTime($date)
where $year = $globalYear
group by $year
return fn:sum($item/price/text())}
</total>
</summary>
<categories>


{
for $category in  doc($categoriesPath)/categories/category
let $id := $category/@id/data()
let $name := $category/name/text()
let $color := $category/color/text()
return (
  <category name="{$name}"
  income="{fn:sum(for $item in doc($itemsPath)/items/item 
  where $item/category-id/text() = $id 
  where fn:year-from-dateTime($item/datetime/text()) = $globalYear
  where $item/price/text() = fn:abs($item/price/text()) 
  return $item/price/text())}" 
  
  expense="{fn:sum(for $item in doc($itemsPath)/items/item 
  where $item/category-id/text() = $id 
  where fn:year-from-dateTime($item/datetime/text()) = $globalYear
  where $item/price/text() != fn:abs($item/price/text()) 
  return $item/price/text())}" 
  
  total="{fn:sum(for $item in doc($itemsPath)/items/item 
  where $item/category-id/text() = $id 
  where fn:year-from-dateTime($item/datetime/text()) = $globalYear
  return $item/price/text())}"
  >

  
  </category> 
  
)

}
</categories>
<subjects>
{
for $subject in  doc($subjectsPath)/subjects/subject
let $id := $subject/@id/data()
let $name := $subject/name/text()
return (
  <subject name="{$name}"
  income="{fn:sum(for $item in doc($itemsPath)/items/item 
  where $item/company-id/text() = $id 
  where fn:year-from-dateTime($item/datetime/text()) = $globalYear
  where $item/price/text() = fn:abs($item/price/text()) 
  return $item/price/text())}" 
  
  expense="{fn:sum(for $item in doc($itemsPath)/items/item 
  where $item/company-id/text() = $id 
  where fn:year-from-dateTime($item/datetime/text()) = $globalYear
  where $item/price/text() != fn:abs($item/price/text()) 
  return $item/price/text())}" 
  
  total="{fn:sum(for $item in doc($itemsPath)/items/item 
  where $item/company-id/text() = $id 
  where fn:year-from-dateTime($item/datetime/text()) = $globalYear
  return $item/price/text())}"
  >
  </subject>

  

)

}



</subjects>
</report>
  


