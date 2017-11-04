package com.jmm.www.calculator.easyCalculator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.jmm.www.calculator.R;

import java.util.List;

public class GridViewAdapter extends BaseAdapter {

    private Context context;
    private List<String> text;
    private List<String> viceText;
    private GridView gridView;
    private int layoutId;
    private int value;
    private int height;
    private ViewGroup.LayoutParams deleteParam;

    public GridViewAdapter(Context context, GridView gridView, List<String> text, List<String> viceText, int layoutId) {
        this.context = context;
        this.text = text;
        this.viceText = viceText;
        this.gridView = gridView;
        this.layoutId = layoutId;
    }

    @Override
    public int getCount() {
        return text.size();
    }

    @Override
    public Object getItem(int position) {
        return text.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder {
        TextView title;
        TextView vice;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        View view;
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(layoutId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.title = (TextView) view.findViewById(R.id.text_item);
            if (viceText != null)
                viewHolder.vice = (TextView) view.findViewById(R.id.text_vice_item);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.title.setText(text.get(position));

        if (viceText != null) {
            viewHolder.vice.setText(viceText.get(position));
        }

        if (height == 0)
            height = gridView.getHeight();

        GridView.LayoutParams param = new GridView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                height / value);
        view.setLayoutParams(param);

        if (layoutId == R.layout.button_operator) {
            if (deleteParam == null)
                deleteParam = MainActivity.activity.delete.getLayoutParams();
            if (deleteParam.height != height / value) {
                deleteParam.height = height / value;
                MainActivity.activity.delete.setLayoutParams(deleteParam);
            }
        }

        return view;
    }

    public void setValue(int value) {
        this.value = value;
        notifyDataSetChanged();
    }
}
