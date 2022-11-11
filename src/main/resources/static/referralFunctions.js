let referralSessions = [];
let referralRevenue = [];
let referralSessionsTotalAverage = [];
let referralRevenueTotalAverage = [];


async function getReferralList() {

     referralSessions = [];
     referralRevenue = [];
     referralSessionsTotalAverage = [];
     referralRevenueTotalAverage = [];

    let referralList = "http://localhost:8080/v0.1/referral/" + token + "/startDate=" + startDate + "/endDate=" + endDate;
    const data = await getList(referralList);

    data.forEach((element) => {
        referralSessions.push(element.sessions);
        referralRevenue.push(element.revenue);
      });
}


async function getReferralListTotalAverage() {
    let referralAverageListAllUsers = "http://localhost:8080/v0.1/referral/average/startDate=" + startDate + "/endDate="+ endDate;
    const data = await getList(referralAverageListAllUsers);

    data.forEach((element) => {
      referralSessionsTotalAverage.push(element.sessions);
      referralRevenueTotalAverage.push(element.revenue);
    });
}

