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

public class RouteViewAdapter extends RecyclerView.Adapter<RouteViewAdapter.MyViewHolder> {
    List<Route>routeList;
    Context context;

    public RouteViewAdapter(List<Route> routeList, Context context) {
        this.routeList = routeList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_line_route_object,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.tv_route_nr.setText(String.valueOf(routeList.get(position).getId()));
        holder.tv_route_desc.setText(routeList.get(position).getName());
        holder.tv_route_driving_day.setText(String.valueOf(routeList.get(position).getDriving_day()));
    }

    @Override
    public int getItemCount() {
        return routeList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tv_route_nr;
        TextView tv_route_desc;
        TextView tv_route_driving_day;
        ConstraintLayout parentlayout1;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_route_nr= itemView.findViewById(R.id.tv_route_Id);
            tv_route_desc= itemView.findViewById(R.id.tv_route_name);
            tv_route_driving_day= itemView.findViewById(R.id.tv_route_day);
            parentlayout1= itemView.findViewById(R.id.oneLineRouteObjectLayout);
        }
    }
}
