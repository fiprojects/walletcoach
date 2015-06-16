<html>
    
<head>
    <title>Major expenses by categories</title>
    <meta charset="UTF-8" />
    <link rel="stylesheet" media="screen, projection" type="text/css" href="style/barchart.css" />
</head>
    
<body>
    <svg class="barchart" width="800" height="600">    
        <!-- Caption -->
        <text class="caption" x="400" y="49" text-anchor="middle">Major expenses by categories</text>

        <!-- Axes -->
        <line class="axis axis-x" x1="100" y1="120" x2="100" y2="530" />
        <line class="axis axis-y" x1="80" y1="480" x2="700" y2="480" />

        <!-- Bars -->
        {
            let $sorted := for $category in //category
            where $category/@expense < 0
            let $expense := -$category/@expense
            order by $expense descending
            return $category

            for $category at $i in $sorted
            let $expense := -$category/@expense
            let $max := -1 * fn:min(//category/@expense)
            let $maxInt := 360
            let $part := ($expense div $max) * $maxInt
              return (
                for $data in $category[position() <= 5]
                let $x := 128.57 + (114.256 * ($i - 1))
                return(
                   <rect class="bar" x="{$x}" y="480" width="85.71" height="{$part}" transform="translate(0, {-$part})" />
                )
            )
        }

        <!-- Legend -->
        {
            let $sorted := for $category in //category
            where $category/@expense < 0
            let $expense := -$category/@expense
            order by $expense descending
            return $category

            for $category at $i in $sorted
            let $expense := -$category/@expense
              return (
                for $data in $category[position() <= 5]
                let $x := 171.425 + (114.256 * ($i - 1))
                return(
                   <text class="separator-caption" x="{$x}" y="500" text-anchor="middle" alignment-baseline="central">{xs:string($category/@name)}</text>
                )
            )
        }
    </svg>
</body>
</html>