document.getElementById('chartElem').onclick = function() { 
let ourRequest = new XMLHttpRequest();
ourRequest.open('GET', 'http://localhost:8080/chart')
ourRequest.onload = function() {
let ourData = JSON.parse(ourRequest.responseText);
let dates = []
let counts = []
for (let key in ourData){
dates.push(key)
counts.push(ourData[key])
}
console.log(dates, counts);
writeGraph(dates, counts)
}
ourRequest.send();
function writeGraph(dates, counts){

var ctx = document.getElementById('myChart').getContext('2d');
    var myChart = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: dates,
            datasets: [{
                label: 'Груз',
                data: counts,
                backgroundColor: [
                    'rgba(216, 27, 96, 0.6)',
                    'rgba(3, 169, 244, 0.6)',
                    'rgba(255, 152, 0, 0.6)',
                    'rgba(29, 233, 182, 0.6)',
                    'rgba(156, 39, 176, 0.6)',
                    'rgba(84, 110, 122, 0.6)'
                ],
                borderColor: [
                    'rgba(216, 27, 96, 1)',
                    'rgba(3, 169, 244, 1)',
                    'rgba(255, 152, 0, 1)',
                    'rgba(29, 233, 182, 1)',
                    'rgba(156, 39, 176, 1)',
                    'rgba(84, 110, 122, 1)'
                ],
                borderWidth: 1
            }]
        },
        options: {
            legend: {
                display: false
            },
            title: {
                display: true,
                text: 'Гистограмма количества грузов',
                position: 'top',
                fontSize: 16,
                padding: 20
            },
            scales: {
                yAxes: [{
                    ticks: {
                        min: 0 
                    }
                }]
            }
        }
    });
}
};
