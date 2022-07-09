//clears the screen when refreshed
function homePage() {
  sessionStorage.clear()
  window.location.reload()

}


$(document).ready(function () {
  if (window.location.hash == '#reload') {
    onReload();
   
  }
});

//function to redirect properly after reloading page
function onReload () {
   myfunc=sessionStorage.getItem('temp')
  
  if(myfunc==1){
    createCarTable();
  } else
  if(myfunc==2){
    createCustomerTable();
  } else
  if(myfunc==3){
    loginUser();
  }
  else
  if(myfunc==4){
    carAdding();
  }else
  if(myfunc==5){
    
   createTable();
  }else
  if(myfunc==6){
    book();
  }else
  if(myfunc==7){
    createOrdersTable();
  }
  //else
  // if(myfunc==8){
  //   orderView();
  // }
}

function reload () {
  window.location.hash = 'reload';
  window.location.reload();
}

function ourCarsAdmin() {
  let tempvalue= 1;
 sessionStorage.setItem('temp',tempvalue)
 reload()
}

function createCarTable(){

  document.getElementById('ourCars').style.display='inline'
  //document.getElementById('myLogin').style.display = 'none'
 
  document.getElementById('ourCarsHeading').style.display = 'inline'
 
//   //Check if user is logged in or not
  if (sessionStorage.getItem('status') == null){

//   //user is not logged in, to go login page
      userLogin()
      
  } else{/*do nothing*/} 


myuser= sessionStorage.getItem('username') 
pass= sessionStorage.getItem('mypass') 

    $.ajax({
      type: "GET",
      url: "http://localhost:8090/api/v1/cars",
      dataType: 'json',
      headers: {
        "Authorization": "Basic " + btoa(myuser + ":" + pass)
      },
      success: function (result){
       // var myObj = JSON.parse(result)
          console.log(result)
          var myarray=result.slice()
    //Set the login session 
    
   
          let table = document.querySelector('table')
          if (myarray.length==0){
        let data = Object.keys(result[0])
        
        generateCarTable(table,result);
      }
        else{let data = Object.keys(myarray[0]) 
          generateCarTable(table,myarray);
        }
        
      }
   
    });
  
  
  }

function generateCarTableHead(table, data) {
  let thead = table.createTHead()
  let row = thead.insertRow()
  for (let key of data) {
    let th = document.createElement('th')
    if (key != 'isAvailable' && key != 'dailyRentPrice' && key != 'id') {
      let text = document.createTextNode(key)
      th.appendChild(text)
      row.appendChild(th)
    }
  }
}

function generateCarTable(table, data) {
  for (let element of data) {
    let row = table.insertRow()

    for (key in element) {
      let cell = row.insertCell()
      if (key != 'isAvailable' && key != 'dailyRentPrice' && key != 'id') {
        let text = document.createTextNode(element[key])
        cell.appendChild(text)
      }
    }

    var button = document.createElement('input')
    button.setAttribute('type', 'button')
    button.setAttribute('value', 'Choose Car')
    button.setAttribute('model', element.type)
    button.setAttribute('name', element.name)
    button.setAttribute('style', 'position:relative;')
    button.setAttribute('style', 'display:inline;')

    row.insertAdjacentElement('beforeend', button)

    button.setAttribute(
      'onclick',
      'updateCar("' +
        element.id +
        
        '")',
    )
  }
}

function addCar(){
  let tempvalue= 4;
  sessionStorage.setItem('temp',tempvalue)
  reload()

}

