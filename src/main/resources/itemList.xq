%declarations%
for $item in /items/item
let $date := $item/datetime/text()
let $day := fn:day-from-dateTime($date)
let $month := fn:month-from-dateTime($date)
let $year := fn:year-from-dateTime($date)
order by $date
%where%
return $item