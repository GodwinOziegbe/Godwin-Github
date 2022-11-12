const cpcAverageListAllUsers = "http://localhost:8080/v0.1/cpcAverage/days=30";

let cpcSessions = [];
let cpcRevenue = [];
let cpcSessionsTotalAverage = [];
let cpcRevenueTotalAverage = [];



async function getCpcList() {

     cpcSessions = [];
     cpcRevenue = [];

     console.log("Token in cpcfunctions " + token);
    const cpcList = "http://localhost:8080/v0.1/cpc/" + token + "/startDate=" + startDate + "/endDate=" + endDate;

    let data = await getList(cpcList)


    data.forEach((element) => {       
        cpcSessions.push(element.sessions);
        cpcRevenue.push(element.revenue);
      });



}

async function getCpcListTotalAverage() {

    cpcSessionsTotalAverage = [];
    cpcRevenueTotalAverage = [];



    let cpcAverageListAllUsers = "http://localhost:8080/v0.1/cpc/average/startDate=" + startDate + "/endDate="+ endDate;
    const data = await getList(cpcAverageListAllUsers)

    data.forEach((element) => {
      cpcSessionsTotalAverage.push(element.sessions);
      cpcRevenueTotalAverage.push(element.revenue);

    });


}
