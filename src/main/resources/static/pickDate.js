document.getElementById("datePickerFrom").valueAsDate = new Date();
document.getElementById("datePickerTo").valueAsDate = new Date();

let currentDate = luxon.DateTime.now().toFormat("yyyy-MM-dd");
let startDate = luxon.DateTime.local().minus({ week: 4 }).toFormat("yyyy-MM-dd");
let endDate = luxon.DateTime.now().toFormat("yyyy-MM-dd");

const getDateBtn = document.getElementById("getBtn");


function checkDates() {
  startDate = document.getElementById("datePickerFrom").value;
  endDate = document.getElementById("datePickerTo").value;

  if (endDate > currentDate || startDate > currentDate || endDate < startDate) {
    alert("Felaktigt val av datum. Var god och välj igen.");
  
  } else {
      getData();
  }
}


getDateBtn.addEventListener("click", function () {
  if(myChart1 != null) {
    destroyChart();
  }

    checkDates();
    
});
