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
		         defaultSeriesType: 'spline',
		         zoomType: 'x'
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
		    	     dateTimeLabelFormats: {
		    	    		second: '%H:%M:%S',
		    	    		minute: '%e. %H:%M',
		    	    		hour: '%e. %b %H:%M',
		    	    		day: '%e. %b',
		    	    		week: '%e. %b',
		    	    		month: '%b \'%y',
		    	    		year: '%Y'
		    	    	},
		    	     maxZoom: 3 * 60 * 60 * 1000 
		    	    
		      },
		      yAxis: {
		         title: {
		            text: 'Temperatur (°C)'
		         }
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
		      plotOptions: {
		          spline: {
		             marker: {
		                enabled: false,
		                states: {
		                   hover: {
		                      enabled: true,
		                      radius: 5
		                   }
		                }
		             },
		             shadow: true,
		             states: {
		                hover: {
		                   lineWidth: 2                  
		                }
		             }
		          }
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