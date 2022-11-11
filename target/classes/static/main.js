
let token;
let dates = [];
let averageRevenue = [];
let averageSession = [];
let totalAverageRevenue = [];
let totalAverageSession = [];


async function getToken() {
  console.log("begin get token");
  const response = await fetch("http://localhost:8080/v0.1/token");
  let data = await response.json().then(response => {
    token = response;
    //Här sparas den inloggades användarID
    console.log("User ID " + token);
  });
  console.log("After response User ID " + token);
  return data;
}


async function getList(url) {
  const response = await fetch(url);
  const data = await response.json();
  dates = [];

  if (dates.length === 0) {
    data.forEach((element) => {
      dates.push(element.date);
    });
  }
  return data;
}

function getAverage() {
  averageRevenue = [];
  averageSession = [];

  for(let i = 0; i < dates.length; i++){
    averageRevenue.push((cpcRevenue[i]+directRevenue[i]+organicRevenue[i]+referralRevenue[i])/4)
    averageSession.push((cpcSessions[i]+directSessions[i]+organicSessions[i]+referralSessions[i])/4)
  }
}

function getTotalAverage() {
  totalAverageRevenue = [];
  totalAverageSession = [];

  console.log(cpcRevenueTotalAverage);
  for(let i = 0; i < dates.length; i++){
    totalAverageRevenue.push(cpcRevenueTotalAverage[i]+directRevenueTotalAverage[i]+organicRevenueTotalAverage[i]+referralRevenueTotalAverage[i]);
    totalAverageSession.push(cpcSessionsTotalAverage[i]+directSessionsTotalAverage[i]+organicSessionsTotalAverage[i]+referralSessionsTotalAverage[i]);

  }

}



getData()

async function getData() {
  await getToken();
  await getCpcList();
  await getDirectList();
  await getOrganicList();
  await getReferralList();
  await getCpcListTotalAverage();
  await getDirectListTotalAverage();
  await getOrganicListTotalAverage();
  await getReferralListTotalAverage();
  getAverage();
  getTotalAverage();
  getRevenueChart();
  getSessionChart();
  getAverageRevenueChart();
  getAverageSessionChart();    
}