function carAdding(){
  //   //Check if user is logged in or not
  document.getElementById('addCar').style.display='none'
  if (sessionStorage.getItem('status') == null){
   
    //   //user is not logged in, to go login page
          userLogin()
          
      } else { document.getElementById('addCar').style.display='inline'}
  myuser=sessionStorage.getItem('username') 
  pass=sessionStorage.getItem('mypass') 
 
     
    document.getElementById('ourCarsHeading').style.display='none'
   document.getElementById('ourCars').style.display='none'
    
    $('#addNew').click(function() {
            
      let myuser=sessionStorage.getItem('username')
     let pass=sessionStorage.getItem('mypass')
    
       rentPrice= $('#dailyRent').val()
       carname= $('#nameofcar').val()
       cartype= $('#typeofcar').val()
      carmodel= $('#modelofcar').val()
       carisAvailable= $('#isAvailableofcar').val()
       
     $.ajax({
      type: "POST",
      url: 'http://localhost:8090/api/v1/admin/addnewcar?dailyRentPrice=' +
      rentPrice +
       '&name=' +
       carname +
       '&car_type=' +
       cartype +
       '&car_model=' +
       carmodel +
       '&isAvailable=' +
       carisAvailable +
      '',
      dataType: 'json',
      headers: {
        
        "Authorization": "Basic " + btoa(myuser + ":" + pass)
    
      },
        
      success: function (result){
       
        
      }
      
    });
    document.getElementById('addCar').style.display="none"
    alert("sucess")
      });
      }
    

function updateCar(id){

document.getElementById('updateCar').style.display='inline'
document.getElementById('ourCarsHeading').style.display='none'
document.getElementById('ourCars').style.display='none'

$('#update').click(function() {
        
  let myuser=sessionStorage.getItem('username')
 let pass=sessionStorage.getItem('mypass')

   rentPrice= $('#dailyRentPrice').val()
   carname= $('#name').val()
   cartype= $('#type').val()
   carmodel= $('#model').val()
   carisAvailable= $('#isAvailable').val()


 $.ajax({
  type: "PUT",
  url: 'http://localhost:8090/api/v1/admin/updatecar?car_id=' +
  id +
  '&dailyRentPrice=' +
   rentPrice +
   '&name=' +
   carname +
   '&type=' +
   cartype +
   '&model=' +
   carmodel +
   '&isAvailable=' +
   carisAvailable +
  '',
  dataType: 'html',
  headers: {
    
    "Authorization": "Basic " + btoa(myuser + ":" + pass)

  },
    
  success: function (result){
   alert(result)
    document.getElementById('updateCar').style.display="none"
  }
  
});
  
  });
  }



function ourCars() {

  let tempvalue= 5;
 sessionStorage.setItem('temp',tempvalue)
 reload()
  
}

function createTable(){
  document.getElementById('ourCars').style.display='inline'
  document.getElementById('ourCarsHeading').style.display = 'inline'
//   //Check if user is logged in or not
  if (sessionStorage.getItem('status') == null){

//   //user is not logged in, to go login page
      userLogin()
      
  } else{/*do nothing*/} 


myuser= sessionStorage.getItem('username') 
pass= sessionStorage.getItem('mypass') 

    $.ajax({
      type: "GET",
      url: "http://localhost:8090/api/v1/cars",
      dataType: 'json',
      headers: {
        "Authorization": "Basic " + btoa(myuser + ":" + pass)
      },
      success: function (result){
       // var myObj = JSON.parse(result)
          console.log(result)
          var myarray=result.slice()
    //Set the login session 
    
   // sessionStorage.setItem('storedResult', JSON.stringify(result));
          let table = document.querySelector('table')
          if (myarray.length==0){
        let data = Object.keys(result[0])
        
        generateTable(table,result);
      }
        else{let data = Object.keys(myarray[0]) 
          generateTable(table,myarray);
        }
        
      }
   
    });
  
  
  }

 

function generateTableHead(table, data) {
  let thead = table.createTHead()
  let row = thead.insertRow()
  for (let key of data) {
    let th = document.createElement('th')
    if (key != 'isAvailable' && key != 'dailyRentPrice' && key != 'id') {
      let text = document.createTextNode(key)
      th.appendChild(text)
      row.appendChild(th)
    }
  }
}

