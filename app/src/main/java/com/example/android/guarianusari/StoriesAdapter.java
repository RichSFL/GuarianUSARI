package com.example.android.guarianusari;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class StoriesAdapter extends ArrayAdapter<Stories> {
    public StoriesAdapter(Context context) {
        super( context, -1, new ArrayList<Stories>() );
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView
                    = LayoutInflater.from( getContext() ).inflate( R.layout.list_item, parent, false );
        }
        TextView title = (TextView) convertView.findViewById( R.id.storyinfo );
        TextView author = (TextView) convertView.findViewById( R.id.storyauthor );
        TextView date = (TextView) convertView.findViewById( R.id.storydate );
        TextView section = (TextView) convertView.findViewById( R.id.storysection );

        Stories currentStories = getItem( position );
        title.setText( currentStories.getTitle() );
        author.setText( currentStories.getAuthor() );
        date.setText( currentStories.getDate() );
        section.setText( currentStories.getSection() );

        return convertView;
    }
}
