Highcharts.setOptions({
    global: {
        useUTC: false
    }
});

var chart;
$(document).ready(function() {
   
   // define the options
   var options = {
		   global: {
			      useUTC: false
			   },
		   chart: {
		         renderTo: 'graph',
		         defaultSeriesType: 'line',
		         zoomType: 'x',
		         marginRight: 130,
		         marginBottom: 25
		      },
		      title: {
		         text: 'Temperatur i solfanger',
		         x: -20 //center
		      },
		      subtitle: {
		         text: '',
		         x: -20
		      },
		      xAxis: {
		    	     type: 'datetime',
		    	     maxZoom: 3 * 60 * 60 * 1000 
		    	    
		      },
		      yAxis: {
		         title: {
		            text: 'Temperatur (°C)'
		         },
		         plotLines: [{
		            value: 0,
		            width: 1,
		            color: '#808080'
		         }]
		      },
		      tooltip: {
		         formatter: function() {
		                   return '<b>'+ this.series.name +'</b><br/>'+
		                   Highcharts.dateFormat('%b %e, %H:%M', this.x) +': '+ this.y +'°C';
		         }
		      },
		      legend: {
		         layout: 'vertical',
		         align: 'right',
		         verticalAlign: 'top',
		         x: 10,
		         y: 100,
		         borderWidth: 0
		      },
      series: [{
         name: 'Temp tak'
        	 
      }, {
         name: 'Temp tank'
      }]
   }
   

   $.getJSON('livetemp/lists', function(liveData) {
       options.series[0].data = liveData.t1;
       options.series[1].data = liveData.t3;
      
      chart = new Highcharts.Chart(options);
   });
   
});