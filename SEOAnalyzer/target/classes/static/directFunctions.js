
let directSessions = []
let directRevenue = []
let directSessionsTotalAverage = []
let directRevenueTotalAverage = []

async function getDirectList() {

     directSessions = []
     directRevenue = []


    let directList = "http://localhost:8080/v0.1/direct/" + token + "/startDate=" + startDate + "/endDate=" + endDate;
    const data = await getList(directList)



    data.forEach((element) => {
        directSessions.push(element.sessions);
        directRevenue.push(element.revenue);
      });
}

async function getDirectListTotalAverage() {

    directSessionsTotalAverage = []
    directRevenueTotalAverage = []

    const directTotalAverage =  "http://localhost:8080/v0.1/direct/average/startDate=" + startDate + "/endDate="+ endDate;
  const data = await getList(directTotalAverage);

  data.forEach((element) => {
      directSessionsTotalAverage.push(element.sessions);
      directRevenueTotalAverage.push(element.revenue);
    });
}
