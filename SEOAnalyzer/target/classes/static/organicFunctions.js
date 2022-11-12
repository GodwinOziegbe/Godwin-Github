

let organicSessions = [];
let organicRevenue = [];
let organicSessionsTotalAverage = [];
let organicRevenueTotalAverage = [];

async function getOrganicList() {

   organicSessions = [];
   organicRevenue = [];


  const organicList = "http://localhost:8080/v0.1/organic/" + token + "/startDate=" + startDate + "/endDate=" + endDate;
  const data = await getList(organicList);

  data.forEach((element) => {
    organicSessions.push(element.sessions);
    organicRevenue.push(element.revenue);
  });
}

async function getOrganicListTotalAverage() {
    organicSessionsTotalAverage = [];
    organicRevenueTotalAverage = [];


    const organicAverageList = "http://localhost:8080/v0.1/organic/average/startDate=" + startDate + "/endDate="+ endDate;
    const data = await getList(organicAverageList);

  data.forEach((element) => {
    organicSessionsTotalAverage.push(element.sessions);
    organicRevenueTotalAverage.push(element.revenue);
  });
}