function generateTable(table, data) {
  for (let element of data) {
    let row = table.insertRow()

    for (key in element) {
      let cell = row.insertCell()
      if (key != 'isAvailable' && key != 'dailyRentPrice' && key != 'id') {
        let text = document.createTextNode(element[key])
        cell.appendChild(text)
      }
    }

    var button = document.createElement('input')
    button.setAttribute('type', 'button')
    button.setAttribute('value', 'Choose Car')
    button.setAttribute('model', element.type)
    button.setAttribute('name', element.name)
    button.setAttribute('style', 'position:relative;')
    button.setAttribute('style', 'display:inline;')

    row.insertAdjacentElement('beforeend', button)

    button.setAttribute(
      'onclick',
      'bookaCar("' +
        element.id +
        '","' +
        element.name +
        '", "' +
        element.type +
        '","' +
        element.dailyRentPrice +
        '","' +
        element.model +
        '")',
    )
  }
}

function bookaCar(id, name, type, dailyRentPrice, model) {
 
  document.getElementById('bookaCar').style.display = 'inline'
  document.getElementById('car').style.fontWeight = 'bold'
  document.getElementById('car').innerHTML = name + ' ' + model
  document.getElementById('rentPrice').innerHTML = dailyRentPrice
  document.getElementById('carId').innerHTML = id
}


function handleForm(event) {
  event.preventDefault()
  validateForm()
  // handles booking Form
  let myrent = document.forms['myForm']['rentaldate'].value
  let myreturn = document.forms['myForm']['returndate'].value

  rentDays = calculateDays(myreturn, myrent)
  let carId = document.getElementById('carId').innerHTML
 // let custId = document.getElementById('customerId').innerHTML
  let myRentPrice = document.getElementById('rentPrice').innerHTML
  let totalPrice = myRentPrice * rentDays

 
    handleBooking(rentDays, totalPrice, carId)
   

}
function validateForm() {
  let x = document.forms['myForm']['rentaldate'].value
  let y = document.forms['myForm']['returndate'].value
  if (x == '' || y == '') {
    alert('date must be entered')
    return false
  }
}

function clearScreen(){
  window.location.reload()
}

function handleBooking(myRentDays, myPrice,  mycarId) {

  //   //Check if user is logged in or not
  if (sessionStorage.getItem('status') == null){

    //   //user is not logged in, to go login page
          userLogin()
          
      } else{/*do nothing*/}
  myuser=sessionStorage.getItem('username') 
  pass=sessionStorage.getItem('mypass') 
 

getCustomerId(myuser,pass)
alert(custId=sessionStorage.getItem('loggedinUser'))

  $.ajax({
    type: "POST",
    url: 'http://localhost:8090/api/v1/rental/ordercar?rentDays=' +
    myRentDays +
    '&totalPrice=' +
    myPrice +
    '&user_id=' +
    custId +
    '&car_id=' +
    mycarId +
    '',
    dataType: 'json',
    headers: {
      
      "Authorization": "Basic " + btoa(myuser + ":" + pass)

    },
    success: function (result){
     
         
    }
    
  });
  alert('booking successful')
  document.getElementById('bookaCar').style.display='none'
}
//});
//}

function getCustomerId(myuser,pass){

  // myuser=sessionStorage.getItem('username') 
  // pass=sessionStorage.getItem('mypass') 
  $.ajax({
      type: "GET",
      url: 'http://localhost:8090/api/v1/getcustomerid?username=' + myuser+'',
      dataType: 'html',
      headers: {
        "Authorization": "Basic " + btoa(myuser + ":" + pass)
      },
      success: function (result){
              
        sessionStorage.setItem('loggedinUser',result) 
      }
      
    });
    
    
}

function calculateDays(myreturn, myrent) {
  var date1 = new Date(myreturn)
  var date2 = new Date(myrent)
  var difference = date1.getTime() - date2.getTime()
  var days = Math.ceil(difference / (1000 * 3600 * 24))
  //console.log(days + ' days to Christmas')
  return days
}

