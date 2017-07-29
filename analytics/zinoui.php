<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Chart - Zino UI HTML5 framework</title>
        <meta name="description" content="Data visualization, pie & donuts charts.">
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <link rel="stylesheet" href="https://static.zinoui.com/1.5/themes/silver/zino.core.css">
        <link rel="stylesheet" href="https://static.zinoui.com/1.5/themes/silver/zino.chart.css">
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
        <script src="https://static.zinoui.com/1.5/compiled/zino.canvas.min.js"></script>
        <script src="https://static.zinoui.com/1.5/compiled/zino.svg.min.js"></script>
        <script src="https://static.zinoui.com/1.5/compiled/zino.chart.min.js"></script>
        <script src="https://static.zinoui.com/js/front.min.js"></script>
                <style type="text/css">body{padding: 15px; font: normal 12px Arial, sans-serif;}</style>
    </head>
    <body>
    <div id="chart"></div>

<script type="text/javascript">
$(function () {
    $("#chart").zinoChart({
        type: "area",
        width: 530,
        height: 320,
        legend: false,
        categories: [{
            'category': [
                {'label': '2008'},
                {'label': '2009'},
                {'label': '2010'},
                {'label': '2011'},
                {'label': '2012'},
                {'label': '2013'}
            ]
        }],
        series: [{
            'label': 'NY Knicks',
            //'color': '#FFCC00',
            'data': [
                {'value': 1},
                {'value': 3},
                {'value': 5},
                {'value': 2},
                {'value': 4},
                {'value': 6}
            ]
        }]
    });
});   
</script>    </body>
</html>