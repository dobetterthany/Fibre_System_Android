package com.example.fibre_system_android;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Main_RecyclerViewAdapter extends RecyclerView.Adapter<Main_RecyclerViewAdapter.ViewHolder>{

    // 리사이클러뷰 안 리사이클러뷰 관련
    // 두 번째 어댑터와 연결
    Second_Recyclerview_Adapter adapter;
    ArrayList<Recycler_item> itemsArrayListFull;
    ArrayList<ArrayList<Recycler_item>> filteredItemsListArray;
    ArrayList<String> titleNames;
    ArrayList<ShowerRange> categoryTypes;
    private Context context;
    private SparseBooleanArray selectedItems = new SparseBooleanArray();
    private int prePosition = -1;
    SelectItemListener listener1;
    Recycler_item recycler_item;


    public Main_RecyclerViewAdapter(ArrayList<String> names,  ArrayList<Recycler_item> items, ArrayList<ShowerRange> categoryTypes, SelectItemListener listener1){
        this.titleNames = names;
        this.itemsArrayListFull = items;
        this.categoryTypes = categoryTypes;
        this.listener1 = listener1;
        this.filteredItemsListArray = new ArrayList<ArrayList<Recycler_item>>();
    }

    @NonNull
    @Override
    public Main_RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main_item, parent, false);
        return new ViewHolder(v);
    }

    private void filterShower(int position) {
        ArrayList<Recycler_item> filteredList = new ArrayList<>();
            for (Recycler_item recycler_item : itemsArrayListFull) {
                if (recycler_item.getShowerRange().ordinal() == categoryTypes.get(position).ordinal() ) {
                    filteredList.add(recycler_item);
                }
            }
            filteredItemsListArray.add(filteredList);
    }

    @Override
    public void onBindViewHolder(@NonNull Main_RecyclerViewAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.recyclerView.setLayoutManager( new LinearLayoutManager(context));

        filterShower(position);


        // Create nested recycler view
        adapter = new Second_Recyclerview_Adapter(context, filteredItemsListArray.get(position), listener1);
        holder.recyclerView.setAdapter(adapter);

        holder.onBind(position);

        holder.textView1.setText(titleNames.get(position));   // Set text for nested recycler view
        holder.textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedItems.get(position)) {
                    // 펼쳐진 Item을 클릭 시
                    selectedItems.delete(position);
                } else {
                    // 직전의 클릭됐던 Item의 클릭상태를 지움
                    selectedItems.delete(prePosition);
                    // 클릭한 Item의 position을 저장
                    selectedItems.put(position, true);
                }
                // 해당 포지션의 변화를 알림
                if (prePosition != -1) notifyItemChanged(prePosition);
                notifyItemChanged(position);
                // 클릭된 position 저장
                prePosition = position;
            }


        });
    }

    @Override
    public int getItemCount() {
        return titleNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // 첫번째 아이템에서 사용했던 텍스트뷰와 리사이클러뷰
        private TextView textView1;
        private RecyclerView recyclerView;
        private int position;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView1 = itemView.findViewById(R.id.main_item_name);
            recyclerView = itemView.findViewById(R.id.second_recyclerview);
        }

        void onBind(int position) {
            this.position = position;
            changeVisibility(selectedItems.get(position));
        }

        // 리사이클러뷰가 접히고 펼쳐질때 애니메이션 효과
        private void changeVisibility(final boolean isExpanded) {
            // height 값을 dp로 지정해서 넣고싶으면 아래 소스를 이용
            int dpValue = 250;
            float d = context.getResources().getDisplayMetrics().density;
            int height = (int) (dpValue * d);

            // ValueAnimator.ofInt(int... values)는 View가 변할 값을 지정, 인자는 int 배열
            ValueAnimator va = isExpanded ? ValueAnimator.ofInt(0, height) : ValueAnimator.ofInt(height, 0);
            // Animation이 실행되는 시간, n/1000초
            va.setDuration(600);
            va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    // value는 height 값
                    int value = (int) animation.getAnimatedValue();
                    // imageView의 높이 변경
                    recyclerView.getLayoutParams().height = value;
                    recyclerView.requestLayout();
                    // imageView가 실제로 사라지게하는 부분
                    recyclerView.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
                }
            });
            // Animation start
            va.start();
        }
    }
}
