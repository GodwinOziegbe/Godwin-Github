const revenueChart = document.getElementById("myRevenueChart").getContext("2d");
const sessionChart = document.getElementById("mySessionChart").getContext("2d");
const averageRevenueChart = document.getElementById("myAverageRevenueChart").getContext("2d");
const averageSessionChart = document.getElementById("myAverageSessionChart").getContext("2d");
let myChart1
let myChart2
let myChart3
let myChart4


function destroyChart() {
  myChart1.destroy();
  myChart2.destroy();
  myChart3.destroy();
  myChart4.destroy();
}


function getRevenueChart() { 
  myChart1 = new Chart(revenueChart, {
    type: "line",
    data: {
      labels: dates,
      datasets: [
        {
          label: "CPC",
          data: cpcRevenue,
          backgroundColor: ["rgba(255, 99, 132, 0.2)"],
          borderColor: ["rgba(255, 99, 132, 1)"],
        },
        {
          label: "Direct",
          data: directRevenue,
          backgroundColor: ["rgba(54, 162, 235, 0.2)"],
          borderColor: ["rgba(54, 162, 235, 1)"],
        },
        {
          label: "Organic",
          data: organicRevenue,
          backgroundColor: ["rgba(255, 206, 86, 0.2)"],
          borderColor: ["rgba(255, 206, 86, 1)"],
        },
        {
          label: "Referral",
          data: referralRevenue,
          backgroundColor: ["rgba(75, 192, 192, 0.2)"],
          borderColor: ["rgba(75, 192, 192, 1)"],
        },
        {
          label: "Average",
          data: averageRevenue,
          backgroundColor: ["rgba(89, 89, 89, 0.2)"],
          borderColor: ["rgba(89, 89, 89, 0.8)"],
          borderWidth: 2,
          pointRadius: 2, 
          tension: 0.3
        },
      ],
    },
    options: {
      elements: {
        line: {
          borderWidth: 2,
          tension: 0.3
        },
        point: {
          pointRadius: 2
        }
      },
      plugins: {
        title: {
          text: "30 days Revenue",
          display: true,
        },
        legend: {
          labels: {
            boxHeight: 10,
            boxWidth: 10,
            padding: 5
          }
        } 
      },
      responsive: false,
    },
  });
}


function getSessionChart() {  
  myChart2 = new Chart(sessionChart, {
    type: "line",
    data: {
      labels: dates,
      datasets: [
        {
          label: "CPC",
          data: cpcSessions,
          backgroundColor: ["rgba(255, 99, 132, 0.2)"],
          borderColor: ["rgba(255, 99, 132, 1)"],
        },
        {
          label: "Direct",
          data: directSessions,
          backgroundColor: ["rgba(54, 162, 235, 0.2)"],
          borderColor: ["rgba(54, 162, 235, 1)"],
        },
        {
          label: "Organic",
          data: organicSessions,
          backgroundColor: ["rgba(255, 206, 86, 0.2)"],
          borderColor: ["rgba(255, 206, 86, 1)"],
        },
        {
          label: "Referral",
          data: referralSessions,
          backgroundColor: ["rgba(75, 192, 192, 0.2)"],
          borderColor: ["rgba(75, 192, 192, 1)"],      
        },
        {
          label: "Average",
          data: averageSession,
          backgroundColor: ["rgba(89, 89, 89, 0.2)"],
          borderColor: ["rgba(89, 89, 89, 0.8)"],
          borderWidth: 2,
          pointRadius: 2,
          tension: 0.3
        },
      ],
    },
    options: {
      elements: {
        line: {
          borderWidth: 2,
          tension: 0.3
        },
        point: {
          pointRadius: 2
        }
      },
      plugins: {
        title: {
          text: "30 days Sessions ",
          display: true,
        },
        legend: {
          labels: {
            boxHeight: 10,
            boxWidth: 10,
            padding: 5
          }
        },
      },
      responsive: false,
    },
  });
}


function getAverageRevenueChart() {
  myChart3 = new Chart(averageRevenueChart, {
    type: "bar",
    data: {
      labels: dates,
      datasets: [
        {
          label: "Your Average",
          data: averageRevenue,
          backgroundColor: ["rgba(89, 89, 89, 0.8)"],
        },
        {
          label: "All Users",
          data: totalAverageRevenue,
          backgroundColor: ["rgba(0, 0, 204, 0.6)"],
        },
      ],
    },
    options: {
      
      plugins: {
        title: {
          text: "30 days Average Revenue",
          display: true,
        },
        legend: {
          labels: {
            boxHeight: 10,
            boxWidth: 10,
            padding: 5
          }
        },
      },
      responsive: false,
    },
  });
}


function getAverageSessionChart() {  
  myChart4 = new Chart(averageSessionChart, {
    type: "bar",
    data: {
      labels: dates,
      datasets: [
        {
          label: "Your Average",
          data: averageSession,
          backgroundColor: ["rgba(89, 89, 89, 0.8)"],
        },
        {
          label: "All Users",
          data: totalAverageSession,
          backgroundColor: ["rgba(0, 0, 204, 0.6)"],
        },
      ],
    },
    options: {
      plugins: {
        title: {
          text: "30 days Average Sessions",
          display: true,
        },
        legend: {
          labels: {
            boxHeight: 10,
            boxWidth: 10,
            padding: 5
          }
        },
      },
      responsive: false,
    },
  });
}