function ourCustomers() {
  let tempvalue= 2;
  sessionStorage.setItem('temp',tempvalue)
  reload()
      
  }
    
  function createCustomerTable() {
       //Check if user is logged in or not
    if (sessionStorage.getItem('status') == null){
      //   //user is not logged in, to go login page
            userLogin()
           
        } else{document.getElementById('ourCustomersHeading').style.display = 'inline'
        document.getElementById('ourCustomers').style.display = 'inline'} 
        
     '<br/>'
    myuser= sessionStorage.getItem('username') 
    pass= sessionStorage.getItem('mypass') 
  
  
    $.ajax({
      type: "GET",
      url: 'http://localhost:8090/api/v1/admin/customers',
      dataType: 'json',
      headers: {
        "Authorization": "Basic " + btoa(myuser + ":" + pass)
      },
      success: function (result){
        
        
         let table = document.querySelector('#ourCustomers')
         //let table=document.getElementById('myOrders').
         let data = Object.keys(result[0])
   
         generateCustomerTable(table, result)
        
       // result1=JSON.stringify(result)  
       
       
      }
   
    });
    
  }
  
  
  function generateCustomerTable(table, data) {
    for (let element of data) {
      let row = table.insertRow()
  
      for (key in element) {
        let cell = row.insertCell()
        if (
          key != 'username' &&
          key != 'password'
         
        ) {
          
          let text = document.createTextNode(element[key])
          cell.appendChild(text)
         
        }
      }
  
      var button = document.createElement('input')
      button.setAttribute('type', 'button')
      button.setAttribute('value', 'View Customer')
      button.setAttribute('style', 'position:relative;')
      button.setAttribute('style', 'display:inline;')
  
      row.insertAdjacentElement('beforeend', button)
  
      button.setAttribute(
        'onclick',
        'viewCustomer("' +
          element.id +
          '","' +
          element.firstname +
          '", "' +
          element.lastname +
          '","' +
          element.email +
         
          '")',
      )
    }
  }
   
  
  function viewCustomer(Id, firstname, lastname, email) {
     
    
    document.getElementById('customerPage').style.display = 'inline'
     
    document.getElementById('customerIdHeading').style.display = 'inline-block'
   
    document.getElementById('customId').innerHTML=Id
            
    document.getElementById('customerFirname').style.display = 'inline'
   
    document.getElementById('customerLastname').style.display='inline' 
  
    document.getElementById('customerEmail').style.display = 'inline'
         
    
    document.getElementById('customerFirstname').innerHTML=firstname
    document.getElementById('customerLastname').innerHTML=lastname
    document.getElementById('customerEmail').innerHTML=email
      
   
   sessionStorage.setItem('customId', Id);
   
  }
  
  

function myOrders() {
  let tempvalue= 7;
 sessionStorage.setItem('temp',tempvalue)
 reload()

}


function createOrdersTable() {
   //Check if user is logged in or not
if (sessionStorage.getItem('status') == null){
  //   //user is not logged in, to go login page
       return userLogin()
       
    } else{/*do nothing*/} 
   
  document.getElementById('myOrdersHeading').style.display = 'inline'
  document.getElementById('myOrders').style.display = 'inline'
 
 
  myuser= sessionStorage.getItem('username') 
  pass= sessionStorage.getItem('mypass') 


  $.ajax({
    type: "GET",
    url: 'http://localhost:8090/api/v1/rental/myorders',
    dataType: 'json',
    headers: {
      "Authorization": "Basic " + btoa(myuser + ":" + pass)
    },
    success: function (result){
      
      
       let table = document.querySelector('#myOrders')
      
       let data = Object.keys(result[0])
 
       generateOrdersTable(table, result)
             
     
    }
 
  });
  
}

function generateOrdersTable(table, data) {
  for (let element of data) {
    let row = table.insertRow()

    for (key in element) {
      let cell = row.insertCell()
      if (
        key != 'returnDate' &&
        key != 'orderDate' &&
        key != 'totalPrice' &&
        key != 'car' &&
        key != 'user' 
        
      ) {
        
        let text = document.createTextNode(element[key])
        cell.appendChild(text)
       
      }
    }

    var button = document.createElement('input')
    button.setAttribute('type', 'button')
    button.setAttribute('value', 'View Order')
    button.setAttribute('style', 'position:relative;')
    button.setAttribute('style', 'display:inline;')

    row.insertAdjacentElement('beforeend', button)

    button.setAttribute(
      'onclick',
      'viewOrder("' +
        element.id +
        '","' +
        element.returnDate +
        '", "' +
        element.rentedDate +
        '","' +
        element.totalPrice +
        '","' +
        element.car.name +
        '","' +
        element.car.type +
        '","' +
        element.car.model +
        '","' +
        element.user.id +
        '")',
    )
  }
}


