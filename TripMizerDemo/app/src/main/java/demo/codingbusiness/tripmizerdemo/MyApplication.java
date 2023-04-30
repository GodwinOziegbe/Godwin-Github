package demo.codingbusiness.tripmizerdemo;

import android.app.Application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyApplication extends Application {
    private static List<Customer> customerList= new ArrayList<>();
    private static List<Route> routeList= new ArrayList<>();
    private static int nextItem=10;

    public MyApplication(){
       fillCustomerList();
       fillRouteList();
   }

    public void fillCustomerList() {

        Customer c0 = new Customer(0,"abc-gruppen","111 Flisavägen");
        Customer c1 = new Customer(1,"def-gruppen","112 Flisavägen");
        Customer c2 = new Customer(2,"ghi-gruppen","113 Flisavägen");
        Customer c3 = new Customer(3,"jkl-gruppen","211 Flisavägen");
        Customer c4 = new Customer(4,"mno-gruppen","212 Flisavägen");
        Customer c5 = new Customer(5,"pqr-gruppen","213 Flisavägen");
        Customer c6 = new Customer(6,"stu-gruppen","311 Flisavägen");
        Customer c7 = new Customer(7,"vwx-gruppen","312 Flisavägen");
        Customer c8 = new Customer(8,"yza-gruppen","313 Flisavägen");
        Customer c9 = new Customer(9,"zaa-gruppen","411 Flisavägen");

        System.out.println("how far");

         customerList.addAll(Arrays.asList(c0,c1,c2,c3,c4,c5,c6,c7,c8,c9));

    }
    private void fillRouteList() {
        Route r0 = new Route(0,"Wellpap,KP måndag", "måndag",customerList);
        Route r1 = new Route(1,"Wellpap, KP tisdag", "tisdag",customerList);
        Route r2 = new Route(2,"plast tisdag", "tisdag",customerList);
        Route r3 = new Route(3,"Bränbart onsdag", "onsdag",customerList);
        Route r4 = new Route(4,"metal onsdag", "onsdag",customerList);
        Route r5 = new Route(5,"FTI torsdag", "torsdag",customerList);
        Route r6 = new Route(6,"Matavfall fredag", "fredag",customerList);
        Route r7 = new Route(7,"Wellpap fredag", "fredag",customerList);

        routeList.addAll(Arrays.asList(r0,r1,r2,r3,r4,r5,r6,r7));
    }


    public static List<Customer> getCustomerList() {
        return customerList;
    }

    public static void setCustomerList(List<Customer> customerList) {
        MyApplication.customerList = customerList;
    }

    public static List<Route> getRouteList() {
        return routeList;
    }

    public static void setRouteList(List<Route> routeList) {
        MyApplication.routeList = routeList;
    }

    public static int getNextItem() {
        return nextItem;
    }

    public static void setNextItem(int nextItem) {
        MyApplication.nextItem = nextItem;
    }
}
