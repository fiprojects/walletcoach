<report year="2015" month="1">


{for $item in doc('C:\Users\Maros\Documents\NetBeansProjects\walletcoach\etc\testdata\items.xml')/items/item
let $date := $item/datetime/text()
let $day := fn:day-from-dateTime($date)
let $month := fn:month-from-dateTime($date)
let $year := fn:year-from-dateTime($date)
where $month = 1
where $year = 2015
where $item/price/text() = fn:abs($item/price/text())
group by $month
let $sum := fn:sum($item/price)
return <income>{$sum}</income>}

{for $item in doc('C:\Users\Maros\Documents\NetBeansProjects\walletcoach\etc\testdata\items.xml')/items/item
let $date := $item/datetime/text()
let $day := fn:day-from-dateTime($date)
let $month := fn:month-from-dateTime($date)
let $year := fn:year-from-dateTime($date)
where $month = 1
where $year = 2015
where $item/price/text() != fn:abs($item/price/text())
group by $month
let $sum := fn:sum($item/price)
return <expense>{$sum}</expense>}

{for $item in doc('C:\Users\Maros\Documents\NetBeansProjects\walletcoach\etc\testdata\items.xml')/items/item
let $date := $item/datetime/text()
let $day := fn:day-from-dateTime($date)
let $month := fn:month-from-dateTime($date)
let $year := fn:year-from-dateTime($date)
where $month = 1
where $year = 2015
group by $month
let $sum := fn:sum($item/price)
return <total>{$sum}</total>}


</report>
  


