<html>
<head>
  <title>Vypis vydajov pocas mesiaca</title>
 </head>
<body>
{
for $item in doc('\\ad.fi.muni.cz\DFS\home\x390895\_profile\Documents\NetBeansProjects\walletcoach\etc\testdata\items.xml')/items/item
let $date := $item/datetime/text()
let $day := fn:day-from-dateTime($date)
let $month := fn:month-from-dateTime($date)
let $year := fn:year-from-dateTime($date)

let $max := fn:max(
    for $item in doc('\\ad.fi.muni.cz\DFS\home\x390895\_profile\Documents\NetBeansProjects\walletcoach\etc\testdata\items.xml')/items/item
    let $date := $item/datetime/text()
    let $day := fn:day-from-dateTime($date)
    let $month := fn:month-from-dateTime($date)
    let $year := fn:year-from-dateTime($date)
    where $month = 3
    where $item/price/text() != fn:abs($item/price/text()) 
    group by $day
    order by $day
    return fn:sum(fn:abs($item/price))
  )
  
  let $width := fn:floor(fn:abs(fn:sum($item/price)) div $max * 100)

where $month = 1
where $item/price/text() != fn:abs($item/price/text()) 
group by $day
order by $day
return <div style="width: 500px; margin-bottom: 5px;"><div class="date" style="display: inline-element; width: 50px;">{$day}. {distinct-values($month)}.</div><div style="display: inline-element; width: 450px;"><div class="bar" style="width: {$width}%; color: white; background-color: red; padding: 3px; text-decoration: bold; border-radius: 2px; text-align: right;" >{fn:abs(fn:sum($item/price))}</div></div><br style="clear: both;" /></div>
}
</body>
</html>


























<html>
<head>
  <title>Vypis vydajov pocas mesiaca</title>
 </head>
<body>
{
for $item in doc('\\ad.fi.muni.cz\DFS\home\x390895\_profile\Documents\NetBeansProjects\walletcoach\etc\testdata\items.xml')/items/item
let $date := $item/datetime/text()
let $day := fn:day-from-dateTime($date)
let $month := fn:month-from-dateTime($date)
let $year := fn:year-from-dateTime($date)

let $max1 := fn:max(
    for $item in doc('\\ad.fi.muni.cz\DFS\home\x390895\_profile\Documents\NetBeansProjects\walletcoach\etc\testdata\items.xml')/items/item
    let $date := $item/datetime/text()
    let $day := fn:day-from-dateTime($date)
    let $month := fn:month-from-dateTime($date)
    let $year := fn:year-from-dateTime($date)
    where $month = 2
    where $item/price/text() != fn:abs($item/price/text()) 
    group by $day
    order by $day
    return fn:sum(fn:abs($item/price))
  )
  
  let $width := fn:floor(fn:abs(fn:sum($item/price)) div $max * 100)

where $month = 2
where $item/price/text() != fn:abs($item/price/text()) 
group by $day
order by $day
return <div style="width: 500px; margin-bottom: 5px;"><div class="date" style="display: inline-element; width: 50px;">{$day}. {distinct-values($month)}.</div><div style="display: inline-element; width: 450px;"><div class="bar" style="width: {$width}%; color: white; background-color: red; padding: 3px; text-decoration: bold; border-radius: 2px; text-align: right;" >{fn:abs(fn:sum($item/price))}</div></div><br style="clear: both;" /></div>
}

{for $item in doc('\\ad.fi.muni.cz\DFS\home\x390895\_profile\Documents\NetBeansProjects\walletcoach\etc\testdata\items.xml')/items/item
let $date := $item/datetime/text()
let $day := fn:day-from-dateTime($date)
let $month := fn:month-from-dateTime($date)
let $year := fn:year-from-dateTime($date)

let $max := fn:max(
    for $item in doc('\\ad.fi.muni.cz\DFS\home\x390895\_profile\Documents\NetBeansProjects\walletcoach\etc\testdata\items.xml')/items/item
    let $date := $item/datetime/text()
    let $day := fn:day-from-dateTime($date)
    let $month := fn:month-from-dateTime($date)
    let $year := fn:year-from-dateTime($date)
    where $month = 2
    where $item/price/text() = fn:abs($item/price/text()) 
    group by $day
    order by $day
    return if ($max1 > fn:sum(fn:abs($item/price)))
    then $max1
    else fn:sum(fn:abs($item/price))

  )
  
  let $width := fn:floor(fn:abs(fn:sum($item/price)) div $max * 100)

where $month = 2
where $item/price/text() = fn:abs($item/price/text()) 
group by $day
order by $day
return <div style="width: 500px; margin-bottom: 5px;"><div class="date" style="display: inline-element; width: 50px;">{$day}. {distinct-values($month)}.</div><div style="display: inline-element; width: 450px;"><div class="bar" style="width: {$width}%; color: white; background-color: green; padding: 3px; text-decoration: bold; border-radius: 2px; text-align: right;" >{fn:abs(fn:sum($item/price))}</div></div><br style="clear: both;" /></div>
}



</body>
</html>
