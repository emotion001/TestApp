package wuguanglei.exam.com.wuguanglei.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import wuguanglei.exam.com.wuguanglei.R;

/**
 * Created by admin on 2016/6/25.
 */
public class MyCursorAdapte extends CursorAdapter {
    private int tittleID, nameID;

    public MyCursorAdapte(Context context, Cursor c) {
        super(context, c);
        tittleID = c.getColumnIndex("title");
        nameID = c.getColumnIndex("nickname");
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.new_layout, parent, false);
        view.setTag(new ViewHold(view));
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        ViewHold viewHold = (ViewHold) view.getTag();

        viewHold.name_tv.setText(cursor.getString(nameID));
        viewHold.tittle_tv.setText(cursor.getString(tittleID));


    }

    class ViewHold {
        private TextView tittle_tv, name_tv;

        public ViewHold(View contentView) {
            tittle_tv = (TextView) contentView.findViewById(R.id.titleTextId);
            name_tv = (TextView) contentView.findViewById(R.id.nameTextId);

        }
    }
}