function viewOrder(orderId, returndate, rentdate, totalprice, carName, carType,carModel,customer) {
 
  document.getElementById('bookaCar').style.display = 'none'
    document.getElementById('myOrdersHeading').style.display = 'none'
    document.getElementById('myOrders').style.display = 'none'

    document.getElementById('orderPage').style.display = 'inline'
   
  document.getElementById('orderNrHeading').style.display = 'inline-block'
  document.getElementById('customerIdHeading').style.display = 'inline-block'
  document.getElementById('custoId').innerHTML=customer
  
 
  document.getElementById('returnDate').style.display = 'inline'
  document.getElementById('rentDate').style.display = 'inline'
  
  document.getElementById('carName').style.display = 'inline'
  document.getElementById('carModel').style.display = 'inline'
  
 document.getElementById('custoId').style.display='inline'
  document.getElementById('ordernr').style.display = 'inline'
 
  
  document.getElementById('updateOrder').style.display='inline' 

  document.getElementById('carTypeText').style.display = 'inline'
  document.getElementById('carType').style.display = 'inline'
  
 
  document.getElementById('carType').innerHTML=carType
  document.getElementById('returnDate').innerHTML=returndate
  document.getElementById('rentDate').innerHTML=rentdate
  document.getElementById('ordernr').innerHTML=orderId
  document.getElementById('totalPrice').innerHTML=totalprice
  document.getElementById('carName').innerHTML=carName
  document.getElementById('carModel').innerHTML=carModel
  
 
 sessionStorage.setItem('rentId', orderId);
 
}

 function updateOrder(){

 
  myuser=   sessionStorage.getItem('username')
    pass= sessionStorage.getItem('mypass')
rentId=sessionStorage.getItem('rentId')

    $.ajax({
      type: "PUT",
      url: 'http://localhost:8090/api/v1/rental/updateorder?rental_id=' + rentId+'',
      dataType: 'html',
      headers: {
        "Authorization": "Basic " + btoa(myuser + ":" + pass)
      },
      success: function (result){
        document.getElementById('myLogin').style.display = 'none'
         alert(result)
         
    //Set the login session 
    
      }
});



}

function userLogin() {
  let tempvalue= 3;
  sessionStorage.setItem('temp',tempvalue)
  reload()
}
function loginUser(){
   //Check if user is logged in or not
   if (sessionStorage.getItem('status') == null){
  sessionStorage.clear();
    document.getElementById('myLogin').style.display = 'inline'
    //user is not logged in
         
    $('#loginbutton').click(function() {
        
    let myuser=$('#username').val();
   let pass=$('#password').val()
      
      $.ajax({
        type: "GET",
        url: "http://localhost:8090/api/v1/login",
        dataType: 'html',
        headers: {
          "Authorization": "Basic " + btoa(myuser + ":" + pass)
        },
        success: function (result){
          document.getElementById('myLogin').style.display = 'none'
           alert(result)
           
      //Set the login session 
      sessionStorage.setItem('status','loggedIn') 
      sessionStorage.setItem('username',myuser) 
      sessionStorage.setItem('mypass',pass) 
               
        }
     
      });
    
    });
    }
  
  else{
  alert("already loged in!")
  
}

} 



function loginDetails(){

homePage();
}

function logOut(){
  sessionStorage.clear();
  homePage();

}


function handleLoginDetails(event) {
  event.preventDefault()
  validateLogin()
  username = document.getElementById('username').value
  let password = document.getElementById('password').value
  alert('login sucess');
  return username,password;
 
}


function confirmBox() {
  let text = 'Press ok to view our Cars!\nPress cancel to view your orders.'
  if (confirm(text) == true) {
    text = 'You pressed OK!'
  ourCars()
  } else {
    text = 'You canceled!'
    myOrders()
  }
}

