package demo.codingbusiness.tripmizerdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class RouteCustomerListViewAdapter extends RecyclerView.Adapter<RouteCustomerListViewAdapter.MyViewHolder> {
    List<Customer>customerList;
    Context context;
    private int next_nr=0;

    public RouteCustomerListViewAdapter(List<Customer> customerList, Context context) {
        this.customerList = customerList;
        this.context = context;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_line_route_customer_object,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }
//assign data to variables
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
    //assign values to the recyclerview layout
    holder.tv_item_num.setText(String.valueOf(getSerialNumber()));
    holder.tv_customer_name.setText(customerList.get(position).getName());
    holder.tv_customer_address.setText(customerList.get(position).getAddress());
    }

    @Override
    public int getItemCount() {
        return customerList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_item_num;
        TextView tv_customer_name;
        TextView tv_customer_address;
        ConstraintLayout parentlayout;

        //connect variable with thier couterparts in both sides of the adapter
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_item_num=itemView.findViewById(R.id.tv_item_nr);
            tv_customer_name=itemView.findViewById(R.id.tv_cust_name);
            tv_customer_address=itemView.findViewById(R.id.tv_cust_addr);
            parentlayout= itemView.findViewById(R.id.oneLineRouteCustomerObjectLayout);

        }
    }

    public  String getSerialNumber(){
        next_nr++;
        String output = String.format("%02d", next_nr);
        return output;
    }

}
