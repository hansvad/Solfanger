$(function() {

    $.getJSON('history/lists', function(data) {
        // Create the chart
        window.chart = new Highcharts.StockChart({
            chart: {
                renderTo: 'container'
            },

            rangeSelector: {
                selected: 1
            },

            title: {
                text: 'Historiske data for solfanger'
            },

            xAxis: {
                maxZoom: 14 * 24 * 3600000 // fourteen days
            },
            yAxis:[{
            	  labels: {
                      formatter: function() {
                         return this.value +' °C';
                      },
                      style: {
                         color: '#89A54E'
                      }
                   },
                   title: {
                      text: 'Temperatur',
                      style: {
                         color: '#89A54E'
                      }
                   },
                   opposite: true
                   
                }, { // Secondary yAxis
                   gridLineWidth: 0,
                   title: {
                      text: 'Tid',
                      style: {
                         color: '#4572A7'
                      }
                   },
                   labels: {
                      formatter: function() {
                         return this.value +' timer';
                      },
                      style: {
                         color: '#4572A7'
                      }
                   }
                   
                }, { // energi yAxis
                   gridLineWidth: 0,
                   title: {
                      text: 'Energi'
                   },
                   labels: {
                      formatter: function() {
                         return this.value +' kWh';
                      },
                      style: {
                         color: '#AA4643'
                      }
                   },
                   opposite: true
               
            	
            }],

            series: [{
                name: 'Temperatur i tank',
                yAxis: 0,
                color: '#89A54E',
                data: data.t3
            },{
               name: 'Tid pumpe tak',
               type: 'column',
               color: '#4572A7',
               yAxis: 1,
               data: data.time_p1
            },{
           	   name: 'Tid pumpe gulvvarme',
        	   type: 'column',
        	   yAxis: 1,
               data: data.time_p2
            },{
           	   name: 'Energi produsert',
        	   type: 'column',
        	   color: '#AA4643',
        	   yAxis:2,
               data: data.energi
            }]
        });
    });

});