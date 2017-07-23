package com.kikebodi.assignment;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.kikebodi.assignment.MainActivity;
import com.kikebodi.assignment.R;
import com.kikebodi.assignment.objects.Position;

import java.util.Arrays;

import static android.content.ContentValues.TAG;

/**
 * Created by Kike Bodi on 20/07/2017.
 * Cortrium
 * bodi.inf@gmail.com
 */

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private Integer[] references;
    private ImageView[] items;

    public ImageAdapter(Context c) {
        mContext = c;
        references = new Integer[((MainActivity)mContext).getTotalItems()];
        Arrays.fill(references, android.R.drawable.alert_dark_frame);
        items = new ImageView[references.length];
    }

    public int getCount() {
        return references.length;
    }

    public ImageView getItem(int position) {
        return items[position];
    }

    public long getItemId(int position) {
        return 0;
    }

    /**
     * Set robot position.
     * @param x
     * @param y
     */
    public void setCursor(int x, int y){
        int currentPosition = getPositionByCoords(x,y);
        references = new Integer[((MainActivity)mContext).getTotalItems()];
        Arrays.fill(references, android.R.drawable.alert_dark_frame);
        references[currentPosition] = getItemDirection();
        notifyDataSetChanged();
    }

    public int getItemDirection(){
        switch (((MainActivity) mContext).getRobotDirection()) {
            case NORTH:
                return R.mipmap.ic_arrow_upward_black_24dp;
            case SOUTH:
                return R.mipmap.ic_arrow_downward_black_24dp;
            case WEST:
                return R.mipmap.ic_arrow_back_black_24dp;
            case EAST:
                return R.mipmap.ic_arrow_forward_black_24dp;
            default:
                return -1;
        }
    }

    /**
     * Regarding the coordinates, get the item position in array
     * @param x
     * @param y
     * @return
     */
    private int getPositionByCoords(int x, int y){
        int colums = Position.max_x+1;
        int pos = (colums*(y)) + x;
        return pos;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(final int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(1, 1, 1, 1);
        } else {
            imageView = (ImageView) convertView;
        }
        items[position] = imageView;
        imageView.setImageResource(references[position]);
        return imageView;
    }
}
